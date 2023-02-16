import { defineStore } from "pinia";
import { login } from "@/apis/auth";
import { setToken, clearToken } from "@/utils/auth";
import pinia from "./index";
import useSettingStore from "@/store/setting";

interface UserInfo {
  id?: string,
  avatar?: string,
  nickname?: string,
  authorities?: Array<any>
}
interface State {
  userInfo: UserInfo;
  token: string;
}

const settingStore = useSettingStore(pinia);

export default defineStore<string, State, any, {
  setUserInfo(userInfo: UserInfo): void;
  setToken(token: string): void;
  login(params: { username: string, password: string }): Promise<any>;
  logout(): Promise<any>;
}>("user", {
  state() {
    return {
      userInfo: {},
      token: ""
    }
  },
  actions: {
    setUserInfo(userInfo: UserInfo) {
      this.userInfo = userInfo;
      settingStore.refreshRoutes(userInfo.authorities || []);
    },
    setToken(token) {
      this.token = token;
    },
    login(params) {
      return login(params)
      .then(res => {
        if(res.code === 200) {
          setToken(res.data);
        } else {
          ElMessage.error(res.message);
          return Promise.reject();
        }
      });
    },
    logout() {
      clearToken();
      settingStore.clearRoutes();
      this.setToken("");
      this.userInfo = {};
      return Promise.resolve();
    }
  }
});
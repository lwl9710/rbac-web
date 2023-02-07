import { defineStore } from "pinia";
import { login } from "@/apis/auth";
import {setToken} from "@/utils/auth";

interface UserInfo {

}
interface State {
  userInfo: UserInfo;
  token: string;
}

export default defineStore<string, State, any, {
  setUserInfo(userInfo: UserInfo): void;
  setToken(token: string): void;
  login(params: { username: string, password: string }): Promise<any>;
}>("user", {
  state() {
    return {
      userInfo: {},
      token: ""
    }
  },
  actions: {
    setUserInfo(userInfo) {
      this.userInfo = userInfo;
    },
    setToken(token) {
      this.token = token;
    },
    login(params) {
      return login(params)
        .then(res => {
          setToken(res.data);
        });
    }
  }
});
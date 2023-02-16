import { createRouter, createWebHashHistory } from "vue-router";
import routes from "@/router/routes";
import { getToken } from "@/utils/auth";
import pinia from "@/store";
import useUserStore from "@/store/user";
import { reqGetUserDetail } from "@/apis/user";

const userStore = useUserStore(pinia);
const router = createRouter({
  routes,
  history: createWebHashHistory(),
  scrollBehavior: (to, from, savePosition) => savePosition ? savePosition : {left: 0, top: 0}
});

const DEFAULT_TITLE: string = "Vite + Vue + TS";

router.beforeEach((to, from, next) => {
  window.document.title = (to.meta.title as string) || DEFAULT_TITLE;
  const token = getToken();
  if(token) {
    if(to.name === "Login") {
      next({ path: "/" });
    } else {
      if(!userStore.userInfo.id) {
        reqGetUserDetail()
        .then((res) => {
          if(res.code === 200) {
            userStore.setUserInfo(res.data);
          }
        })
        .then(() => next());
      } else {
        next();
      }
    }
  } else {
    if(to.name === "Login") {
      next();
    } else {
      next({ name: "Login" });
    }
  }
})

export default router;

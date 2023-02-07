import { createRouter, createWebHashHistory } from "vue-router";
import routes from "@/router/routes";
import { getToken } from "@/utils/auth";

const router = createRouter({
  routes,
  history: createWebHashHistory(),
  scrollBehavior: (to, from, savePosition) => savePosition ? savePosition : {left: 0, top: 0}
});

const DEFAULT_TITLE: string = "Vite + Vue + TS";

router.beforeEach((to, from, next) => {
  window.document.title = (to.meta.title as string) || DEFAULT_TITLE;
  const token = getToken();
  if(to.name === "Login") {
    if(token) {
      next({ path: "/" });
    } else {
      next();
    }
  } else {
    if(token) {
      next();
    } else {
      next({ name: "Login" });
    }
  }
})

export default router;

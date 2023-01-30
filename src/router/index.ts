import { createRouter, createWebHashHistory } from "vue-router";
import routes from "@/router/routes";

const router = createRouter({
  routes,
  history: createWebHashHistory(),
  scrollBehavior: (to, from, savePosition) => savePosition ? savePosition : {left: 0, top: 0}
});

const DEFAULT_TITLE: string = "Vite + Vue + TS";

router.beforeEach((to, from, next) => {
  window.document.title = (to.meta.title as string) || DEFAULT_TITLE;
  next();
})

export default router;

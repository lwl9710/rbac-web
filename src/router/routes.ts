import BasicLayout from "@/layouts/BasicLayout/index.vue";
import { RouteRecordRaw } from "vue-router";

/**
 * RouteMeta 参数介绍
 * icon 菜单图标
 * title 标题
 * keepAlive 是否进行缓存
 * hidden 是否隐藏菜单
 */
const pages: Array<RouteRecordRaw> = [
  {
    path: "/login",
    name: "Login",
    component: () => import("@/pages/Login/index.vue"),
    meta: { title: "登录", hidden: true }
  }
];
const routes: Array<RouteRecordRaw> = [
  ...pages,
  {
    path: "/",
    redirect: "/dashboard",
    component: BasicLayout,
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: () => import("@/views/Dashboard/index.vue"),
        meta: { icon: "Menu", title: "控制台", keepAlive: true }
      },
    ]
  },
  {
    path: "/system",
    redirect: "/roleList",
    component: BasicLayout,
    meta: { icon: "Setting", title: "系统管理" },
    children: [
      {
        path: "roleList",
        name: "RoleList",
        component: () => import("@/views/system/Role/index.vue"),
        meta: { icon: "User", title: "角色列表" }
      },
      {
        path: "permissionList",
        name: "PermissionList",
        component: () => import("@/views/system/Permission/index.vue"),
        meta: { icon: "Operation", title: "权限列表" }
      },
      {
        path: "userList",
        name: "UserList",
        component: () => import("@/views/system/User/index.vue"),
        meta: { icon: "UserFilled", title: "用户列表" }
      }
    ]
  },
  {
    path: "/:pathMatch(.*)",
    name: "404",
    component: () => import("@/pages/error/404.vue"),
    meta: { hidden: true }
  }
];

export default routes;

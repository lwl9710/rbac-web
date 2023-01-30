import { defineStore } from "pinia";
import router from "@/router";
import { RouteRecordRaw } from "vue-router";

function filterRoutes(routes: Array<RouteRecordRaw>): MenuRoute[] {
  return routes.filter(route => !route.meta?.hidden).map(route => {
    return {
      name: route.name || "--",
      meta: route.meta || {},
      children: filterRoutes(route.children || [])
    }
  }) as MenuRoute[];
}

function generateRoutes() {
  return filterRoutes(JSON.parse(JSON.stringify(router.options.routes)));
}

export default defineStore("setting", {
  state() {
    return {
      // 侧边栏是否折叠
      isCollapse: false,
      routes: generateRoutes(), // 路由信息
      navList: [] as Array<NavItem>
    }
  },
  actions: {
    refreshRoutes() {
      this.routes = generateRoutes();
    },
    addNavItem(navItem: NavItem) {
      const cacheNavItem: NavItem | undefined = this.navList.find(({ name }) => name === navItem.name);
      if(cacheNavItem === undefined) {
       this.navList.push(navItem);
      } else {
        cacheNavItem.params = navItem.params;
        cacheNavItem.query = navItem.query;
      }
    },
    removeNavItem(navItem: NavItem) {
      const index: number = this.navList.findIndex(({ name }) => name === navItem.name);
      if(index !== -1) {
        this.navList.splice(index, 1);
      }
    }
  }
});

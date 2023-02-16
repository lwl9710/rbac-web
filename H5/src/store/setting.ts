import { defineStore } from "pinia";
import router from "@/router";
import { RouteRecordRaw } from "vue-router";

function filterRoutes(routes: Array<RouteRecordRaw>, authorityMap: StringObject, parentPath: string = ""): MenuRoute[] {
  return routes.filter(route => {
    return (!route.meta?.hidden && authorityMap[`${parentPath}${route.path}`]) || route.path === "/";
  }).map(route => {
    return {
      name: route.name || "--",
      meta: route.meta || {},
      children: filterRoutes(route.children || [], authorityMap, `${parentPath}${route.path + (route.path.endsWith("/") ? "" : "/" )}`)
    }
  }) as MenuRoute[];
}

function generateRoutes(authorities: Array<any>) {
  const routes = JSON.parse(JSON.stringify(router.options.routes));
  const authorityMap: StringObject = {};
  authorities.forEach(authority => {
    authorityMap[authority.url] = true;
  });
  return filterRoutes(routes, authorityMap);
}

export default defineStore("setting", {
  state() {
    return {
      // 侧边栏是否折叠
      isCollapse: false,
      routes: [] as MenuRoute[], // 路由信息
      navList: [] as Array<NavItem>
    }
  },
  actions: {
    clearNavList() {
      this.navList = [];
    },
    clearRoutes() {
      this.routes = [];
    },
    refreshRoutes(authorities: Array<any>) {
      this.routes = generateRoutes(authorities.filter(authority => authority.type === 2));
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

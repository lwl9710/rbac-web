<script lang="ts" setup>
import useSettingStore from "@/store/setting";
const store = useSettingStore();
const route = useRoute();
const router = useRouter();
const isActive = (routeName: string) => {
  return routeName === route.name;
}
const clickRemoveNav = (navItem: NavItem, index: number) => {
  if(store.navList.length > 1) {
    if(isActive(navItem.name)) {
      // 活动状态替换活动页
      let nextRouteName: string = store.navList[index > 0 ? (index - 1) : (index + 1)].name;
      router.replace({ name: nextRouteName });
    }
    store.removeNavItem(navItem);
  }
}
const addNavItem = () => {
  store.addNavItem({
    name: route.name as string,
    title: route.meta.title as string || "--",
    query: route.query,
    params: route.params
  });
}
const clickToRoute = (nav: NavItem) => {
  addNavItem();
  router.replace({ name: nav.name, params: nav.params, query: nav.query })
  .then(() => {
    addNavItem();
  });
}
onMounted(() => {
  store.addNavItem({
    name: route.name as string,
    title: route.meta.title as string || "--",
    query: route.query,
    params: route.params
  });
});
</script>
<template>
<nav>
  <el-scrollbar>
    <ul class="nav-list">
      <li class="nav-item" :class="{active: isActive(nav.name)}" v-for="(nav, index) in store.navList" :key="nav.name" @click="clickToRoute(nav)">
        <div class="status"></div>
        <div class="name">{{ nav.title }}</div>
        <div v-if="store.navList.length > 1" class="icon" @click.stop="clickRemoveNav(nav,index)">
          <el-icon><Close /></el-icon>
        </div>
      </li>
    </ul>
  </el-scrollbar>
</nav>
</template>
<style lang="scss" scoped>
nav {
  overflow: hidden;
  background-color: #FFF;
  border-bottom: 1px solid #e4e7ed;
  user-select: none;
  .nav-list {
    vertical-align: top;
    display: inline-block;
    min-width: calc(100% - 10px);
    padding: 4px 5px 2px;
    white-space: nowrap;
    line-height: 1em;
    .nav-item {
      display: inline-flex;
      justify-content: flex-start;
      align-items: center;
      padding: 0 5px;
      height: 24px;
      background-color: #fff;
      border: 1px solid #e4e7ed;
      font-size: 13px;
      cursor: pointer;
      &:not(:first-child) {
        margin-left: 5px;
      }
      &:hover {
        color: #409EFF;
        border-color: #409EFF;
        .status {
          background-color: #67C23A;
        }
      }
      &.active {
        color: #FFF;
        background-color: #409EFF;
        border-color: #409EFF;
        .status {
          background-color: #67C23A;
        }
      }
      .status {
        margin-right: 8px;
        width: 8px;
        height: 8px;
        background-color: #BFCBD9;
        border-radius: 50%;
      }
      .icon {
        margin-left: 5px;
        padding: 1px;
        border-radius: 50%;
        line-height: 1em;
        ::v-deep(.el-icon) {
          vertical-align: top;
        }
        &:hover {
          background-color: #CCC;
        }
      }
    }
  }
}
</style>

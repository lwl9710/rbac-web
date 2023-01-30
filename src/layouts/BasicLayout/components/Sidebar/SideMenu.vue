<script name="SideMenu" lang="ts" setup>
import useSettingStore from "@/store/setting";
import IconSpan from "./IconSpan.vue";
const props = defineProps<{menu: MenuRoute}>();
const route = useRoute();
const router = useRouter();
const store = useSettingStore();
const addNavItem = () => {
  store.addNavItem({
    name: route.name as string,
    title: route.meta.title as string || "--",
    query: route.query,
    params: route.params
  });
}
const clickToRoute = (routeName: string) => {
  addNavItem();
  router.push({ name: routeName })
  .then(() => {
    addNavItem();
  });
}
</script>
<template>
  <el-sub-menu v-if="props.menu.children && props.menu.children.length > 1"  :index="props.menu.name">
    <template #title>
      <IconSpan :icon="props.menu.meta.icon" :title="props.menu.meta.title"></IconSpan>
    </template>
    <SideMenu v-for="menu in props.menu.children" :key="menu.name" :menu="menu"></SideMenu>
  </el-sub-menu>
  <el-menu-item v-else-if="props.menu.children && props.menu.children.length === 1" :index="props.menu.children[0].name" @click="clickToRoute(props.menu.children[0].name)">
    <IconSpan :icon="props.menu.children[0].meta.icon" :title="props.menu.children[0].meta.title"></IconSpan>
  </el-menu-item>
  <el-menu-item :index="props.menu.name" @click="clickToRoute(props.menu.name)" v-else>
    <IconSpan :icon="props.menu.meta.icon" :title="props.menu.meta.title"></IconSpan>
  </el-menu-item>
</template>

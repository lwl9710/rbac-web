<script lang="ts" setup>
import Sidebar from "./components/Sidebar/index.vue";
import AppMain from "./components/AppMain/index.vue";
import { useRouter } from "vue-router";
const router = useRouter();
const cacheList = router.getRoutes().filter(route => route.meta.keepAlive).map(route => route.name as string);
</script>
<template>
<div class="basic-layout">
  <Sidebar></Sidebar>
  <AppMain>
    <router-view v-slot="{ Component, route }">
      <transition mode="out-in">
        <keep-alive :include="cacheList" max="10">
          <component :is="Component" />
        </keep-alive>
      </transition>
    </router-view>
  </AppMain>
</div>
</template>
<style lang="scss" scoped>
.basic-layout {
  overflow: auto;
  display: flex;
  height: 100vh;
  background-color: #EEE;
}
.v-enter-active,
.v-leave-active {
  transition: opacity 0.3s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>

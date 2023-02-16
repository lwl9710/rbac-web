<script lang="ts" setup>
import Header from "./Header.vue";
import Navbar from "./Navbar.vue";
</script>
<template>
<main>
  <Header></Header>
  <Navbar></Navbar>
  <section class="content">
    <el-scrollbar>
      <slot></slot>
    </el-scrollbar>
  </section>
</main>
</template>
<style lang="scss" scoped>
main {
  overflow: auto;
  flex: 1;
  .content {
    padding-top: 10px;
    height: calc(100% - 88px);
  }
}
</style>

<script lang="ts" setup>
import Zh_CN from "element-plus/lib/locale/lang/zh-cn";
const locale = Zh_CN;
</script>
<template>
<ElConfigProvider :locale="locale">
  <router-view></router-view>
</ElConfigProvider>
</template>

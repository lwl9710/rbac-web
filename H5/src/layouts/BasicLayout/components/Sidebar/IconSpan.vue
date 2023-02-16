<script lang="ts" setup>
const props = defineProps<{
  icon?: string,
  title?: string
}>();
</script>
<template>
  <el-icon v-if="icon">
    <component :is="props.icon"></component>
  </el-icon>
  <span>{{ props.title || '--' }}</span>
</template>

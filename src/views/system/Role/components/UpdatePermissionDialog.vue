<script lang="ts" name="UpdatePermissionDialog" setup>
import {reqGetPermissionList} from "@/apis/permission";
import {getTableOption} from "@/views/system/Permission/options";
import {Ref} from "vue";

const props = withDefaults(defineProps<{
  modelValue?: boolean
}>(), {
  modelValue: false
});
const emits = defineEmits<{
  (e: "update:modelValue", v: boolean): void
}>();
const permissions: Ref = ref([]);
function clickConfirmPermissions() {
  console.log("确定权限");
}

function getAllList() {
  reqGetPermissionList({})
  .then(res => {
    if(res.code === 200) {
      const newPermissions: any[] = [];
      res.data.list.forEach((item: any) => {
        console.log(item);
      });
      permissions.value = newPermissions;
    }
  })
}

onMounted(() => {
  getAllList();
});
</script>
<template>
  <el-dialog
    title="更新权限"
    :model-value="props.modelValue"
    @update:modelValue="emits('update:modelValue', $event)"
    :close-on-click-modal="false"
    :destroy-on-close="true"
    width="800px"
  >
    <template #footer>
      <el-button @click="emits('update:modelValue', false)">取消</el-button>
      <el-button @click="clickConfirmPermissions">确定</el-button>
    </template>
  </el-dialog>
</template>
<style lang="scss" scoped>

</style>
<script lang="ts" name="UpdatePermissionDialog" setup>
import {reqGetPermissionList} from "@/apis/permission";
import { Ref } from "vue";

const props = withDefaults(defineProps<{
  modelValue?: boolean
}>(), {
  modelValue: false
});
const emits = defineEmits<{
  (e: "update:modelValue", v: boolean): void,
  (e: "confirm", v: any): void,
}>();
const showDialog = ref(false);
const permissions: Ref = ref([]);
const treeProps = {
  label: "description",
  children: "children"
}

const treeRef: Ref = ref(null);
function clickConfirmPermissions() {
  emits('confirm', {
    data: treeRef.value.getCheckedKeys(),
    done: () => showDialog.value = false
  });
}

function getAllList() {
  reqGetPermissionList({})
  .then(res => {
    if(res.code === 200) {
      const newPermissions: any[] = [];
      const permissionMap: StringObject = {};
      res.data.list.forEach((item: any) => {
        item.children = [];
        permissionMap[item.id] = item;
      });
      res.data.list.forEach((item: any) => {
        if(item.parentId) {
          const parent = permissionMap[item.parentId];
          parent.children.push(item);
        } else {
          newPermissions.push(item);
        }
      });
      permissions.value = newPermissions;
    }
  })
}

defineExpose({
  show(permissions: any = []) {
    showDialog.value = true;
    nextTick(() => {
      treeRef.value.setCheckedKeys(permissions);
    });
  }
});

onMounted(() => {
  getAllList();
});
</script>
<template>
  <el-dialog
    title="更新权限"
    v-model="showDialog"
    :close-on-click-modal="false"
    width="800px"
  >
    <el-tree ref="treeRef" show-checkbox node-key="id" :data="permissions" :props="treeProps">
    </el-tree>
    <template #footer>
      <el-button @click="showDialog = false">取消</el-button>
      <el-button @click="clickConfirmPermissions" type="primary">确定</el-button>
    </template>
  </el-dialog>
</template>
<style lang="scss" scoped>

</style>
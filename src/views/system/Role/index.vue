<script lang="ts" setup>
import UpdatePermissionDialog from "./components/UpdatePermissionDialog.vue";
import { tableOption } from "./options";
import { reqGetRoleList, reqAddRole, reqUdateRole, reqDelRole, reqUpdatePermissions } from "@/apis/role";
import { Ref } from "vue";
import { reqFindPermissions } from "@/apis/permission";
const tableLoading = ref(false);
const option = ref(tableOption);
const list: Ref<any[]> = ref([]);
const params = ref({
  pageNum: 1,
  pageSize: 10
});
let currentRow: any = {};
const permissionDialogRef: Ref = ref(null);
const total = ref(0);
function getList() {
  tableLoading.value = true;
  reqGetRoleList(toRaw(params.value))
  .then(res => {
    if(res.code === 200) {
      list.value = res.data.list;
      total.value = res.data.total;
    }
  }).finally(() => {
    tableLoading.value = false;
  })
}

function onAddRow({ data, done }: CrudTableEventParam) {
  done();
  reqAddRole(data)
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("添加成功");
      getList();
    }
  })
}

function onEditRow({ $index = 0 , data, done }: CrudTableEventParam) {
  data.id = list.value[$index].id;
  done();
  reqUdateRole(data)
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("编辑成功");
      getList();
    }
  });
}

function onDelRow({ row }: { row: any }) {
  ElMessageBox.confirm("您确定删除吗?", "警告", { type: "warning" })
  .then(() => reqDelRole(row.id))
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("删除成功");
      getList();
    }
  });
}

function clickShowDialog(row: any) {
  reqFindPermissions(row.id || "")
  .then(res => {
    if(res.code == 200) {
      currentRow = row;
      permissionDialogRef.value.show(res.data.split(","));
    }
  });
}

function onConfirmPermissions({ data, done }: any) {
  reqUpdatePermissions({ roleId: currentRow.id, permissions: data })
  .then(res => {
    if(res.code === 200) {
      done();
      ElMessage.success("更新成功");
    }
  })
}

onMounted(() => {
  getList();
})
</script>
<template>
<BasicContainer v-loading="tableLoading">
  <crud-table
    :option="option"
    :data="list"
    v-model:total="total"
    v-model:page-size="params.pageSize"
    v-model:current-page="params.pageNum"
    @delRow="onDelRow"
    @editRow="onEditRow"
    @addRow="onAddRow"
    @sizeChange="getList"
    @currentChange="getList"
  >
    <template #operator="{ row }">
      <el-button link type="success" icon="operation" @click="clickShowDialog(row)">权限</el-button>
    </template>
  </crud-table>
  <UpdatePermissionDialog ref="permissionDialogRef" @confirm="onConfirmPermissions"></UpdatePermissionDialog>
</BasicContainer>
</template>

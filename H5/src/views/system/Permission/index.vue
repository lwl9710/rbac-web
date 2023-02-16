<script lang="ts" setup>
import { getTableOption } from "./options";
import { reqGetPermissionList, reqAddPermission, reqUdatePermission, reqDelPermission } from "@/apis/permission";
import { Ref } from "vue";
const tableLoading = ref(false);
const option = ref(getTableOption());
const list: Ref<any[]> = ref([]);
const params = ref({
  pageNum: 1,
  pageSize: 10
});
const total = ref(0);
function getList() {
  tableLoading.value = true;
  reqGetPermissionList(toRaw(params.value))
      .then(res => {
        if(res.code === 200) {
          list.value = res.data.list;
          total.value = res.data.total;
        }
      }).finally(() => {
    tableLoading.value = false;
  })
}

function getMenuList() {
  reqGetPermissionList({ type: 2 })
  .then(res => {
    if(res.code === 200) {
      option.value = getTableOption(res.data.list.map(({id, description}: any) => ({ label: description, value: id })));
    }
  })
}

function onAddRow({ data, done }: CrudTableEventParam) {
  done();
  reqAddPermission(data)
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
  reqUdatePermission(data)
      .then(res => {
        if(res.code === 200) {
          ElMessage.success("编辑成功");
          getList();
        }
      });
}

function onDelRow({ row }: { row: any }) {
  ElMessageBox.confirm("您确定删除吗?", "警告", { type: "warning" })
  .then(() =>reqDelPermission(row.id))
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("删除成功");
      getList();
    }
  });
}

onMounted(() => {
  getList();
  getMenuList();
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
    ></crud-table>
  </BasicContainer>
</template>

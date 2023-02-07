<script lang="ts" setup>
import { tableOption } from "./options";
import { reqGetRoleList, reqAddRole, reqUdateRole, reqDelRole } from "@/apis/role";
import { Ref } from "vue";
const tableLoading = ref(false);
const option = ref(tableOption);
const list: Ref<any[]> = ref([]);
const params = ref({
  pageNum: 1,
  pageSize: 10
});
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
  reqDelRole(row.id)
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("删除成功");
      getList();
    }
  });
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
  ></crud-table>
</BasicContainer>
</template>

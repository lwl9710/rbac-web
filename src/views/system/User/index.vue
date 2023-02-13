<script lang="ts" setup>
import { getTableOption } from "./options";
import { reqGetUserList, reqAddUser, reqUdateUser, reqDelUser } from "@/apis/user";
import { reqGetRoleList } from "@/apis/role";
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
  reqGetUserList(toRaw(params.value))
      .then(res => {
        if(res.code === 200) {
          list.value = res.data.list;
          total.value = res.data.total;
        }
      }).finally(() => {
    tableLoading.value = false;
  })
}

function getRoleList() {
  reqGetRoleList({})
  .then(res => {
    if(res.code === 200) {
      option.value = getTableOption(res.data.list.map(({ id, roleDescription }: any) => {
        return {
          label: roleDescription,
          value: id
        }
      }));
    }
  })
}

function onAddRow({ data, done }: CrudTableEventParam) {
  done();
  reqAddUser(data)
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
  reqUdateUser(data)
      .then(res => {
        if(res.code === 200) {
          ElMessage.success("编辑成功");
          getList();
        }
      });
}

function onDelRow({ row }: { row: any }) {
  ElMessageBox.confirm("您确定删除吗?", "警告", { type: "warning" })
  .then(() => reqDelUser(row.id))
  .then(res => {
    if(res.code === 200) {
      ElMessage.success("删除成功");
      getList();
    }
  });
}

function onReset() {
  params.value = {
    pageNum: 1,
    pageSize: 10
  };
  getList();
}

function onSearch(data: any) {
  params.value = Object.assign({
    pageNum: 1,
    pageSize: 10
  }, data);
  getList();
}

onMounted(() => {
  getList();
  getRoleList();
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
        @search="onSearch"
        @reset="onReset"
    >
      <template #avatar="{ row }">
        <img class="avatar" :src="row.avatar" alt="">
      </template>
    </crud-table>
  </BasicContainer>
</template>
<style lang="scss" scoped>
.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  vertical-align: top;
}
</style>
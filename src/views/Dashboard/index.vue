<script name="Dashboard" lang="ts" setup>
import { Option } from "@/components/CrudTable/types";
import { Ref } from "vue";
const tableOption: Ref<Option> = ref({
  showAdd: true,
  showEdit: true,
  showDel: true,
  columns: [
    {
      label: "姓名",
      prop: "name",
      search: true,
      rules: [
        { required: true, message: "请输入姓名", trigger: "blur" }
      ]
    },
    {
      label: "性别",
      prop: "gender",
      search: true,
      rules: [
        { required: true, message: "请输入性别", trigger: "blur" }
      ],
      dataType: "select",
      selectList: [
        { label: "男", value: 1 },
        { label: "女", value: 0 }
      ]
    },
    {
      label: "年龄",
      prop: "age",
      search: true,
      rules: [
        { required: true, message: "请输入年龄", trigger: "blur" }
      ],
    },
    {
      label: "头像",
      prop: "avatar",
      dataType: "file",
      accept: "image/*",
      width: 120,
      slot: true
    },
    {
      label: "创建时间",
      prop: "createTime",
      search: true,
      dataType: "datetime",
      pickerType: "datetimerange",
      format: "YYYY-MM-DD HH:mm:ss",
      valueFormat: "YYYY-MM-DD HH:mm:ss",
      hideEdit: true,
      hideAdd: true
    }
  ]
});
const tableData = ref([
  {
    name: "小明",
    avatar: "https://img2.baidu.com/it/u=1385559459,94521354&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1675098000&t=21386f6dfa6bd270796954b514ba58f4",
    gender: "男",
    age: 23,
    createTime: Date.now()
  },
  {
    name: "小明",
    avatar: "https://img2.baidu.com/it/u=1385559459,94521354&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1675098000&t=21386f6dfa6bd270796954b514ba58f4",
    gender: "男",
    age: 23,
    createTime: Date.now()
  }
]);
const pagination = ref({
  total: 2,
  pageSize: 10,
  currentPage: 1
});
const onSearch = (data: any) => {
  console.log("触发搜索", data);
}
const onDelRow = (data: any) => {
  console.log("删除行", data);
}

const onEditRow = ({ data, done }: any) => {
  console.log("编辑行", data);
  done();
}

function onAddRow({ data, done }: any) {
  console.log("添加行: ", data);
  // done();
}
function onSizeChange() {
  console.log("页面变更");
}
</script>
<template>
<BasicContainer>
  <crud-table
    :data="tableData"
    :option="tableOption"
    v-model:total="pagination.total"
    v-model:page-size="pagination.pageSize"
    v-model:current-page="pagination.currentPage"
    @delRow="onDelRow"
    @editRow="onEditRow"
    @addRow="onAddRow"
    @sizeChange="onSizeChange"
    @search="onSearch"
  >
    <template #avatar="{ row }">
      <img style="width: 50px; height: 50px" :src="row.avatar" alt="">
    </template>
  </crud-table>
</BasicContainer>
</template>

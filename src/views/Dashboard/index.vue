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
      rules: [
        { required: true, message: "请输入姓名", trigger: "blur" }
      ]
    },
    {
      label: "性别",
      prop: "gender",
      rules: [
        { required: true, message: "请输入性别", trigger: "blur" }
      ],
      dataType: "select",
      selectList: [
        { label: "女", value: 0 },
        { label: "男", value: 1 }
      ]
    },
    {
      label: "年龄",
      prop: "age",
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
      dataType: "datetime",
      pattern: "YYYY-MM-DD HH:mm:ss",
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

const onDelRow = (data: any) => {
  console.log("删除行", data);
}

const onEditRow = ({ data, done }: any) => {
  console.log("编辑行", data);
  done();
}

function onAddRow({ data, done }: any) {
  console.log("添加行: ", data);
  done();
}

</script>
<template>
<BasicContainer>
  <crud-table :data="tableData" :option="tableOption" @delRow="onDelRow" @editRow="onEditRow" @addRow="onAddRow">
    <template #avatar="{ row }">
      <img style="width: 50px; height: 50px" :src="row.avatar" alt="">
    </template>
  </crud-table>
</BasicContainer>
</template>
<style lang="scss" scoped>

</style>

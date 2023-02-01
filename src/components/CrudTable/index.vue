<script name="CrudTable" lang="ts" setup>
import { Column, Option, Pagination } from "./types";
import { DEFAULT_ALIGN ,formatDateTime } from "./option";
import { Ref } from "vue";
import RowChangeDialog from "./components/RowChangeDialog.vue";

interface Emits {
  (e: 'current-change', v: number): void;
  (e: 'size-change', v:number): void;
  (e: "add-row" | "edit-row" | "del-row", rawData: any): void;
}

interface Props {
  data: Array<StringObject>;
  option: Option;
  total?: number,
  pageSize?: number,
  pageSizes?: Array<number>,
  currentPage?: number
}
let $index = -1;

const emits = defineEmits<Emits>();
const props = withDefaults(defineProps<Props>(), {
  data: () => [],
  option: () => ({
    border: true,
    columns: []
  }),
  total: 0,
  pageSize: 10,
  currentPage: 1,
  pageSizes: () => [10, 20, 50, 100]
});
// 表格Prop
const tableProp = computed(() => {
  return {
    data: props.data,
    border: props.option.border === undefined ? true : props.option.border
  }
});
const showOperator = computed(() => {
  return props.option.showDel || props.option.showEdit;
});
const tableColumns = computed(() => {
  let columns = JSON.parse(JSON.stringify(props.option.columns)) as Array<Column>;
  let operatorColumnIndex: number = columns.findIndex(column => column.prop === 'operator');
  if(operatorColumnIndex === -1) {
    // 不存在
    if(showOperator.value) {
      columns = columns.concat({label: "操作", prop: "operator", width: "180px", hideEdit: true, hideAdd: true});
    }
  } else {
    columns[operatorColumnIndex] = Object.assign({hideAdd: true, hideEdit: true}, columns[operatorColumnIndex]);
  }
  return columns.map((column) => {
    return Object.assign({
      align: props.option.align || DEFAULT_ALIGN,
      dataType: "string"
    }, column);
  });
});
const rowDialog: Ref = ref(null);
const clickAddRow = () => {
  rowDialog.value?.show(1);
}
const clickEditRow = (slotProps: any) => {
  rowDialog.value?.show(2, slotProps.row);
  $index = slotProps.$index;
};
const clickConfirm = (eventType: "add-row" | "edit-row", data: any) => {
  const eventData: any = {
    data,
    done() {
      rowDialog.value?.hide();
    }
  }
  if(eventType === "edit-row") {
    eventData.$index = $index;
  }
  emits(eventType, eventData);
}

const handleSizeChange = () => {

}
const handleCurrentChange = () => {

}
</script>
<template>
<div class="crud-table">
  <div class="crud-operator" v-if="props.option.showAdd">
    <el-button icon="plus" type="success" @click="clickAddRow">新增</el-button>
  </div>
  <el-table v-bind="tableProp">
    <el-table-column
      v-for="(column, index) in tableColumns"
      :key="index"
      v-bind="column"
    >
      <template v-if="column.slot" #default="{ name, ...slotProps }">
        <slot :name="column.prop" v-bind="slotProps"></slot>
      </template>
      <template v-else-if="column.dataType === 'datetime'" #default="{ row }">{{ formatDateTime(row[column.prop], column.valueFormat) }}</template>
      <template v-else-if="showOperator && column.prop === 'operator'" #default="{ name, ...slotProps }">
        <el-button v-if="props.option.showEdit" @click="clickEditRow(slotProps)" icon="edit" link type="primary">编辑</el-button>
        <el-button v-if="props.option.showDel" @click="emits('del-row', slotProps)" icon="delete" link type="danger">删除</el-button>
        <slot name="operator" v-bind="slotProps"></slot>
      </template>
    </el-table-column>
  </el-table>
  <div class="crud-pagination">
    <el-pagination
        background
        :current-page="props.currentPage"
        :page-size="props.pageSize"
        :page-sizes="props.pageSizes"
        layout="total, sizes, prev, pager, next, jumper"
        :total="props.total"
        @size-change="emits('size-change', $event)"
        @current-change="emits('current-change', $event)"
    />
  </div>
  <RowChangeDialog
      ref="rowDialog"
      :columns="tableColumns"
      @add="clickConfirm('add-row', $event)"
      @edit="clickConfirm('edit-row', $event)"
  ></RowChangeDialog>
</div>
</template>
<style lang="scss" scoped>
.crud-table {
  .crud-operator {
    display: flex;
    justify-content: space-between;
    padding-bottom: 20px;
  }
  ::v-deep(.el-table) {
    th.el-table__cell {
      color: #000000d9;
      background-color: #fafafa
    }
  }
}
.crud-pagination {
  padding-top: 20px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
</style>

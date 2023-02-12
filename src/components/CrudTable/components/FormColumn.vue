<script lang="ts" setup>
import { Column } from "../types";

const props = defineProps<{
  modelValue: any;
  column: Column
}>();

const getPlaceholder = (column: Column, prefix: string = "请输入") => {
  return column.placeholder || (prefix + column.label);
}

const emits = defineEmits<{ (e: "update:modelValue", value: any): void }>();
</script>
<template>
<div class="form-column-item">
  <el-input
    v-if="props.column.dataType === 'string'"
    :clearable="props.column.clearable"
    :modelValue="props.modelValue"
    @update:modelValue="emits('update:modelValue', $event)"
    :placeholder="getPlaceholder(column)"
    :type="props.column.nativeType"
    :minlength="props.column.minlength"
    :maxlength="props.column.maxlength"
    :show-word-limit="props.column.showWordLimit"
    :resize="props.column.resize"
  ></el-input>
  <el-date-picker
      :clearable="props.column.clearable"
      v-else-if="props.column.dataType === 'datetime'"
      :modelValue="props.modelValue"
      @update:modelValue="emits('update:modelValue', $event)"
      :type="props.column.pickerType || props.column.dataType"
      :format="props.column.format"
      :value-format="props.column.valueFormat"
      :placeholder="getPlaceholder(column, '选择')"
      :start-placeholder="props.column.startPlaceHolder || '请选择开始时间'"
      :end-placeholder="props.column.endPlaceHolder || '请选择结束时间'"
  />
  <el-select
    v-else-if="props.column.dataType === 'select'"
    :multiple="props.column.multiple"
    :clearable="props.column.clearable"
    :filterable="props.column.filterable"
    :modelValue="props.modelValue"
    @update:modelValue="emits('update:modelValue', $event)"
    style="width: 100%"
    :placeholder="getPlaceholder(props.column, '选择')"
  >
    <el-option v-for="item in props.column.selectList" :key="item.value" v-bind="item" />
  </el-select>
  <el-radio-group
    v-else-if="props.column.dataType === 'radio'"
    :modelValue="props.modelValue"
    @update:modelValue="emits('update:modelValue', $event)"
  >
    <el-radio v-for="item in props.column.selectList" :key="item.value" :label="item.value">{{ item.label }}</el-radio>
  </el-radio-group>
  <el-upload
      v-else-if="props.column.dataType === 'file'"
      class="avatar-uploader"
      :name="props.column.name || 'file'"
      :headers="props.column.headers"
      :data="props.column.data"
      :accept="props.column.accept || '*'"
      :action="props.column.action"
      :show-file-list="false"
  >
    <img v-if="props.modelValue" :src="props.modelValue" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
</div>
</template>
<style lang="scss" scoped>
.form-column-item {
  width: 100%;
  ::v-deep(.el-date-editor) {
    width: 100%;
    > .el-input__wrapper{
      display: flex;
      box-sizing: border-box;
      width: 100%;
    }
  }
  ::v-deep(.el-upload) {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    &:hover {
      border-color: var(--el-color-primary);
    }
  }
  $UPLOAD_SIZE: 120px;
  ::v-deep(.el-icon.avatar-uploader-icon) {
    color: #8c939d;
    width: $UPLOAD_SIZE;
    height: $UPLOAD_SIZE;
    text-align: center;
    font-size: 28px;
  }
  .avatar-uploader .avatar {
    display: block;
    width: $UPLOAD_SIZE;
    height: $UPLOAD_SIZE;
  }
}
</style>

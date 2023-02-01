export type DataType = "string" | "datetime" | "select" | "radio" | "file" | "datetimerange";

export interface Column {
  label: string;
  prop: string;
  type?: string,
  clearable?: boolean;
  search?: boolean;
  align?: string;
  slot?: boolean;
  headerSlot?: boolean;
  dataType?: string | DataType;
  pickerType?: string;
  format?: string;
  valueFormat?: string;
  width?: string | number;
  hideAdd?: boolean;
  hideEdit?: boolean;
  selectList?: Array<{ label: string, value: any }>;
  accept?: string;
  action?: string;
  formWidth?: string;
  placeholder?: string;
  headers?: StringObject;
  data?: StringObject;
  name?: string;
  rules?: Array<StringObject>;
  startPlaceHolder?: string;
  endPlaceHolder?: string;
}

export interface Option {
  leftSlot?: boolean;
  selection?: boolean;
  selectable?: (row: any, index: number) => boolean;
  clearable?: boolean;
  align?: string;
  border?: boolean;
  showEdit?: boolean;
  showAdd?: boolean;
  showDel?: boolean;
  columns: Array<Column>;
  [prop: string]: any
}

export interface Pagination {
  total?: number;
  pageSize?: number;
  pageSizes?: Array<number>;
  currentPage?: number;
}

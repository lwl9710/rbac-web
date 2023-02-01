export type DataType = "string" | "datetime" | "select" | "radio" | "file";

export interface Column {
  align?: string;
  prop: string;
  label: string;
  slot?: boolean;
  dataType?: string | DataType;
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
  rules?: Array<StringObject>
}

export interface Option {
  align?: string;
  border?: boolean;
  showEdit?: boolean;
  showAdd?: boolean;
  showDel?: boolean;
  columns: Array<Column>;
}

export interface Pagination {
  total?: number;
  pageSize?: number;
  pageSizes?: Array<number>;
  currentPage?: number;
}

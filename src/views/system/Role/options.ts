import {Option} from "@/components/CrudTable/types";

export const tableOption = {
  border: true,
  showAdd: true,
  showEdit: true,
  showDel: true,
  columns: [
    {
      label: "角色名称",
      prop: "roleName"
    },
    {
      label: "角色描述",
      prop: "roleDescription"
    },
    {
      label: "更新时间",
      prop: "updateTime",
      hideEdit: true,
      hideAdd: true
    },
    {
      label: "创建时间",
      prop: "createTime",
      hideEdit: true,
      hideAdd: true
    }
  ]
} as Option;
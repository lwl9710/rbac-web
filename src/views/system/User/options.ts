import { Option } from "@/components/CrudTable/types";

export const tableOption = {
  border: true,
  showAdd: true,
  showEdit: true,
  showDel: true,
  columns: [
    {
      label: "用户名",
      prop: "username",
      search: true,
    },
    {
      label: "用户昵称",
      prop: "nickname",
      search: true,
    },
    {
      label: "角色名称",
      prop: "roleName",
      search: true,
    },
    {
      label: "头像",
      prop: "avatar",
      dataType: "file",
      slot: true
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
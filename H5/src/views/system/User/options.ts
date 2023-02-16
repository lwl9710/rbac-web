import { Option } from "@/components/CrudTable/types";

export function getTableOption(selectList = []): Option {
  return {
    border: true,
    showEdit: true,
    showDel: true,
    columns: [
      {
        label: "用户名",
        prop: "username",
        search: true,
        hideEdit: true
      },
      {
        label: "用户昵称",
        prop: "nickname",
        search: true
      },
      {
        search: true,
        label: "角色",
        dataType: "select",
        prop: "roleId",
        selectList
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
  }
}
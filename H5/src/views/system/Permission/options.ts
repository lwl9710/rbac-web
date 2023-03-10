import { Option } from "@/components/CrudTable/types";

export function getTableOption(selectList = []): Option {
  return {
    border: true,
    showAdd: true,
    showEdit: true,
    showDel: true,
    columns: [
      {
        label: "权限URL",
        prop: "url",
      },
      {
        label: "权限类型",
        prop: "type",
        dataType: "select",
        selectList: [
          { label: "普通类型", value: 1 },
          { label: "菜单类型", value: 2 },
        ]
      },
      {
        label: "权限描述",
        prop: "description"
      },
      {
        label: "父级权限",
        prop: "parentId",
        dataType: "select",
        filterable: true,
        selectList
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
};
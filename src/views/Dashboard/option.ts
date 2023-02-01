import { Option } from "@/components/CrudTable/types";

export const TABLE_OPTION: Option = {
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
}

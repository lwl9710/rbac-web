import request from "@/utils/request";

// 获取角色列表
export function reqGetUserList(data: any) {
  return request.post("/user/list", data);
}

// 添加角色
export function reqAddUser(data: any) {
  return request.put("/user/add", data);
}

// 修改角色
export function reqUdateUser(data: any) {
  return request.patch("/user/update", data);
}

// 删除角色
export function reqDelUser(id: string) {
  return request.delete(`/user/delete/${id}`);
}
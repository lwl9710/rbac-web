import request from "@/utils/request";

// 获取角色列表
export function reqGetRoleList(data: any) {
  return request.post("/role/list", data);
}

// 添加角色
export function reqAddRole(data: any) {
  return request.put("/role/add", data);
}

// 修改角色
export function reqUdateRole(data: any) {
  return request.patch("/role/update", data);
}

// 删除角色
export function reqDelRole(id: string) {
  return request.delete(`/role/delete?id=${id}`);
}

// 更新角色权限
export function reqUpdatePermissions(data: any) {
  return request.patch("/role/updatePermission", data);
}
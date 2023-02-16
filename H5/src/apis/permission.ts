import request from "@/utils/request";

// 获取权限列表
export function reqGetPermissionList(data: any) {
  return request.post("/permission/list", data);
}

// 添加权限
export function reqAddPermission(data: any) {
  return request.put("/permission/add", data);
}

// 修改权限
export function reqUdatePermission(data: any) {
  return request.patch("/permission/update", data);
}

// 删除权限
export function reqDelPermission(id: string) {
  return request.delete(`/permission/delete?id=${id}`);
}

// 获取权限信息
export function reqFindPermissions(id: string) {
  return request.get(`/permission/find?roleId=${id}`);
}
import request from "@/utils/request";

// 用户登陆
export function login(data: any): Promise<any> {
  return request.post("/authentication/login", data);
}
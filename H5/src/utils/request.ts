import axios from "axios";
import { getToken } from "@/utils/auth";

const request = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL || "",
  timeout: import.meta.env.VITE_NETWORK_TIMEOUT || 30 * 1000
});

request.interceptors.request.use(config => {
  const token = getToken();
  if(token) {
    config.headers["Authorization"] = token;
  }
  return config;
});

request.interceptors.response.use(response => {
  const responseBody = response.data;
  if(responseBody.code === 401) {
    ElMessage.warning(responseBody.message);
  }

  if(response.request.meta?.isBody === false) {
    return response;
  } else {
    return responseBody;
  }
});

export default request;

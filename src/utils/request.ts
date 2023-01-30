import axios from "axios";

const request = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL || "",
  timeout: import.meta.env.VITE_NETWORK_TIMEOUT || 30 * 1000
});

request.interceptors.request.use(config => {
  return config;
});

request.interceptors.response.use(response => {
  if(response.request.meta?.isBody === false) {
    return response;
  } else {
    return response.data;
  }
});

export default request;

import pinia from "@/store";
import useUserStore from "@/store/user";

const userStore = useUserStore(pinia);

export function getToken(): string {
  return sessionStorage.getItem("token") || "";
}

export function setToken(token: string): void {
  userStore.setToken(token);
  sessionStorage.setItem("token", token);
}

export function clearToken() {
  userStore.setToken("");
  sessionStorage.removeItem("token");
}
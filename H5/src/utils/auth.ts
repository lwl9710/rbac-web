export function getToken(): string {
  return sessionStorage.getItem("token") || "";
}

export function setToken(token: string): void {
  sessionStorage.setItem("token", token);
}

export function clearToken() {
  sessionStorage.removeItem("token");
}
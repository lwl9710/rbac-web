import "axios";
declare interface AxiosRequestMeta {
  isBody: boolean
}

declare module "axios" {
  export interface AxiosRequestConfig<D = any> {
    meta?: AxiosRequestMeta
  }
};

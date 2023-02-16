declare type StringNumber = string | number;

declare interface StringObject<V = any> {
  [props: string]: V
}

declare interface MenuRouteMeta {
  title?: string,
  icon?: string,
  hidden?: boolean
}

declare interface MenuRoute {
  name: string,
  meta: MenuRouteMeta,
  children?: Array<MenuRoute>
}

declare interface NavItem {
  name: string,
  title: string,
  params: StringObject,
  query: StringObject
}

declare interface CrudTableEventParam<T = any> {
  $index?: number,
  data: T,
  done: () => void
}

// 获取css有效值
export function getCssValue(value: StringNumber): string {
  const strValue: string = value.toString();
  return /^\d+$/.test(strValue) ? `${strValue}px` : strValue
}

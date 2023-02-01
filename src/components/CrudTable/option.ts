export const DEFAULT_ALIGN = "center";

export const DEFAULT_PATTERN = "YYYY-MM-DD HH:mm:ss";

export function getLengthInt(value: string | number, length: number = 2) {
  return "0".repeat(length - value.toString().length) + value;
}

export function formatDateTime(value: string | number | Date, pattern: string = DEFAULT_PATTERN) {
  const datetime:Date = new Date(value);
  pattern = pattern.replaceAll("YYYY", `${ datetime.getFullYear() }`);
  pattern = pattern.replaceAll("MM", `${ getLengthInt(datetime.getMonth() + 1) }`);
  pattern = pattern.replaceAll("DD", `${ getLengthInt(datetime.getDate()) }`);
  pattern = pattern.replaceAll("HH", `${ getLengthInt(datetime.getHours()) }`);
  pattern = pattern.replaceAll("mm", `${ getLengthInt(datetime.getMinutes()) }`);
  pattern = pattern.replaceAll("ss", `${ getLengthInt(datetime.getSeconds()) }`);
  return pattern;
}

export function initValue(type: string = "string"): any {
  switch (type) {
    case "string": return "";
    case "select": return "";
    case "radio": return "";
    case "file": return "";
    case "datetime": return "";
    case "datetimerange": return ["", ""]
  }
}

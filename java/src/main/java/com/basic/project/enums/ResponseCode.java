package com.basic.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "请求成功"),
    WARNING(300, "请求警告"),
    FAILURE(400, "请求失败"),
    DENIED(401, "没有权限"),
    UNAUTHORIZED(403, "凭证异常");

    private final Integer code;
    private final String message;
}

package com.basic.project.dto;

import com.basic.project.enums.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<T> {
    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "状态描述")
    private String message;
    @Schema(description = "返回信息")
    private T data;

    public static <T> ResponseDTO<T> success(T data) {
        return create(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseDTO<T> warning() {
        return create(ResponseCode.WARNING, null);
    }

    public static <T> ResponseDTO<T> warning(String message) {
        return warning(message, null);
    }

    public static <T> ResponseDTO<T> warning(String message, T data) {
        return new ResponseDTO<>(ResponseCode.WARNING.getCode(), message, data);
    }

    public static <T> ResponseDTO<T> failure() {
        return create(ResponseCode.FAILURE, null);
    }

    public static <T> ResponseDTO<T> failure(String message) {
        return failure(message, null);
    }

    public static <T> ResponseDTO<T> failure(String message, T data) {
        return new ResponseDTO<>(ResponseCode.FAILURE.getCode(), message, data);
    }

    public static <T> ResponseDTO<T> denied() {
        return create(ResponseCode.DENIED, null);
    }

    public static <T> ResponseDTO<T> denied(String message) {
        return failure(message, null);
    }

    public static <T> ResponseDTO<T> denied(String message, T data) {
        return new ResponseDTO<>(ResponseCode.DENIED.getCode(), message, data);
    }

    public static <T> ResponseDTO<T> unauthorized() {
        return create(ResponseCode.UNAUTHORIZED, null);
    }

    public static <T> ResponseDTO<T> unauthorized(String message) {
        return failure(message, null);
    }

    public static <T> ResponseDTO<T> unauthorized(String message, T data) {
        return new ResponseDTO<>(ResponseCode.UNAUTHORIZED.getCode(), message, data);
    }

    public static <T> ResponseDTO<T> create(ResponseCode code, T data) {
        return new ResponseDTO<>(code.getCode(), code.getMessage(), data);
    }
}
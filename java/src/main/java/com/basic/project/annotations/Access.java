package com.basic.project.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
    Policy value() default Policy.CERTIFICATE;

    enum Policy {
        // 需要凭证
        CERTIFICATE,
        // 需要权限
        AUTHORITY,
        // 匿名接口
        ANONYMOUS;
    }
}

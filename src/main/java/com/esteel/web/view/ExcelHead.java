package com.esteel.web.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHead {
    String name();

    boolean required() default false;

    boolean count() default false;

    String dateFormat() default "yyyy-MM-dd";

    int enlarge() default 1;

    int reduce() default 1;

    String message() default "Row no：%s，column no：%s，%s can not be null.";
}
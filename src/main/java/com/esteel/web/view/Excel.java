package com.esteel.web.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
    String fileName();

    String sheetName();

    int sheetStartIndex() default 0;

    int rowStartIndex() default 0;

    int clumnStartIndesx() default 0;
}
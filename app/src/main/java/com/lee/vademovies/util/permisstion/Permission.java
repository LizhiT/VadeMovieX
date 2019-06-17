package com.lee.vademovies.util.permisstion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created :  LiZhIX
 * Date :  2019/6/15 9:29
 * Description  :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    /* Permissions */
    String[] permissions();

    /* Rationales */
    int[] rationales() default {};

    /* Rejects */
    int[] rejects() default {};
}
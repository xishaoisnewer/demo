package com.yooli.demo.pettern.factory;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/8
 * Time: 下午10:14
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    int min() default 1;

    int max() default 10;

    boolean isNotNull() default true;
}

package com.baizhi.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述信息 (自定义Redis缓存注解)
 *
 * @author : buxiaoyu
 * @date : 2019-07-29 17:40
 * @version: V_1.0.0
 */


//声明该注解使用的位置：在方法上使用
@Target(value = ElementType.METHOD)
//声明该注解在什么时候生效  运行时生效
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RedisCache {
}

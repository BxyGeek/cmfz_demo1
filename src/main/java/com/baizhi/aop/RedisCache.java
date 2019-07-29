package com.baizhi.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

/**
 * 类描述信息 (Redis缓存AOP编程切入点)
 *
 * @author : buxiaoyu
 * @date : 2019-07-29 17:40
 * @version: V_1.0.0
 */
//声明这个类是一个配置类
@Configuration
@Aspect
@Slf4j
public class RedisCache {

    @Autowired
    private Jedis jedis;



    @Around(value = "execution(* com.baizhi.service.*.query*(..))")
    //ProceedingJoinPoint 环绕通知
    public Object cache(ProceedingJoinPoint proceedingJoinPoint){
        //1.获取房前要执行的方法
        //2.判断要执行的方法上是否含有自定义的Redis缓存的注解
        //3.如果含有该注解，先去缓存中去拿
        //     3.1 如果缓存中有直接将结果返回
        //     3.2 如果没有去查询数据库  然后缓存一份到缓存中
        //4.如果没有该注解  直接放行  说明不是查询方法

        //1.获取房前要执行的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //2.判断要执行的方法上是否含有自定义的Redis缓存的注解
        Method method = signature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotation.RedisCache.class);
        //3.如果含有该注解，先去缓存中去拿
        if (b){
            StringBuilder builder = new StringBuilder();
            //获取类的全限定名
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            builder.append(className).append(".");
            //获取方法名
            String methodName = method.getName();
            builder.append(methodName).append(":");
            //获取方法参数
            Object[] args = proceedingJoinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                builder.append(arg.toString());
                if (i == args.length-1){
                    break;
                }
                builder.append(",");
            }
            String key = builder.toString();

            //     3.1 如果缓存中有直接将结果返回
            if (jedis.exists(key)){
                String s = jedis.get(key);
                Object result = JSONObject.parse(s);
                return result;
            }
            //     3.2 如果没有去查询数据库  然后缓存一份到缓存中
            else {
                try {
                    Object result = proceedingJoinPoint.proceed();
                    jedis.set(key, JSONObject.toJSONString(result));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

        }
        //4.如果没有该注解  直接放行  说明不是查询方法
        else {
            try {
                Object result = proceedingJoinPoint.proceed();
                return result;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }


}

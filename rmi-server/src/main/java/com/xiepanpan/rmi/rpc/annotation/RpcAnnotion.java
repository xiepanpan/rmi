package com.xiepanpan.rmi.rpc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotion {

    /**
     * 对外发布的服务的接口地址
     * @return
     */
    Class<?> value();

    String version() default "";
}

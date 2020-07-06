package com.xiepanpan.rmi.rpc;

import java.lang.reflect.Proxy;
import java.rmi.server.RemoteObjectInvocationHandler;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class RpcClientProxy {

    public<T> T clientProxy(final Class<T> interfaceCls,final String host,final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class[]{interfaceCls},
                new RemoteInvocationHandler(host,port));
    }
}
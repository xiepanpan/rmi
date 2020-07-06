package com.xiepanpan.rmi.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        //通过tcp传输协议进行传输
        TcpTransport tcpTransport = new TcpTransport(this.host,this.port);
        //发送请求
        return tcpTransport.send(request);
    }
}
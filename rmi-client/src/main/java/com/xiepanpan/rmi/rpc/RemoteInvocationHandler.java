package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        //根据接口名称得到对应的服务地址
        String serviceAddress = serviceDiscovery.discovery(request.getClassName());
        //通过tcp传输协议进行传输
        TcpTransport tcpTransport = new TcpTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
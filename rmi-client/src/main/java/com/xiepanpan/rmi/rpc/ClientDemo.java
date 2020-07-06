package com.xiepanpan.rmi.rpc;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();

        IXpHello iXpHello = rpcClientProxy.clientProxy(IXpHello.class, "localhost", 8889);
        System.out.println(iXpHello.sayHello("zhoujielun"));
    }
}
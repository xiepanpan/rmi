package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.zk.IServiceDiscovery;
import com.xiepanpan.rmi.rpc.zk.ServiceDiscoveryImpl;
import com.xiepanpan.rmi.rpc.zk.ZkConfig;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
//        RpcClientProxy rpcClientProxy = new RpcClientProxy();
//
//        IXpHello iXpHello = rpcClientProxy.clientProxy(IXpHello.class, "localhost", 8889);
//        System.out.println(iXpHello.sayHello("zhoujielun"));

        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl(ZkConfig.CONNECTION_STR);

        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);

        for (int i = 0; i < 10; i++) {
            IXpHello iXpHello = rpcClientProxy.clientProxy(IXpHello.class, null);
            System.out.println(iXpHello.sayHello("xpp"));
            Thread.sleep(1000);
        }
    }
}
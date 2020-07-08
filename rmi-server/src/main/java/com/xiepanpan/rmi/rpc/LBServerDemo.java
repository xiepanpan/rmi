package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.zk.IRegisterCenter;
import com.xiepanpan.rmi.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * @author: xiepanpan
 * @Date: 2020/7/8
 * @Description: 模拟负载均衡1
 */
public class LBServerDemo {

    public static void main(String[] args) throws IOException {
        IXpHello iXpHello = new XpHelloImpl();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8081");
        rpcServer.bind(iXpHello);
        rpcServer.publish();
        System.in.read();
    }
}

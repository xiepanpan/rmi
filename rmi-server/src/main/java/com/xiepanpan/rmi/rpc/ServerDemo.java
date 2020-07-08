package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.zk.IRegisterCenter;
import com.xiepanpan.rmi.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

public class ServerDemo {

    public static void main(String[] args) throws IOException {
        IXpHello iXpHello = new XpHelloImpl();
        IXpHello iXpHello2=new XpHelloImpl2();
//        RpcServer rpcServer = new RpcServer();
//        rpcServer.publish(iXpHello,8889);
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8081");
        rpcServer.bind(iXpHello,iXpHello2);
        rpcServer.publish();
        System.in.read();
    }

}

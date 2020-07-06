package com.xiepanpan.rmi.rpc;

public class ServerDemo {

    public static void main(String[] args) {
        IXpHello iXpHello = new XpHelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(iXpHello,8889);
    }

}

package com.xiepanpan.rmi.rpc;

@RpcAnnotion(IXpHello.class)
public class XpHelloImpl implements IXpHello {
    public String sayHello(String msg) {
        return "Hello" + msg;
    }
}

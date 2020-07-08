package com.xiepanpan.rmi.rpc;

@RpcAnnotion(IXpHello.class)
public class XpHelloImpl implements IXpHello {
    @Override
    public String sayHello(String msg) {
        return "Hello" + msg;
    }
}

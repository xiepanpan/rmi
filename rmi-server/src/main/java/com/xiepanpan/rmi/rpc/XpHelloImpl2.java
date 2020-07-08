package com.xiepanpan.rmi.rpc;

@RpcAnnotion(value = IXpHello.class,version = "2.0")
public class XpHelloImpl2 implements IXpHello {
    @Override
    public String sayHello(String msg) {
        return "Hello 2.0" + msg;
    }
}

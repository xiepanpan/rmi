package com.xiepanpan.rmi.rpc;

public class XpHelloImpl implements IXpHello {
    public String sayHello(String msg) {
        return "Hello" + msg;
    }
}

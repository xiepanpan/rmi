package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.annotation.RpcAnnotion;

@RpcAnnotion(value = IXpHello.class,version = "2.0")
public class XpHelloImpl2 implements IXpHello {
    @Override
    public String sayHello(String msg) {
        return "Hello 2.0" + msg;
    }
}

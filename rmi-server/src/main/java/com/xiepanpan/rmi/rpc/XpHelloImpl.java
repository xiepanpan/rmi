package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.annotation.RpcAnnotion;

@RpcAnnotion(IXpHello.class)
public class XpHelloImpl implements IXpHello {
    @Override
    public String sayHello(String msg) {
        return "Hello" + msg;
    }
}

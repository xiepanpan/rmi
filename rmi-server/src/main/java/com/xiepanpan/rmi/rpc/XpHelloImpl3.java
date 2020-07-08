package com.xiepanpan.rmi.rpc;

import com.xiepanpan.rmi.rpc.annotation.RpcAnnotion;

@RpcAnnotion(value = IXpHello.class)
public class XpHelloImpl3 implements IXpHello {
    @Override
    public String sayHello(String msg) {
        return "另一台服务器Hello" + msg;
    }
}

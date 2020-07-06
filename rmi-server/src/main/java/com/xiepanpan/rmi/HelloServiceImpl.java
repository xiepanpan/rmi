package com.xiepanpan.rmi;

import com.xiepanpan.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected HelloServiceImpl() throws RemoteException {
        // super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException{
        return "Hello,"+msg;
    }
}

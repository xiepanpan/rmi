package com.xiepanpan.rmi;

import com.xiepanpan.rmi.HelloServiceImpl;
import com.xiepanpan.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) throws Exception {
        try {
            IHelloService helloService=new HelloServiceImpl();//已经发布了一个远程对象

            LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://127.0.0.1/hello",helloService); //注册中心 key - value
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

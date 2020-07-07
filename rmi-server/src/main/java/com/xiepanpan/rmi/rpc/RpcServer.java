package com.xiepanpan.rmi.rpc;


import com.xiepanpan.rmi.rpc.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于发布一个远程服务
 */
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService excutorService = Executors.newCachedThreadPool();

    //注册中心
    private IRegisterCenter registerCenter;

    //服务发布地址
    private String serviceAddress;

    //存放服务名称和服务对象之间的关系
    Map<String,Object> handleMap = new HashMap<String, Object>();


    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    public void bind(Object... services) {
        for (Object service: services) {
            RpcAnnotion annotation = service.getClass().getAnnotation(RpcAnnotion.class);
            String version = annotation.version();
            String serviceName = annotation.value().getName();
            if (version!=null&& version.equals("")) {
                serviceName = serviceName+"-"+version;
            }
            handleMap.put(serviceName,service);

        }
    }

    /**
     * 发布
     * @param service
     * @param port
     */
    public void  publish() {
        ServerSocket serverSocket = null;
        try {

            String[] addrs = serviceAddress.split(":");
            //启动一个服务监听
            serverSocket = new ServerSocket(Integer.parseInt(addrs[1]));

            for (String interfaceName: handleMap.keySet()) {
                registerCenter.register(interfaceName,serviceAddress);
                System.out.println("注册服务成功："+interfaceName+"->" + serviceAddress);
            }


            while (true) {
                //循环监听
                Socket accept = serverSocket.accept();
                //通过线程池去处理请求
                excutorService.execute(new ProcessorHandler(accept,handleMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

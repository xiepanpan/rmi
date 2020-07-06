package com.xiepanpan.rmi.rpc;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于发布一个远程服务
 */
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService excutorService = Executors.newCachedThreadPool();

    /**
     * 发布
     * @param service
     * @param port
     */
    public void  publish(final Object service,int port) {
        ServerSocket serverSocket = null;
        try {
            //启动一个服务监听
            serverSocket = new ServerSocket(port);
            while (true) {
                //循环监听
                Socket accept = serverSocket.accept();
                //通过线程池去处理请求
                excutorService.execute(new ProcessorHandler(accept,service));
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

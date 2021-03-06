package com.xiepanpan.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:
 */
public class TcpTransport {

    private String serviceAddress;

    public TcpTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    /**
     * 创建一个socket连接
     * @return
     */
    private Socket newSocket() {
        System.out.println("创建一个新的连接");
        Socket socket ;
        try {
            String[] arrs = serviceAddress.split(":");
            socket = new Socket(arrs[0],Integer.parseInt(arrs[1]));
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("连接建立失败");
        }

    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;

        try {
            socket = newSocket();
            //获取输出流 将客户端需要调用的远程方法参数request发出去
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            //获取输入流 得到服务器的返回结果
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object readObject = inputStream.readObject();
            inputStream.close();
            outputStream.close();
            return readObject;
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常：",e);
        } finally {
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
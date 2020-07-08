package com.xiepanpan.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description: 处理socket请求
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;
    //服务端发布的服务
    Map<String,Object> handlerMap;
    public ProcessorHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    public void run() {
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());

            //反序列化远程传输的对象RpcRequest
            RpcRequest request = (RpcRequest) inputStream.readObject();

            //通过反射调用本地方法
            Object result = invoke(request);

            //通过输出流将结果输出客户端
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(result);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 通过反射调用服务
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        String serviceName = request.getClassName();
        String version = request.getVersion();

        if (version!=null&&!version.equals("")) {
            serviceName=serviceName+"-"+version;
        }
        //从handlerMap中 根据客户端请求的地址 去拿到响应的服务 通过反射发起调用
        Object service = handlerMap.get(serviceName);

        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service,args);
    }
}
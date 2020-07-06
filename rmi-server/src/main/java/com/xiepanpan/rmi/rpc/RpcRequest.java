package com.xiepanpan.rmi.rpc;

import java.io.Serializable;

/**
 * @author: xiepanpan
 * @Date: 2020/7/7 0007
 * @Description:  传输参数
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = 1883137246681461537L;
    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
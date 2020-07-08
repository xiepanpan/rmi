package com.xiepanpan.rmi.rpc.zk;

/**
 * 服务发现
 */
public interface IServiceDiscovery {

    /**
     * 根据请求服务地址 获取对应的调用地址
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}

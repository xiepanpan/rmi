package com.xiepanpan.rmi.rpc.zk;

/**
 * 服务发现
 */
public interface IServiceDiscovery {

    String discovery(String serviceName);
}

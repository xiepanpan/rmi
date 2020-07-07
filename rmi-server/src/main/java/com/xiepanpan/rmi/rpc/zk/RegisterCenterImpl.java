package com.xiepanpan.rmi.rpc.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author: xiepanpan
 * @Date: 2020/7/8 0008
 * @Description:
 */
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();

        curatorFramework.start();
    }

    public void register(String serviceName, String serviceAddress) {

        //注册相应的服务
        String servicePath = ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;

        try {
            //判断 /register/product-service 是否存在 不存在就创建
            if (curatorFramework.checkExists().forPath(servicePath)==null) {
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath,"0".getBytes());

            }
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功："+rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
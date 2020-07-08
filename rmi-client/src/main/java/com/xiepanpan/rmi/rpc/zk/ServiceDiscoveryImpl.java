package com.xiepanpan.rmi.rpc.zk;

import com.xiepanpan.rmi.rpc.zk.loadBalance.LoadBalance;
import com.xiepanpan.rmi.rpc.zk.loadBalance.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiepanpan
 * @Date: 2020/7/8 0008
 * @Description:
 */
public class ServiceDiscoveryImpl implements IServiceDiscovery {


    List<String> response = new ArrayList<String>();

    private CuratorFramework curatorFramework;

    private String address;

    public ServiceDiscoveryImpl(String address) {
        this.address = address;
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(address)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();

        curatorFramework.start();
    }

    public String discovery(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            response = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常："+e);
        }

        //动态发现服务节点的变化
        registerWatcher(path);

        //负载均衡机制
        LoadBalance loadBalance = new RandomLoadBalance();

        //返回调用的服务地址
        return loadBalance.selectHost(response);
    }

    private void registerWatcher( final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                response = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);

        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册pathChild Watcher 异常:" +e);
        }
    }
}
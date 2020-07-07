package com.xiepanpan.rmi.rpc.zk.loadBalance;

import java.util.List;
import java.util.Random;

/**
 * @author: xiepanpan
 * @Date: 2020/7/8 0008
 * @Description:  实现随机负载均衡
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    String doSelect(List<String> response) {
        int len = response.size();
        Random random = new Random();
        return  response.get(random.nextInt(len));
    }
}
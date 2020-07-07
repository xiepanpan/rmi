package com.xiepanpan.rmi.rpc.zk.loadBalance;

import java.util.List;

/**
 * @author: xiepanpan
 * @Date: 2020/7/8 0008
 * @Description:
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    public String selectHost(List<String> response) {
        if (response == null || response.size()==0) {
            return null;
        }
        if (response.size()==1) {
            return response.get(0);
        }
        return doSelect(response);
    }

    abstract String doSelect(List<String> response);


}
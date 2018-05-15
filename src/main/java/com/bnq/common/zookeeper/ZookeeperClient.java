package com.bnq.common.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by liqiang on 2018/4/26.
 */
public class ZookeeperClient {

    private static ZookeeperConnectionCenter zookeeperConnectionCenter = new ZookeeperConnectionCenter();
    public static String server = "18.217.209.186:2181";

    public static ZooKeeper getClient() throws IOException {
        return zookeeperConnectionCenter.connect("10.184.120.23:2181");
    }

    public static ZkClient getZkClient(){
        return new ZkClient(server);
    }

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        ZookeeperClient.server = server;
    }
}

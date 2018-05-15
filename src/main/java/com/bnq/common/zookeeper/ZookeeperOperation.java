package com.bnq.common.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liqiang on 2018/4/26.
 */
public class ZookeeperOperation {

    static Logger logger = LoggerFactory.getLogger(ZookeeperOperation.class);

    //connect zookeeper
    private static ZookeeperConnectionCenter zookeeperConnectionCenter;
    //connectedCli
    private static ZooKeeper zooKeeper;

    public static boolean createNode(String path, byte[] data) {
        String nodeName = null;
        try {
            nodeName = zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            logger.error(" createNode error",e);
            return false;
        } catch (InterruptedException e) {
            logger.error(" createNode error",e);
            return false;
        }
        if(nodeName != null){
            return true;
        }
        return false;
    }

    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path,true);
    }

    public static void getChild(String path) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        List<String> children = zooKeeper.getChildren(path, true);
        for (String child : children) {
            byte[] data = zooKeeper.getData(path+"/"+child, false, null);
            System.out.println("child :"+child+"===="+new String(data,"UTF-8").toString());
        }

    }

    public static Stat setZnode(String path,byte[] data,int version) throws InterruptedException {
        Stat stat = null;
        try {
            stat = zooKeeper.setData(path, data, version);
        } catch (KeeperException e) {
            logger.error(" set node error",e);
            //e.printStackTrace();
            return null;
        }
        return stat;
    }

    public static void delZnode(String path,int version){
        try {
            zooKeeper.delete(path,version);
        } catch (InterruptedException e) {
            logger.error(" del node error",e);
        } catch (KeeperException e) {
            logger.error(" del node error",e);
        }
    }


    public static byte[] getNodeData(String path) throws KeeperException, InterruptedException {
        Stat stat = znode_exists(path);
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        if(stat != null) {
            System.out.println("Node exists and the node version is " +
                stat.getVersion());
            byte[] nodeName = zooKeeper.getData(path, /*true*/we -> {
                if (we.getType() == Watcher.Event.EventType.NodeDataChanged) {
                    switch(we.getState()) {
                        case Expired:
                            connectedSignal.countDown();
                            break;
                    }

                } else {
                    String path1 = "/MyFirstZnode";

                    try {
                        byte[] bn = zooKeeper.getData(path1,
                            false, null);
                        String data = new String(bn,
                            "UTF-8");
                        System.out.println(data);
                        connectedSignal.countDown();

                    } catch(Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            },null);
            if(nodeName != null){
                return nodeName;
            }
            return null;
        } else {
            System.out.println("Node does not exists");
            return null;
        }

    }


    public static void main(String[] args) {

        // zNode path
        String path = "/MyFirstZnode"; // Assign path to znode
        String child = "/MyFirstZnode/child-3"; // Assign path to znode

        // data in byte array
        byte[] data = "My first child app-3".getBytes(); // Declare data

        try {
            zookeeperConnectionCenter = new ZookeeperConnectionCenter();
            zooKeeper = zookeeperConnectionCenter.connect("10.184.120.23:2181");
            //boolean node = createNode(path, data);// Create the data to the specified path
            //createNode(child,data);
//byte[] nodeData = getNodeData(path);
            //String val = new String(nodeData,"UTF-8");
            /*Stat stat = znode_exists(path);
            setZnode(path,"update date".getBytes(),stat.getVersion());
            delZnode(path,stat.getVersion());
            System.out.println(node);*/
            getChild(path);
            zookeeperConnectionCenter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); //Catch error message
        }
    }
}

package com.bnq.common.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by liqiang on 2018/5/8.
 */
public class ZookeeperLock implements Lock {

    static Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    private String lockNode;

    private ZooKeeper zooKeeper;

    private CountDownLatch downLatch = new CountDownLatch(1);

    public class Mywatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            if(Watcher.Event.EventType.NodeDeleted.equals(event.getType())){
                logger.info(event.getPath()+"节点删除。");
                downLatch.countDown();
            }
            if(Watcher.Event.EventType.NodeDataChanged.equals(event.getType())){
                logger.info(event.getPath()+"节点数据改变。");
            }
            if(Watcher.Event.EventType.NodeCreated.equals(event.getType())){
                logger.info(Thread.currentThread().getName()+"-"+event.getPath()+"加锁创建了。");
            }
        }
    }


    public ZookeeperLock (String lockNode) throws IOException {
        this.lockNode = lockNode;
        zooKeeper = ZookeeperClient.getClient();
        //ZooKeeper.States state = zooKeeper.getState();
        zooKeeper.register(event -> {
            if(ZooKeeper.States.NOT_CONNECTED.equals(event.getState())){
                try {
                    zooKeeper = ZookeeperClient.getClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void lock() {
        if(!tryLock()){
            waitForLock();
            lock();
        }
    }

    public String getLockData(){
        try {
            byte[] data = zooKeeper.getData(lockNode, new Mywatcher(), null);
            return new String(data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void waitForLock() {
        try {
            Stat exists = zooKeeper.exists(lockNode, new Mywatcher());
            if(exists != null){
                downLatch.await();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock() {
        try {
            //byte[] b = {1};
            zooKeeper.create(lockNode, Thread.currentThread().getName().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Stat stat = new Stat();
        try {
            zooKeeper.getData(lockNode,true,stat);
            zooKeeper.delete(lockNode, stat.getVersion());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

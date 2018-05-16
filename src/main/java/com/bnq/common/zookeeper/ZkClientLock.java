package com.bnq.common.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by liqiang on 2018/5/8.
 */
public class ZkClientLock implements Lock {

    static Logger logger = LoggerFactory.getLogger(ZkClientLock.class);
    //父节点
    private String lockNode;

    private ZkClient zkClient;

    private long firstTimestamp = 0L;

    private long overTimestamp = 0L;

    /** 当前节点名称 */
    private ThreadLocal<String> currentNode = new ThreadLocal<>();
    /** 前一节点名称 */
    private ThreadLocal<String> previousNode = new ThreadLocal<>();

    //private CountDownLatch countDownLatch ;

    public ZkClientLock(String lockNode){
        this.lockNode = lockNode;
        zkClient = ZookeeperClient.getZkClient();
        zkClient.setZkSerializer(new MyZkSerializer());
        if (!this.zkClient.exists(lockNode)){
            this.zkClient.createPersistent(lockNode);
        }
    }

    @Override
    public void lock() {
        if(!tryLock()){
            waitForLock();
            lock();
        }
    }

    public String getLockData(){
        return zkClient.readData(lockNode);
    }

    private void waitForLock() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //将listener注册到zkClient中
        MyZkDataListener myZkDataListener = new MyZkDataListener(countDownLatch);
        zkClient.subscribeDataChanges(this.previousNode.get(),myZkDataListener);
        //如果nodeName仍旧存在，则继续监听，让当前线程阻塞
        if (this.zkClient.exists(this.previousNode.get())){
            try{
                logger.info(countDownLatch+" await;");
                countDownLatch.await();
            }catch (Exception e){
                logger.error("countDown await exception",e);
            }
        }
        //监听到节点已被删除，将listener注销掉
        this.zkClient.unsubscribeDataChanges(this.previousNode.get(), myZkDataListener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock() {
        //创建临时顺序节点
        if (this.currentNode.get() == null){
            this.currentNode.set(this.zkClient.createEphemeralSequential(lockNode.concat("/"), "testZk"));
        }
        List<String> childNodeList = this.zkClient.getChildren(lockNode);
        Collections.sort(childNodeList);
        //如果当前节点是最小节点，则成功获取锁
        if(this.currentNode.get().equals(lockNode.concat("/").concat(childNodeList.get(0)))){
            logger.info(Thread.currentThread().getName()+" get lockNode :"+this.currentNode.get());
            return true;
        }
        //获取前一节点
        int currentIndex = childNodeList.indexOf(this.currentNode.get().substring(lockNode.length() + 1));
        this.previousNode.set(lockNode.concat("/").concat(childNodeList.get(currentIndex - 1)));
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (firstTimestamp == 0L) {
            if(tryLock()){//firstTimestamp为0时第一次直接取锁
                return true;
            }
            firstTimestamp = System.currentTimeMillis();
            overTimestamp = firstTimestamp+unit.toMillis(time);
        }
        if(System.currentTimeMillis() <= overTimestamp){//在有效时间内继续尝试取锁
            if(tryLock()){
                return true;
            }
            waitForLock();
            tryLock(time,unit);//取锁不成功继续递归取
        }
        return false;//超时返回false
    }

    @Override
    public void unlock() {
        try {
            zkClient.delete(this.currentNode.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
       return null;
    }
}

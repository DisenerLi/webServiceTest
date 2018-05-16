package com.bnq.common.zookeeper;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqiang on 2018/5/9.
 */
public class ZookeeperTest {

    static {
        DOMConfigurator.configure("D:\\workspace\\webServiceTest\\src\\main\\java\\com\\bnq\\common\\zookeeper\\log4j.xml");
    }

    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static CountDownLatch endTaskCount = new CountDownLatch(100);

    static Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);


    public static class MyThread implements Runnable{

        @Override
        public void run() {
            ZookeeperLock zlock = null;
            try {
                countDownLatch.await();
                zlock = new ZookeeperLock("/orderLock");
            } catch (Exception e) {
                e.printStackTrace();
            }
            zlock.lock();
            try {
                logger.info(Thread.currentThread().getName()+" get lock ,do some:"+zlock.getLockData());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                zlock.unlock();
                logger.info(Thread.currentThread().getName()+" unlock end");
                endTaskCount.countDown();
            }
        }
    }

    /** 定义成静态锁，确保每个线程进来后都是使用同一个锁 */
    private static ZkClientLock zkClientLock = new ZkClientLock("/orderLock");

    public static class MyZkThread implements Runnable{

        private volatile String name;

        public MyZkThread(){}

        public MyZkThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            zkClientLock.lock();
            try {
                logger.info(String.format("%s get lock ,do some:%s", Thread.currentThread().getName(), zkClientLock.getLockData()));
                //Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                zkClientLock.unlock();
                logger.info(String.format("%s unlock end", Thread.currentThread().getName()));
                endTaskCount.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        /*Thread t1 = new Thread(new MyZkThread(),"t1");
        Thread t2 = new Thread(new MyZkThread(),"t2");
        Thread t3 = new Thread(new MyZkThread(),"t3");
        Thread t4 = new Thread(new MyZkThread(),"t4");
        Thread t5 = new Thread(new MyZkThread(),"t5");
        Thread t6 = new Thread(new MyZkThread(),"t6");
        Thread t7 = new Thread(new MyZkThread(),"t7");
        Thread t8 = new Thread(new MyZkThread(),"t8");
        Thread t9 = new Thread(new MyZkThread(),"t9");
        Thread t10 = new Thread(new MyZkThread(),"t10");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t4.start();
        t4.start();
        t4.start();
        t4.start();*/
        ExecutorService executorService = Executors.newCachedThreadPool();//10个线程复用
        for (int i = 0; i < 100; i++) {//如果开启的多个线程循环使用，会导致ThreadLocal变量线程冲突，需要单独开新的线程
            executorService.execute(new MyZkThread("t"+i));
        }
        countDownLatch.countDown();
        endTaskCount.await();
        logger.info("结束");
        executorService.shutdown();
    }
}

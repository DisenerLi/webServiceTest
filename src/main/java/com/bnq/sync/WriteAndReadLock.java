package com.bnq.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liqiang on 2017/7/28.
 */
public class WriteAndReadLock {

    public static void mains(String[] args) {
            BufferLockAO bufferAO = new BufferLockAO();
            final Writer writer = new Writer(bufferAO);
            final Reader reader = new Reader(bufferAO);
            final Thread t1 = new Thread(writer, "writer");
            final Thread t2 = new Thread(reader, "reader");
            bufferAO.setProcessReady();
            t1.start();
            t2.start();
            new Thread(new Runnable() {
                public void run() {
                    long start = System.currentTimeMillis();
                    for (; ; ) {
                        //等5秒钟去中断读
                        if (System.currentTimeMillis()
                            - start > 5000) {
                            System.out.println("不等了，尝试中断");
                            t2.interrupt();
                            System.out.println("t2.isInterrupted() == " + t2.isInterrupted());
                            break;
                        }
                    }
                }
            }).start();
            //System.out.println("t2.isInterrupted() == "+t2.isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final Buffer bufferAO = new BufferLockAO();
        //final Writer writer = new Writer(bufferAO);
        final CountDownLatch begin = new CountDownLatch(1); //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(100);
        //final Reader reader = new Reader(bufferAO);
        //BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        //Thread t1 = new Thread(writer,"thread");
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(100,500,1000, TimeUnit.MILLISECONDS,workQueue);

        for (int i = 1; i <= 100; i++) {
            final int finalI = i;
           exec.execute(() -> {
                //bufferAO.setProcessReady();
                try {
                    begin.await();
                    if(finalI %2==0) {
                        bufferAO.write();
                    }else {
                        bufferAO.read();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            //System.out.println("get:"+submit.isDone());
        }
        System.out.println("开始执行");
        begin.countDown(); // begin减一，开始并发执行
        exec.awaitTermination(5, TimeUnit.SECONDS);//等待直到关闭后所有任务都已完成执行请求，或发生超时，或当前线程为中断，以先发生者为准。然后调用shutdown关闭线程池
        exec.shutdown();//停止线程池
    }

    static class Writer implements Runnable{

        private Buffer bufferAO;

        public Writer(Buffer bufferAO){
            this.bufferAO = bufferAO;
        }

        public void run() {
            bufferAO.setProcessReady();
            try {
                bufferAO.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Reader implements Runnable{

        private Buffer bufferAO;

        public Reader(Buffer bufferAO){
            this.bufferAO = bufferAO;
        }

        public void run() {
            try {
                bufferAO.read();
            } catch (InterruptedException e) {
                System.out.println("interrupted...");
            }
            //System.out.println("end read---------");
        }
    }
}

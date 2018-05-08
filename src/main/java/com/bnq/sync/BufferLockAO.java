package com.bnq.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liqiang on 2017/7/28.
 */
public class BufferLockAO extends Buffer{
    private ReentrantLock lock = new ReentrantLock();
    private Condition writeReady = lock.newCondition();
    private Condition readReady = lock.newCondition();
    private AtomicInteger count = new AtomicInteger(1);

    public BufferLockAO(){
    }

    public void write() throws InterruptedException{
        lock.lock();
        try {
            //long start = System.currentTimeMillis();
            //System.out.println("start write something>>>>>>>>>>>>>>>>>"+count);
            for (;;) {
                //System.out.println("write line >>>>>>>>>>");
               /* if(!isProcessReady){
                    //System.out.println(Thread.currentThread().getName()+" wait write>>>>"+count);
                   lockReady.await(5, TimeUnit.SECONDS);
                }*/
                ////break;
                //if (System.currentTimeMillis() - start >1000) {
                //    if(count<=0){
                //        System.out.println(Thread.currentThread().getName()+" continue write>>>>"+count);
                //        break;}
                //    count--;
                //    System.out.println(Thread.currentThread().getName()+" continue write>>>>"+count);
                //    break;
                //}
                if(count.get() < 1 ){
                    System.out.println(Thread.currentThread().getName()+" write something end-----------》"+count.incrementAndGet());
                }else {
                    System.out.println(Thread.currentThread().getName()+" write await");
                    if(writeReady.await(10,TimeUnit.SECONDS)){
                        System.out.println(Thread.currentThread().getName()+" write weakup");
                    }else {
                        System.out.println("没有请求了，那关闭writer");
                        break;
                    }
                }

                //break;
            }
            //System.out.println("end write <<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readReady.signal();
            lock.unlock();
            //isProcessReady = false;
        }
    }

    public void read() throws InterruptedException {
        /*if(isProcessReady){
            isProcessReady = false;
        }*/
        lock.lock();
        try {
            //readReady.await();
            //Thread.sleep(2000);
            //isProcessReady = true;
            if(count.get() > 0 ){
                //System.out.println(Thread.currentThread().getName()+" read get " + count);
                System.out.println(Thread.currentThread().getName()+" read something end-----------》"+count.getAndDecrement());
                //Thread.sleep(100);
            }else{
                //System.out.println(Thread.currentThread().getName()+" read empty -----------》"+count.get());
                writeReady.signal();
                System.out.println(Thread.currentThread().getName()+" read await");
                readReady.await();
                System.out.println(Thread.currentThread().getName()+" read weakup");
                read();
            }
            //lockReady.signal();
        }finally {
            lock.unlock();
        }
    }

    public void setProcessReady(){
        isProcessReady = true;
    }

    public void endRead(){
        isProcessReady = false;
    }
}
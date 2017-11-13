package com.bnq.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liqiang on 2017/7/28.
 */
public class BufferLockAO extends Buffer{
    private ReentrantLock lock = new ReentrantLock();
    private Condition lockReady = lock.newCondition();
    private int count = 1;

    public BufferLockAO(){
    }

    public void write() throws InterruptedException{
        lock.lock();
        try {
            long start = System.currentTimeMillis();
            //System.out.println("start write something>>>>>>>>>>>>>>>>>"+count);
            for (;;) {
                //System.out.println("write line >>>>>>>>>>");
                if(!isProcessReady){
                    System.out.println(Thread.currentThread().getName()+" wait write>>>>"+count);
                   lockReady.await();
                }
                ////break;
                //if (System.currentTimeMillis() - start >1000) {
                //    if(count<=0){
                //        System.out.println(Thread.currentThread().getName()+" continue write>>>>"+count);
                //        break;}
                //    count--;
                //    System.out.println(Thread.currentThread().getName()+" continue write>>>>"+count);
                //    break;
                //}
                if(count <=0 ){
                    break;
                }
                //Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+" get "+count);
                count--;
                break;
            }
            //System.out.println("end write <<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            isProcessReady = false;
        }
    }

    public void read() throws InterruptedException {
        if(isProcessReady == true){
            isProcessReady = false;
        }
        lock.lock();
        try {
            lockReady.signal();
            System.out.println(Thread.currentThread().getName()+" read something end-----------》"+count);
            //Thread.sleep(2000);
            //isProcessReady = true;
            if(count > 0 ){
                System.out.println(Thread.currentThread().getName()+" get" + count);
                count--;
                Thread.sleep(100);
            }
        }finally {
            lock.unlock();
        }
    }

    public void setProcessReady(){
        isProcessReady = true;
    }
}
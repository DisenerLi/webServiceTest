package com.bnq.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liqiang on 2017/7/28.
 */
public class VolatileTest {

    public static int a = 0;

    public static volatile int b = 0;

    public static int c = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(4);
    private static CountDownLatch doneSignal = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        SyncThread syncThread = new SyncThread(100);
        Thread t1 = new Thread(syncThread, "t1");
        Thread t2 = new Thread(syncThread, "t2");
        Thread t3 = new Thread(syncThread, "t3");
        Thread t4 = new Thread(syncThread, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println(syncThread.count.get());
        doneSignal.countDown();
        countDownLatch.await();
        System.out.println(syncThread.count.get());
    }

    public void addA(){
        a++;
    }

    public void addB(){
        b++;
    }

    public void addC(){
        c++;
    }

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        VolatileTest.a = a;
    }

    public static int getB() {
        return b;
    }

    public static void setB(int b) {
        VolatileTest.b = b;
    }

    public static int getC() {
        return c;
    }

    public static void setC(int c) {
        VolatileTest.c = c;
    }

    public static class SyncThread implements Runnable{

        public AtomicInteger count = new AtomicInteger();

        private byte[] lock = new byte[0];

        public SyncThread(int count){
            this.count.set(count);
        }

        private static int applyAsInt(int count) {
            while (count > 0) count--;
            return count;
        }

        public void setCount(int count){
            this.count.set(count);
        }

        public void delCount() throws InterruptedException {
                while (0 < count.get()) {
                    //val = count.decrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " count:" + count.decrementAndGet());
                }
            //count.getAndUpdate(SyncThread::applyAsInt);
        }

        public void run() {
            try {
                doneSignal.await();
                //System.out.println(Thread.currentThread().getName() + " continue :" + System.currentTimeMillis());
                delCount();
                countDownLatch.countDown();
                //Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.bnq.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liqiang on 2018/5/8.
 */
public class TestDeadLockCTU implements Runnable{

    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private boolean flag ;

    public TestDeadLockCTU(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        try{
            countDownLatch.await();
            if(flag) {
                lock1.lockInterruptibly();
                System.out.println("lock1.lockInterruptibly()");
                //TimeUnit.SECONDS.sleep(10);
                lock2.lockInterruptibly();
            }else {
                lock2.lockInterruptibly();
                System.out.println("lock2.lockInterruptibly()");
                TimeUnit.SECONDS.sleep(1);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
                System.out.println(flag + "线程释放lock1锁");
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                System.out.println(flag + "线程释放lock2锁");
            }
            System.out.println(flag + "线程已退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new TestDeadLockCTU(true));
        Thread thread2 = new Thread(new TestDeadLockCTU(false));
        thread1.start();
        thread2.start();
        //主线程休眠5秒
        TimeUnit.SECONDS.sleep(5);
        //thread1.join();
        //thread2.join();
        countDownLatch.countDown();
        thread1.interrupt();
    }
}

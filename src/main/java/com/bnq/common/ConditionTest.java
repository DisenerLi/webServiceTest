package com.bnq.common;

import com.google.common.base.Optional;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liqiang on 2018/5/14.
 */
public class ConditionTest {

    static ReentrantLock lock = new ReentrantLock();
    static Condition reader = lock.newCondition();
    static Condition writer = lock.newCondition();
    static volatile int count = 10;
    static volatile Integer val;
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    static class MyThread extends Thread {

        boolean flag;

        MyThread (){}

        public MyThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis()+"_"+flag+"....wait start...");
                countDownLatch.await();
                System.out.println(System.currentTimeMillis()+"_"+flag+"....wait continue...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(System.currentTimeMillis()+"_"+flag+"....wait continue...");
            lock.lock();
            try {
                if (flag) {
                    for (int i = 1; i < 99; i++) {
                        try {
                            if (count <= 0) {
                                writer.signal();
                                System.out.println("decr await");
                                reader.await();
                            }
                            System.out.println(i+"_decr ..." + count--);
                            //TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    for (int i = 1; i < 91; i++) {
                        try {
                            if (count >= 10) {
                                reader.signal();
                                System.out.println("add await");
                                writer.await();
                            }
                            System.out.println(i+"_add ..." + count++);
                            //TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }
    }

    public static Optional<Integer> add(Integer num1,Integer num2){
        Optional.of(num1).or(num1);
        System.out.println(num1+num2);
        return Optional.of(num1+num2);
    }

    public static void main(String[] args) throws InterruptedException {
        /*MyThread t1 = new MyThread(true);
        MyThread t2 = new MyThread(false);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        countDownLatch.countDown();*/
        ConditionTest.val = 1;
        //Optional<Integer> count = Optional.of(ConditionTest.val);
        //System.out.println(add(1,null));
        boolean equals = Objects.equals("1", "1");
        String s = "1";
        String b = "2";
        for (byte b1 : "1".getBytes()) {
            System.out.println(b1);
            System.out.println(new Byte("1"));
        }
        System.out.println(31 * 0 + '1');
        System.out.println(Objects.toString(val));
        System.out.println(com.google.common.base.Objects.equal(val,1));
    }
}

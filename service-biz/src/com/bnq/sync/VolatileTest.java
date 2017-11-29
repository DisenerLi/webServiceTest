package com.bnq.sync;

/**
 * Created by liqiang on 2017/7/28.
 */
public class VolatileTest {

    public static int a = 0;

    public static volatile int b = 0;

    public static int c = 0;


    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread(10);
            Thread t1 = new Thread(syncThread, "t1");
            Thread t2 = new Thread(syncThread, "t2");
            Thread t3 = new Thread(syncThread, "t3");
            Thread t4 = new Thread(syncThread, "t4");
            t1.start();
            t2.start();
            t3.start();
            t4.start();
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

        public static int count;

        private byte[] lock = new byte[0];

        public SyncThread(int count){
            SyncThread.count = count;
        }

        public void setCount(int count){
           SyncThread.count = count;
        }

        public synchronized static void delCount(){
            for(int i = 0;i< 10 ;i++) {
                --count;
            }
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }

        public void run() {

             /*(lock)*/{
                try {
                    /*for (int i = 0; i < 2; i++) {
                        //System.out.println(Thread.currentThread().getName() + " count:" + (count++));
                        setCount(count + i);
                    }*/
                    delCount();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

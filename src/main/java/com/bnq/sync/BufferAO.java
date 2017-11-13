package com.bnq.sync;

/**
 * Created by liqiang on 2017/7/28.
 */
public class BufferAO extends Buffer{

    private byte[] lock = new byte[0];

    private volatile int count = 3;

    public BufferAO(){
    }

    @Override
    public void setProcessReady() {
        isProcessReady = true;
    }

    public  void write() throws InterruptedException{
        synchronized (lock){
            long start = System.currentTimeMillis();
            //System.out.println("start write something>>>>>>>>>>>>>>>>>" + count);
            for(;;){
                //if(!isProcessReady){
                    //Thread.sleep(2000);
                if(count <=0 ){
                    break;
                }
                //Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+" get "+count);
                count--;
                break;
                //}
                //if(System.currentTimeMillis() - start > 100){
                //    //isProcessReady = !isProcessReady;
                //    break;
                //}
            }
            //System.out.println(Thread.currentThread().getName()+" <<<<<<<<<<<<<<<<<<<<<<<< " + count);
        }
    }

    public /*synchronized*/ void read() throws InterruptedException{
        //if(isProcessReady){
        //    isProcessReady = false;
        //}
        //System.out.println("start read something|||||||||||||||"+count);
        synchronized (lock){
            if(count > 0 ){
                count--;
                System.out.println("read get");
                Thread.sleep(100);
            }
            //Thread.currentThread().notify();
            System.out.println("read something|||||||||||||||"+count);
        }
    }
}

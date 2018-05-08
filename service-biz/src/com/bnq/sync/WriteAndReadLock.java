package com.bnq.sync;

/**
 * Created by liqiang on 2017/7/28.
 */
public class WriteAndReadLock {

    public static void main(String[] args) {
        /*final Buffer bufferAO = new BufferLockAO();
        //final Writer writer = new Writer(bufferAO);
        final CountDownLatch begin = new CountDownLatch(1); //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(100);
        //final Reader reader = new Reader(bufferAO);
        //BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        //Thread t1 = new Thread(writer,"thread");
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(100,500,1000, TimeUnit.MILLISECONDS,workQueue);

        for (int i = 1; i <= 100; i++) {
                //String task = "task@ " + i;
                //System.out.println("创建任务并提交到线程池中：" + task);
                //executor.execute(writer);

            final int finalI = i;
            exec.submit(new Runnable() {
                    public void run() {
                        bufferAO.setProcessReady();
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
                    }
                });
        }
        System.out.println("开始执行");
        begin.countDown(); // begin减一，开始并发执行
        exec.shutdown();*/
        //
        //final Thread t1 = new Thread(writer,"writer1");
        //final Thread t2 = new Thread(writer,"writer2");
        //final Thread t3 = new Thread(writer,"writer3");
        //final Thread t4 = new Thread(writer,"writer4");
        //final Thread t5 = new Thread(writer,"writer5");
        //final Thread t6 = new Thread(writer,"writer6");
        ////final Thread t2 =  new Thread(reader,"reader");
        //bufferAO.setProcessReady();
        //t1.start();
        //t2.start();
        //t3.start();
        //t4.start();
        //t5.start();
        //t6.start();
        ////t2.start();

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

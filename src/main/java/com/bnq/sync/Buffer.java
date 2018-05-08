package com.bnq.sync;

/**
 * Created by liqiang on 2017/11/2.
 */
public abstract class Buffer {

    protected volatile boolean isProcessReady = false;

    /**
     * 读
     */
    public abstract void write() throws InterruptedException;

    /**
     * 写
     */
    public abstract void read() throws InterruptedException;

    /**
     * 设置进程标志
     */
    public abstract void setProcessReady();
}

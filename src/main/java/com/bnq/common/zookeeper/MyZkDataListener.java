package com.bnq.common.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liqiang on 2018/5/9.
 */
public class MyZkDataListener implements IZkDataListener {

    Logger logger = LoggerFactory.getLogger(MyZkDataListener.class);

    private CountDownLatch countDownLatch ;

    public MyZkDataListener(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void handleDataChange(String s, Object o) throws Exception {
        logger.info("监听到节点：" + s + "，数据被改变成：" + o);
    }
    @Override
    public void handleDataDeleted(String s) throws Exception {
        logger.info("监听到节点" + s + "已被删除");
        countDownLatch.countDown();
    }
}

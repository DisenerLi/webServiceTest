package com.bnq.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liqiang on 2018/5/16.
 */
public class DubboProductServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");
        //IProductService productService = xmlApplicationContext.getBean("productService", IProductService.class);
        //System.out.println(productService.getProductCount(1L));
        Thread.currentThread().join();
        //xmlApplicationContext.stop();
    }
}

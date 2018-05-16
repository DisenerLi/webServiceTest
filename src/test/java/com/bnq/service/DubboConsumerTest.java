package com.bnq.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bnq.service.dubbo.IProductService;

/**
 * Created by liqiang on 2018/5/16.
 */
public class DubboConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("dubbo.xml");
        IProductService productService = (IProductService) xmlApplicationContext.getBean("productService");
        System.out.println(productService.getProductById(2L));
        //Thread.currentThread().join();
    }
}

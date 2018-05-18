package com.bnq.service;

import com.bnq.service.dubbo.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by liqiang on 2018/5/16.
 */
public class DubboConsumerTest {

    static Logger logger = LoggerFactory.getLogger("testLog");

    public static void main(String[] args) throws InterruptedException, ExecutionException, UnknownHostException {

        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("dubbo.xml");
        //IProductService productService = (IProductService) xmlApplicationContext.getBean("productService");
        IProductService newProductService = (IProductService) xmlApplicationContext.getBean("newProductService");
        System.out.println(newProductService.getProductById(23L));
        //System.out.println(productService.getProductById(20L));
        //Future<Object> future = RpcContext.getContext().getFuture();
        //RpcContext.getContext().getAttachments();
        //System.out.println("async wait res:"+future.get());

        //Thread.currentThread().join();
        /*InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(new String(localHost.getAddress()));
        System.out.println(localHost.getCanonicalHostName());
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
        System.out.println(localHost.isLoopbackAddress());
        System.out.println(InetAddress.getLoopbackAddress().getHostAddress());*/
        logger.info("print log.");

    }

    public static void objectToSerializable(Object object){
        try {
            byte[] buf = new byte[2048];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutput output = new ObjectOutputStream(out);
            output.writeObject(object);
            byte[] byteArray = out.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\webServiceTest\\src\\test\\java\\com\\bnq\\service\\application.txt");
            fileOutputStream.write(byteArray);
            fileOutputStream.flush();
            fileOutputStream.close();
            output.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

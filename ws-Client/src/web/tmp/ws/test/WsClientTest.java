package web.tmp.ws.test;

import web.tmp.ws.hello.HelloWSService;
import web.tmp.ws.hello.HelloWorld;

/**
 * Created by liqiang on 2017/11/7.
 */
public class WsClientTest {

    public static void main(String[] args) {
        //创建HelloWorldImplService对象
        HelloWSService hws = new HelloWSService();
        //通过getHelloWorldImplPort方法获取HelloWorldImpl实例
        HelloWorld hw = hws.getHelloWSPort();
        //使用HelloWorldImpl实例，像直接调用服务端方法一样，调用Web服务，获取WebService响应数据
        String retVal = hw.sayHelloWorldFrom("john");
        System.out.println(retVal);

    }
}

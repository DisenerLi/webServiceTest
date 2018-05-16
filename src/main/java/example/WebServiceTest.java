package example;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqiang on 2018/5/10.
 */
public class WebServiceTest {

    static final Exchanger<String> exgr = new Exchanger<>();

    static final CountDownLatch countDownLatch = new CountDownLatch(1);

    static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String args[]){
        /*System.out.println(TimeUnit.MICROSECONDS.toMillis(199999));
        threadPool.execute(() -> {
            String msg = "hello A";
            try {
                countDownLatch.await();
                exgr.exchange(msg,200,TimeUnit.MILLISECONDS);
                System.out.println("send A end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });


        threadPool.execute(() -> {
            String msg = "hello B";
            try {
                countDownLatch.await();
                exgr.exchange(msg,5,TimeUnit.NANOSECONDS);
                System.out.println("send B end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.countDown();
        threadPool.shutdown();*/
       /* HelloWSService service = new HelloWSService();
        String nihao = service.getHelloWSPort().sayHelloWorldFrom("nihao");
        System.out.println(nihao);
        long product = service.getHelloWSPort().getProduct(1L);
        System.out.println("product:"+product);
        Collections.sort(new ArrayList<ProductCountDO>(), (o1, o2) -> ComparisonChain.start().compare(product,product).result());
        Collections.sort(new ArrayList<ProductCountDO>(), Comparator.comparing(ProductCountDO::getId).thenComparing(ProductCountDO::getProductNum));*/
        ArrayList strList = new ArrayList<>();
        strList.add("qwe");
        strList.add("1");
        //strList.add('1');
        strList.add("asd");
        strList.add(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-123));
        System.out.println(Integer.toHexString(-123));
        System.out.println(Integer.toOctalString(-123));
        System.out.println(Integer.toUnsignedLong(-123));
        Ordering<String> compare = Ordering.natural().nullsFirst();
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        strList.sort(compare);
        for (Object o : strList) {
            System.out.println(o);
        }
    }
}

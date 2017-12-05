package example;

import com.bnq.publish.SpringTestPublisher;
import javax.annotation.Resource;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liqiang on 2017/11/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/application.xml"})
@Transactional
@Rollback
public class SpringPubEventTest {

    @Resource
    private SpringTestPublisher publisher;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/application.xml");
        SpringTestPublisher publisher = (SpringTestPublisher) context.getBean("springTestPublisher");
        publisher.processTestEvent("hello world");
    }

    @Test
    public void testPubEvent(){
        publisher.processTestEvent("hello world");
    }
}

package example;

import com.bnq.publish.SpringTestPublisher;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        SpringTestPublisher publisher = new SpringTestPublisher();
        publisher.processTestEvent("hello world");
    }
    @Test
    public void testPubEvent(){
        publisher.processTestEvent("hello world");
        publisher.processMyEvent("my notice");
    }
}

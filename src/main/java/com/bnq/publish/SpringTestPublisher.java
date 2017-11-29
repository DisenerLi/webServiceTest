package com.bnq.publish;

import com.bnq.event.SpringTestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by liqiang on 2017/11/28.
 */
@Component
public class SpringTestPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void processTestEvent(String msg){
        publisher.publishEvent(new SpringTestEvent(this, msg));
    }
}

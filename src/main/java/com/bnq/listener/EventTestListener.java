package com.bnq.listener;

import com.bnq.event.SpringTestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by liqiang on 2017/11/28.
 */
@Component
public class EventTestListener implements ApplicationListener {

    @EventListener
    @Async
    public Future<Boolean> processUpdateCaseBrowseEvent(SpringTestEvent springTestEvent) {
        if(springTestEvent == null) {
            return new AsyncResult<>(false);
        }
        System.out.println("msg:"+springTestEvent.getMsg());
        return new AsyncResult<>(true);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("eventType:"+event.getClass().toString());

        if(event instanceof SpringTestEvent){
            System.out.println("[onApplicationEvent]source:"+event.getSource());
            System.out.println("[onApplicationEvent]msg:"+((SpringTestEvent) event).getMsg());
        }
    }
}

package com.bnq.listener;

import com.bnq.event.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by liqiang on 2018/4/10.
 */
@Component
public class MyEventListener  {

    @EventListener
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyEvent happend msg:"+event.getMsg());
    }
}

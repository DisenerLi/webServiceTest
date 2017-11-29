package com.bnq.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by liqiang on 2017/11/29.
 */
public class SpringTestEvent extends ApplicationEvent {

    private String msg;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SpringTestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

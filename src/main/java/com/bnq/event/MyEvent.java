package com.bnq.event;

/**
 * Created by liqiang on 2018/4/10.
 */

public class MyEvent {

    private Object source;
    private String msg;

    public MyEvent(Object source, String msg) {
        this.source = source;
        this.msg = msg;
    }

    public Object getSource() {
        return source;
    }

    public String getMsg() {
        return msg;
    }

}

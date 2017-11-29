package com.bnq.webservice.test;

/**
 * Created by LJ0000276 on 2017/3/30.
 */
public class TestAface implements Aface {

    public static Aface aface = new TestAface();

    public void send() {
        noImpl();
        System.out.printf("1111");
    }

    public void noImpl(){
        System.out.println("noImpl");
    }

    public static void main(String[] args) {
        aface.send();
    }
}

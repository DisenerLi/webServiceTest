package com.reflect.bean;

/**
 * Created by liqiang on 2017/7/13.
 */
public class ClassBeanSon {

    public long date;

    public void sonMethod(){
        System.out.println("ClassBeanSon.sonMethod()  ");
    }

    public static class staInner{

        void staMethod(String par){
            System.out.println("staInner.staMethod() print param (" + par + ")");
        }
    }
}

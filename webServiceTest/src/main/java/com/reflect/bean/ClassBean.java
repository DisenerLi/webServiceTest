package com.reflect.bean;

import com.bnq.entity.User;

/**
 * Created by liqiang on 2017/7/12.
 */
public class ClassBean {

    private int count ;

    public String type;

    protected long time;

    public ClassBean(){
        this.count = 0;
        type = "ClassBean";
        time = System.currentTimeMillis();
    }

    public ClassBean(int count,String type){
        this.count = count;
        this.type = type;
    }

    public static class abs{
       void getType(){}
    }

    public static class abs2{
        void getType(){}
    }

    public class abs3{

    }

    public int getCount(){
        return count;
    }

    public void print(){
        System.out.println("print method.");
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String muchParam(User user,int count,String ster,byte b){
        return user.toString()+count+ster+b;
    }
}

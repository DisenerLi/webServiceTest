package com.reflect.test;

import com.bnq.entity.User;
import com.reflect.bean.AbsClass;
import com.reflect.bean.ClassBean;
import example.HelloWorld;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by liqiang on 2017/7/12.
 */
public class ClassTest {

    public static void main(String[] args) {
        ClassBean classBean = new ClassBean();
        Class clzss = classBean.getClass();
        ClassLoader loader = clzss.getClassLoader();
        ClassLoader loader2 ;
        ClassLoader loader3 ;
        try {
            Class hello = loader.loadClass("example.HelloWorld");
            loader2 = hello.getClassLoader();
            Class.forName("com.bnq.entity.User");
            HelloWorld helloWorld = new HelloWorld();
            loader3 = helloWorld.getClass().getClassLoader();
            System.out.println(loader.equals(loader2));
            System.out.println(loader.equals(loader3));
            System.out.println(loader.getParent().toString());
            System.out.println(loader.toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testClass(){
        Class intCls = int.class;
        Class booleanCls = boolean.class;
        Class intArrCls = new int[]{}.getClass();
        Class userArr = new User[]{}.getClass();
        Class pls = intCls.getClass();
        System.out.println("canonicalName:");
        System.out.println(intCls.getCanonicalName());
        System.out.println(pls.getCanonicalName());
        System.out.println(booleanCls.getCanonicalName());
        System.out.println(intArrCls.getCanonicalName());
        System.out.println(userArr.getCanonicalName());
        System.out.println("simpleName:");
        System.out.println(intCls.getSimpleName());
        System.out.println(pls.getSimpleName());
        System.out.println(booleanCls.getSimpleName());
        System.out.println(intArrCls.getSimpleName());
        System.out.println(userArr.getSimpleName());
    }

    @Test
    public void innerClass(){
        Class classbean = new ClassBean().getClass();
        /*
        Class clb = ClassBean.abs.class;
        Class clb2 = ClassBean.abs2.class;
        Class clb3 = ClassBean.abs3.class;
        Class clb4 = new ClassBean.abs2().getClass();
        Class clb5 = new ClassBean.abs2[][][]{}.getClass();
        */

        Method[] methods = classbean.getDeclaredMethods();
        Class[] inner = classbean.getClasses();
        for (Class aClass : inner) {
            System.out.println("innerClass:"+aClass.getSimpleName()+"  ");
        }
        for(Method method:methods){
            System.out.println(method.getName()+" :" +getMethodParam(method.getParameterTypes()));
        }
      /*  System.out.println(getName(clb));
        System.out.println(getName(clb2));
        System.out.println(getName(clb3));
        System.out.println(getName(clb4));
        System.out.println(getName(clb5));*/
    }

    private String getMethodParam(Class<?>[] parameterTypes) {
        StringBuilder sp = new StringBuilder();
        if(parameterTypes != null && parameterTypes.length>0){
            for (Class<?> type : parameterTypes) {
                sp.append(type.getSimpleName() + " ");
            }
        }
        return sp.toString();
    }

    public String getName(Class cla){
        return "name->>> "+cla.getName()+"  canonicalName->>> " +cla.getCanonicalName() +"  simpleName->>> "+cla.getSimpleName();
    }

    @Test
    public void classLoader() throws Exception{
        ClassBean clb = new ClassBean();
        Class cls = clb.getClass();
        Class pls = ClassBean.class;
        Class fls = Class.forName("com.reflect.bean.ClassBean");

        System.out.println("cls loader : " + cls.getClassLoader());
        System.out.println("pls loader : " + pls.getClassLoader());
        System.out.println("fls loader : " + fls.getClassLoader());
        System.out.println("system loader : " + cls.isAnonymousClass());
    }

    @Test
    public void getModifier(){
        AbsClass absClass = new AbsClass() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
        Class abs = ClassBean.class;
        Class getcls = absClass.getClass();
        Field[] fields = abs.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(" field:" + field.getName() + " type:" + field.getType().getSimpleName());
        }
        System.out.println("getModifiers : "+abs.getModifiers());
        System.out.println("isAnonymousClass : "+abs.isAnonymousClass());
        System.out.println("Modifiers : "+ Modifier.toString(abs.getModifiers()));
    }
}

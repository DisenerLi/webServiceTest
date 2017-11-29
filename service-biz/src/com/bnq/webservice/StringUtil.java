package com.bnq.webservice;

/**
 * Created by LJ0000276 on 2017/3/23.
 */
public class StringUtil {

    public static final String EMPTYSTR = "";
    public static final String[] STRARRAY = null;

    public boolean isEmpty(String s){
        return s != null && (EMPTYSTR.equals(s));

    }
    public boolean isNotEmpty(String s){
        return !isEmpty(s);

    }

    public boolean isNull(String s){
        return s == null;

    }

    public boolean isNotNull(String s){
        return !isNull(s);

    }
}

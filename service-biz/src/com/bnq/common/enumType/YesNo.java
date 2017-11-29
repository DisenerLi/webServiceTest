package com.bnq.common.enumType;

/**
 * Created by liqiang on 2017/11/6.
 */
public enum YesNo {

    Yes(1,"是","y"),
    No(0,"否","n");

    private Integer index;
    private String name;
    private String code;

    YesNo(Integer index,String name,String code){
        this.index = index;
        this.name = name;
        this.code = code;
    }

    public boolean equals(Integer code){
        return code != null && this.code.equals(code);
    }

    public static YesNo getStat(Integer code){
        for (YesNo yesNo : YesNo.values()) {
            if(yesNo.equals(code)){
                return yesNo;
            }
        }
        return null;
    }


    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

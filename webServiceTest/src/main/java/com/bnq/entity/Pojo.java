package com.bnq.entity;

import java.util.Date;

/**
 * 实体类
 */
public class Pojo {
    //温度
    private String detail;
    //日期
    private Date  date;
    //最高
    private Integer temperature_max;
    //最低
    private Integer temperature_min;
    //
    private Boolean bool ;

    private Byte aByte;

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getTemperature_max() {
        return temperature_max;
    }
    public void setTemperature_max(Integer temperature_max) {
        this.temperature_max = temperature_max;
    }
    public int getTemperature_min() {
        return temperature_min;
    }
    public void setTemperature_min(Integer temperature_min) {
        this.temperature_min = temperature_min;
    }


    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }
}
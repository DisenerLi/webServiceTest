package com.bnq.dao.query;

import java.util.Date;

/**
 * Created by liqiang on 2017/4/13.
 */
public class PojoQuery {
    private Long id;
    //地区
    private String location;
    //地区Id
    private Long cityId;
    //温度
    private String detail;
    //日期
    private Date tdate;
    //最高
    private Integer temperature_max;
    //最低
    private Integer temperature_min;
    //
    private Boolean isbool ;

    private Byte aByte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Integer getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(Integer temperature_max) {
        this.temperature_max = temperature_max;
    }

    public Integer getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(Integer temperature_min) {
        this.temperature_min = temperature_min;
    }

    public Boolean getIsbool() {
        return isbool;
    }

    public void setIsbool(Boolean isbool) {
        this.isbool = isbool;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }

    @Override
    public String toString() {
        return "PojoQuery{" +
            "id=" + id +
            ", location='" + location + '\'' +
            ", cityId=" + cityId +
            ", detail='" + detail + '\'' +
            ", tdate=" + tdate +
            ", temperature_max=" + temperature_max +
            ", temperature_min=" + temperature_min +
            ", isbool=" + isbool +
            ", aByte=" + aByte +
            '}';
    }

    public PojoQuery(Long id, String location, Long cityId, String detail, Date tdate, Integer temperature_max, Integer temperature_min, Boolean isbool, Byte aByte) {
        this.id = id;
        this.location = location;
        this.cityId = cityId;
        this.detail = detail;
        this.tdate = tdate;
        this.temperature_max = temperature_max;
        this.temperature_min = temperature_min;
        this.isbool = isbool;
        this.aByte = aByte;
    }

}

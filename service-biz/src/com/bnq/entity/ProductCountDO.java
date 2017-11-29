package com.bnq.entity;

import java.util.Date;

/**
 * Created by liqiang on 2017/11/6.
 */
public class ProductCountDO {

    //id
    private Long id;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //产品code
    private String productCode;
    //产品名称
    private String productName;
    //产品数量
    private Integer productNum;
    //已销售数量
    private Integer saleCount;
    //是否删除，1-是，0-否
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

}

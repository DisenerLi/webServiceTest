package com.bnq.ao;

import com.bnq.entity.ProductCountDO;
import common.AbstractTest;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.Map;

/**
 * Created by liqiang on 2017/11/6.
 */
public class ProductAOTest extends AbstractTest {

    @SpringBeanByType
    private ProductAO productAO;

    @Test
    public void createProduct() throws Exception {
        ProductCountDO productCountDO = new ProductCountDO();
        productCountDO.setProductCode("1001");
        productCountDO.setProductName("顶级产品");
        productCountDO.setProductNum(100);
        productCountDO.setSaleCount(0);
        Map<String,Object> resMap = productAO.createProduct(productCountDO);
    }

}
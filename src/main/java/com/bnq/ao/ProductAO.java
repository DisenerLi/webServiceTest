package com.bnq.ao;

import com.bnq.common.BaseAO;
import com.bnq.dao.ProductCountDao;
import com.bnq.entity.ProductCountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liqiang on 2017/11/6.
 */
@Service
public class ProductAO extends BaseAO {

    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    @Autowired
    private ProductCountDao productCountDao;

    public Map<String,Object> createProduct(ProductCountDO productCountDO){
        Map<String,Object> resMap = new HashMap<>();
        if(productCountDO == null){
            resMap.put(CODE,1);
            resMap.put(MESSAGE,"产品为空");
        }
       //Long id = productCountDao.insert(productCountDO);
        productCountDO = new ProductCountDO();
        productCountDO.setId(1L);
        productCountDO.setProductNum(2);
        productCountDao.update(productCountDO);
       //if(id != null){
       //    resMap.put(CODE,0);
       //    resMap.put(MESSAGE,"成功");
       //}

       return resMap;
    }
}

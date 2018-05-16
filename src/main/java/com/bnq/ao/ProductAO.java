package com.bnq.ao;

import com.bnq.common.BaseAO;
import com.bnq.dao.ProductCountDao;
import com.bnq.entity.ProductCountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liqiang on 2017/11/6.
 */
@Component
public class ProductAO extends BaseAO {

    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    public static int count = 12;

    @Autowired
    private ProductCountDao productCountDao;

    public Map<String,Object> createProduct(ProductCountDO productCountDO){
        Map<String,Object> resMap = new HashMap<>();
        if(productCountDO == null){
            resMap.put(CODE,1);
            resMap.put(MESSAGE,"产品为空");
        }
       Long id = productCountDao.insert(productCountDO);
       // productCountDO = new ProductCountDO();
       // productCountDO.setId(1L);
       // productCountDO.setProductNum(20);
       // productCountDao.insert(productCountDO);
       if(id != null){
           resMap.put(CODE,0);
           resMap.put(MESSAGE,"成功");
       }

       return resMap;
    }

    public long decrProduct(Long id){
        ProductCountDO productCountDO = productCountDao.queryById(id);
        productCountDO.setProductNum(productCountDO.getProductNum()-1);
        Integer oldCount = productCountDO.getSaleCount()==null?0 : productCountDO.getSaleCount();
        Integer newCount = oldCount + 1;
        productCountDO.setSaleCount(newCount);
        long update = productCountDao.updateWithSaleCount(productCountDO, oldCount);
        //System.out.println("update res:"+update);
        return update;
    }

    public ProductCountDO getProductCount(Long id){
        ProductCountDO productCountDO = productCountDao.queryById(id);
        if(productCountDO != null){
            return productCountDO;
        }
        return null;
    }

    /**
     * 减少数量
     * @return
     */
    public int decrCount(){
        count--;
        return count;
    }


}

package com.bnq.service.dubbo.impl;

import com.bnq.ao.ProductAO;
import com.bnq.entity.ProductCountDO;
import com.bnq.service.dubbo.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liqiang on 2018/5/16.
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductAO productAO;

    @Override
    public long getProductCount(Long id) {
        return productAO.decrProduct(id);
    }

    @Override
    public ProductCountDO getProductById(Long id) {
        return productAO.getProductCount(id);
    }
}

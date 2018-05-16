package com.bnq.service.dubbo;

import com.bnq.entity.ProductCountDO;

/**
 * Created by liqiang on 2018/5/16.
 */
public interface IProductService {

    long getProductCount(Long id);

    ProductCountDO getProductById(Long id);
}

package com.bnq.dao;

import com.bnq.dao.query.ProductCountQuery;
import com.bnq.entity.ProductCountDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liqiang on 2017/11/6.
 */
@Repository
public interface ProductCountDao {

    List<ProductCountDO> query(ProductCountQuery query);

    Long insert(ProductCountDO productCountDO);

    void update(ProductCountDO productCountDO);

    void deleteById(Long id);


}

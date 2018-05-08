package com.bnq.dao;

import com.bnq.dao.query.ProductCountQuery;
import com.bnq.entity.ProductCountDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liqiang on 2017/11/6.
 */
@Repository
public interface ProductCountDao {

    /**
     * query查询
     * @param query
     * @return
     */
    List<ProductCountDO> query(ProductCountQuery query);

    /**
     * 插入新的记录
     * @param productCountDO
     * @return
     */
    Long insert(ProductCountDO productCountDO);

    /**
     * 更新记录
     * @param productCountDO
     */
    void update(ProductCountDO productCountDO);

    /**
     * 根据id删除数量
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查询产品数量
     * @param id
     * @return
     */
    ProductCountDO queryById(Long id);

    /**
     * 更新记录根据销售量条件，乐观锁
     * @param productCountDO
     */
    long updateWithSaleCount(@Param("productCountDO") ProductCountDO productCountDO, @Param("count")Integer count);

}

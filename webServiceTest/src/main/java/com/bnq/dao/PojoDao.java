package com.bnq.dao;

import com.bnq.ao.PojoQuery;
import com.bnq.entity.Pojo;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;

/**
 * Created by liqiang on 2017/4/13.
 */
public interface PojoDao {

    Pojo getPojoById(Long id) throws Exception;

    List<T> queryAll() throws Exception;

    Pojo queryPojoByLoc(String loc) throws Exception;

    Pojo queryByCityID(Long id) throws Exception;

    void insert(Pojo pojo) throws Exception;

    void deleteById(Long id) throws Exception;

    void updatePojo(Pojo pojo) throws Exception;

    void queryByParam(PojoQuery query) throws Exception;
}

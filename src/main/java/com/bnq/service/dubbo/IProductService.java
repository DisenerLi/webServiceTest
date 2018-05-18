package com.bnq.service.dubbo;

import com.bnq.entity.ProductCountDO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by liqiang on 2018/5/16.
 */
@Path("product")
public interface IProductService {

    long getProductCount(Long id);

    @GET
    @Path("{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    ProductCountDO getProductById(@PathParam("id") Long id);
}

package com.bnq.webmvc;

import com.bnq.ao.ProductAO;
import com.bnq.entity.ProductCountDO;
import com.bnq.service.dubbo.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liqiang on 2018/5/18.
 */
@Controller
@RequestMapping("product")
public class ProductController  {

	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	@Autowired
	private ProductAO productAO;

	@RequestMapping("/{id}")
	@ResponseBody
	public ProductCountDO getProductById(@PathVariable("id")Long id){
		return productService.getProductById(id);
	}

}

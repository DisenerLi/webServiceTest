package com.bnq.service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.io.IOException;

/**
 * Created by liqiang on 2018/5/18.
 */
public class DubboConsumerGenericTest {

	public static void main(String[] args) throws IOException {
		//应用注册配置
		ApplicationConfig config = new ApplicationConfig();
		config.setName("dubboConsumer2");

		//	注册中心配置
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("10.184.120.23:2181");
		registryConfig.setProtocol("zookeeper");
		//registryConfig.setGroup();
		//服务接口配置
		ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(config);
		referenceConfig.setRegistry(registryConfig);
		referenceConfig.setVersion("1.0.1");
		referenceConfig.setGroup("dubbo");
		referenceConfig.setInterface("com.bnq.service.dubbo.IProductService");
		referenceConfig.setGeneric(true);

		GenericService genericService = referenceConfig.get();
		Object getProductById = genericService.$invoke("getProductById", new String[]{"java.lang.Long"},new Object[]{22});
		System.out.println(JSON.json(getProductById));
	}
}

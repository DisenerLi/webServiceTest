package com.bnq.webservice;

import com.bnq.entity.User;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by liqiang on 2017/6/7.
 */
@WebService(endpointInterface="com.bnq.webservice.TestDaoService",
            serviceName="testService",
            portName="testServicePort",
            name = "testService",
            targetNamespace = "http://ws.bnq.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TestDaoService {


    /**
     * 获取用户
     * @return
     */
    @WebMethod
    public User getUser(@WebParam(name = "userid",partName = "userid") String userid){
        return new User();
    }
}

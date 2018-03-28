
package com.bnq.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HelloWSService", targetNamespace = "http://www.tmp.web/ws/hello", wsdlLocation = "http://127.0.0.1:8080/webService/services/HelloWorld?wsdl")
public class HelloWSService
    extends Service
{

    private final static URL HELLOWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELLOWSSERVICE_EXCEPTION;
    private final static QName HELLOWSSERVICE_QNAME = new QName("http://www.tmp.web/ws/hello", "HelloWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8080/webService/services/HelloWorld?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLOWSSERVICE_WSDL_LOCATION = url;
        HELLOWSSERVICE_EXCEPTION = e;
    }

    public HelloWSService() {
        super(__getWsdlLocation(), HELLOWSSERVICE_QNAME);
    }

    public HelloWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOWSSERVICE_QNAME, features);
    }

    public HelloWSService(URL wsdlLocation) {
        super(wsdlLocation, HELLOWSSERVICE_QNAME);
    }

    public HelloWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOWSSERVICE_QNAME, features);
    }

    public HelloWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWSPort")
    public HelloWorld getHelloWSPort() {
        return super.getPort(new QName("http://www.tmp.web/ws/hello", "HelloWSPort"), HelloWorld.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWSPort")
    public HelloWorld getHelloWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.tmp.web/ws/hello", "HelloWSPort"), HelloWorld.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOWSSERVICE_EXCEPTION!= null) {
            throw HELLOWSSERVICE_EXCEPTION;
        }
        return HELLOWSSERVICE_WSDL_LOCATION;
    }

}

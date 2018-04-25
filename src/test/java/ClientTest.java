import com.bnq.webservice.client.HelloWorld;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liqiang on 2017/4/6.
 */
public class ClientTest {

    public static Logger logger = LoggerFactory.getLogger(ClientTest.class);

    public static void main(String[] args) throws Exception {
        URL wsdlURL = new URL("http://localhost:8080/webService/services/HelloWorld?wsdl");
        QName serviceName = new QName("http://www.tmp.web/ws/hello","HelloWSService");
        Service service = Service.create(wsdlURL,serviceName);
        HelloWorld helloWorld = service.getPort(HelloWorld.class);
        String res = helloWorld.sayHelloWorldFrom(" jack");
        logger.info("response = [" + res + "]");
        JaxWsPortProxyFactoryBean clientWebService = clientWebService();
        Object object = clientWebService.getObject();
        //other method
        //HelloWorld helloWorld1 = HelloWorldService.create();


        /*System.out.println("args = [" + emtList.get(0) + "]") ;*/
   /*     if(p.getaByte() >0 ){

        }*/
        /*for(Integer q:emtList){
            System.out.println("q = [" + q + "]");
        }
        int a = 20 - p.getTemperature_max();
        if(1 > p.getTemperature_max()){
            System.out.println("args = [" + p.getTemperature_max() + "]");
        }*/
    }

    public static JaxWsPortProxyFactoryBean clientWebService(){
        JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
        try {
            proxy.setWsdlDocumentUrl(new URL("http://localhost:8080/webService/services/HelloWorld?wsdl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        proxy.setServiceName("HelloWSService");
        //proxy.setPortName("SpitterServiceEndpointPort");
        proxy.setNamespaceUri("http://www.tmp.web/ws/hello");
        proxy.setServiceInterface(HelloWorld.class);
        return proxy;
    }
}

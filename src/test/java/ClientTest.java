import com.bnq.entity.Pojo;
import com.bnq.webservice.HelloWorld;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 2017/4/6.
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
        URL wsdlURL = new URL("http://localhost:8080/services/HelloWorld?wsdl");
        QName serviceName = new QName("http://www.tmp.web/ws/hello","HelloWSService");
        Service service = Service.create(wsdlURL,serviceName);
        HelloWorld helloWorld = service.getPort(HelloWorld.class);
        String res = helloWorld.sayHelloWorldFrom(" jack");
        System.out.println("response = [" + res + "]");
        //other method
        //HelloWorld helloWorld1 = HelloWorldService.create();

        Pojo p = new Pojo();
        List<Integer> emtList = new ArrayList<Integer>();
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
}

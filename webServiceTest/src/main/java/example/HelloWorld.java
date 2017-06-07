package example;

import com.bnq.dao.PojoDao;
import com.bnq.entity.Pojo;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqiang on 2017/3/23.
 */
@WebService(
    endpointInterface = "example.HelloWorld",
    portName = "HelloWSPort",
    serviceName = "HelloWSService",
    targetNamespace = "http://www.tmp.web/ws/hello")
public class HelloWorld {

  private final static String config = "config/log4j.xml";
  protected static Logger logger = LoggerFactory.getLogger("webservice_log");

  private PojoDao pojoDao;
    static void loadFile() {
      URL fileurl = HelloWorld.class.getClassLoader().getResource(config);
      /*URI uri = fileurl.toURI();
      System.out.println("uri.getRawPath() : " +uri.getRawPath());
      System.out.println("uri.getScheme() : " +uri.getScheme());
      System.out.println("uri.getSchemeSpecificPart() : " +uri.getSchemeSpecificPart());
      System.out.println("uri.getHost() : " +uri.getHost());
      System.out.println("uri.getPath() : " +uri.getPath());
      System.out.println("URL.getPath() : " +fileurl.getPath());
      System.out.println("URL.getContent() : " +fileurl.getContent());
      System.out.println("URL.getFile() : " +fileurl.getFile());*/

        File configFile = new File(fileurl.getFile());
        InputStream stream = HelloWorld.class.getClassLoader().getResourceAsStream(config);
        if(configFile.exists()){
            System.out.println("load file config................");
            //PropertyConfigurator.configure(config);
            DOMConfigurator.configureAndWatch(configFile.getAbsolutePath(),10000);
        }else if(stream != null){
            System.out.println("load inputStream config................");
            PropertyConfigurator.configure(stream);
        }else{
            System.out.println("load default config................");
            //BasicConfigurator.configure();
            System.exit(0);
        }
    }

  public HelloWorld(){
      //BasicConfigurator.configure();
  }


  @WebMethod
  public  @WebResult(name="result")String sayHelloWorldFrom(String id) {

    logger.debug("get service interface param from : [{}]" ,id);
      Pojo pojo = null;
      try {
          pojo = pojoDao.getPojoById(Long.valueOf(id));
          if(pojo != null){
              logger.info(pojo.toString());
          }
    }catch (Exception e){
        logger.error("查询出错",e);
    }
    return pojo.toString();
  }

  public static void main(String[] argv) throws Exception{
    loadFile();
    Object implementor = new HelloWorld ();
    String address = "http://localhost:80/HelloWorld";
    Endpoint endpoint = Endpoint.publish(address, implementor);
    if(endpoint.isPublished()){
        List<Source> metadata= endpoint.getMetadata();
        if(metadata != null && metadata.size()>0) {
            for (Source source : metadata) {
                logger.info("source.getSystemId = [" + source.getSystemId() + "]");
            }
        }
    }
  }

    public PojoDao getPojoDao() {
        return pojoDao;
    }

    public void setPojoDao(PojoDao pojoDao) {
        this.pojoDao = pojoDao;
    }
}

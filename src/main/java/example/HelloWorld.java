package example;

import com.bnq.ao.ProductAO;
import com.bnq.entity.ProductCountDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by liqiang on 2017/3/23.
 */

@Component
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService(
    endpointInterface = "example.HelloWorld",
    portName = "HelloWSPort",
    serviceName = "HelloWSService",
    targetNamespace = "http://www.tmp.web/ws/hello")
public class HelloWorld extends SpringBeanAutowiringSupport {

  private final static String config = "config/log4j.xml";
  protected static Logger logger = LoggerFactory.getLogger("webservice_log");

  //private PojoDao pojoDao;
    @Resource
    private ProductAO productAO;

    //@Resource
    //private WebServiceContext wsc;
    public static void loadFile() {
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

    logger.info("get service interface param from : [{}]" ,id);
      String Json = "";
      //MessageContext ctx = wsc.getMessageContext();
      //HttpServletRequest request = (HttpServletRequest)ctx.get(SOAPMessageContext.SERVLET_REQUEST);
      //logger.debug("----------------------------------------------------------------------");
      //logger.debug("ServletContextName=" + request.getSession().getServletContext().getServletContextName());
      //logger.debug("ContextPath=" + request.getSession().getServletContext().getContextPath());
      //logger.debug("RealPath=" + request.getSession().getServletContext().getRealPath("/"));
      //logger.debug("ServerInfo=" + request.getSession().getServletContext().getServerInfo());
      //logger.debug("----------------------------------------------------------------------");
      try {
          //pojo = pojoDao.getPojoById(Long.valueOf(id));
          ProductCountDO productCountDO = new ProductCountDO();
          productCountDO.setProductName(id);
          productCountDO.setProductNum(10);
          //if(productAO == null){
          //    productAO = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ProductAO.class);
          //}
          Map<String,Object> resMap = productAO.createProduct(productCountDO);
          ObjectMapper mapper = new ObjectMapper();
          // Convert object to JSON string
          Json =  mapper.writeValueAsString(resMap);
          if(resMap != null){
              logger.info("res:"+ Json);
          }
    }catch (Exception e){
        logger.error("查询出错",e);
    }
    return "hello "+ Json;
  }

  @WebMethod
  public @WebResult(name="result")long getProduct(long id){
      long l = productAO.decrProduct(id);
      return l;
  }

  public static void main(String[] argv) throws Exception{
    loadFile();
    Object implementor = new HelloWorld ();
    String address = "http://localhost:8080/HelloWorld";
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

/*    public PojoDao getPojoDao() {
        return pojoDao;
    }

    public void setPojoDao(PojoDao pojoDao) {
        this.pojoDao = pojoDao;
    }*/
}

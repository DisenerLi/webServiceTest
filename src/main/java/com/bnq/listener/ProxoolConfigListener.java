package com.bnq.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
import org.logicalcobwebs.proxool.configuration.ServletConfigurator;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by liqiang on 2017/4/14.
 */
public class ProxoolConfigListener implements ServletContextListener {

    private static final Log LOG = LogFactory.getLog(ServletConfigurator.class);
    private static final String XML_FILE_PROPERTY = "xmlFile";
    private static final String PROPERTY_FILE_PROPERTY = "propertyFile";
    private static final String AUTO_SHUTDOWN_PROPERTY = "autoShutdown";
    private static final String JDBC_PROPERTY = "jdbc";
    private boolean autoShutdown = true;

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String appDir = sce.getServletContext().getRealPath("/");

        Properties properties = new Properties();
        Enumeration names = servletContext.getInitParameterNames();

        while(names.hasMoreElements()) {
            String name = (String)names.nextElement();
            String value = servletContext.getInitParameter(name);
            File file;
            if(XML_FILE_PROPERTY.equals(name)) {
                try {
                    file = new File(value);
                    if(file.isAbsolute()) {
                        JAXPConfigurator.configure(value, false);
                    } else {
                        JAXPConfigurator.configure(appDir + File.separator + value, false);
                    }
                } catch (ProxoolException var9) {
                    LOG.error("Problem configuring " + value, var9);
                }
            } else if(PROPERTY_FILE_PROPERTY.equals(name)) {
                try {
                    file = new File(value);
                    if(file.isAbsolute()) {
                        PropertyConfigurator.configure(value);
                    } else {
                        PropertyConfigurator.configure(appDir + File.separator + value);
                    }
                } catch (ProxoolException var10) {
                    LOG.error("Problem configuring " + value, var10);
                }
            } else if(AUTO_SHUTDOWN_PROPERTY.equals(name)) {
                this.autoShutdown = Boolean.valueOf(value).booleanValue();
            } else if(name.startsWith(JDBC_PROPERTY)) {
                properties.setProperty(name, value);
            }
        }

        if(properties.size() > 0) {
            try {
                PropertyConfigurator.configure(properties);
            } catch (ProxoolException var8) {
                LOG.error("Problem configuring using init properties", var8);
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        if(this.autoShutdown) {
            ProxoolFacade.shutdown(0);
        }
    }
}

package com.bnq.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;

/**
 * Created by liqiang on 2018/3/30.
 */
public class MailConfig {
    //邮箱服务器域名
    private String host;
    //协议(SMTP,POP,IMAP,EXCHANGE,MIME)
    private String protocol;
    //
    private String port;
    //是否需要认证
    private boolean isAuth;
    //超时时间
    private long timeout;
    //域名，认证使用
    private String domain;
    //邮箱或者用户账号
    private String userName;
    //验证密码
    private String password;

    public MailConfig(String host, String protocol, String port, boolean isAuth, long timeout, String domain, String userName, String password) {
        this.host = host;
        this.protocol = protocol;
        this.port = port;
        this.isAuth = isAuth;
        this.timeout = timeout;
        this.domain = domain;
        this.userName = userName;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getConfigProps(){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", getProtocol());
        props.setProperty("mail.smtp.host", getHost());
        props.setProperty("mail.smtp.port", getPort());
        // 指定验证为true
        props.setProperty("mail.smtp.auth", String.valueOf(isAuth()));
        props.setProperty("mail.smtp.timeout", String.valueOf(getTimeout()));
        props.setProperty("mail.smtp.auth.ntlm.domain", getDomain());
        return props;
    }

    public Authenticator getAuthenticator(){
        return new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(getUserName(), getPassword());//要使用工号，不能使用邮箱名认证
            }
        };
    }
}

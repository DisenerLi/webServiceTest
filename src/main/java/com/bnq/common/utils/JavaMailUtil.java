package com.bnq.common.utils;

import com.bnq.common.MailConfig;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

/**
 * Created by liqiang on 2018/3/30.
 */
public class JavaMailUtil {

    private JavaMailUtil() {
    }

    public void SpringMailSender(){

    }

    public static Session getSession(MailConfig config){
        return Session.getInstance(config.getConfigProps(), config.getAuthenticator());
    }

    public static void main(String[] args) throws Exception {
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", "mail.b-and-qchina.com");
        props.setProperty("mail.smtp.port", "25");
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout", "30000");
        props.setProperty("mail.smtp.auth.ntlm.domain", "mail.b-and-qchina.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", "25");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("LJ0000276", "1234@abc");//要使用工号，不能使用邮箱名认证
            }
        };
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        // 设置发送者
        message.setFrom(new InternetAddress("qiang.li@b-and-qchina.com"));
        // 设置发送方式与接收者
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("qiang.li@b-and-qchina.com"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("lqwk_ml@163.com"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("614873936@qq.com"));
        // 设置主题
        message.setSubject("bnq邮件发送测试");
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        // Fill the message
        messageBodyPart.setText("内容有中文--饕餮---");
        multipart.addBodyPart(messageBodyPart);
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
         /*DataSource source = new FileDataSource("D:\\中台APP\\业主APP\\一期\\APP一期问题跟踪表V1.0.xlsx");
        messageBodyPart.setDataHandler(new DataHandler(source));
        String filename = MimeUtility.encodeText(source.getName(),"UTF-8",null);
        filename = filename.replaceAll("\r", "").replaceAll("\n", "");
        System.out.println(filename);
        messageBodyPart.setFileName(filename);*/
        ((MimeBodyPart) messageBodyPart).attachFile("D:\\中台APP\\业主APP\\一期\\APP一期问题跟踪表V1.0.xlsx","application/octet-stream",null);
        multipart.addBodyPart(messageBodyPart);
        // 设置内容
        message.setContent(multipart);
        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }

    public static void openConnectSend(Session session) throws Exception {
        Transport transport = session.getTransport();
        transport.connect();
        transport.close();
    }
}

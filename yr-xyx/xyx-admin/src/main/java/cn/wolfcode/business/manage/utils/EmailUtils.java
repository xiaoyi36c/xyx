package cn.wolfcode.business.manage.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: yyy
 * Date: 2023/6/12
 * Time: 10:26
 * Description:
 */
@Slf4j
@Component
public class EmailUtils {

    private static String myEmailAccount;

    private static String myEmailPassword;

    private static String smtp;

    /**
     * 发送推送邮件
     * */
    public static void senPushEmail(Map emailContent, Map org) {
        try {
            Session session = getSession();
            MimeMessage message = EmailUtils.createMimeMessage(session, (String) org.get("orgEmails"), (String) emailContent.get("article_url"),"信易学数据推送");
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            log.info("发送邮件失败：{}\n", e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * 发送账户通知邮件
     * */
    public static void senAccountEmail(String sendTo,String content,String subject) {
        try {
            Session session = getSession();
            MimeMessage message = EmailUtils.createMimeMessage(session,sendTo,content,subject);
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            log.info("发送邮件失败：{}\n", e.getMessage());
            e.printStackTrace();
        }
    }




    public static MimeMessage createMimeMessage(Session session, String receiveMailAccount, String message,String subject) {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(myEmailAccount, "信易学", "UTF-8"));

            String[] split = receiveMailAccount.split(",");
            for(int i = 0; i < split.length; i++){
                mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(split[i]));
            }
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setContent(message, "text/html;charset=UTF-8");
            mimeMessage.setSentDate(new Date());
            mimeMessage.saveChanges();

            return mimeMessage;
        } catch (Exception e) {
            log.info("创建邮件失败：{}\n",e.getMessage());
        }
        return null;
    }

    public static Session getSession(){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", smtp);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        return session;
    }


    @Value("${othermail.username}")
    public  void setPassword(String username) {
        myEmailAccount = username;
    }
    @Value("${othermail.password}")
    public  void setUrl(String password) {
        myEmailPassword = password;
    }
    @Value("${othermail.smtpmsg}")
    public  void setSmtp(String smtpmsg) {
        smtp = smtpmsg;
    }
}

package generic;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailUtility {
	
private static final String SMTP_HOST = "smtp.gmail.com";
private static final String SMTP_PORT = "465";
private static final String EMAIL_USERNAME = "pratiksha.damodar@legitquest.com";
private static final String EMAIL_PASSWORD = "qxfr qcts ycwu eokj";

public static void sendEmail(String recipient, String subject, String body, String attachmentPath) {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", SMTP_HOST);
    properties.put("mail.smtp.port", SMTP_PORT);
    properties.put("mail.smtp.ssl.enable", "true");
	properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Enable SSL
    properties.put("mail.smtp.socketFactory.fallback", "false");
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));
            multipart.addBodyPart(attachmentPart);
        }

        message.setContent(multipart);
        Transport.send(message);

        System.out.println("Email sent successfully.");
    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
}
}



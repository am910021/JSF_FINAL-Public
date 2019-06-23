/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author yuri
 */
public class Smtp {

    private static final Logger LOG = Logger.getLogger(Smtp.class.getName());

    public static boolean send(String address, String subject, String text) {

        final String sUsername = "example@example.com";
        final String sPassword = "password";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        try {
            Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sUsername, sPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("example@example.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(address)
            );
            message.setSubject(subject);

            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return true;
    }
}

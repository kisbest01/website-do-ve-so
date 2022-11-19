/**
 * 
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author KISS
 *
 */
public class EmailUtility {
	public void sendMail(String host, String port, String senderMail, String senderName, String password, String recipientMail, String subject, String messenger) throws UnsupportedEncodingException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.post", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		// creates a new session with an authenticator
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, password);
			}
		};
		
		Session session = Session.getInstance(properties, authenticator);
		
		 // creates a new e-mail message
		Message message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(senderMail, senderName));
		InternetAddress[] toAddresses = {new InternetAddress(recipientMail)};
		message.setRecipients(Message.RecipientType.TO, toAddresses);
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setContent(messenger, "text/html;charset=UTF-8;");
		
		// sends the e-mail
		Transport.send(message);
	}
}

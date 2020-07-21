package com.umc;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.sun.mail.smtp.SMTPTransport;
import com.umc.dao.SendEmail;

import java.util.Date;
import java.util.Properties;

@Component
public class SendEmailImpl implements SendEmail {
	public void send(String to, String from, String subj, String message, String pwd) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com"); // optional, defined in SMTPTransport
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.port", "587"); // default port 25

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

			// from
			msg.setFrom(new InternetAddress(from));

			// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			// subject
			msg.setSubject(subj);

			// content
			msg.setText(message);

			msg.setSentDate(new Date());

			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect("smtp.gmail.com", from, pwd);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

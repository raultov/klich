package net.softforlife.klich.ws.service.impl;

import net.softforlife.klich.enumeration.NOTIFICATION_TYPE;
import net.softforlife.klich.ws.service.WSEmailSender;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.*;
import javax.mail.internet.*;
import net.softforlife.klich.JSF.web.util.*;

import org.springframework.security.util.UrlUtils;
public class WSEmailSenderImpl implements WSEmailSender {
	
	private String propertiesFile;
	private ResourceBundle myResources;
	
	public void init() {
		Locale lCastellano = new Locale("ES","es");
		myResources = ResourceBundle.getBundle(propertiesFile, lCastellano);
	}

	@Override
	public boolean send(String [] to, String subject, String body) {

	    Properties props = System.getProperties();
	    //props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.starttls.enable", myResources.getString("mail.smtp.starttls.enable"));
	    props.put("mail.smtp.host", myResources.getString("mail.smtp.host"));
	    props.put("mail.smtp.user", myResources.getString("mail.smtp.user"));
	    props.put("mail.smtp.password", myResources.getString("mail.smtp.password"));
	    props.put("mail.smtp.port", myResources.getString("mail.smtp.port"));
	    props.put("mail.smtp.auth", myResources.getString("mail.smtp.auth"));

	    //String[] to = {"to@gmail.com"}; // added this line

	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i=0; i < to.length; i++ ) { // changed from a while loop
				toAddress[i] = new InternetAddress(to[i]);
			}
			
			System.out.println(Message.RecipientType.TO);

			for( int i=0; i < toAddress.length; i++) { // changed from a while loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			
			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
	    
	    } catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	    
	    return true;
	}
	
	@Override
	public boolean sendResetPasswordEmail(String [] to, String userId, String code) {
		
		String subject = myResources.getString("mail.remember_pass.subject");
		String body = myResources.getString("mail.remember_pass.body");
		String path = myResources.getString("mail.remember_pass.path");
		String url = URLUtils.getServerURL();
		body = body.replaceFirst("\\?", to[0]);
		
		String link = path + "notifications/notification.jsf?user=" + userId + "&code=" + code + "&type=" + NOTIFICATION_TYPE.RESET_PASS.getId(); 
		
		body = body.replaceFirst("\\?", link);
		
		return send(to, subject, body);
	}


	public String getPropertiesFile() {
		return propertiesFile;
	}

	public void setPropertiesFile(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}
}

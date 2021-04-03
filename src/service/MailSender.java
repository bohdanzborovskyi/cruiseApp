package service;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailSender
{
	final static String from = "baga142536@gmail.com";		
	public static String text = "We ale glad to see you in our cruise firm. Thank you for your choice and registrtation in our service. Have a nice day!";
	static Logger log = Logger.getLogger(MailSender.class);
	final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public MailSender() 
	{
		
	}
	
	public static void sendMessage(String email,String text) 
	{		
		try 
		{
			Properties props = System.getProperties();
		    props.setProperty("mail.smtp.host", "smtp.gmail.com");
		    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		    props.setProperty("mail.smtp.socketFactory.fallback", "false");
		    props.setProperty("mail.smtp.port", "465");
		    props.setProperty("mail.smtp.user", "baga142536@gmail.com");		   
		    props.setProperty("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "true");
		    props.put("mail.store.protocol", "pop3");
		    props.put("mail.transport.protocol", "smtp");			
		    Session session = Session.getInstance(props,
		            new javax.mail.Authenticator() {
		                                @Override
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication("baga142536@gmail.com","142536BaGa1994");
		                }
		            });		
		    session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(RecipientType.TO, new InternetAddress(email));
			message.setSubject("Welcome to Outlander company");
		    message.setText(text);
		    Transport t = session.getTransport("smtp");
		    t.connect("baga142536@gmail.com","142536BaGa1994");
		    t.sendMessage(message, message.getAllRecipients());
		    t.close();
		}catch(MessagingException exc) 
		{
			log.info("Problem with sending email" + exc + " " + email);
		}
	}
	
	public static String getBuyMessage(String userName) 
	{
		return "Dear " + userName + " your cruise was buy!Thank you for purchase.";
	}
	
	public static String getAbortMessage(String userName) 
	{
		return "Dear " + userName + " your cruise was abort!Thank you for purchase.";
	}
	

}

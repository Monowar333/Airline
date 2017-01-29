package ua.nure.korkh.SummaryTask4.Mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.Roles;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.MailSendException;

/**
 * Send email
 * 
 * @author Korkh
 * 
 */
public class SendMail {

	private static final Logger LOG = Logger.getLogger(SendMail.class);
	private Properties props;

	public SendMail() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	public void sendMailFlight(File file, User user) throws MailSendException {
		LOG.debug("mail sent brigade start");
		Session session = Session.getDefaultInstance(
				props,
				new GMailAuthenticator(CredentialsBundle
						.resolveCredentials("mail.username"), CredentialsBundle
						.resolveCredentials("mail.password")));

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("monowar333@gmail.com"));

			msg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			LOG.debug("Send massege to " + user.getEmail());
			BodyPart messageBodyPart = new MimeBodyPart();
			msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
			msg.setSubject("Flight");
			messageBodyPart
					.setContent(
							"<!DOCTYPE html>"
									+ "\n\n<html>"
									+ "\n\n<head>"
									+ "\n\n<h3> Dear "
									+ user.getName()
									+ " "
									+ user.getSuname()
									+ ",</h3>"
									+ "\n\n</head>"
									+ "\n\n<body>"
									+ "\n\n<h3>Your took part in new Flight for more detailed information please watch file.</h3>"
									+ "\n\n</body>" + "\n\n</html>",
							"text/html;charset=UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(MimeUtility.encodeText(source
					.getName()));
			multipart.addBodyPart(attachmentBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (MessagingException e) {
			LOG.error("Mail not sendt", e);
			throw new MailSendException("Mail not send");
		} catch (UnsupportedEncodingException e) {
			LOG.error("fail sent", e);
		}
		LOG.debug("mail sent finished");
	}

	public void sendMailClaim(User user, String emailSender) throws MailSendException {
		LOG.debug("mail sent claim start");
		Session session = Session.getDefaultInstance(
				props,
				new GMailAuthenticator(CredentialsBundle
						.resolveCredentials("mail.username"), CredentialsBundle
						.resolveCredentials("mail.password")));

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailSender));
			msg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			LOG.debug("Send massege to " + user.getEmail());
			BodyPart messageBodyPart = new MimeBodyPart();
			msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
			msg.setSubject("Claim");
			if ((Roles.getRole(user).getName()).equals(Roles.ADMINISTRATOR.getName())) {
				messageBodyPart
						.setContent(
								"<!DOCTYPE html>"
										+ "\n\n<html>"
										+ "\n\n<head>"
										+ "\n\n<h3> Dear "
										+ user.getName()
										+ " "
										+ user.getSuname()
										+ ",</h3>"
										+ "\n\n</head>"
										+ "\n\n<body>"
										+ "\n\n<h3>You have new claim, for more detailed information please watch your cabinet.</h3>"
										+ "\n\n</body>" + "\n\n</html>",
								"text/html;charset=UTF-8");
			} else {
				messageBodyPart
						.setContent(
								"<!DOCTYPE html>"
										+ "\n\n<html>"
										+ "\n\n<head>"
										+ "\n\n<h3> Dear "
										+ user.getName()
										+ " "
										+ user.getSuname()
										+ ",</h3>"
										+ "\n\n</head>"
										+ "\n\n<body>"
										+ "\n\n<h3>Your claim changed status, for more detailed information please watch your cabinet.</h3>"
										+ "\n\n</body>" + "\n\n</html>",
								"text/html;charset=UTF-8");
			}
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (MessagingException e) {
			LOG.error("Mail not sendt", e);
			throw new MailSendException("Mail not send");
		}
		LOG.debug("mail sent finished");
	}

	public void sendMailRegistr(User user, String emailSender) throws MailSendException {
		LOG.debug("mail sent new user");
		Session session = Session.getDefaultInstance(
				props,
				new GMailAuthenticator(CredentialsBundle
						.resolveCredentials("mail.username"), CredentialsBundle
						.resolveCredentials("mail.password")));

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailSender));
			msg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			LOG.debug("Send massege to " + user.getEmail());
			BodyPart messageBodyPart = new MimeBodyPart();
			msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
			msg.setSubject("Register");
				messageBodyPart
						.setContent(
								"<!DOCTYPE html>"
										+ "\n\n<html>"
										+ "\n\n<head>"
										+ "\n\n<h3> Dear "
										+ user.getName()
										+ " "
										+ user.getSuname()
										+ ",</h3>"
										+ "\n\n</head>"
										+ "\n\n<body>"
										+ "\n\n<h3>To access the personal cabinet enter:</h3>"
										+ "\n\n<h3>login: " + user.getLogin() +"</h3>"
										+ "\n\n<h3>password: " + user.getPassword() +"</h3>"
										+ "\n\n<h3>To activate the account click on the link " +
										"<a href=\"http://localhost:8080/SummaryTask4/controller?command=activeaccount&link=" + 
										user.getLinckaccept() +"\">"+ user.getLinckaccept() +"</a></h3>"
										+ "\n\n</body>" + "\n\n</html>",
								"text/html;charset=UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (MessagingException e) {
			LOG.error("Mail not sendt", e);
			throw new MailSendException("Mail not send");
		}
		LOG.debug("mail sent finished");
		
	}
	
	static class GMailAuthenticator extends Authenticator {
		String user;
		String pw;

		public GMailAuthenticator(String username, String password) {
			super();
			this.user = username;
			this.pw = password;
			LOG.debug("username " + this.user + " password " + this.pw);
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, pw);
		}
	}


}

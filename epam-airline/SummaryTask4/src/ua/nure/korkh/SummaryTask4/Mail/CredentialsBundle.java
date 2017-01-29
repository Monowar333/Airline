package ua.nure.korkh.SummaryTask4.Mail;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class CredentialsBundle {

	private static final Logger LOG = Logger.getLogger(CredentialsBundle.class);
	static ResourceBundle rb;
	static {
		try {
			rb = ResourceBundle.getBundle("resources");
		} catch (Exception e) {
			LOG.error("fail resourse bundle", e);
		}
	}

	public static String resolveCredentials(String label) {
		return rb.getString(label);
	}
}

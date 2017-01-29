package ua.nure.korkh.SummaryTask4.exception;


public class MailSendException extends AppException {

	private static final long serialVersionUID = 1L;

	public MailSendException() {
		super();
	}

	public MailSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailSendException(String string) {
		super(string);
	}

}

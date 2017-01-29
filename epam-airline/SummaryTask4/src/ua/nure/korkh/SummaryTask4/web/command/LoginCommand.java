package ua.nure.korkh.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.Roles;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.functional.MD5Util;
import ua.nure.korkh.SummaryTask4.functional.VerifyCaptch;

/**
 * Login command.
 * 
 * @author Korkh
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.debug("Command starts");
		boolean valid = true;
		HttpSession session = request.getSession();

		UserDAO manager = factorty.getUserDAO();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		User user = manager.findByLogin(login);
		LOG.trace("Found in DB: user --> " + user);
		if (user == null
				|| !(MD5Util.md5Custom(password)).equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}
		
		if (user.getRegistrstatus() == false) {
			throw new AppException("Active your account");
		}
	     if (valid) {	 
	         String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	         LOG.debug("gRecaptchaResponse=" + gRecaptchaResponse);	 
	         // Verify CAPTCHA.
	         valid = VerifyCaptch.verify(gRecaptchaResponse);
	         if (!valid) {
	        	 throw new AppException("Captcha invalid!");
	         }
	     }
	     
		Roles userRole = Roles.getRole(user);
		LOG.trace("userRole --> " + userRole);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Roles.ADMINISTRATOR) {
			forward = Path.COMMAND_ADMIN_CABINET;
		}

		if (userRole == Roles.DISPATCHER) {
			forward = Path.COMMAND_DISPATCHER_CABINET;
		}
		
		if (userRole == Roles.PILOT) {
			forward = Path.COMMAND_BRIGADE_CABINET;
		}
		
		if (userRole == Roles.NAVIGATOR) {
			forward = Path.COMMAND_BRIGADE_CABINET;
		}
		
		if (userRole == Roles.RADIOMAN) {
			forward = Path.COMMAND_BRIGADE_CABINET;
		}
		
		if (userRole == Roles.STEWARDESS) {
			forward = Path.COMMAND_BRIGADE_CABINET;
		}

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.info("User " + user + " logged as "
				+ userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return forward;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		LoginCommand.factorty = factorty;
	}
}
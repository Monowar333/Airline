package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Mail.SendMail;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.MailSendException;
import ua.nure.korkh.SummaryTask4.functional.AutoGenereatPassword;
import ua.nure.korkh.SummaryTask4.functional.MD5Util;
import ua.nure.korkh.SummaryTask4.functional.ValidateFields;
import ua.nure.korkh.SummaryTask4.functional.ValidatePattern;
import ua.nure.korkh.SummaryTask4.functional.ValidateRegular;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.UserFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Save user.
 * 
 * @author Korkh
 * 
 */
public class SaveUser extends Command {
	private String login = null;
	private String suname = null;
	private String name = null;
	private String email = null;
	private Date dateofbirth = null;
	private int roleId = 0;
	private String telephone = null;
	private String sex = null;
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SaveUser.class);
	private static DAOFactory factory = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing FlightList.execute");
		HttpSession session = request.getSession();
		UserFormValidate validateuser = new UserFormValidate();
		int numberError = 0;
		login = request.getParameter("login");
		suname = request.getParameter("suname");
		name = request.getParameter("name");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
	
		UserDAO userDAO = factory.getUserDAO();

		try {
			dateofbirth = Date.valueOf(request.getParameter("dateofbirth"));
			validateuser.setDateofbirth(dateofbirth);
		} catch (IllegalArgumentException e) {
			LOG.error("fail value dateofbirth " + dateofbirth);
			validateuser.setDateofbirthError(ErrorMessege.ERR_DATE);
			numberError++;
		}

		try {
			roleId = Integer.valueOf(request.getParameter("role"));
			validateuser.setRoleId(roleId);
		} catch (NumberFormatException e) {
			LOG.error("fail value roleId " + roleId);
			validateuser.setRoleId(roleId);
			validateuser.setRoleIdError(ErrorMessege.ERR_CHOOSE_ROLE);
			numberError++;
		}

		sex = request.getParameter("sex");
		if (ValidateFields.sexUser(sex)) {
			validateuser.setSex(sex);
		} else {
			LOG.error("fail value sex " + sex);
			validateuser.setSexError(ErrorMessege.ERR_CHOOSE_SEX);
			numberError++;
		}

		if (ValidateRegular.regular(telephone,
				ValidatePattern.TELEPHONE.pattern()) == false) {
			LOG.error("fail value telephone" + telephone);
			validateuser.setTelephoneError(ErrorMessege.ERR_TELEPHONE);
			numberError++;
		} else {
			validateuser.setTelephone(telephone);
		}

		if (ValidateRegular
				.regular(login, ValidatePattern.LOGIN_PASS.pattern()) == false) {
			LOG.error("fail value login" + login);
			validateuser.setLoginError(ErrorMessege.ERR_LOGIN);
			numberError++;
		} else if(userDAO.findByLogin(login) != null){
			LOG.error("fail value login" + login);
			validateuser.setLoginError(ErrorMessege.ERR_LOGIN_EXSIST);
			numberError++;
		}else{
			validateuser.setLogin(login);
		}

		if (ValidateRegular.regular(name, ValidatePattern.NAME.pattern()) == false) {
			LOG.error("fail value name " + name);
			validateuser.setNameError(ErrorMessege.ERR_NAME);
			numberError++;
		} else {
			validateuser.setName(name);
		}

		if (ValidateRegular.regular(suname, ValidatePattern.NAME.pattern()) == false) {
			LOG.error("fail value suname " + suname);
			validateuser.setSunameError(ErrorMessege.ERR_SURNAME);
			numberError++;
		} else {
			validateuser.setSuname(suname);
		}

		if (ValidateRegular.regular(email, ValidatePattern.EMAIL.pattern()) == false) {
			LOG.error("fail value email");
			validateuser.setEmailError(ErrorMessege.ERR_EMAIL);
			numberError++;
		} else if(userDAO.findByEmail(email) != null){
			LOG.error("fail value email" + email);
			validateuser.setEmailError(ErrorMessege.ERR_EMAIL_EXSIST);
			numberError++;
		}else{
			validateuser.setEmail(email);
		}

		if (numberError > 0) {
			session.setAttribute("useradd", validateuser);
			return Path.COMMAND_ADD_USER;
		}
		
		validateuser.setLinckaccept(MD5Util.md5Custom(validateuser.getLogin()));
		validateuser.setRegistrstatus(false);
		validateuser.setPassword(AutoGenereatPassword.genereat(7));
		userDAO.insertUser(validateuser);
		LOG.trace("Save user in DB --> " + validateuser);
		User admin = (User) session.getAttribute("user");
		sendMail(validateuser, admin.getEmail());
		LOG.info("Finished executing FlightList.execute");
		return Path.COMMAND_ADMIN_CABINET;
	}
	
	private static void sendMail(User user, String email)
			throws IOException, DBException, MailSendException {
		SendMail send = new SendMail();
			send.sendMailRegistr(user, email);
	}
	
	public static void setFactory(DAOFactory factory) {
		SaveUser.factory = factory;
	}
}

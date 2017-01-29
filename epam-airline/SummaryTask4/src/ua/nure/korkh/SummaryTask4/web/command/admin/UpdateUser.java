package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.functional.ValidateFields;
import ua.nure.korkh.SummaryTask4.functional.ValidatePattern;
import ua.nure.korkh.SummaryTask4.functional.ValidateRegular;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.UserFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Update user.
 * 
 * @author Korkh
 * 
 */
public class UpdateUser extends Command {

	private static final Logger LOG = Logger.getLogger(UpdateFlight.class);
	private static final long serialVersionUID = 1L;
	private static DAOFactory factory = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing UpdateUser.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing UpdateUser.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		HttpSession session = request.getSession();
		if (session.getAttribute("updateuser") == null) {
			Integer id = Integer.valueOf(request.getParameter("check"));
			LOG.debug("Get the request param check:" + id);
			UserDAO managerUser = factory.getUserDAO();
			User user = managerUser.findByID(id);
			UserFormValidate userup = new UserFormValidate(user);
			session.setAttribute("updateuser", userup);
			LOG.trace("Set the request attribute: updateuser --> " + userup);
		}
		RoleDAO managerRole = factory.getRoleDAO();
		List<Role> rolelist = managerRole.findAll();
		request.setAttribute("rolelist", rolelist);
		LOG.trace("Set the request attribute: rolelist --> " + rolelist);
		LOG.info("Finish GET");
		return Path.PAGE_ADMIN_UPDATE_USER;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws DBException {
		LOG.info("Start POST");
		HttpSession session = request.getSession();
		UserFormValidate validateuser = (UserFormValidate) session
				.getAttribute("updateuser");
		int numberError = 0;
		String suname = null;
		String name = null;
		String email = null;
		Date dateofbirth = null;
		int roleId = 0;
		String telephone = null;
		String sex = null;
		
		UserDAO userDAO = factory.getUserDAO();
		
		suname = request.getParameter("suname");
		name = request.getParameter("name");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");

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
		if(ValidateFields.sexUser(sex)) {
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
		} else{
			validateuser.setEmail(email);
		}

		if (numberError > 0) {
			session.setAttribute("useradd", validateuser);
			return Path.COMMAND_UPDATE_USER;
		}

	
		userDAO.UpdateUser(validateuser);
		LOG.trace("Update user " + validateuser);
		session.removeAttribute("updateuser");
		LOG.info("Finish POST");
		return Path.COMMAND_ADMIN_CABINET;
	}
	
	public static void setFactory(DAOFactory factory) {
		UpdateUser.factory = factory;
	}

}

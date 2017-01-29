package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.Roles;
import ua.nure.korkh.SummaryTask4.DAO.StatusClaim;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Mail.SendMail;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.MailSendException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ClaimFormValidate;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Add claim.
 * 
 * @author Korkh
 * 
 */
public class AddClaim extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddClaim.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing AddClaim.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing AddClaim.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		HttpSession session = request.getSession();
		if (session.getAttribute("validatenewclaim") == null) {

			Integer id = Integer.valueOf(request.getParameter("idflight"));
			long curTime = System.currentTimeMillis();
			String curStringDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(curTime);
			ClaimFormValidate claimvalidate = new ClaimFormValidate();
			claimvalidate.setDate(Date.valueOf(curStringDate));
			claimvalidate.setIdflight(id);
			request.setAttribute("date", curStringDate);
			request.setAttribute("idflight", id);
			session.setAttribute("validatenewclaim", claimvalidate);
			LOG.trace("Set the request attribute: idflight --> " + id);
			LOG.trace("Set the request attribute: date --> " + curStringDate);
		}
		UserDAO managerUser = factorty.getUserDAO();
		List<User> adminlist = managerUser.findByRole(Roles.ADMINISTRATOR
				.ordinal() + 1);
		request.setAttribute("adminlist", adminlist);
		LOG.trace("Set the request attribute: adminlist --> " + adminlist);
		LOG.info("Finish GET");
		return Path.PAGE_DISPATCHER_ADD_CALAIM;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		{
			LOG.info("Start POST");
			HttpSession session = request.getSession();
			ClaimFormValidate claimvalidate = (ClaimFormValidate) session
					.getAttribute("validatenewclaim");
			int numberError = 0;
			User currentUser = (User) session.getAttribute("user");
			String discription = null;
			Integer idadmin = null;

			try {
				idadmin = Integer.valueOf(request.getParameter("adminlist"));
				claimvalidate.setIdadministrator(idadmin);
			} catch (NumberFormatException e) {
				LOG.error("fail value Idadministrato " + idadmin);
				claimvalidate
						.setIdadministratorError(ErrorMessege.ERR_ADMINISTRATOR);
				numberError++;
			}
			discription = request.getParameter("description");
			if (discription.isEmpty() || discription == null) {
				LOG.error("fail value discription " + discription);
				claimvalidate.setDescriptionError(ErrorMessege.ERR_DISCRIPTION);
				numberError++;
			} else {
				claimvalidate.setDescription(discription);
			}

			if (numberError > 0) {
				session.setAttribute("validatenewclaim", claimvalidate);
				return Path.COMMAND_ADD_CLAIM;
			}
			claimvalidate.setIddispatcher(currentUser.getId());
			claimvalidate.setStatus(StatusClaim.NEW.getName());
			ClaimDAO claimDAO = factorty.getClaimDAO();
			claimDAO.insertClaim(claimvalidate);
			LOG.trace("Add claim " + claimvalidate);
			sendMail(idadmin, currentUser.getEmail());
			LOG.info("Finish POST");
			return Path.COMMAND_DISPATCHER_CABINET;
		}

	}

	private static void sendMail(Integer idadministrator, String emailDispatcher)
			throws IOException, DBException, MailSendException {
		UserDAO usermanager = factorty.getUserDAO();
		SendMail send = new SendMail();
		User user = usermanager.findByID(idadministrator);
		send.sendMailClaim(user, emailDispatcher);

	}
	
	public static void setFactorty(DAOFactory factorty) {
		AddClaim.factorty = factorty;
	}
}

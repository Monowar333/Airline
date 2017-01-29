package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.StatusClaim;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Mail.SendMail;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.entity.Claim;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.MailSendException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Claim List.
 * 
 * @author Korkh
 * 
 */
public class ClaimList extends Command {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClaimList.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info ("Start executing ClaimList.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info ("End executing ClaimList.execute");
		return result;	
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException,AppException {

		LOG.info("Commands starts GET");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		ClaimBeanDAO claimBean = factorty.getClaimBeanDAO();
		List<ClaimBean> claimlist = claimBean.findByIDAdmin(user.getId());	
		request.setAttribute("claimlist", claimlist);		
		LOG.trace("Set the request attribute: claimlist --> " + claimlist);
		LOG.info("Commands finished GET");
		return Path.PAGE_ADMIN_CLAIM_LIST;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException,AppException {
		LOG.info("Commands starts POST change status claim");
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("user");
		Integer id = Integer.valueOf(request.getParameter("idclaim"));
		String status = (request.getParameter("changestaus"));
		if (!status.isEmpty() && (status.equals(StatusClaim.EXECUTED.getName()) 
				|| status.equals(StatusClaim.REJECTED.getName()))){
			ClaimDAO claimDAO = factorty.getClaimDAO();
			Claim claim = claimDAO.findByID(id);
			claim.setStatus(status);
			claimDAO.changeStatus(claim);
			if(claim.getIddispatcher() != null){
			sendMail(claim.getIddispatcher(), currentUser.getEmail());
			}
		}
		LOG.info("Commands finished POST change status claim");
		return Path.COMMAND_CLAIM_MENU;
	}
	
	private static void sendMail(Integer idadispatcher, String emailAdministrator) throws IOException, DBException, MailSendException{
		UserDAO usermanager = factorty.getUserDAO();
		SendMail send = new SendMail();
			User user = usermanager.findByID(idadispatcher);
			send.sendMailClaim(user, emailAdministrator);

	}
	
	public static void setFactorty(DAOFactory factorty) {
		ClaimList.factorty = factorty;
	}
}

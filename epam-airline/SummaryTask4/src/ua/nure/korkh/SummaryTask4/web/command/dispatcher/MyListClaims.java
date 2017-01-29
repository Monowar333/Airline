package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * My List Claim
 * 
 * @author Korkh
 * 
 */
public class MyListClaims extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MyListClaims.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing DispatcherCabinet.execute");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ClaimBeanDAO claimBean = factorty.getClaimBeanDAO();
		List<ClaimBean> claimlist = claimBean.findByIDDispatcher(user.getId());
		request.setAttribute("claimlist", claimlist);
		LOG.trace("Set the request attribute: claimlist --> " + claimlist);
		LOG.info("End executing MyListClaims.execute");
		return Path.PAGE_DISPATCHER_CALAIMS;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		MyListClaims.factorty = factorty;
	}
}

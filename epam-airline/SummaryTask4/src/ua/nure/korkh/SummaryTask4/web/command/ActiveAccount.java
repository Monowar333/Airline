package ua.nure.korkh.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.DBException;


/**
 * Active account  command.
 * 
 * @author Korkh
 * 
 */
public class ActiveAccount extends Command{
	
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ActiveAccount.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
		LOG.debug("Command starts");
		String link = request.getParameter("link");
		String messege = null;
		UserDAO manager = factorty.getUserDAO(); 
		System.out.println(link);
		User user = manager.findByLinckAccept(link);
		if(user == null){
			messege = "Link are not active";
		}else{
			user.setLinckaccept(null);
			user.setRegistrstatus(true);
			manager.changeStatus(user);
			messege = "Your account is active!!!";
		}
		request.setAttribute("StatusAccount",messege);
		LOG.debug("Set the request attribute: StatusAccount --> " + messege);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_ACCEPTREGISTR;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		ActiveAccount.factorty = factorty;
	}

}

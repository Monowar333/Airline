package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
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
import ua.nure.korkh.SummaryTask4.web.command.Command;


/**
 * Administrator cabinet.
 * 
 * @author Korkh
 * 
 */
public class AdminCabinet extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AdminCabinet.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		UserDAO manager = factorty.getUserDAO();
		List<User> userlist = manager.findAll();
		RoleDAO managerRole = factorty.getRoleDAO();
		List<Role> rolelist = managerRole.findAll();
		request.setAttribute("rolelist", rolelist);	
		request.setAttribute("userlist", userlist);		
		request.setAttribute("user", user);		
		LOG.trace("Set the request attribute: rolelist --> " + rolelist);
		LOG.trace("Set the request attribute: userlist --> " + userlist);
		LOG.trace("Set the request attribute: user --> " + user);
		LOG.debug("Commands finished AdminCabinet.execute");
		return Path.PAGE_ADMIN_CABINET;
	}

	public static void setFactorty(DAOFactory factorty) {
		AdminCabinet.factorty = factorty;
	}
}
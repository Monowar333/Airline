package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Add new user
 * 
 * @author Korkh
 * 
 */
public class AddUser extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddUser.class);
	
	private static DAOFactory factorty = new DAOFactoryMySQL();
	/**
	 * Serializable comparator used with TreeMap container. When the servlet
	 * container tries to serialize the session it may fail because the session
	 * can contain TreeMap object with not serializable comparator.
	 * 
	 * @author Korkh
	 * 
	 */	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");
		RoleDAO manager = factorty.getRoleDAO();
		List<Role> rolelist = manager.findAll();
		request.setAttribute("rolelist", rolelist);				
		LOG.trace("Set the request attribute: rolelist --> " + rolelist);
		LOG.info ("End executing AddUser.execute");
		return Path.PAGE_ADMIN_ADD_USER;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		AddUser.factorty = factorty;
	}
}

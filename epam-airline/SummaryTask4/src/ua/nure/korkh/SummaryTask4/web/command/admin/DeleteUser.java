package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Delete User.
 * 
 * @author Korkh
 * 
 */
public class DeleteUser extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClaimList.class);
	private static DAOFactory factory = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.debug("Commands started");
		String[] id = (request.getParameterValues("check"));
		Integer[] check = new Integer[id.length];
		for (int j = 0; j < id.length; j++) {
			check[j] = Integer.valueOf(id[j]);
		}
		LOG.trace("get the request attribute: check --> " + check);
		UserDAO manager = factory.getUserDAO();
		manager.deleteUsers(check);
		LOG.trace("Command finished");
		return Path.COMMAND_ADMIN_CABINET;
	}
	
	public static void setFactory(DAOFactory factory) {
		DeleteUser.factory = factory;
	}

}

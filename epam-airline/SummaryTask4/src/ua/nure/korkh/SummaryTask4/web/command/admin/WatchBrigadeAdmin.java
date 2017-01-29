package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * watchbrigadeadmin command
 * 
 * @author Korkh
 * 
 */
public class WatchBrigadeAdmin extends Command{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WatchBrigadeAdmin.class);
	private static DAOFactory factory = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		Integer id = Integer.valueOf(request.getParameter("id"));
		BrigadeBeanDAO flightbean = factory.getBrigadeBeanDAO();
		List<BrigadeBean> beanlist = flightbean.findAllByIdFlight(id);
		RoleDAO managerRole = factory.getRoleDAO();
		List<Role> rolelist = managerRole.findAll();
		request.setAttribute("flightbrigadelist", beanlist);
		request.setAttribute("rolelist", rolelist);
		LOG.trace("Set the request attribute: flightbrigadelist --> "
				+ beanlist);
		LOG.info("Finish GET");
		return Path.PAGE_ADMIN_BRIGADE_FLIGHT;
	}
	
	public static void setFactory(DAOFactory factory) {
		WatchBrigadeAdmin.factory = factory;
	}
}

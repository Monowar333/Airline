package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

public class PlaneList extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PlaneList.class);
	private static DAOFactory factory = new DAOFactoryMySQL();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing FlightList.execute");
		PlaneDAO flightbean = factory.getPlaisDAO();
		List<Plane> planelist = flightbean.findAll();
		request.setAttribute("planelist", planelist);
		LOG.trace("Set the request attribute: planelist --> " + planelist);
		LOG.info("End executing FlightList.execute");
		return Path.PAGE_ADMIN_PLANE_LIST;
	}

	public static void setFactory(DAOFactory factory) {
		PlaneList.factory = factory;
	}
}

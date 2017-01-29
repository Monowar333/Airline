package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;
import ua.nure.korkh.SummaryTask4.web.command.FlightsListSort;

/**
 * Flight List.
 * 
 * @author Korkh
 * 
 */
public class FlightList extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FlightList.class);
	private static DAOFactory factory = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing FlightList.execute");
		List<FlightsBean> beanlist;
		String findBy = request.getParameter("findBy");
		String findvalue = request.getParameter("findvalue");
		FligtsBeanDAO flightbean = factory.getFligtsBeanDAO();
		if (findBy != null && findvalue != null && !findBy.isEmpty()
				&& !findvalue.isEmpty()) {
			LOG.debug("Find flight bean list by param:" + findBy + " "
					+ findvalue);
			beanlist = flightbean.findByParam(findBy, findvalue);
		} else {
			beanlist = flightbean.findAll();
		}
		String sort = request.getParameter("sort");
		if (sort != null && !sort.isEmpty()) {
			LOG.debug("Sort flight bean list by param:" + sort);
			beanlist = FlightsListSort.sortFlightsBean(sort, beanlist);
		}
		request.setAttribute("flightlist", beanlist);
		LOG.trace("Set the request attribute: flightlist --> " + beanlist);
		LOG.info("End executing FlightList.execute");
		return Path.PAGE_ADMIN_FLIGHT_LIST;
	}
	
	public static void setFactory(DAOFactory factory) {
		FlightList.factory = factory;
	}
}

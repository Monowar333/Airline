package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;
import ua.nure.korkh.SummaryTask4.web.command.FlightsListSort;

/**
 * Dispatcher cabinet
 * 
 * @author Korkh
 * 
 */
public class DispatcherCabinet extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DispatcherCabinet.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing DispatcherCabinet.execute");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<FlightsBean> beanlist;
		String findBy = request.getParameter("findBy");
		String findvalue = request.getParameter("findvalue");
		FligtsBeanDAO flightbean = factorty.getFligtsBeanDAO();
		if (findBy != null && findvalue != null && !findBy.isEmpty()
				&& !findvalue.isEmpty()) {
			LOG.debug("Find flight bean list by param:" + findBy + " "
					+ findvalue);
			beanlist = flightbean.findByParam(findBy, findvalue);
		} else {
			beanlist = flightbean.findAll();
			String sort = request.getParameter("sort");
			LOG.trace("sort by  --> " + sort);
			if (sort != null) {
				LOG.debug("Sort flight bean list by param:" + sort);
				beanlist = FlightsListSort.sortFlightsBean(sort, beanlist);
			}
		}
		request.setAttribute("flightlist", beanlist);
		request.setAttribute("user", user);
		LOG.trace("Set the request attribute: flightlist --> " + beanlist);
		LOG.trace("Set the request attribute: user --> " + user);
		LOG.info("End executing DispatcherCabinet.execute");
		return Path.PAGE_DISPATCHER_CABINET;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		DispatcherCabinet.factorty = factorty;
	}
}

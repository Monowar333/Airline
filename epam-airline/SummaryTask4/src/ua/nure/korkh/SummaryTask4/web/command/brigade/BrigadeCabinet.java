package ua.nure.korkh.SummaryTask4.web.command.brigade;

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


public class BrigadeCabinet extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BrigadeCabinet.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing BrigadeCabinet.execute");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<FlightsBean> beanlist;
		DAOFactory factorty = new DAOFactoryMySQL();
		FligtsBeanDAO flightbean = factorty.getFligtsBeanDAO();
		beanlist = flightbean.findAllByIdUser(user.getId());
		String sort = request.getParameter("sort");
		System.out.println(sort);
		if (sort != null) {
			LOG.debug("Sort flight bean list by param:" + sort);
			beanlist = FlightsListSort.sortFlightsBean(sort, beanlist);
		}
		request.setAttribute("flightlist", beanlist);
		request.setAttribute("user", user);
		LOG.trace("Set the request attribute: flightlist --> " + beanlist);
		LOG.trace("Set the request attribute: user --> " + user);
		LOG.info("End executing BrigadeCabinet.execute");
		return Path.PAGE_BRIGADE_CABINET;
	}
}

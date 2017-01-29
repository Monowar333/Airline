package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.command.Command;

public class WorkWithAirport extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WorkWithAirport.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing WorkWithAirport.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing WorkWithAirport.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		HttpSession session = request.getSession();
		Integer size = 0;
		if(session.getAttribute("pagevalue") != null){
			size = (Integer) session.getAttribute("pagevalue");
			String page = String.valueOf(request.getParameter("page"));
			System.out.println(page);
			if(page == null){
				size = 0;
			}else if( page.equals("previous") && size != 0){
				size -=1;
			}else if(page.equals("next")){
				size+=1;
			}
		}
		session.setAttribute("pagevalue", size);
		Integer start = 0 + (100*size);
		Integer finish = 100 + (100*size);
		AirportDAO manager = factorty.getAirportDAO();
		List<Airport> airportList = new ArrayList<>();
		airportList.addAll(manager.findAll().subList(start, finish));
		request.setAttribute("airportlist", airportList);
		return Path.PAGE_DISPATCHER_AIRPORTLIST;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		{
			LOG.info("Start POST");
			Integer id = Integer.valueOf(request.getParameter("idairport"));
			String status = (request.getParameter("changestaus"));
			AirportDAO airportDAO = factorty.getAirportDAO();
			LOG.trace("Get the request parametr: changestaus --> " + status);
			Airport airport = airportDAO.findByID(id);
			airport.setStatus(status);
			airportDAO.changeStatusAirport(airport);
			LOG.trace("Change status airport " + airport);
			LOG.info("Finish POST");
			return Path.COMMAND_GO_TO_AIRPORT_LIST;
		}

	}
	
	public static void setFactorty(DAOFactory factorty) {
		WorkWithAirport.factorty = factorty;
	}
}

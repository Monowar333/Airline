package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.functional.ValidateFields;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Update flight
 * 
 * @author Korkh
 * 
 */
public class UpdateFlight extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UpdateFlight.class);
	private static DAOFactory factory = new DAOFactoryMySQL();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing UpdateFlight.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing UpdateFlight.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		HttpSession session = request.getSession();
		if (session.getAttribute("validateupdateflight") == null) {
			Integer id = Integer.valueOf(request.getParameter("check"));
			LOG.debug("Get the request param check:" + id);
			FlightDAO managerFlight = factory.getFlightDAO();
			Flight flight = managerFlight.findByID(id);
			FlightFormValidate flightvalifate = new FlightFormValidate(flight);
			session.setAttribute("validateupdateflight", flightvalifate);
			LOG.trace("Set the request attribute: validateupdateflight --> "
					+ flightvalifate);
		}
		AirportDAO airpormanager = factory.getAirportDAO();
		List<Airport> airportlist = airpormanager.findAll();
		PlaneDAO plainsmanager = factory.getPlaisDAO();
		List<Plane> plainslist = plainsmanager.findAll();
		request.setAttribute("airportlist", airportlist);
		request.setAttribute("plainslist", plainslist);
		LOG.trace("Set the request attribute: plainslist --> " + plainslist);
		LOG.info("Finish GET");
		return Path.PAGE_ADMIN_UPDATE_FLIGHT;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start POST");
		HttpSession session = request.getSession();
		FlightFormValidate validateflight = (FlightFormValidate) session
				.getAttribute("validateupdateflight");
		int numberError = 0;
		String number = null;
		Date departuredate = null;
		int fromwhence = 0;
		int where = 0;
		Integer idplane = 0;
		String status = null;

		try {
			departuredate = Date.valueOf(request.getParameter("departuredate"));
			validateflight.setDeparturedate(departuredate);
		} catch (IllegalArgumentException e) {
			LOG.error("fail value departuredate " + departuredate);
			validateflight.setDeparturedateError(ErrorMessege.ERR_DATE);
			numberError++;
		}

		try {
			fromwhence = Integer.valueOf(request.getParameter("fromwhence"));
			validateflight.setFromwhence(fromwhence);
		} catch (NumberFormatException e) {
			LOG.error("fail value fromwhence " + fromwhence);
			validateflight.setFromwhenceError(ErrorMessege.ERR_AIRPORT);
			numberError++;
		}

		try {
			where = Integer.valueOf(request.getParameter("where"));
			validateflight.setWhere(where);
		} catch (NumberFormatException e) {
			LOG.error("fail value where " + where);
			validateflight.setWhereError(ErrorMessege.ERR_AIRPORT);
			numberError++;
		}

		try {
			idplane = Integer.valueOf(request.getParameter("idplains"));
			validateflight.setIdplains(idplane);
		} catch (NumberFormatException e) {
			LOG.error("fail value idplains " + idplane);
			validateflight.setIdplainsError(ErrorMessege.ERR_PLAINS);
			numberError++;
		}

		status = request.getParameter("status");
		if(ValidateFields.statusFlight(status)){
			validateflight.setStatus(status);
		} else {
			LOG.error("fail value status " + status);
			validateflight
					.setStatusError(ErrorMessege.ERR_CHOOSE_STATUS_AIRPORT);
			numberError++;
		}

		number = request.getParameter("number");
		if (number.isEmpty()) {
			LOG.error("fail value number " + number);
			validateflight.setNumberError(ErrorMessege.ERR_NUMBER);
			numberError++;
		} else {
			System.out.println(number);
			validateflight.setNumber(number);
		}

		if (numberError > 0) {
			session.setAttribute("validateflight", validateflight);
			return Path.COMMAND_UPDATE_FLIGHT;
		}
		FlightDAO flightDAO = factory.getFlightDAO();
		flightDAO.UpdateFlight(validateflight);
		LOG.trace("Update Flight " + validateflight);
		session.setAttribute("updateflight", null);
		LOG.info("Finish POST");
		return Path.COMMAND_FlIGHT_MENU;
	}
	
	public static void setFactory(DAOFactory factory) {
		UpdateFlight.factory = factory;
	}
}

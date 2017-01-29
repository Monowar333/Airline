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
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.functional.GenerateNum;
import ua.nure.korkh.SummaryTask4.functional.ValidateFields;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Add new flight
 * 
 * @author Korkh
 * 
 */

public class AddFlight extends Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddFlight.class);
	
	private static DAOFactory factorty = new DAOFactoryMySQL();
	


	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info ("End executing AddFlight.execute");
		return result;

		
	}
	
	private static String doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
	AppException {
		LOG.debug("Commands starts GET");	
		HttpSession session = request.getSession();
		if (session.getAttribute("validateflight") == null) {
			FlightFormValidate validateflight = new FlightFormValidate();
			validateflight.setNumber("R"+GenerateNum.getNumber());
			session.setAttribute("validateflight", validateflight);
		}
		AirportDAO airpormanager = factorty.getAirportDAO();
		List<Airport> airportlist = airpormanager.findAll();
		PlaneDAO plainsmanager = factorty.getPlaisDAO();
		List<Plane> plainslist = plainsmanager.findAll();
		request.setAttribute("airportlist", airportlist);	
		request.setAttribute("plainslist", plainslist);
		LOG.trace("Found in DB: plains --> " + plainslist);
		LOG.debug("Commands finished go to add flight list");
		return Path.PAGE_ADMIN_ADD_FLIGHT;
	}

	private static String doPost (HttpServletRequest request, HttpServletResponse response) throws DBException {		
		LOG.debug("Commands starts POST");
		int numberError = 0;
		String number = null;	
		Date departuredate = null;
		int fromwhence = 0;	
		int where = 0;	
		Integer idplane = 0;	
		String status = null;
		HttpSession session = request.getSession();
		FlightFormValidate validateflight =(FlightFormValidate) session.getAttribute("validateflight");
		
		
		try{
			departuredate =  Date.valueOf(request.getParameter("departuredate"));
			validateflight.setDeparturedate(departuredate);
		}catch (IllegalArgumentException e){
			LOG.error("fail value departuredate " + departuredate);
			validateflight.setDeparturedateError(ErrorMessege.ERR_DATE);
			numberError++;
		}
		
		try{
			fromwhence = Integer.valueOf(request.getParameter("fromwhence"));
			validateflight.setFromwhence(fromwhence);	
		}catch (NumberFormatException e){
			LOG.error("fail value fromwhence " + fromwhence);
			validateflight.setFromwhenceError(ErrorMessege.ERR_AIRPORT);
			numberError++;
		}
		
		try{
			where = Integer.valueOf(request.getParameter("where"));
			validateflight.setWhere(where);
			if(fromwhence != 0 && fromwhence == where){
				LOG.error("fromwhence == where " + where);
				validateflight.setWhereError(ErrorMessege.ERR_AIRPORT_EQUALS);
				numberError++;
			}
		}catch (NumberFormatException e){
			LOG.error("fail value where " + where);
			validateflight.setWhereError(ErrorMessege.ERR_AIRPORT);
			numberError++;
		}
		
		try{
			idplane = Integer.valueOf(request.getParameter("idplains"));
			validateflight.setIdplains(idplane);
			PlaneDAO plainsmanager= factorty.getPlaisDAO();
			if(plainsmanager.findByDepartureDate(departuredate, idplane).size() != 0){
				LOG.error("fail value idplains " + idplane);
				validateflight.setIdplainsError(ErrorMessege.ERR_PLAINS_EXSIST);
				numberError++;
			}
		}catch (NumberFormatException e){
			LOG.error("fail value idplains " + idplane);
			validateflight.setIdplainsError(ErrorMessege.ERR_PLAINS_EXSIST);
			numberError++;
		}
		
		status = request.getParameter("status");
		if(ValidateFields.statusFlight(status)){
			validateflight.setStatus(status);
		}else{
			LOG.error("fail value status " + status);
			validateflight.setStatusError(ErrorMessege.ERR_CHOOSE_STATUS_AIRPORT);
			numberError++;
		}
		
		number = request.getParameter("number");
		if(number.isEmpty()){
			LOG.error("fail value number " + number);
			validateflight.setNumberError(ErrorMessege.ERR_NUMBER);
			numberError++;
		}else{
			validateflight.setNumber(number);
		}

		if(numberError > 0){
			session.setAttribute("validateflight", validateflight);
			return Path.COMMAND_ADD_FLIGHT;
		}
		FlightDAO flightDAO = factorty.getFlightDAO();
		flightDAO.insertFlight(validateflight);		
		LOG.debug("Commands finished save new flight go to command flighr menu");
		return Path.COMMAND_FlIGHT_MENU;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		AddFlight.factorty = factorty;
	}
}

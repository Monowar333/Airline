package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Change Status Flight
 * 
 * @author Korkh
 * 
 */
public class ChangeStatusFlight extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddClaim.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing ChangeStatusFlight.execute");
		Integer id = Integer.valueOf(request.getParameter("idflight"));
		String status = (request.getParameter("changestaus"));
		FlightDAO flightDAO = factorty.getFlightDAO();
		LOG.trace("Get the request parametr: changestaus --> " + status);
		Flight flight = flightDAO.findByID(id);
		flight.setStatus(status);
		flightDAO.changeStatus(flight);
		LOG.trace("Change status flight " + flight);
		LOG.info("Finish executing ChangeStatusFlight.execute");
		return Path.COMMAND_DISPATCHER_CABINET;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		ChangeStatusFlight.factorty = factorty;
	}
}

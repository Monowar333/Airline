package ua.nure.korkh.SummaryTask4.web.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.Hash.HashDB;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.WorkWithExcel.WorkWithExcel;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Context listener.
 * 
 * @author Korkh
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		// no operation
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");
		HashDB.init();
		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
		DAOFactory factory = new DAOFactoryMySQL();
		AirportDAO airportDAO = factory.getAirportDAO();
		List<Airport> airportlistDB = null;
		try {
			airportlistDB = airportDAO.findAll();
		} catch (DBException e1) {
			e1.printStackTrace();
		}
		WorkWithExcel excel = new WorkWithExcel();
		List<Airport> listairportExcel = null;
		try {
			listairportExcel = excel.FromExelToDBmedications();
		} catch (DBException | IOException e1) {
			e1.printStackTrace();
		}
		int sizeExcel = listairportExcel.size();
		int sizeDB = airportlistDB.size();
		int delta = sizeExcel - sizeDB;
		System.out.println(sizeExcel + " " + sizeDB);
		if (delta > 0) {
			List<Airport> writeToDB = new ArrayList<>();
			writeToDB.addAll(listairportExcel.subList((sizeExcel - delta), sizeExcel));
			System.out.println(writeToDB);
			try {
				airportDAO.insertAirport(writeToDB);
			} catch (DBException e) {
				e.printStackTrace();
			}
		}
		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext
					.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}

	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initCommandContainer() {

		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.korkh.SummaryTask4.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException(
					"Cannot initialize Command Container");
		}
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
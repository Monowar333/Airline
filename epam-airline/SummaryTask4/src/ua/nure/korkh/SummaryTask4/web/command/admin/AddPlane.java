package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.PlaneFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

public class AddPlane extends Command {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddPlane.class);

	private static DAOFactory factorty = new DAOFactoryMySQL();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing AddPlane.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing AddPlane.execute");
		return result;

	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.debug("Commands starts GET");
		LOG.debug("Commands finished go to add flight list");
		return Path.PAGE_ADMIN_ADD_PLANE;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws DBException {
		LOG.debug("Commands starts POST");
		int numberError = 0;
		String model = null;
		int crew = 0;
		int numberOfSeats = 0;
		HttpSession session = request.getSession();
		PlaneFormValidate validateplane = new PlaneFormValidate();

		model = (request.getParameter("model"));
		if (model.isEmpty()) {
			validateplane.setModelError(ErrorMessege.ERR_PLANE_MODEL);
			numberError++;
		} else {
			validateplane.setModel(model);
		}

		try {
			crew = Integer.valueOf(request.getParameter("crew"));
			if (crew < 1) {
				throw new NumberFormatException();
			}
			validateplane.setCrew(crew);
		} catch (NumberFormatException e) {
			LOG.error("fail value crew " + crew);
			validateplane.setCrewError(ErrorMessege.ERR_PLANE_CREW);
			numberError++;
		}

		try {
			numberOfSeats = Integer.valueOf(request
					.getParameter("numberofseats"));
			if (numberOfSeats < 1) {
				throw new NumberFormatException();
			}
			validateplane.setNumberofseats(numberOfSeats);
		} catch (NumberFormatException e) {
			LOG.error("fail value numberofseats " + numberOfSeats);
			validateplane
					.setNumberofseatsError(ErrorMessege.ERR_PLANE_NUMBER_OF_SEATS);
			numberError++;
		}

		if (numberError > 0) {
			session.setAttribute("addplanevalidate", validateplane);
			return Path.COMMAND_ADD_PLANE;
		}

		PlaneDAO planeDAO = factorty.getPlaisDAO();
		planeDAO.insertPlane(validateplane);
		LOG.debug("Commands finished save new flight go to command flighr menu");
		return Path.COMMAND_PLANE_MENU;
	}

	public static void setFactorty(DAOFactory factorty) {
		AddPlane.factorty = factorty;
	}
}

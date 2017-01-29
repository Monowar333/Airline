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
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.PlaneFormValidate;
import ua.nure.korkh.SummaryTask4.web.command.Command;

public class UpdatePlane extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UpdatePlane.class);
	private static DAOFactory factory = new DAOFactoryMySQL();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing UpdatePlane.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			result = doPost(request, response);
		}
		LOG.info("End executing UpdatePlane.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		HttpSession session = request.getSession();
		if (session.getAttribute("validateupdateplane") == null) {
			Integer id = Integer.valueOf(request.getParameter("check"));
			LOG.debug("Get the request param check:" + id);
			PlaneDAO planeDAO = factory.getPlaisDAO();
			Plane plane = planeDAO.findByID(id);
			PlaneFormValidate planevalifate = new PlaneFormValidate(plane);
			session.setAttribute("validateupdateplane", planevalifate);
			LOG.trace("Set the request attribute: validateupdateplane --> "
					+ planevalifate);
		}
		LOG.info("Finish GET");
		return Path.PAGE_ADMIN_UPDATE_PLANE;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start POST");
		HttpSession session = request.getSession();
		PlaneFormValidate validateplane = (PlaneFormValidate) session
				.getAttribute("validateupdateplane");
		int numberError = 0;
		String model = null;
		int crew = 0;
		int numberOfSeats = 0;

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
		PlaneDAO planeDAO = factory.getPlaisDAO();
		planeDAO.UpdatePlane(validateplane);
		
		LOG.trace("Update Flight " + validateplane);
		LOG.info("Finish POST");
		return Path.COMMAND_PLANE_MENU;
	}
	
	public static void setFactory(DAOFactory factory) {
		UpdatePlane.factory = factory;
	}
}

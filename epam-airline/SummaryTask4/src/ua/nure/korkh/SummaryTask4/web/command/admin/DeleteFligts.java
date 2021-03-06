package ua.nure.korkh.SummaryTask4.web.command.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

public class DeleteFligts extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DeleteFligts.class);
	private static DAOFactory factory = new DAOFactoryMySQL();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing DeleteFligts.execute");
		String[] id = (request.getParameterValues("check"));
		Integer[] check = new Integer[id.length];
		for (int j = 0; j < id.length; j++) {
			check[j] = Integer.valueOf(id[j]);
		}
		LOG.trace("GET the request attribute: check --> " + check);
		PlaneDAO planeDAO = factory.getPlaisDAO();
		planeDAO.deletePlane(check);
		LOG.info("Finished executing DeleteFligts.execute");
		return Path.COMMAND_FlIGHT_MENU;
	}

	public static void setFactory(DAOFactory factory) {
		DeleteFligts.factory = factory;
	}
}

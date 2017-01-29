package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Brigade;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 *Delete user from brigade
 * 
 * @author Korkh
 * 
 */
public class DeleteBrigade extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DeleteBrigade.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing DeleteBrigade.execute");
		System.out.println(request.getParameter("idbrigadeuser"));
		Integer id = null;
		Integer idf = null;
		id = Integer.valueOf(request.getParameter("idbrigadeuser"));
		idf = Integer.valueOf(request.getParameter("idflight"));
		if (id == null){
			throw new AppException("Fail input param");
		}
		BrigadeDAO brigademanager = factorty.getBrigadeDAO();
		Brigade brigade = brigademanager.findByID(id);
		brigademanager.DeleteBrigade(brigade);
		LOG.trace("Delete from Brigade " + brigade);
		LOG.info("End executing DeleteBrigade.execute");
		return Path.COMMAND_ADD_BRIGADE + "&id=" + idf;
	}
	
	public static void setFactorty(DAOFactory factorty) {
		DeleteBrigade.factorty = factorty;
	}
}

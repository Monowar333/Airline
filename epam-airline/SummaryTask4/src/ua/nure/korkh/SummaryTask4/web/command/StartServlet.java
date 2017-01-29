package ua.nure.korkh.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Servlet implementation class StartServlet
 * @author Korkh
 */
@WebServlet({"/startpage"})
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(StartServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("Strt srvlet finished");
		RequestDispatcher disp = null;
		List<FlightsBean> beanlist = null;
		String findBy = request.getParameter("findBy");
		String findvalue = request.getParameter("findvalue");
		DAOFactory factorty = new DAOFactoryMySQL();
		FligtsBeanDAO flightbean = factorty.getFligtsBeanDAO();
		if(findBy != null && findvalue != null && !findBy.isEmpty() && !findvalue.isEmpty()){
			try {
				beanlist = flightbean.findByParam(findBy, findvalue);
			} catch (DBException e) {
				LOG.error(e);
			}
		}
		String sort = request.getParameter("sort");
		if(sort != null){
			LOG.error(sort + " +++++++++++++++++++");
			try {
				beanlist = flightbean.SORTByParam(sort, "DESC");
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			try {
				beanlist = flightbean.findAll();
			} catch (DBException e) {
				LOG.error(e);
			}
		}	
		request.setAttribute("flightlist", beanlist);		
		LOG.debug("Strt srvlet finished");
		System.out.println("go to flightlist");
		disp = request.getRequestDispatcher(Path.PAGE_LOGIN);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

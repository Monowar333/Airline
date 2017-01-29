package ua.nure.korkh.SummaryTask4.web.command.dispatcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.BrigadeDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.Roles;
import ua.nure.korkh.SummaryTask4.DAO.StatusFlight;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.Mail.SendMail;
import ua.nure.korkh.SummaryTask4.Mail.PDFBilder.PDFBilder;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.entity.Brigade;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.MailSendException;
import ua.nure.korkh.SummaryTask4.web.RequaastType;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.ErrorMessege;
import ua.nure.korkh.SummaryTask4.web.command.Command;

/**
 * Watch Brigade
 * 
 * @author Korkh
 * 
 */
public class WatchBrigade extends Command {

	static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WatchBrigade.class);
	private static DAOFactory factorty = new DAOFactoryMySQL();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing WatchBrigade.execute");
		String result = null;
		if (request.getMethod().equals(RequaastType.GET.toString())) {
			result = doGet(request, response);
		} else if (request.getMethod().equals(RequaastType.POST.toString())) {
			try {
				result = doPost(request, response);
			} catch (Exception e) {
				LOG.error("error", e);
			}
		}
		LOG.info("End executing WatchBrigade.execute");
		return result;
	}

	private static String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start GET");
		Integer id = Integer.valueOf(request.getParameter("id"));
		FlightDAO flightmanager = factorty.getFlightDAO();
		Flight flight = flightmanager.findByID(id);
		BrigadeBeanDAO flightbean = factorty.getBrigadeBeanDAO();
		List<BrigadeBean> beanlist = flightbean.findAllByIdFlight(id);
		RoleDAO managerRole = factorty.getRoleDAO();
		UserDAO managerUser = factorty.getUserDAO();
		List<Role> rolelist = managerRole.findAll();
		List<User> pilotlist = managerUser
				.findByRoleANDIdflight(Roles.PILOT.ordinal() + 1, flight);
		List<User> navigatortlist = managerUser.findByRoleANDIdflight(Roles.NAVIGATOR
				.ordinal() + 1, flight);
		List<User> radiomanlist = managerUser.findByRoleANDIdflight(Roles.RADIOMAN
				.ordinal() + 1, flight);
		List<User> stewaerdesslist = managerUser.findByRoleANDIdflight(Roles.STEWARDESS
				.ordinal() + 1, flight);
		request.setAttribute("idflight", id);
		request.setAttribute("pilotlist", pilotlist);
		request.setAttribute("navigatortlist", navigatortlist);
		request.setAttribute("radiomanlist", radiomanlist);
		request.setAttribute("stewaerdesslist", stewaerdesslist);
		request.setAttribute("rolelist", rolelist);
		request.setAttribute("flightbrigadelist", beanlist);
		LOG.trace("Set the request attribute: idflight --> " + id);
		LOG.trace("Set the request attribute: pilotlist --> " + pilotlist);
		LOG.trace("Set the request attribute: navigatortlist --> "
				+ navigatortlist);
		LOG.trace("Set the request attribute: radiomanlist --> " + radiomanlist);
		LOG.trace("Set the request attribute: stewaerdesslist --> "
				+ stewaerdesslist);
		LOG.trace("Set the request attribute: flightbrigadelist --> "
				+ beanlist);
		LOG.info("Finish GET");
		return Path.PAGE_DISPATCHER_BRIGADE_FLIGHT;
	}

	private static String doPost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("Start POST");
		Integer flightid = null;
		Integer pilot = null;
		Integer radioman = null;
		Integer navigator = null;
		Integer stewardess = null;
		Integer stewardess1 = null;
		Integer stewardess2 = null;
		List<Brigade> brigadeList = new ArrayList<>();
		HttpSession session = request.getSession();
		try {
			flightid = Integer.valueOf(request.getParameter("idflight"));
			System.out.println(flightid);
		} catch (Exception e) {
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		try {
			pilot = Integer.valueOf(request.getParameter("pilot"));
			Brigade b = new Brigade();
			b.setIdflight(flightid);
			b.setIdusers(pilot);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + pilot);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		try {
			radioman = Integer.valueOf(request.getParameter("radioman"));
			Brigade b = new Brigade();
			b.setIdflight(flightid);
			b.setIdusers(radioman);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + radioman);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		try {
			navigator = Integer.valueOf(request.getParameter("navigator"));
			Brigade b = new Brigade();
			b.setIdflight(flightid);
			b.setIdusers(navigator);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + navigator);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		try {
			radioman = Integer.valueOf(request.getParameter("radioman"));
			Brigade b = new Brigade();
			b.setIdflight(flightid);
			b.setIdusers(radioman);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + radioman);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		
		try {
			stewardess = Integer.valueOf(request.getParameter("stewardess"));
			Brigade b = new Brigade();
			b.setIdusers(stewardess);
			b.setIdflight(flightid);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + stewardess);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		try {
			stewardess1 = Integer.valueOf(request.getParameter("stewardess1"));
			if(stewardess != null && stewardess1 == stewardess){
				session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
				return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
			}
			Brigade b = new Brigade();
			b.setIdusers(stewardess1);
			b.setIdflight(flightid);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + stewardess1);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		try {
			stewardess2 = Integer.valueOf(request.getParameter("stewardess2"));
			Brigade b = new Brigade();
			if(stewardess != null && stewardess2 == stewardess || stewardess1 != null && stewardess2 == stewardess1){
				session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
				return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
			}
			b.setIdusers(stewardess2);
			b.setIdflight(flightid);
			brigadeList.add(b);
		} catch (Exception e) {
			LOG.info("no exsist param " + stewardess2);
			session.setAttribute("addbrigarerror", ErrorMessege.ERR_ADD_BRIGADE);
			return Path.COMMAND_ADD_BRIGADE + "&id=" + flightid;
		}
		
		System.out.println(brigadeList);
		BrigadeDAO flightBeanDAO = factorty.getBrigadeDAO();
		flightBeanDAO.addBrigade(brigadeList);
		//if (brigadeList.size() == 7){
			FlightDAO flightDAO = factorty.getFlightDAO();
			Flight flight = flightDAO.findByID(flightid);
			flight.setStatus(StatusFlight.WAITING.getName());
			flightDAO.changeStatus(flight);
		//}
		
		sendMail(flightid, brigadeList);
		LOG.trace("Add brigade " + brigadeList);
		LOG.info("Finish POST");
		return Path.COMMAND_DISPATCHER_CABINET;
	}

	private static void sendMail(Integer flightid, List<Brigade> listin)
			throws IOException, DBException, MailSendException {
		FligtsBeanDAO mahagerflight = factorty.getFligtsBeanDAO();
		FlightsBean flightBean = mahagerflight.findById(flightid);
		BrigadeBeanDAO managerBrigade = factorty.getBrigadeBeanDAO();
		List<BrigadeBean> list = managerBrigade.findAllByIdFlight(flightid);
		UserDAO usermanager = factorty.getUserDAO();
		PDFBilder bulder = new PDFBilder();
		File file = null;
		try {
			file = bulder.buildPdfDocumentFlight(list, flightBean);
			LOG.debug("file create " + file.getAbsolutePath());
		} catch (Exception e) {
			LOG.debug("Error work with file", e);
		}
		SendMail send = new SendMail();
		for (Brigade br : listin) {
			User user = usermanager.findByID(br.getIdusers());
			send.sendMailFlight(file, user);
		}
		file.delete();
	}
	
	public static void setFactorty(DAOFactory factorty) {
		WatchBrigade.factorty = factorty;
	}
}

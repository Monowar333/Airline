package ua.nure.korkh.SummaryTask4.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.Roles;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;

/**
 * Security filter. Disabled by default. Uncomment Security filter section in
 * web.xml to enable.
 * 
 * @author Korkh
 * 
 */
public class CommandAccessFilter implements Filter {

	private static final Logger LOG = Logger
			.getLogger(CommandAccessFilter.class);

	// commands access
	private Map<Roles, List<String>> accessMap = new HashMap<Roles, List<String>>();
	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	public void destroy() {
		LOG.debug("Filter destruction starts");
		// do nothing
		LOG.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOG.debug("Filter starts");
		if (accessAllowed(request)) {
			LOG.debug("Filter finished");

			chain.doFilter(request, response);
		} else {
			String errorMessasge = "You do not have permission to access the requested resource";

			request.setAttribute("errorMessage", errorMessasge);
			LOG.trace("Set the request attribute: errorMessage --> "
					+ errorMessasge);

			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request,
					response);
		}
	}

	private boolean accessAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String commandName = request.getParameter("command");

		if (commandName == null || commandName.isEmpty()) {
			return false;
		}

		if (outOfControl.contains(commandName)) {
			return true;
		}

		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}

		Roles userRole = (Roles) session.getAttribute("userRole");
		if (userRole == null) {
			return false;
		}
		
		User user = (User) session.getAttribute("user");
		if(user.getRegistrstatus() == false){
			return false;
		}

		if (!commandName.equals("adduser") && !commandName.equals("saveuser")) {
			session.removeAttribute("useradd");
			LOG.info("remove session atribut user");
		}

		if (!commandName.equals("updateuser")) {
			session.removeAttribute("updateuser");
			LOG.info("remove session atribut updateuser");
		}

		if (!commandName.equals("addflight")
				&& !commandName.equals("saveflight")) {
			session.removeAttribute("validateflight");
			LOG.info("remove session atribut validateflight");
		}

		if (!commandName.equals("updateflight")
				&& !commandName.equals("saveupdateflight")) {
			session.removeAttribute("validateupdateflight");
			LOG.info("remove session atribut validateupdateflight");
		}

		if (!commandName.equals("watchbrigade")
				&& !commandName.equals("saveflightbrigade")) {
			session.removeAttribute("addbrigarerror");
			LOG.info("remove session atribut addbrigarerror");
		}

		if (!commandName.equals("savenewclaim")
				&& !commandName.equals("addclaim")) {
			session.removeAttribute("validatenewclaim");
			LOG.info("remove session atribut validatenewclaim");
		}
		
		if (!commandName.equals("listairport")
				&& !commandName.equals("changestatusairport")) {
			session.removeAttribute("pagevalue");
			LOG.info("remove session atribut pagevalue");
		}
		
		if (!commandName.equals("addplane")
				&& !commandName.equals("savenewplane")) {
			session.removeAttribute("addplanevalidate");
			LOG.info("remove session atribut addplanevalidate");
		}

		if (!commandName.equals("saveupdateplane")
				&& !commandName.equals("updateplane")) {
			session.removeAttribute("validateupdateplane");
			LOG.info("remove session atribut validateupdateplane");
		}
		
		return accessMap.get(userRole).contains(commandName)
				|| commons.contains(commandName);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");
		// roles
		accessMap.put(Roles.ADMINISTRATOR,
				asList(fConfig.getInitParameter("administrator")));
		accessMap.put(Roles.NAVIGATOR,
				asList(fConfig.getInitParameter("brigade")));
		accessMap.put(Roles.PILOT,
				asList(fConfig.getInitParameter("brigade")));
		accessMap.put(Roles.RADIOMAN,
				asList(fConfig.getInitParameter("brigade")));
		accessMap.put(Roles.STEWARDESS,
				asList(fConfig.getInitParameter("brigade")));
		accessMap.put(Roles.DISPATCHER,
				asList(fConfig.getInitParameter("dispatcher")));
		LOG.trace("Access map --> " + accessMap);

		// commons
		commons = asList(fConfig.getInitParameter("common"));
		LOG.trace("Common commands --> " + commons);

		// out of control
		outOfControl = asList(fConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);
		//
		LOG.debug("Filter initialization finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}
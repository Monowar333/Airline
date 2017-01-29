package ua.nure.korkh.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.exception.AppException;
import ua.nure.korkh.SummaryTask4.web.command.Command;
import ua.nure.korkh.SummaryTask4.web.command.CommandContainer;

/**
 * Main servlet controller.
 * 
 * @author Korkh
 * 
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger LOG = Logger.getLogger(Controller.class);

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Controller starts");

		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);
		System.out.println(request.getMethod());
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		String path = Path.PAGE_ERROR_PAGE;
		RequestDispatcher disp = null;
		HttpSession session = request.getSession(false);
		try {
			path = command.execute(request, response);
			if (path == null) {
				LOG.info("Redirect to address = " + path);
				LOG.info("Controller proccessing finished");
				disp = request.getRequestDispatcher(Path.PAGE_ERROR_PAGE);
				disp.forward(request, response);
			} else if (request.getMethod().equals(RequaastType.GET.toString())) {
				LOG.info("Forward to address = " + path);
				LOG.info("Controller proccessing finished");
				disp = request.getRequestDispatcher(path);
				disp.forward(request, response);
			} else if (request.getMethod().equals(RequaastType.POST.toString())) {
				LOG.info("Redirect to address = " + path);
				LOG.info("Controller proccessing finished");
				response.sendRedirect(path);
			}

		} catch (AppException appException) {
			session.setAttribute("errorMessage", appException.getMessage());
			LOG.error(appException.getMessage());
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request,
					response);
		}
	}

}
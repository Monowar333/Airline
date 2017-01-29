package ua.nure.korkh.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.exception.AppException;

public class EditLanguageCommand extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(EditLanguageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		LOG.info("Start executing EditLanguageCommand");

		String currentCommand = null;
		String language = null;
		HttpSession session = request.getSession(true);

		currentCommand = request.getParameter("currentCommand");
		language = request.getParameter("language");

		LOG.info("currentCommand: " + currentCommand);
		LOG.info("language: " + language);

		session.setAttribute("userLanguage", language);

		if (currentCommand.equals("") || (currentCommand == null)
				|| currentCommand.equals("editLanguage")) {
			return "/startpage";
		} else {
			return Path.COMMAND_REDIRECT_TO_COMMAND + currentCommand;
		}
	}

}

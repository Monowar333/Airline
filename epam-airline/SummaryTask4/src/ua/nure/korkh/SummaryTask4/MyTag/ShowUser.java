package ua.nure.korkh.SummaryTask4.MyTag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.functional.LocaleUtil;
/**
 * For user tag
 * @author Korkh
 *
 */
public class ShowUser extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ShowUser.class);

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public int doStartTag() throws JspException {

		HttpSession session = pageContext.getSession();
		String name = LocaleUtil.getValueByKey("cabinet.admin.users.name",
				session);
		String surname = LocaleUtil.getValueByKey(
				"cabinet.admin.users.surname", session);
		String telephone = LocaleUtil.getValueByKey(
				"cabinet.admin.users.telephone", session);
		String email = LocaleUtil.getValueByKey("cabinet.admin.users.email",
				session);
		String dateofbirthday = LocaleUtil.getValueByKey(
				"cabinet.admin.users.datebirthday", session);
		String sex = LocaleUtil.getValueByKey("cabinet.admin.users.sex",
				session);

		try {
			JspWriter jspOut = pageContext.getOut();

			jspOut.println("<table id = \"user\">");
			jspOut.println("<tr>");
			jspOut.println("<td>" + name + "</td>");
			jspOut.println("<td>" + user.getName() + "</td>");
			jspOut.println("</tr>");
			jspOut.println("<tr>");
			jspOut.println("<td>" + surname + "</td>");
			jspOut.println("<td>" + user.getSuname() + "</td>");
			jspOut.println("</tr>");
			jspOut.println("<tr>");
			jspOut.println("<td>" + telephone + "</td>");
			jspOut.println("<td>" + user.getTelephone() + "</td>");
			jspOut.println("</tr>");
			jspOut.println("<tr>");
			jspOut.println("<td>" + email + "</td>");
			jspOut.println("<td>" + user.getEmail() + "</td>");
			jspOut.println("</tr>");
			jspOut.println("<tr>");
			jspOut.println("<td>" + dateofbirthday + "</td>");
			jspOut.println("<td>" + user.getDateofbirth() + "</td>");
			jspOut.println("</tr>");
			jspOut.println("<tr>");
			jspOut.println("<td>" + sex + "</td>");
			jspOut.println("<td>" + user.getSex() + "</td>");
			jspOut.println("</tr>");

			jspOut.println("</tr>");
			jspOut.println("</table>");
		} catch (IOException e) {
			LOG.error(e);
		}
		return SKIP_BODY;
	}
}

package ua.nure.korkh.SummaryTask4.MyTag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Current Date Tag
 * 
 * @author Korkh
 * 
 */
public class TodayTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String mFormat;

	public void setFormat(String pFormat) {
		mFormat = pFormat;
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Date today = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
			String time = "<hr><b> Current Time:" + dateFormatter.format(today)
					+ "</b><hr/>";
			out.print(time);

		} catch (IOException ioe) {
			throw new JspException("Error: " + ioe.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return SKIP_PAGE;
	}
}
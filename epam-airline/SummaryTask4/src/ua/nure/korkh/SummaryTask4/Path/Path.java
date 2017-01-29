package ua.nure.korkh.SummaryTask4.Path;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Korkh
 * 
 */
public final class Path {

	// recaptch
	public static final String SITE_KEY = "6LeBwxIUAAAAAOpP25fsCmCFjnLWcvy0jJObcBek";

	public static final String SECRET_KEY = "6LeBwxIUAAAAAFEwRNztn9atT5IAn16wbRYV83-L";

	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_ERROR_ACCEPTREGISTR = "/WEB-INF/jsp/acceptregistr.jsp";
	public static final String COMMAND_REDIRECT_TO_COMMAND = "controller?command=";
	/**
	 * admin page forward
	 */
	public static final String PAGE_ADMIN_CABINET = "/WEB-INF/jsp/admin/admincabinet.jsp";
	public static final String PAGE_ADMIN_UPDATE_USER = "/WEB-INF/jsp/admin/updateuser.jsp";
	public static final String PAGE_ADMIN_CLAIM_LIST = "/WEB-INF/jsp/admin/claimlist.jsp";
	public static final String PAGE_ADMIN_FLIGHT_LIST = "/WEB-INF/jsp/admin/flightlist.jsp";
	public static final String PAGE_ADMIN_ADD_USER = "/WEB-INF/jsp/admin/adduser.jsp";
	public static final String PAGE_ADMIN_ADD_FLIGHT = "/WEB-INF/jsp/admin/addflight.jsp";
	public static final String PAGE_ADMIN_UPDATE_FLIGHT = "/WEB-INF/jsp/admin/updateflight.jsp";
	public static final String PAGE_ADMIN_BRIGADE_FLIGHT = "/WEB-INF/jsp/admin/brigade.jsp";
	public static final String PAGE_ADMIN_PLANE_LIST = "/WEB-INF/jsp/admin/planelist.jsp";
	public static final String PAGE_ADMIN_ADD_PLANE = "/WEB-INF/jsp/admin/addplane.jsp";
	public static final String PAGE_ADMIN_UPDATE_PLANE = "/WEB-INF/jsp/admin/updateplane.jsp";
	
	/**
	 * admin commands
	 */
	public static final String COMMAND_PLANE_MENU = "controller?command=planelist";
	public static final String COMMAND_ADMIN_CABINET = "controller?command=admincabinet";
	public static final String COMMAND_FlIGHT_MENU = "controller?command=flightlist";
	public static final String COMMAND_CLAIM_MENU = "controller?command=claimlistadmin";
	public static final String COMMAND_ADD_USER = "controller?command=adduser";
	public static final String COMMAND_UPDATE_USER = "controller?command=updateuser";
	public static final String COMMAND_ADD_FLIGHT = "controller?command=addflight";
	public static final String COMMAND_UPDATE_FLIGHT = "controller?command=updateflight";
	public static final String COMMAND_ADD_PLANE = "controller?command=addplane";
	/**
	 * dispatcher page forward
	 */
	public static final String PAGE_DISPATCHER_CABINET = "/WEB-INF/jsp/dispatcher/dispatchercabinet.jsp";
	public static final String PAGE_DISPATCHER_BRIGADE_FLIGHT = "/WEB-INF/jsp/dispatcher/flightbrigade.jsp";
	public static final String PAGE_DISPATCHER_ADD_CALAIM = "/WEB-INF/jsp/dispatcher/addclaim.jsp";
	public static final String PAGE_DISPATCHER_CALAIMS = "/WEB-INF/jsp/dispatcher/claimlist.jsp";
	public static final String PAGE_DISPATCHER_AIRPORTLIST = "/WEB-INF/jsp/dispatcher/airportlist.jsp";

	/**
	 * dispatcher commands
	 */
	public static final String COMMAND_DISPATCHER_CABINET = "controller?command=dispatchercabinet";
	public static final String COMMAND_ADD_BRIGADE = "controller?command=watchbrigade";
	public static final String COMMAND_ADD_CLAIM = "controller?command=addclaim";
	public static final String COMMAND_GO_TO_AIRPORT_LIST = "controller?command=listairport";
	/**
	 * brigade page forward
	 */
	public static final String PAGE_BRIGADE_CABINET = "/WEB-INF/jsp/brigade/brigadecabinet.jsp";

	/**
	 * brigade commands
	 */

	public static final String COMMAND_BRIGADE_CABINET = "controller?command=brigadecabinet";
}
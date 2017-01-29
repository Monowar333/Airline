package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;

public class AdminCabinetTest extends Mockito{
	
	private List<User> userlist;
	private User user;
	private List<Role> rolelist;
	List<Plane> plainslist;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private HttpSession session;
	@Mock
	private DAOFactory factorty;
	@Mock
	private UserDAO userDAO;
	@Mock
	private RoleDAO roleDAO;


	private AdminCabinet addFligh = new AdminCabinet();

	@Before
	public void setupCommand() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Post_1() throws Exception {
		when(session.getAttribute("user")).thenReturn(user);
		when(factorty.getRoleDAO()).thenReturn(roleDAO);
		when(factorty.getUserDAO()).thenReturn(userDAO);
		when(roleDAO.findAll()).thenReturn(rolelist);
		when(req.getSession()).thenReturn(session);
		AdminCabinet.setFactorty(factorty);
		assertEquals(Path.PAGE_ADMIN_CABINET, addFligh.execute(req, resp));
	}


}

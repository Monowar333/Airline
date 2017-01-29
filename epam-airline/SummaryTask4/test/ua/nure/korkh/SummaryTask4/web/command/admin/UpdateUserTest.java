package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.UserFormValidate;

public class UpdateUserTest extends Mockito {
	User user;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private HttpSession session;
	@Mock
	private DAOFactory factorty;
	@Mock
	private UserDAO userDao;
	@Mock
	private RoleDAO roleDao;

	private UpdateUser updateUser = new UpdateUser();

	@Before
	public void setupCommand() {
		user = new UserFormValidate();
		MockitoAnnotations.initMocks(this);
	}
	@Test
	
	
	public void GET_1() throws Exception {
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(factorty.getRoleDAO()).thenReturn(roleDao);
		when(req.getMethod()).thenReturn("GET");
		when(req.getParameter("check")).thenReturn("1");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("updateuser")).thenReturn(user);
		when(userDao.findByID(1)).thenReturn(user);
		when(req.getSession()).thenReturn(session);
		updateUser.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	public void GET_2() throws Exception {
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(factorty.getRoleDAO()).thenReturn(roleDao);
		when(req.getMethod()).thenReturn("GET");
		when(req.getParameter("check")).thenReturn("1");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("updateuser")).thenReturn(null);
		when(userDao.findByID(1)).thenReturn(user);
		when(req.getSession()).thenReturn(session);
		updateUser.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_1() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADMIN_CABINET, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_2() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-7701-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_3() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("pTest");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_4() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("pTest");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_5() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("testgmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_6() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0+507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_7() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("malettt");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
	
	@Test
	public void POST_8() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("ggg");
		when(session.getAttribute("updateuser")).thenReturn(new UserFormValidate());
		when(req.getSession()).thenReturn(session);
		doNothing().when(userDao).UpdateUser(any(UserFormValidate.class));
		updateUser.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_USER, updateUser.execute(req, resp));
	}
}

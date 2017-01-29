package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

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
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.UserFormValidate;

public class SaveUserTest extends Mockito{

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

	private SaveUser saveUser = new SaveUser();

	@Before
	public void setupCommand() {
		user = new User();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Execute_1() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(session.getAttribute("user")).thenReturn(new User());
		when(req.getSession()).thenReturn(session);
		when(userDao.findByEmail(anyString()))
				.thenReturn(null);
		when(userDao.findByLogin(anyString()))
		.thenReturn(null);
		doNothing().when(userDao).insertUser(any(UserFormValidate.class));
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADMIN_CABINET, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_2() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("maleeee");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_3() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-012215");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_4() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test‡‡‡‡≥≥≥");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_5() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("‡ÔÔ‡ÔÂÍÂ");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("---050757254321230");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	
	
	@Test
	public void Execute_6() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("fffest");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	
	@Test
	public void Execute_7() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("dddddest");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	
	@Test
	public void Execute_8() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("fffffest");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_9() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("testgmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_10() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		when(userDao.findByEmail(anyString())).thenReturn(user);
		when(userDao.findByLogin(anyString())).thenReturn(null);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	
	@Test
	public void Execute_11() throws Exception {

		when(factorty.getUserDAO()).thenReturn(userDao);
		when(req.getParameter("dateofbirth")).thenReturn("2017-01-15");
		when(req.getParameter("login")).thenReturn("test");
		when(req.getParameter("password")).thenReturn("testtt");
		when(req.getParameter("suname")).thenReturn("Test");
		when(req.getParameter("name")).thenReturn("Test");
		when(req.getParameter("email")).thenReturn("test@gmail.com");
		when(req.getParameter("telephone")).thenReturn("0507572540");
		when(req.getParameter("sex")).thenReturn("male");
		when(req.getParameter("role")).thenReturn("1");
		when(req.getSession()).thenReturn(session);
		when(userDao.findByEmail(anyString())).thenReturn(null);
		when(userDao.findByLogin(anyString())).thenReturn(user);
		saveUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_USER, saveUser.execute(req, resp));
	}
	

}

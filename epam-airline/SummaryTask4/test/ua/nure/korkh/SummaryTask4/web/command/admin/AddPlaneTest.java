package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.web.Controller;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.PlaneFormValidate;

public class AddPlaneTest extends Mockito{

	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private DAOFactory factorty;
	@Mock
	private PlaneDAO planeDAO;
	@Mock
	private HttpSession session;

	private AddPlane addPlane = new AddPlane();

	@Before
	public void setupCommand() {
		
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Get_1() throws Exception {
		when(req.getMethod()).thenReturn("GET");
		assertEquals(Path.PAGE_ADMIN_ADD_PLANE, addPlane.execute(req, resp));
	}
	
	
	@Test
	public void POST_1() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("dfsdfs");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("5");
		doNothing().when(planeDAO).insertPlane(any(PlaneFormValidate.class));
		addPlane.setFactorty(factorty);
		assertEquals(Path.COMMAND_PLANE_MENU, addPlane.execute(req, resp));
	}
	
	@Test
	public void POST_2() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(req.getSession()).thenReturn(session);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("5");
		doNothing().when(planeDAO).insertPlane(any(PlaneFormValidate.class));
		addPlane.setFactorty(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, addPlane.execute(req, resp));
	}
	
	

	@Test
	public void POST_3() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(req.getSession()).thenReturn(session);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("asdas");
		when(req.getParameter("crew")).thenReturn("dgsdfgd");
		when(req.getParameter("numberofseats")).thenReturn("5");
		doNothing().when(planeDAO).insertPlane(any(PlaneFormValidate.class));
		addPlane.setFactorty(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, addPlane.execute(req, resp));
	}
	
	

	@Test
	public void POST_4() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(req.getSession()).thenReturn(session);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("rewrerw");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("weqweq");
		doNothing().when(planeDAO).insertPlane(any(PlaneFormValidate.class));
		addPlane.setFactorty(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, addPlane.execute(req, resp));
	}
	

}

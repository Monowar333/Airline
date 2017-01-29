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
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.PlaneFormValidate;

public class UpdatePlaneTest extends Mockito {

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

	private UpdatePlane updatePlane = new UpdatePlane();

	@Before
	public void setupCommand() {
		
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Get_1() throws Exception {
		when(req.getMethod()).thenReturn("GET");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(new PlaneFormValidate());
		assertEquals(Path.PAGE_ADMIN_UPDATE_PLANE, updatePlane.execute(req, resp));
	}
	
	@Test
	public void Get_2() throws Exception {
		when(req.getMethod()).thenReturn("GET");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(null);
		when(req.getParameter("check")).thenReturn("1");
		when(planeDAO.findByID(1)).thenReturn(new Plane());
		updatePlane.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_UPDATE_PLANE, updatePlane.execute(req, resp));
	}
	
	
	@Test
	public void POST_1() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("dfsdfs");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("5");
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(new PlaneFormValidate());
		doNothing().when(planeDAO).UpdatePlane(any(PlaneFormValidate.class));
		updatePlane.setFactory(factorty);
		assertEquals(Path.COMMAND_PLANE_MENU, updatePlane.execute(req, resp));
	}
	@Test
	public void POST_2() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("5");
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(new PlaneFormValidate());
		doNothing().when(planeDAO).UpdatePlane(any(PlaneFormValidate.class));
		updatePlane.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, updatePlane.execute(req, resp));
	}
	
	

	@Test
	public void POST_3() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("dfsdfs");
		when(req.getParameter("crew")).thenReturn("dfsdf");
		when(req.getParameter("numberofseats")).thenReturn("5");
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(new PlaneFormValidate());
		doNothing().when(planeDAO).UpdatePlane(any(PlaneFormValidate.class));
		updatePlane.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, updatePlane.execute(req, resp));
	}
	
	@Test
	public void POST_4() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getParameter("model")).thenReturn("dfsdfs");
		when(req.getParameter("crew")).thenReturn("5");
		when(req.getParameter("numberofseats")).thenReturn("dsfsdfs");
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("validateupdateplane")).thenReturn(new PlaneFormValidate());
		doNothing().when(planeDAO).UpdatePlane(any(PlaneFormValidate.class));
		updatePlane.setFactory(factorty);
		assertEquals(Path.COMMAND_ADD_PLANE, updatePlane.execute(req, resp));
	}

}

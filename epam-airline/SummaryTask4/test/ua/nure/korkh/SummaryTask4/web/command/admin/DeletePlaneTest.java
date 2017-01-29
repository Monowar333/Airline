package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;

public class DeletePlaneTest extends Mockito{

	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private DAOFactory factorty;
	@Mock
	private PlaneDAO palneDAO;



	private DeletePlane deletePlane = new DeletePlane();

	@Before
	public void setupCommand() {
		MockitoAnnotations.initMocks(this);

	}
 
	@Test
	public void Execute_1() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"1","3"});
		when(factorty.getPlaisDAO()).thenReturn(palneDAO);
		doNothing().when(palneDAO).deletePlane(new Integer[]{1,3});
		deletePlane.setFactory(factorty);
		assertEquals(Path.COMMAND_PLANE_MENU, deletePlane.execute(req, resp));
	}
	
	@Test(expected=NumberFormatException.class)
	public void Execute_2() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"sdasd","sads"});
		when(factorty.getPlaisDAO()).thenReturn(palneDAO);
		deletePlane.setFactory(factorty);
		deletePlane.execute(req, resp);
	}
}

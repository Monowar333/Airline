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
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;

public class DeleteUserTest extends Mockito{

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



	private DeleteUser deleteUser = new DeleteUser();

	@Before
	public void setupCommand() {
		MockitoAnnotations.initMocks(this);

	}
 
	@Test
	public void Execute_1() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"1","3"});
		when(factorty.getUserDAO()).thenReturn(userDAO);
		doNothing().when(userDAO).deleteUsers(new Integer[]{1,3});
		deleteUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADMIN_CABINET, deleteUser.execute(req, resp));
	}
	
	@Test(expected=NumberFormatException.class)
	public void Execute_2() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"sdasd","sads"});
		when(factorty.getUserDAO()).thenReturn(userDAO);
		deleteUser.setFactory(factorty);
		assertEquals(Path.COMMAND_ADMIN_CABINET, deleteUser.execute(req, resp));
	}


}

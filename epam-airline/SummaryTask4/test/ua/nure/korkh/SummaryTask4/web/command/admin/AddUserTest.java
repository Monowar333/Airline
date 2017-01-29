package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.web.Controller;


public class AddUserTest extends Mockito{


	private List<Role> rolelist;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private DAOFactory factorty;
	@Mock
	private RoleDAO roleDAO;

	private Controller controller = new Controller();
	private AddUser addFligh = new AddUser();

	@Before
	public void setupCommand() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Get_1() throws Exception {
		when(factorty.getRoleDAO()).thenReturn(roleDAO);
		when(roleDAO.findAll()).thenReturn(rolelist);
		AddUser.setFactorty(factorty);
		assertEquals(Path.PAGE_ADMIN_ADD_USER, addFligh.execute(req, resp));
	}
	
	
	@Test
	public void execute_1() throws Exception {
		when(req.getMethod()).thenReturn("GET");
		when(req.getParameter("command")).thenReturn("adduser");
		//when(adduser.execute(req, resp)).thenReturn(Path.PAGE_ADMIN_ADD_USER);
		controller.doGet(req, resp);
	}
	

}

package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;

public class WatchBrigadeAdminTest extends Mockito{

	private List<BrigadeBean> brigadeBean;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private DAOFactory factorty;
	@Mock
	private BrigadeBeanDAO brigadeBeanDAO;
	@Mock
	private RoleDAO roleDAO;


	private WatchBrigadeAdmin watchBrigadeAdmin = new WatchBrigadeAdmin();

	@Before
	public void setupCommand() {
		brigadeBean = new ArrayList<>();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Execute_1() throws Exception {
		when(req.getParameter("id")).thenReturn("1");
		when(factorty.getBrigadeBeanDAO()).thenReturn(brigadeBeanDAO);
		when(factorty.getRoleDAO()).thenReturn(roleDAO);
		when(brigadeBeanDAO.findAllByIdFlight(1)).thenReturn(brigadeBean);
		watchBrigadeAdmin.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_BRIGADE_FLIGHT, watchBrigadeAdmin.execute(req, resp));
	}

}

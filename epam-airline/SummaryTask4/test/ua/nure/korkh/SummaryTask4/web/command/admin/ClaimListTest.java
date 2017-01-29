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
import org.powermock.api.mockito.PowerMockito;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.entity.Claim;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;

public class ClaimListTest extends Mockito{

	private User user;
	private Claim claim;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private HttpSession session;
	@Mock
	private DAOFactory factorty;
	@Mock
	private ClaimBeanDAO claimBeanDAO;
	@Mock
	private ClaimDAO claimDAO;


	private ClaimList claimList = new ClaimList();

	@Before
	public void setupCommand() {
		claim = new Claim();
		claim.setId(1);
		claim.setStatus("executed");
		user = new User();
		user.setId(1);
		MockitoAnnotations.initMocks(this);

	}

	// status is incorrect  
	@Test
	public void Post_1() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("idclaim")).thenReturn("1");
		when(req.getParameter("changestaus")).thenReturn("executed");
		when(factorty.getClaimDAO()).thenReturn(claimDAO);
		when(claimDAO.findByID(anyInt())).thenReturn(claim);
		when(session.getAttribute("user")).thenReturn(user);
		when(req.getSession()).thenReturn(session);
		doNothing().when(claimDAO).changeStatus(any(Claim.class));
		claimList.setFactorty(factorty);
		assertEquals(Path.COMMAND_CLAIM_MENU, claimList.execute(req, resp));
	}

	@Test
	public void Post_2() throws Exception {
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("idclaim")).thenReturn("1");
		when(req.getParameter("changestaus")).thenReturn("sda");
		when(factorty.getClaimDAO()).thenReturn(claimDAO);
		when(claimDAO.findByID(1)).thenReturn(claim);
		when(session.getAttribute("user")).thenReturn(user);
		when(req.getSession()).thenReturn(session);
		verify(claimDAO,never()).changeStatus(claim);
		verifyNoMoreInteractions(claimDAO);
		claimList.setFactorty(factorty);
		assertEquals(Path.COMMAND_CLAIM_MENU, claimList.execute(req, resp));
	}
	
	@Test
	public void Get_1() throws Exception {
		when(req.getMethod()).thenReturn("GET");
		when(factorty.getClaimBeanDAO()).thenReturn(claimBeanDAO);
		when(claimBeanDAO.findByIDAdmin(1)).thenReturn(new ArrayList<ClaimBean>());
		when(session.getAttribute("user")).thenReturn(user);
		when(req.getSession()).thenReturn(session);
		claimList.setFactorty(factorty);
		assertEquals(Path.PAGE_ADMIN_CLAIM_LIST, claimList.execute(req, resp));
	}

	

}

package ua.nure.korkh.SummaryTask4.web.command.admin;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.entity.Claim;
import ua.nure.korkh.SummaryTask4.entity.User;

public class DeleteFligtsTest extends Mockito {

	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private HttpSession session;
	@Mock
	private DAOFactory factorty;
	@Mock
	private FlightDAO flightDAO;



	private DeleteFligts deleteFligts = new DeleteFligts();

	@Before
	public void setupCommand() {
		MockitoAnnotations.initMocks(this);

	}
 
	@Test
	public void Execute_1() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"1","3"});
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		doNothing().when(flightDAO).deleteFlight(new Integer[]{1,3});
		deleteFligts.setFactory(factorty);
		assertEquals(Path.COMMAND_FlIGHT_MENU, deleteFligts.execute(req, resp));
	}
	
	@Test(expected=NumberFormatException.class)
	public void Execute_2() throws Exception {
		when(req.getParameterValues("check")).thenReturn(new String[]{"sdasd","sads"});
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		deleteFligts.setFactory(factorty);
		assertEquals(Path.COMMAND_FlIGHT_MENU, deleteFligts.execute(req, resp));
	}

	

}

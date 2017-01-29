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

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.entity.Role;

public class FlightListTest extends Mockito {

	private List<FlightsBean> flightBean;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private DAOFactory factorty;
	@Mock
	private FligtsBeanDAO flightDAO;

	private FlightList flightList = new FlightList();

	@Before
	public void setupCommand() {
		flightBean = new ArrayList<>();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void Execute_1() throws Exception {
		when(req.getParameter("findBy")).thenReturn("id");
		when(req.getParameter("findvalue")).thenReturn("5");
		when(req.getParameter("sort")).thenReturn("");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_2() throws Exception {
		when(req.getParameter("sort")).thenReturn("byid");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_3() throws Exception {
		when(req.getParameter("sort")).thenReturn("byfromcity");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_4() throws Exception {
		when(req.getParameter("sort")).thenReturn("byfromcountry");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_5() throws Exception {
		when(req.getParameter("sort")).thenReturn("byfromname");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_6() throws Exception {
		when(req.getParameter("sort")).thenReturn("bywherecity");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_7() throws Exception {
		when(req.getParameter("sort")).thenReturn("bywherecountry");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_8() throws Exception {
		when(req.getParameter("sort")).thenReturn("bywherename");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_9() throws Exception {
		when(req.getParameter("sort")).thenReturn("number");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_10() throws Exception {
		when(req.getParameter("sort")).thenReturn("departuredate");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_11() throws Exception {
		when(req.getParameter("sort")).thenReturn("plains");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}

	@Test
	public void Execute_12() throws Exception {
		when(req.getParameter("sort")).thenReturn("status");
		when(factorty.getFligtsBeanDAO()).thenReturn(flightDAO);
		when(flightDAO.findAll()).thenReturn(flightBean);
		flightList.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_FLIGHT_LIST, flightList.execute(req, resp));
	}
}

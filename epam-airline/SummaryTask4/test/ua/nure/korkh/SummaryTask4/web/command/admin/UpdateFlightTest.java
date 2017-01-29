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
import ua.nure.korkh.SummaryTask4.Path.Path;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.web.ValidateForms.FlightFormValidate;

public class UpdateFlightTest extends Mockito {
	private Flight flight;
	private List<Airport> airportlist;
	private List<Plane> plainslist;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse resp;
	@Mock
	private HttpSession session;
	@Mock
	private DAOFactory factorty;
	@Mock
	private PlaneDAO planeDAO;
	@Mock
	private FlightDAO flightDAO;
	@Mock
	private AirportDAO airportDAO;

	private UpdateFlight updateFlight = new UpdateFlight();
	
	@Before
	public void setupCommand() {
		flight = new Flight();
		airportlist = new ArrayList<>();
		Airport a = new Airport();
		a.setCity("test");
		a.setCountry("test");
		a.setId(1);
		airportlist.add(a);
		plainslist = new ArrayList<>();
		Plane p = new Plane();
		p.setCrew(5);
		p.setId(1);
		p.setModel("Test");
		plainslist.add(p);
		MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void GET_1() throws Exception {
		when(factorty.getAirportDAO()).thenReturn(airportDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(req.getMethod()).thenReturn("GET");
		when(req.getParameter("check")).thenReturn("1");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(flightDAO.findByID(1)).thenReturn(flight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
	
	@Test
	public void GET_2() throws Exception {
		when(factorty.getAirportDAO()).thenReturn(airportDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(req.getMethod()).thenReturn("GET");
		when(req.getParameter("check")).thenReturn("1");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(null);
		when(flightDAO.findByID(1)).thenReturn(flight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.PAGE_ADMIN_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
	@Test
	public void Post_1() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("2");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("test");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		when(planeDAO.findByDepartureDate(Date.valueOf("2017-01-15"), 1))
				.thenReturn(new ArrayList<Plane>());
		doNothing().when(flightDAO).UpdateFlight(any(FlightFormValidate.class));
		System.out.println(req.getAttribute("plainslist"));
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_FlIGHT_MENU, updateFlight.execute(req, resp));
	}
	

	@Test
	public void Post_2() throws Exception {
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017--100-15");
		when(req.getParameter("fromwhence")).thenReturn("2");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("test");
		when(planeDAO.findByDepartureDate(Date.valueOf("2017-01-15"), 1))
		.thenReturn(new ArrayList<Plane>());
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}

	@Test
	public void Post_3() throws Exception {
		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("1");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("test");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(planeDAO.findByDepartureDate(Date.valueOf("2017-01-15"), 1))
				.thenReturn(plainslist);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}

	@Test
	public void Post_4() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("1");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_inn");
		when(req.getParameter("number")).thenReturn("test");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}

	@Test
	public void Post_5() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("2");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
	@Test
	public void Post_6() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("");
		when(req.getParameter("where")).thenReturn("1");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
	@Test
	public void Post_7() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("1");
		when(req.getParameter("where")).thenReturn("");
		when(req.getParameter("idplains")).thenReturn("1");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
	@Test
	public void Post_8() throws Exception {

		when(factorty.getFlightDAO()).thenReturn(flightDAO);
		when(factorty.getPlaisDAO()).thenReturn(planeDAO);
		when(req.getMethod()).thenReturn("POST");
		when(req.getParameter("departuredate")).thenReturn("2017-01-15");
		when(req.getParameter("fromwhence")).thenReturn("1");
		when(req.getParameter("where")).thenReturn("2");
		when(req.getParameter("idplains")).thenReturn("");
		when(req.getParameter("status")).thenReturn("check_in");
		when(req.getParameter("number")).thenReturn("");
		FlightFormValidate validateupdateflight = new FlightFormValidate();
		when(session.getAttribute("validateupdateflight")).thenReturn(validateupdateflight);
		when(req.getSession()).thenReturn(session);
		updateFlight.setFactory(factorty);
		assertEquals(Path.COMMAND_UPDATE_FLIGHT, updateFlight.execute(req, resp));
	}
	
}

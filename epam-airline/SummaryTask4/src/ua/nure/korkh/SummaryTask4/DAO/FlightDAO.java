package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Flight Table
 * 
 * @author Korkh
 */
public interface FlightDAO {
	/**
	 * Returns Flights with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Flights entity.
	 * @throws DBException
	 */
	Flight findByID(Integer id) throws DBException;

	/**
	 * Returns all Flights.
	 * 
	 * @return List of Flights entities.
	 * @throws DBException
	 */
	List<Flight> findAll() throws DBException;

	/**
	 * insert new Flights
	 * 
	 * @throws DBException
	 */
	void insertFlight(Flight flight) throws DBException;

	/**
	 * update Flights
	 * 
	 * @throws DBException
	 */
	void UpdateFlight(Flight flight) throws DBException;

	/**
	 * change status Flights
	 * 
	 * @throws DBException
	 */
	void changeStatus(Flight flight) throws DBException;

	/**
	 * delete Flights with input id's
	 * 
	 * @throws DBException
	 */
	void deleteFlight(Integer... check) throws DBException;
}

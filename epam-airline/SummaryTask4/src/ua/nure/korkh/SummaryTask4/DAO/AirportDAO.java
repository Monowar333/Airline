package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Airport Table
 * 
 * @author Korkh
 */
public interface AirportDAO {
	/**
	 * Returns airport with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Airport entity.
	 * @throws DBException
	 */
	Airport findByID(Integer id) throws DBException;

	/**
	 * Returns all Airport.
	 * 
	 * @return List of Airport entities.
	 * @throws DBException
	 */
	List<Airport> findAll() throws DBException;

	/**
	 * update Airport
	 * 
	 * @param airport
	 * @throws DBException
	 */
	void changeStatusAirport(Airport airport) throws DBException;

	/**
	 * insert Airports
	 * 
	 * @param airport
	 * @throws DBException
	 */
	public void insertAirport(List<Airport> airport) throws DBException;
}

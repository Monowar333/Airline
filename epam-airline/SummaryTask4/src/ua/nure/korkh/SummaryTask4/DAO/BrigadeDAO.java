package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Brigade;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Brigade Table
 * 
 * @author Korkh
 */
public interface BrigadeDAO {
	/**
	 * Returns Brigade with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Brigade entity.
	 * @throws DBException
	 */
	Brigade findByID(Integer id) throws DBException;

	/**
	 * Returns all Brigade.
	 * 
	 * @return List of Brigade entities.
	 * @throws DBException
	 */
	List<Brigade> findAll() throws DBException;

	/**
	 * Returns Brigade with the given id flight.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return List of Brigade entities.
	 * @throws DBException
	 */
	List<Brigade> findBrigade(Integer idFlight) throws DBException;

	/**
	 * Returns Brigade with the given id user.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return List of Brigade entities.
	 * @throws DBException
	 */
	List<Brigade> findflight(Integer idUser) throws DBException;

	/**
	 * insert new Brigade
	 * 
	 * @param list
	 * @throws DBException
	 */
	void addBrigade(List<Brigade> list) throws DBException;
	
	
	/**
	 * delete Brigade with input id
	 * 
	 * @throws DBException
	 */
	void DeleteBrigade(Brigade brigade) throws DBException;
}

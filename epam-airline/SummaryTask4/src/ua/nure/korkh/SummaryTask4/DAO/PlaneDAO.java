package ua.nure.korkh.SummaryTask4.DAO;

import java.sql.Date;
import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Plains Table
 * 
 * @author Korkh
 */
public interface PlaneDAO {
	/**
	 * Returns Plains with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Plains entity.
	 * @throws DBException
	 */
	Plane findByID(Integer id) throws DBException;

	/**
	 * Returns all Plains.
	 * 
	 * @return List of Plains entities.
	 * @throws DBException
	 */
	List<Plane> findAll() throws DBException;
	
	/**
	 * Returns all Plains.
	 * 
	 * @return List of Plains entities.
	 * @throws DBException
	 */
	List<Plane> findByDepartureDate(Date date, Integer idplains) throws DBException;
	
	
	/**
	 * delete Plane with input id's
	 * 
	 * @throws DBException
	 */
	void deletePlane(Integer... check) throws DBException;
	
	/**
	 * insert new Plane
	 * 
	 * @throws DBException
	 */
	void insertPlane(Plane plane) throws DBException;

	/**
	 * update Plane
	 * 
	 * @throws DBException
	 */
	void UpdatePlane(Plane plane) throws DBException;

}

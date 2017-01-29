package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Claim;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Claim Table
 * 
 * @author Korkh
 */
public interface ClaimDAO {
	/**
	 * Returns airport with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Airport entity.
	 * @throws DBException
	 */
	Claim findByID(Integer id) throws DBException;

	/**
	 * Returns all Airport.
	 * 
	 * @return List of Airport entities.
	 * @throws DBException
	 */
	List<Claim> findAll() throws DBException;

	/**
	 * insert new Claim
	 * 
	 * @throws DBException
	 */
	void insertClaim(Claim claim) throws DBException;

	/**
	 * Update Claim
	 * 
	 * @throws DBException
	 */
	void changeStatus(Claim claim) throws DBException;
}

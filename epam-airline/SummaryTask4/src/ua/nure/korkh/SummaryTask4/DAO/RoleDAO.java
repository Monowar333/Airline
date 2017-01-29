package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain Role Table
 * 
 * @author Korkh
 */
public interface RoleDAO {
	/**
	 * Returns Role with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return Role entity.
	 * @throws DBException
	 */
	Role findByID(Integer id) throws DBException;

	/**
	 * Returns all Role.
	 * 
	 * @return List of Role entities.
	 * @throws DBException
	 */
	List<Role> findAll() throws DBException;
}

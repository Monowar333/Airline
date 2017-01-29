package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * User DAO.
 * 
 * @author Korkh
 * 
 */
public interface UserDAO {
	/**
	 * Returns User with the given login.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	User findByLogin(String login) throws DBException;
	
	
	/**
	 * Returns User with the given email.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	User findByEmail(String email) throws DBException;
	
	/**
	 * Returns User with the given linck accept.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	User findByLinckAccept(String linckaccept) throws DBException;


	/**
	 * Returns User with the given Id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	User findByID(Integer id) throws DBException;

	/**
	 * Returns all User.
	 * 
	 * @return List of User entities List.
	 * @throws DBException
	 */
	List<User> findAll() throws DBException;

	/**
	 * Returns Users with the given Role.
	 * 
	 * @param Id
	 *            Id Role.
	 * @return User entites List.
	 * @throws DBException
	 */
	List<User> findByRole(Integer roleId) throws DBException;

	/**
	 * Returns Users with the given Role.
	 * 
	 * @param Id
	 *            Id Role.
	 * @return User entites List.
	 * @throws DBException
	 */
	List<User> findByRoleANDIdflight(Integer roleId, Flight flight) throws DBException;
	/**
	 * insert new User
	 * 
	 * @throws DBException
	 */
	void insertUser(User user) throws DBException;

	/**
	 * update new User
	 * 
	 * @throws DBException
	 */
	void UpdateUser(User user) throws DBException;
	
	/**
	 * change status account
	 * 
	 * @throws DBException
	 */
	void changeStatus(User user) throws DBException;

	/**
	 * delete User with input id
	 * 
	 * @throws DBException
	 */
	void deleteUsers(Integer... check) throws DBException;
}

package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain FlightsBean bean
 * 
 * @author Korkh
 */
public interface FligtsBeanDAO {
	/**
	 * Returns all Flights.
	 * 
	 * @return List of Flights entities.
	 * @throws DBException
	 */
	List<FlightsBean> findAll() throws DBException;
	
	/**
	 * Returns all by user.
	 * 
	 * @return List of Flights entities.
	 * @throws DBException
	 */
	List<FlightsBean> findAllByIdUser(Integer id) throws DBException;


	List<FlightsBean> SORTByParam(String colum, String value)
			throws DBException;
	
	/**
	 * Return List FlightsBean
	 * 
	 * @param colum
	 *            - colum name in db
	 * @param value
	 *            - value of colum
	 * @return
	 * @throws DBException
	 */
	List<FlightsBean> findByParam(String colum, String value)
			throws DBException;

	/**
	 * Return FlightsBean
	 * 
	 * @param colum
	 *            - colum name in db
	 * @param value
	 *            - value of colum
	 * @return
	 * @throws DBException
	 */
	FlightsBean findById(Integer id) throws DBException;
}

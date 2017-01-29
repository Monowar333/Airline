package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;

import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain ClaimBean bean
 * 
 * @author Korkh
 */
public interface ClaimBeanDAO {
	/**
	 * Returns ClaimBean with the given id dispatcher.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return List of ClaimBean entities.
	 * @throws DBException
	 */
	List<ClaimBean> findByIDDispatcher(Integer id) throws DBException;

	/**
	 * Returns ClaimBean with the given id admin.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return List of ClaimBean entities.
	 * @throws DBException
	 */
	List<ClaimBean> findByIDAdmin(Integer id) throws DBException;
}

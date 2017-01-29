package ua.nure.korkh.SummaryTask4.DAO;

import java.util.List;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class to obtain BrigadeBean bean
 * 
 * @author Korkh
 */
public interface BrigadeBeanDAO {
	/**
	 * Returns airport with the given id.
	 * 
	 * @param Id
	 *            Id identifier.
	 * @return BrigadeBean entity.
	 * @throws DBException
	 */
	List<BrigadeBean> findAllByIdFlight(Integer id) throws DBException;
	
}

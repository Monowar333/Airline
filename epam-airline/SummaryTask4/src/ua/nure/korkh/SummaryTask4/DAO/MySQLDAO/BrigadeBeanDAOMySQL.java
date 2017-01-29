package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class BrigadeBeanDAOMySQL implements BrigadeBeanDAO {

	private static final Logger LOG = Logger
			.getLogger(BrigadeBeanDAOMySQL.class);
	private static final String SQL_GET_FLIGHTS_BEANS = "SELECT o.id, o.idflight, u.name, u.suname, u.roleid"
			+ "	FROM brigade o, users u"
			+ "	WHERE o.idusers=u.id AND o.idflight = ?;";

	@Override
	public List<BrigadeBean> findAllByIdFlight(Integer id) throws DBException {
		List<BrigadeBean> brigadeList = new ArrayList<BrigadeBean>();
		ResultSet rs = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_GET_FLIGHTS_BEANS)) {
			ps.setInt(1, id);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				brigadeList.add(extractBrigadeBean(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_ID, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_ID, e);
		}
		return brigadeList;
	}

	private BrigadeBean extractBrigadeBean(ResultSet rs) throws SQLException {
		BrigadeBean brigade = new BrigadeBean();
		brigade.setId(rs.getInt("id"));
		brigade.setIdFligts(rs.getInt("idflight"));
		brigade.setName(rs.getString("u.name"));
		brigade.setSuname(rs.getString("u.suname"));
		brigade.setRole(rs.getInt("u.roleid"));
		return brigade;
	}

}

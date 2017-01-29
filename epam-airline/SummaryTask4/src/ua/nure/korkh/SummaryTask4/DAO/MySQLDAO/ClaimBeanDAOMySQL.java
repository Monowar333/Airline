package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.bean.ClaimBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class ClaimBeanDAOMySQL implements ClaimBeanDAO {

	private static final Logger LOG = Logger.getLogger(ClaimBeanDAOMySQL.class);
	private static final String SQL_SELECT_BY_IDDISPATCHER = "select * from claim " +
			"inner join flight s " + 
			"on claim.idflight = s.id " + 
			"left join users u " +
			"on claim.idadministrator = u.id  or  claim.idadministrator = null " +
			"left join users r " +
			"on claim.iddispatcher = r.id " + 
			"where claim.iddispatcher = ?;";
	private static final String SQL_SELECT_BY_IDADMINISTRATOR = "select * from claim " +
			"inner join flight s " + 
			"on claim.idflight = s.id " + 
			"left join users u " +
			"on claim.idadministrator = u.id " +
			"left join users r " +
			"on claim.iddispatcher = r.id  or  claim.iddispatcher = null " + 
			"where claim.idadministrator = ?;";
	@Override
	public List<ClaimBean> findByIDDispatcher(Integer id) throws DBException {
		List<ClaimBean> claimList = new ArrayList<ClaimBean>();
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_IDDISPATCHER)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				claimList.add(extractClaim(rs));
			}
		} catch (SQLException e) {
			LOG.error(
					Messages.EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_DISPATCHER,
					e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_DISPATCHER,
					e);
		}
		return claimList;
	}

	@Override
	public List<ClaimBean> findByIDAdmin(Integer id) throws DBException {
		List<ClaimBean> claimList = new ArrayList<ClaimBean>();
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_IDADMINISTRATOR)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				claimList.add(extractClaim(rs));
			}
		} catch (SQLException e) {
			LOG.error(
					Messages.EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_ADMINISTRATOR,
					e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_ADMINISTRATOR,
					e);
		}
		return claimList;
	}

	private ClaimBean extractClaim(ResultSet rs) throws SQLException {
		ClaimBean claim = new ClaimBean();
		claim.setId(rs.getInt("id"));
		claim.setNameadministrator(rs.getString("u.name"));
		claim.setSunameadministrator(rs.getString("u.suname"));
		claim.setNamedispatcher(rs.getString("r.name"));
		claim.setSunamedispatcher(rs.getString("r.suname"));
		claim.setNumber(rs.getString("s.number"));
		claim.setStatus(rs.getString("status"));
		claim.setDescription(rs.getString("description"));
		claim.setDate(rs.getDate("date"));
		return claim;
	}

}

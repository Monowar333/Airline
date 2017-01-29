package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.entity.Claim;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class ClaimDAOMySQL implements ClaimDAO {

	private static final Logger LOG = Logger.getLogger(RoleDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM claim WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM claim";
	private static final String SQL_INSERT = "INSERT INTO `claim` "
			+ "(`idadministrator`, `status`, `description`, `idflight`,"
			+ " `iddispatcher`, `date`) " + "VALUES(?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_STATUS = "UPDATE `claim` SET  `status` = ? WHERE `id` = ?;";

	@Override
	public Claim findByID(Integer id) throws DBException {
		Claim claim = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				claim = extractClaim(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_CLAIM_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_CLAIM_BY_ID,
					e);
		}
		return claim;
	}

	@Override
	public List<Claim> findAll() throws DBException {
		List<Claim> claimList = new ArrayList<Claim>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_ALL)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				claimList.add(extractClaim(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_CLAIMS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_CLAIMS, e);
		}
		return claimList;
	}

	@Override
	public void insertClaim(Claim claim) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
			ps.setInt(1, claim.getIdadministrator());
			ps.setString(2, claim.getStatus());
			ps.setString(3, claim.getDescription());
			ps.setInt(4, claim.getIdflight());
			ps.setInt(5, claim.getIddispatcher());
			ps.setDate(6, claim.getDate());
			ps.executeUpdate();

		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_INSERT_CLAIM, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_INSERT_CLAIM, e);
		}
	}

	@Override
	public void changeStatus(Claim claim) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_UPDATE_STATUS)) {
			ps.setString(1, claim.getStatus());
			ps.setLong(2, claim.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_CLAIM, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_CLAIM, e);
		}

	}

	private Claim extractClaim(ResultSet rs) throws SQLException {
		Claim claim = new Claim();
		claim.setId(rs.getInt("id"));
		claim.setIdflight(rs.getInt("idflight"));
		claim.setIdadministrator(rs.getInt("idadministrator"));
		claim.setIddispatcher(rs.getInt("iddispatcher"));
		claim.setStatus(rs.getString("status"));
		claim.setDescription(rs.getString("description"));
		claim.setDate(rs.getDate("date"));
		return claim;
	}

}

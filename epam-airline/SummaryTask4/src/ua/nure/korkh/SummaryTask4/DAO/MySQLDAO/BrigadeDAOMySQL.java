package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.BrigadeDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.entity.Brigade;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class BrigadeDAOMySQL implements BrigadeDAO {

	private static final Logger LOG = Logger.getLogger(BrigadeDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM brigade WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM brigade";
	private static final String SQL_SELECT_BRIGADE = "SELECT * FROM brigade WHERE idflight = ?";
	private static final String SQL_SELECT_FLIGHTS = "SELECT * FROM brigade WHERE idflight = ?";
	private static final String SQL_INSERT = "INSERT INTO `brigade` "
			+ "(idusers, idflight) VALUES(?, ?);";
	private static final String SQL_DELETE = "DELETE FROM `brigade` WHERE `id`= ?;";
	
	@Override
	public Brigade findByID(Integer id) throws DBException {
		Brigade brigade = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				brigade = extractBrigade(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADE_BY_ID, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_BRIGADE_BY_ID, e);
		}
		return brigade;
	}

	@Override
	public List<Brigade> findAll() throws DBException {
		List<Brigade> brigadeList = new ArrayList<Brigade>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_ALL)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				brigadeList.add(extractBrigade(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADE, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADE, e);
		}
		return brigadeList;
	}

	@Override
	public List<Brigade> findBrigade(Integer idFlight) throws DBException {
		List<Brigade> brigadeList = new ArrayList<Brigade>();
		ResultSet rs = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BRIGADE)) {
			ps.setInt(1, idFlight);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				brigadeList.add(extractBrigade(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDFLIGHT,
					e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDFLIGHT, e);
		}
		return brigadeList;
	}

	@Override
	public List<Brigade> findflight(Integer idUser) throws DBException {
		List<Brigade> brigadeList = new ArrayList<Brigade>();
		ResultSet rs = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_FLIGHTS)) {
			ps.setInt(1, idUser);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				brigadeList.add(extractBrigade(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDUSERS, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDUSERS, e);
		}
		return brigadeList;
	}

	@Override
	public void addBrigade(List<Brigade> list) throws DBException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			for (Brigade bri : list) {
				stmt = connection.prepareStatement(SQL_INSERT);
				stmt.setInt(1, bri.getIdusers());
				stmt.setInt(2, bri.getIdflight());
				stmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			}
			LOG.error(Messages.EXCEPTION_CAN_NOT_INSERT_NEW_BRIGADE, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_INSERT_NEW_BRIGADE, e);
		} finally {
			// close connection
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error(Messages.EXCEPTION_CANNOT_CLOSE_CONNECTION, e);
				throw new DBException(
						Messages.EXCEPTION_CANNOT_CLOSE_CONNECTION, e);
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				LOG.error(Messages.EXCEPTION_CANNOT_CLOSE_STATEMENT, e);
				throw new DBException(
						Messages.EXCEPTION_CANNOT_CLOSE_STATEMENT, e);
			}
		}

	}

	@Override
	public void DeleteBrigade(Brigade brigade) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
			ps.setInt(1, brigade.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_DELETE_DRIGADE, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_DELETE_DRIGADE, e);
		}	
	}
	
	private Brigade extractBrigade(ResultSet rs) throws SQLException {
		Brigade brigade = new Brigade();
		brigade.setId(rs.getInt("id"));
		brigade.setIdflight(rs.getInt("idflight"));
		brigade.setIdusers(rs.getInt("idusers"));
		return brigade;
	}
}

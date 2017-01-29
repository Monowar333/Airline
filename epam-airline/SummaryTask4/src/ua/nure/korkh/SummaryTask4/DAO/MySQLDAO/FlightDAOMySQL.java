package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class FlightDAOMySQL implements FlightDAO {

	private static final Logger LOG = Logger.getLogger(FlightDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM flight WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM flight";
	private static final String SQL_INSERT = "INSERT INTO `flight` "
			+ "(`fromwhence`, `where`, `departuredate`, `number`,"
			+ " `idplains`, `status`) " + "VALUES(?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE `flight` SET `fromwhence` = ?, `where` = ?, `departuredate` = ?, `number` = ?, `idplains` = ?, `status` = ? WHERE `id` = ?;";
	private static final String SQL_DELETE = "DELETE FROM `flight` WHERE `id`= ?;";
	private static final String SQL_UPDATE_STATUS = "UPDATE `flight` SET  `status` = ? WHERE `id` = ?;";

	@Override
	public Flight findByID(Integer id) throws DBException {
		Flight flight = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				flight = extractFlight(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BY_ID,
					e);
		}
		return flight;
	}

	@Override
	public List<Flight> findAll() throws DBException {
		List<Flight> flightList = new ArrayList<Flight>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_ALL)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				flightList.add(extractFlight(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT, e);
		}
		return flightList;
	}

	@Override
	public void insertFlight(Flight flight) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
			ps.setInt(1, flight.getFromwhence());
			ps.setInt(2, flight.getWhere());
			ps.setDate(3, flight.getDeparturedate());
			ps.setString(4, flight.getNumber());
			ps.setInt(5, flight.getIdplains());
			ps.setString(6, flight.getStatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_CLAIM_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_CLAIM_BY_ID,
					e);
		}
	}

	@Override
	public void UpdateFlight(Flight flight) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
			ps.setInt(1, flight.getFromwhence());
			ps.setInt(2, flight.getWhere());
			ps.setDate(3, flight.getDeparturedate());
			ps.setString(4, flight.getNumber());
			ps.setInt(5, flight.getIdplains());
			ps.setString(6, flight.getStatus());
			ps.setLong(7, flight.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_UPDATE_PLANE, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_UPDATE_PLANE, e);
		}

	}

	@Override
	public void deleteFlight(Integer... check) throws DBException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			for (int j = 0; j < check.length; j++) {
				stmt = connection.prepareStatement(SQL_DELETE);
				stmt.setInt(1, check[j]);
				stmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(Messages.EXCEPTION_CANNOT_ROLLBACK, e);
				throw new DBException(
						Messages.EXCEPTION_CANNOT_ROLLBACK, e);
			}
			LOG.error(Messages.EXCEPTION_CAN_NOT_DELETE_PLANE, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_DELETE_PLANE, e);
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
	public void changeStatus(Flight flight) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_UPDATE_STATUS)) {
			ps.setString(1, flight.getStatus());
			ps.setLong(2, flight.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_FLIGHT, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_FLIGHT, e);
		}

	}

	private Flight extractFlight(ResultSet rs) throws SQLException {
		Flight flight = new Flight();
		flight.setId(rs.getInt("id"));
		flight.setDeparturedate(rs.getDate("departuredate"));
		flight.setFromwhence(rs.getInt("fromwhence"));
		flight.setWhere(rs.getInt("where"));
		flight.setStatus(rs.getString("status"));
		flight.setIdplains(rs.getInt("idplains"));
		flight.setNumber(rs.getString("number"));
		return flight;
	}

}

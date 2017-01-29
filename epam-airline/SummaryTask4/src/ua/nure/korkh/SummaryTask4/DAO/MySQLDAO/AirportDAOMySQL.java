package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.Hash.HashDB;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class AirportDAOMySQL implements AirportDAO {

	private static final Logger LOG = Logger.getLogger(AirportDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM airport WHERE id = ?";
	private static final String SQL_SELECT_ALL_AIRPORT = "SELECT * FROM airport";
	private static final String SQL_INSERT = "INSERT INTO `airport` "
			+ "(name, country, city, status, iatacode)"
			+ " VALUES(?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_STATUS = "UPDATE `airport` SET  `status` = ? WHERE `id` = ?;";

	@Override
	public Airport findByID(Integer id) throws DBException {
		Airport airport = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				airport = extractAirport(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_AIRPORT_BY_ID, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_AIRPORT_BY_ID, e);
		}
		return airport;
	}

	@Override
	public List<Airport> findAll() throws DBException {
		LOG.info("find Airports start");
		List<Airport> airportList = new ArrayList<Airport>();
		ResultSet resultSet = null;
		if (HashDB.getData(SQL_SELECT_ALL_AIRPORT) == null) {
			System.out.println("select from db");
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection
							.prepareStatement(SQL_SELECT_ALL_AIRPORT)) {
				ps.executeQuery();

				resultSet = ps.getResultSet();
				while (resultSet.next()) {
					airportList.add(extractAirport(resultSet));
				}
			} catch (SQLException e) {
				LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_AIRPORT, e);
				throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_AIRPORT,
						e);
			}
			HashDB.setData(SQL_SELECT_ALL_AIRPORT, airportList);
		}
		airportList = (List<Airport>) HashDB.getData(SQL_SELECT_ALL_AIRPORT);
		return airportList;
	}

	@Override
	public void insertAirport(List<Airport> airport) throws DBException {
		LOG.info("insertion of new Airports start");
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			for (Airport ar : airport) {
				stmt = connection.prepareStatement(SQL_INSERT);
				stmt.setString(1, ar.getName());
				stmt.setString(2, ar.getCountry());
				stmt.setString(3, ar.getCity());
				stmt.setString(4, ar.getStatus());
				stmt.setString(5, ar.getIatacode());
				stmt.executeUpdate();
			}

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			LOG.error(Messages.EXCEPTION_CAN_NOT_INSERT_AIRPORT, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_INSERT_AIRPORT, e);

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
	public void changeStatusAirport(Airport airport) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_UPDATE_STATUS)) {
			ps.setString(1, airport.getStatus());
			ps.setLong(2, airport.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_AIRPORT, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS_AIRPORT, e);
		}
		HashDB.deleteData(SQL_SELECT_ALL_AIRPORT);
		LOG.info("Delete query from Hash" + SQL_SELECT_ALL_AIRPORT);
	}

	private Airport extractAirport(ResultSet rs) throws SQLException {
		Airport airport = new Airport();
		airport.setId(rs.getInt("id"));
		airport.setName(rs.getString("name"));
		airport.setCountry(rs.getString("country"));
		airport.setCity(rs.getString("city"));
		airport.setIatacode(rs.getString("iatacode"));
		airport.setStatus(rs.getString("status"));
		return airport;
	}

}

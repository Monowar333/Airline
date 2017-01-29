package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class FligtsBeanDAOMySQL implements FligtsBeanDAO {

	private static final Logger LOG = Logger
			.getLogger(FligtsBeanDAOMySQL.class);

	private static final String SQL_GET_FLIGHTS_BEANS_BY_ID = "SELECT o.id, o.number, u.city, u.country, u.name,"
			+ "r.city, r.country, r.name,"
			+ "o.departuredate, s.model, o.status"
			+ "	FROM flight o, airport u, airport r, plains s"
			+ "	WHERE o.fromwhence=u.id AND o.where=r.id AND o.idplains=s.id AND o.id=?;";
	private static final String SQL_GET_FLIGHTS_BEANS ="SELECT * from flight "+ 
						"inner join airport r " +
						"on r.id = flight.where " +
						"inner join airport u " + 
						"on u.id = flight.fromwhence " +
						"left join plains s " +
						"on s.id = flight.idplains or s.id = null;";
	private static final String SQL_GET_FLIGHTS_BEANS_SORT_BY_PARAM ="SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null ORDER BY ";
	private static final String SQL_GET_FLIGHTS_BYIDUSER ="SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"WHERE flight.id in (select brigade.idflight from brigade where idusers = ?);";
	private static final String SQL_GET_FLIGHTS_BEANS_BY_NUMBER ="SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where flight.number=?;";
	private static final String SQL_GET_FLIGHTS_BEANS_FROM_CITY = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where u.city=?;";
	private static final String SQL_GET_FLIGHTS_BEANS_FROM_COUNTRY = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where u.country = ?;";

	private static final String SQL_GET_FLIGHTS_BEANS_FROM_AIRPORT = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where  u.name = ?;";
	private static final String SQL_GET_FLIGHTS_BEANS_FROM_TO_CITY = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where r.city = ?;";
	private static final String SQL_GET_FLIGHTS_BEANS_FROM_TO_COUNTRY = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where r.country = ?;";

	private static final String SQL_GET_FLIGHTS_BEANS_FROM_TO_AIRPORT = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where r.name = ?;";

	private static final String SQL_GET_FLIGHTS_BEANS_DEPARTURE_DATE = "SELECT * from flight "+ 
			"inner join airport r " +
			"on r.id = flight.where " +
			"inner join airport u " + 
			"on u.id = flight.fromwhence " +
			"left join plains s " +
			"on s.id = flight.idplains or s.id = null " +
			"where flight.departuredate = ?;"; 
	


	@Override
	public List<FlightsBean> findAll() throws DBException {
		List<FlightsBean> flightList = new ArrayList<FlightsBean>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_GET_FLIGHTS_BEANS)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				flightList.add(extractFlightsBean(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS,
					e);
		}
		return flightList;
	}

	@Override
	public List<FlightsBean> findByParam(String colun, String value)
			throws DBException {
		String query = null;

		switch (colun) {
		case "findBynumber":
			query = SQL_GET_FLIGHTS_BEANS_BY_NUMBER;
			break;
		case "findfromCity":
			query = SQL_GET_FLIGHTS_BEANS_FROM_CITY;
			break;
		case "findBfromCountry":
			query = SQL_GET_FLIGHTS_BEANS_FROM_COUNTRY;
			break;
		case "findByAirportName":
			query = SQL_GET_FLIGHTS_BEANS_FROM_AIRPORT;
			break;
		case "findfromtoCity":
			query = SQL_GET_FLIGHTS_BEANS_FROM_TO_CITY;
			break;
		case "findBfromtoCountry":
			query = SQL_GET_FLIGHTS_BEANS_FROM_TO_COUNTRY;
			break;
		case "findByFromtoAirportName":
			query = SQL_GET_FLIGHTS_BEANS_FROM_TO_AIRPORT;
			break;
		case "findBydeparturedate":
			query = SQL_GET_FLIGHTS_BEANS_DEPARTURE_DATE;

			break;
		}
		List<FlightsBean> flifhtBeanList = new ArrayList<FlightsBean>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			if (query.equals(SQL_GET_FLIGHTS_BEANS_DEPARTURE_DATE)) {
				ps.setDate(1, Date.valueOf(value));
			} else {
				ps.setString(1, value);
			}
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				flifhtBeanList.add(extractFlightsBean(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS_BY_COLUM_AND_VALUE,
					e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS_BY_COLUM_AND_VALUE,
					e);
		}
		return flifhtBeanList;
	}

	@Override
	public FlightsBean findById(Integer id) throws DBException {
		FlightsBean flightsBean = null;
		ResultSet rs = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_GET_FLIGHTS_BEANS_BY_ID)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				flightsBean = extractFlightsBean(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHTS_BEANS_BY_ID, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHTS_BEANS_BY_ID, e);
		}
		return flightsBean;
	}
	
	@Override
	public List<FlightsBean> findAllByIdUser(Integer id) throws DBException {
		List<FlightsBean> flightsBean = new ArrayList<FlightsBean>();
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_GET_FLIGHTS_BYIDUSER)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flightsBean.add(extractFlightsBean(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_FLIGHTS_BEANS_BY_ID, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHTS_BEANS_BY_ID, e);
		}
		return flightsBean;
	}

	@Override
	public List<FlightsBean> SORTByParam(String colum, String value)
			throws DBException {
		List<FlightsBean> flifhtBeanList = new ArrayList<FlightsBean>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.
						prepareStatement(SQL_GET_FLIGHTS_BEANS_SORT_BY_PARAM + colum + " " + value +";")) {
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				flifhtBeanList.add(extractFlightsBean(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS_BY_COLUM_AND_VALUE,
					e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS_BY_COLUM_AND_VALUE,
					e);
		}
		return flifhtBeanList;
	}

	private FlightsBean extractFlightsBean(ResultSet rs) throws SQLException {
		FlightsBean flight = new FlightsBean();
		flight.setId(rs.getInt("id"));
		flight.setDeparturedate(rs.getDate("departuredate"));
		flight.setFromwhenceCity(rs.getString("u.city"));
		flight.setFromwhenceCountry(rs.getString("u.country"));
		flight.setFromwhenceName(rs.getString("u.name"));
		flight.setWhereCity(rs.getString("r.city"));
		flight.setWhereCountry(rs.getString("r.country"));
		flight.setWhereName(rs.getString("r.name"));
		flight.setStatus(rs.getString("status"));
		flight.setIdplains(rs.getString("s.model"));
		flight.setNumber(rs.getString("number"));
		return flight;
	}




}

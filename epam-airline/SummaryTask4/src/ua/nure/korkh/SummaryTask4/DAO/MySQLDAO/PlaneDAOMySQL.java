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
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.Hash.HashDB;
import ua.nure.korkh.SummaryTask4.entity.Plane;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class PlaneDAOMySQL implements PlaneDAO {

	private static final Logger LOG = Logger.getLogger(PlaneDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM plains WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM plains";
	private static final String SQL_SELECT_BY_DEPDATE_IDPLAIN = 
			"SELECT * FROM airline.plains p " + 
			"left join airline.flight f " +
			"on p.id = f.idplains " + 
			"where f.departuredate = ? and p.id = ?;";
	private static final String SQL_INSERT = "INSERT INTO `plains` "
			+ "(`crew`, `numberofseats`, `model`) VALUES(?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE `plains` SET `crew` = ?, `numberofseats` = ?, `model` = ? WHERE `id` = ?;";
	private static final String SQL_DELETE = "DELETE FROM `plains` WHERE `id`= ?;";

	@Override
	public Plane findByID(Integer id) throws DBException {
		Plane flight = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				flight = extractPlains(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID,
					e);
		}
		return flight;
	}

	@Override
	public List<Plane> findAll() throws DBException {
		List<Plane> plainsList = new ArrayList<Plane>();
		ResultSet resultSet = null;
		if (HashDB.getData(SQL_SELECT_ALL) == null) {
			System.out.println("select from db");
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_ALL)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				plainsList.add(extractPlains(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_PLAINS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_PLAINS, e);
		}
		HashDB.setData(SQL_SELECT_ALL, plainsList);
	}
		plainsList = (List<Plane>) HashDB.getData(SQL_SELECT_ALL);
	return plainsList;
	}
	
	@Override
	public List<Plane> findByDepartureDate(Date date, Integer idplains) throws DBException {
		List<Plane> plainsList = new ArrayList<Plane>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_DEPDATE_IDPLAIN)) {
			ps.setDate(1, date);
			ps.setInt(2, idplains);
			System.out.println(ps.toString());
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				plainsList.add(extractPlains(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID_ROLE, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID_ROLE, e);
		}
		return plainsList;

	}
	
	@Override
	public void deletePlane(Integer... check) throws DBException {
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LOG.error(Messages.EXCEPTION_CAN_NOT_DELETE_FLIGTS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_DELETE_FLIGTS, e);
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
		HashDB.deleteData(SQL_SELECT_ALL);
		LOG.info("Delete query from Hash" + SQL_SELECT_ALL);
		
	}

	@Override
	public void insertPlane(Plane plane) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
			ps.setInt(1, plane.getCrew());
			ps.setInt(2, plane.getNumberofseats());
			ps.setString(3, plane.getModel());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_INSERT_PLANE, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_INSERT_PLANE,e);
		}
		HashDB.deleteData(SQL_SELECT_ALL);
		LOG.info("Delete query from Hash" + SQL_SELECT_ALL);
		
	}

	@Override
	public void UpdatePlane(Plane plane) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
			ps.setInt(1, plane.getCrew());
			ps.setInt(2, plane.getNumberofseats());
			ps.setString(3, plane.getModel());
			ps.setInt(4, plane.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_UPDATE_FLIGHT, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_UPDATE_FLIGHT, e);
		}
		HashDB.deleteData(SQL_SELECT_ALL);
		LOG.info("Delete query from Hash" + SQL_SELECT_ALL);
		
	}
	
	private Plane extractPlains(ResultSet rs) throws SQLException {
		Plane plains = new Plane();
		plains.setId(rs.getInt("id"));
		plains.setCrew(rs.getInt("crew"));
		plains.setNumberofseats(rs.getInt("numberofseats"));
		plains.setModel(rs.getString("model"));
		return plains;
	}





}

package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;
import ua.nure.korkh.SummaryTask4.entity.Flight;
import ua.nure.korkh.SummaryTask4.entity.User;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;
import ua.nure.korkh.SummaryTask4.functional.MD5Util;

public class UserDAOMySQL implements UserDAO {

	private static final Logger LOG = Logger.getLogger(ClaimDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM users";
	private static final String SQL_SELECT_BY_ROLE = "SELECT * FROM users WHERE roleid = ?;";
	private static final String SQL_SELECT_BY_LINCKACCEPT = "SELECT * FROM users WHERE linckaccept = ?;";
	private static final String SQL_INSERT = "INSERT INTO `users` "
			+ "(login, password, suname, name, email,"
			+ " dateofbirth, telephone, roleid, sex, linckaccept, registrstatus) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_DELETE = "DELETE FROM `users` WHERE `id`= ? AND roleid <> 1;";
	private static final String SQL_UPDATE = "UPDATE `users` SET `suname` = ?, `name` = ?, `email` = ?, `dateofbirth` = ?, `telephone` = ?, `roleid` = ?, `sex` = ? WHERE `id` = ?;";
	private static final String SQL_CHANGESTATUS = "UPDATE `users` SET `linckaccept` = ?, `registrstatus` = ? WHERE `id` = ?;";
	private static final String SQL_SELECT_BY_ROLE_AND_DEPDATE = 
			"Select * from users WHERE users.id not in (" +
			"SELECT b.idusers from airline.brigade b "+
			"INNER JOIN airline.flight f " +
		    "ON f.id = b.idflight " +
		    "where f.departuredate = ?) and roleid = ?;";
	@Override
	public User findByID(Integer id) throws DBException {
		User user = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = extractUser(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
		}
		return user;
	}

	@Override
	public List<User> findAll() throws DBException {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_ALL)) {
			ps.executeQuery();
			resultSet = ps.getResultSet();
			while (resultSet.next()) {
				userList.add(extractUser(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
		}
		return userList;
	}

	@Override
	public User findByLogin(String login) throws DBException {
		User user = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_LOGIN)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = extractUser(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_LOGIN, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_LOGIN, e);
		}
		return user;
	}
	
	@Override
	public User findByEmail(String email) throws DBException {
		User user = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_EMAIL)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = extractUser(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_EMAIL, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_EMAIL, e);
		}
		return user;
	}
	
	@Override
	public User findByLinckAccept(String linckaccept) throws DBException {
		User user = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_LINCKACCEPT)) {
			ps.setString(1, linckaccept);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = extractUser(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_LINK, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_LINK, e);
		}
		return user;
	}

	@Override
	public void insertUser(User user) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, MD5Util.md5Custom(user.getPassword()));
			ps.setString(3, user.getSuname());
			ps.setString(4, user.getName());
			ps.setString(5, user.getEmail());
			ps.setDate(6, user.getDateofbirth());
			ps.setString(7, user.getTelephone());
			ps.setInt(8, user.getRoleId());
			ps.setString(9, user.getSex());
			ps.setString(10, user.getLinckaccept());
			ps.setBoolean(11, user.getRegistrstatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_USER_BY_ID, e);
		}
	}

	@Override
	public void UpdateUser(User user) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
			ps.setString(1, user.getSuname());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setDate(4, user.getDateofbirth());
			ps.setString(5, user.getTelephone());
			ps.setInt(6, user.getRoleId());
			ps.setString(7, user.getSex());
			ps.setLong(8, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_UPDATE_USER, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_UPDATE_USER, e);
		}

	}
	
	@Override
	public void changeStatus(User user) throws DBException {
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_CHANGESTATUS)) {
			ps.setString(1, user.getLinckaccept());
			ps.setBoolean(2, user.getRegistrstatus());
			ps.setLong(3, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_CHANGE_STATUS, e);
		}

	}

	@Override
	public void deleteUsers(Integer... in) throws DBException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			for (int j = 0; j < in.length; j++) {
				stmt = connection.prepareStatement(SQL_DELETE);
				stmt.setInt(1, in[j]);
				stmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(Messages.EXCEPTION_CANNOT_ROLLBACK, e);
				throw new DBException(Messages.EXCEPTION_CANNOT_ROLLBACK, e);
			}
			LOG.error(Messages.EXCEPTION_CAN_NOT_DELETE_USERS, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_DELETE_USERS, e);
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
	public List<User> findByRole(Integer roleId) throws DBException {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ROLE)) {
			ps.setInt(1, roleId);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userList.add(extractUser(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USERS_BY_ID_ROLE, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_USERS_BY_ID_ROLE, e);
		}
		return userList;
	}
	
	@Override
	public List<User> findByRoleANDIdflight(Integer roleId, Flight flight)
			throws DBException {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ROLE_AND_DEPDATE)) {
			ps.setInt(2, roleId);
			ps.setDate(1, flight.getDeparturedate());
			System.out.println(ps.toString());
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userList.add(extractUser(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_USERS_BY_ID_ROLE, e);
			throw new DBException(
					Messages.EXCEPTION_CAN_NOT_FIND_USERS_BY_ID_ROLE, e);
		}
		return userList;

	}


	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setDateofbirth(rs.getDate("dateofbirth"));
		user.setName(rs.getString("name"));
		user.setSuname(rs.getString("suname"));
		user.setEmail(rs.getString("email"));
		user.setTelephone(rs.getString("telephone"));
		user.setSex(rs.getString("sex"));
		user.setRoleId(rs.getInt("roleid"));
		user.setLinckaccept(rs.getString("linckaccept"));
		user.setRegistrstatus(rs.getBoolean("registrstatus"));
		return user;
	}





}

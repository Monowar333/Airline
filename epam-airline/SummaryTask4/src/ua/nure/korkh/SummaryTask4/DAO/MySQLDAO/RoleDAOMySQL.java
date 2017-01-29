package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.Hash.HashDB;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

public class RoleDAOMySQL implements RoleDAO {

	private static final Logger LOG = Logger.getLogger(ClaimDAOMySQL.class);
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM role WHWRE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM role";

	@Override
	public Role findByID(Integer id) throws DBException {
		Role role = null;
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection
						.prepareStatement(SQL_SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				role = extractRole(rs);
		} catch (SQLException e) {
			LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
			throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID, e);
		}
		return role;
	}

	@Override
	public List<Role> findAll() throws DBException {
		List<Role> roleList = new ArrayList<Role>();
		ResultSet resultSet = null;
		if (HashDB.getData(SQL_SELECT_ALL) == null) {
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection
							.prepareStatement(SQL_SELECT_ALL)) {
				ps.executeQuery();
				resultSet = ps.getResultSet();
				while (resultSet.next()) {
					roleList.add(extractRole(resultSet));
				}
			} catch (SQLException e) {
				LOG.error(Messages.EXCEPTION_CAN_NOT_FIND_ROLES, e);
				throw new DBException(Messages.EXCEPTION_CAN_NOT_FIND_ROLES, e);
			}
			HashDB.setData(SQL_SELECT_ALL, roleList);
		}
		roleList = (List<Role>) HashDB.getData(SQL_SELECT_ALL);
		return roleList;
	}

	private Role extractRole(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		return role;
	}
}

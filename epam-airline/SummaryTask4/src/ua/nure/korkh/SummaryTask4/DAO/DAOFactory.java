package ua.nure.korkh.SummaryTask4.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.korkh.SummaryTask4.exception.DBException;
import ua.nure.korkh.SummaryTask4.exception.Messages;

import org.apache.log4j.Logger;

/**
 * DAOFactory manager.
 * 
 * @author Korkh
 * 
 */

public abstract class DAOFactory {

	private static final Logger LOG = Logger.getLogger(DAOFactory.class);
	private static DataSource dataSource = getDataSource();


	private static Connection connection;



	private static DataSource getDataSource() {

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/airline");
			LOG.trace("Data source ==> " + dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	public static Connection getConnection() throws DBException {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}

		return connection;
	}

	public abstract RoleDAO getRoleDAO();

	public abstract UserDAO getUserDAO();

	public abstract ClaimDAO getClaimDAO();

	public abstract PlaneDAO getPlaisDAO();

	public abstract BrigadeDAO getBrigadeDAO();

	public abstract FlightDAO getFlightDAO();

	public abstract AirportDAO getAirportDAO();

	public abstract FligtsBeanDAO getFligtsBeanDAO();

	public abstract BrigadeBeanDAO getBrigadeBeanDAO();

	public abstract ClaimBeanDAO getClaimBeanDAO();

}

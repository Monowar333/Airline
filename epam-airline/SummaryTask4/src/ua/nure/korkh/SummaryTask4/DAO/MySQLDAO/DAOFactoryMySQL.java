package ua.nure.korkh.SummaryTask4.DAO.MySQLDAO;

import ua.nure.korkh.SummaryTask4.DAO.AirportDAO;
import ua.nure.korkh.SummaryTask4.DAO.BrigadeBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.BrigadeDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.ClaimDAO;
import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.FlightDAO;
import ua.nure.korkh.SummaryTask4.DAO.FligtsBeanDAO;
import ua.nure.korkh.SummaryTask4.DAO.PlaneDAO;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.UserDAO;

public class DAOFactoryMySQL extends DAOFactory {

	@Override
	public RoleDAO getRoleDAO() {
		// TODO Auto-generated method stub
		return new RoleDAOMySQL();
	}

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return new UserDAOMySQL();
	}

	@Override
	public ClaimDAO getClaimDAO() {
		// TODO Auto-generated method stub
		return new ClaimDAOMySQL();
	}

	@Override
	public PlaneDAO getPlaisDAO() {
		// TODO Auto-generated method stub
		return new PlaneDAOMySQL();
	}

	@Override
	public BrigadeDAO getBrigadeDAO() {
		// TODO Auto-generated method stub
		return new BrigadeDAOMySQL();
	}

	@Override
	public FlightDAO getFlightDAO() {
		// TODO Auto-generated method stub
		return new FlightDAOMySQL();
	}

	@Override
	public AirportDAO getAirportDAO() {
		// TODO Auto-generated method stub
		return new AirportDAOMySQL();
	}

	@Override
	public FligtsBeanDAO getFligtsBeanDAO() {
		// TODO Auto-generated method stub
		return new FligtsBeanDAOMySQL();
	}

	@Override
	public BrigadeBeanDAO getBrigadeBeanDAO() {
		// TODO Auto-generated method stub
		return new BrigadeBeanDAOMySQL();
	}

	@Override
	public ClaimBeanDAO getClaimBeanDAO() {
		// TODO Auto-generated method stub
		return new ClaimBeanDAOMySQL();
	}
}

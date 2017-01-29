package ua.nure.korkh.SummaryTask4.DBPoll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOFactoryTest {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String DAO_FACTORY_CLASS_NAME = "ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/airline?useSSL=false";
	public static final String LOGIN = "root";
	public static final String PASSWORD = "root";
	private static DAOFactoryTest instance = null; 
	
	private static synchronized DAOFactoryTest getInstance(){
		if(instance == null){
			try{
				 Class.forName(DRIVER_CLASS_NAME);
				 Class clazz = Class.forName(DAO_FACTORY_CLASS_NAME);
				 instance =(DAOFactoryTest) clazz.newInstance();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return instance;
	}
	
	public static Connection getConnection() throws SQLException {
		 Connection connection = null;
		 connection = DriverManager.getConnection(DB_URL,LOGIN, PASSWORD);
		 return connection;
	} 
}

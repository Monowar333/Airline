package ua.nure.korkh.SummaryTask4.DBPoll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class DBPoolCacheMy {
	  private static MysqlDataSource dataSource;
      private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(20);
      
      
      public DBPoolCacheMy() throws SQLException {
    	  System.out.println("init connection");
//    		try {
    			dataSource = new MysqlDataSource();
    			dataSource.setURL("jdbc:mysql://localhost:3306/airline?useSSL=false");
    			dataSource.setUser("root");
    			dataSource.setPassword("root");
//    			Context initContext = new InitialContext();
//    			Context envContext = (Context) initContext.lookup("java:/comp/env");
//    			dataSource = (MysqlDataSource) envContext.lookup("jdbc/airline");
//    		} catch (NamingException e) {
//    			e.printStackTrace();
//    		}
          for (int i = 0; i < 20; i++) {//ѕодготавливаем соединени€
        	  Connection con = dataSource.getConnection();
              connections.add(con);
              
          }
          System.out.println(connections.toString());
      }

      public Connection getConnection() throws SQLException {
          try {  // пробуем получить свободное соединение
              return connections.poll(2, TimeUnit.SECONDS);
          } catch (InterruptedException e) {
              return null;
          }
      }

      public void putConnection(Connection connection) throws SQLException {
          connections.add(connection);
      }
  
}

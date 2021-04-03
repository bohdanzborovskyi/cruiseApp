package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  Class for connection to application database.
 */

public class DBConnection {
	
	private static Connection connection = null;
	public static Properties prop = null;
	
	 public static Connection getConnection() {		 
	        if (connection != null)
	            return connection;
	        else {
	            try {
	            	prop = PropertyFactory.getPropsDB();
	                String driver = prop.getProperty("driver");
	                String url = prop.getProperty("url");	                
	                prop.setProperty("useUnicode","true");
	                prop.setProperty("characterEncoding","UTF-8");
	                Class.forName(driver); 
	                connection = DriverManager.getConnection(url, prop);	          
//	                Class.forName("com.mysql.jdbc.Driver");
//	            	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/CruiseDB","root", "root"); 
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }return connection;
	        }

	    }
	
	
}


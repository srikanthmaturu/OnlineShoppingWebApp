/**
 * 
 */
package onlineshoppingwebapp.models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Srikanth
 *
 */
public class Database {
	
	public static String defaultServerName = "localhost";
	public static String defaultDatabaseName = "`CSCE464OnlineShoppingDB`";
	
	public static Connection getConnection(String userName, String password, String serverName, String portNumber) throws SQLException{
		Connection conn;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		
		conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/", connectionProps);
		
		//System.out.println("Connected to database");
		
		return conn;
		
	}
	
	public static Connection localConnect() {
		Connection connection = null;
		
		try{
			connection = Database.getConnection("root", "mysql", "localhost", "3306");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection unlConnect() {
		Connection connection = null;
		
		try{
			connection = Database.getConnection("smaturu", "Sr1k@nth", "cse.unl.edu", "3306");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection connect(String serverName){
    	try{
    		Class.forName("com.mysql.jdbc.Driver");	
    	}catch(ClassNotFoundException e){
    		System.err.println(e);
    	}
		
		if(serverName == "cse.unl.edu"){
			return unlConnect();
		}
		else{
			return localConnect();
		}
	}
	
}

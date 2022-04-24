import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnection
{
	Connection conn=null;
	public static Connection dbConnector() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:Realter-db.db"); //Here add the URL of the DB
			//JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
			
		} catch(Exception e){
			//JOptionPane.showMessageDialog(null, e);
			return null;
		} //Test comment
		
	}
}

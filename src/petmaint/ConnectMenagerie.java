
/**
 * Requires driver "com.mysql.jdbc.Driver" contained in the jar file
 * "mysql-connector-java-5.0.6-bin.jar". There is one residing in residing 
 * in the /usr/pdx/misc/users/greg/java/jdbc/ directory. That is the one
 * pointed to by the Eclipse IDE. gcw
 */

package petmaint;

import java.sql.*;

public class ConnectMenagerie {
	
	Connection conn = null;
    public ConnectMenagerie() throws Exception{
        System.out.println("Connecting to database server.");
    
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/menagerie";
            
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection(url, userName, password);
        }

        catch(Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println("error: "+e.toString());
            throw e;   
        }
        
        System.out.println("Connection established.\n");
    }
    
    public void DisconnectMenagerie() {
        if(conn != null) {
            try { 
                conn.close();
        		System.out.println("Database connection terminated.");
            }
        	
            catch(Exception e) {
                // ignore these errors.
            }
        }
    }
}


// Requires driver "com.mysql.jdbc.Driver" contained in the jar file
// "mysql-connector-java-5.0.6-bin.jar". There is one residing in residing 
// in the /usr/pdx/misc/users/greg/java/jdbc/ directory. That is the one
// pointed to by the Eclipse IDE. gcw
//

package petmaint;

import java.sql.*;

public class Connect {
	public static void Dashes() {
	  System.out.println("-----------------------");
	}
	
    public static void main(String[] args) {
        System.out.println("Connecting to database server.");
        
        Connection conn = null;
    
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/menagerie";
            
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection(url, userName, password);
        }

        catch(Exception e) {
            System.err.println("Cannot connect to database server");
        }
        
        System.out.println("Connection established.\n");
        
        try {
            Statement s = conn.createStatement();
            // String whichSpecies = "'dog'";
            String whichSex = "'f'";
            s.executeQuery("SELECT name, species, owner FROM pet WHERE pet.sex="+whichSex);
            ResultSet rs = s.getResultSet();
            int count = 0;
        
            while(rs.next()) {
                String petName  = rs.getString("name");
                String petSpecies = rs.getString("species");
            	String petOwner = rs.getString("owner");
            	
            	Dashes();
            	System.out.println("    Name: "+petName);
            	System.out.println(" Species: "+petSpecies);
            	System.out.println("   Owner: "+petOwner);
            	count ++;
            }
            
            Dashes();
            rs.close();
            s.close();
            
            System.out.println("\nRows retrieved: "+count);
            
            Statement ns = conn.createStatement();
         
            // String whichName1 = "'jasper'";
            // String whichName2 = "'Jasper'";
            String newName = "'Jasper'";
            String whichOwner = "'julie'";
            
            count = ns.executeUpdate("UPDATE pet SET pet.name="+newName+" WHERE pet.owner="+whichOwner);
            
            System.out.println("Row updated: "+count);
        }
        
        catch(Exception e) {
            System.err.println("\nError issuing statement to database.");
        }
        
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

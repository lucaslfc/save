package BDD;

import java.sql.DriverManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

import miage.genielogiciel.App;

public class ObjBDD {
	
	public static Connection connection = null;
	
	public static boolean CreateConnexion() throws IOException {
		
		InputStream inputStream = App.class.getResourceAsStream("/BaseDeDonnees/config.properties");
    	Properties props = new Properties();
    	props.load(inputStream);
    	
    	String nomConnexion	= props.getProperty("nomConnexion");
    	String motDePasse	= props.getProperty("motDePasse");
    	
    	String protocole 	=  props.getProperty("protocole");
    	String ip 			=  props.getProperty("ip");
    	String port 		=  props.getProperty("port");
    	String nomBase 		=  props.getProperty("nomBase");
		
		try {
	       Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver") ;
	       Driver pilote = (Driver)c.newInstance() ;
	       DriverManager.registerDriver(pilote);

	       String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC" ;

	       ObjBDD.connection = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;
	    }  catch (Exception e) {
	  	  //System.out.println(e);
	  	  return false;
	    }
		return true;
	}
	
	public static boolean requeteInsert(String query) {
		Statement smt = null;
		try {
			smt = ObjBDD.connection.createStatement();
			smt.executeUpdate(query);
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean requeteUpdate(String query) {
		return true;
	}
	
	public static boolean requeteDelete(String query) {
		return true;
	}
	
	public static ResultSet requeteSelect(String query) {
		ResultSet rs = null;
		Statement smt = null;
		try {
			smt = ObjBDD.connection.createStatement();
			rs = smt.executeQuery(query) ;
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	
	
	
	
	
	public static void requeteUpdatePlaceStationnement(String query) {
		
		Statement smt = null;
	    try {
	    	
	        @SuppressWarnings("null")
			boolean results = smt.execute(query);
	        int rsCount = 0;

	        // Loop through the available result sets.
	        do {
	            if (results) {
	                ResultSet rs = smt.getResultSet();
	                rsCount++;

	                // Show data from the result set.
	                System.out.println("RESULT SET #" + rsCount);
	                while (rs.next()) {
	                    System.out.println(rs.getString("RefPlaceStationnement") + ", " + rs.getString("Statut"));
	                }
	            }
	            System.out.println();
	            results = smt.getMoreResults();
	        } while (results);
	    }
	    // Handle any errors that may have occurred.
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
}

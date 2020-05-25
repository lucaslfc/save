package BDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import OperationClient.User;
import OperationPlaceStationnement.PlaceStationnement;
import OperationReservation.Reservation;
import OperationVehicule.Vehicule;

public class TriggerBDD {

	
	
	
	

	public static Runnable updateStatutPlace = new Runnable() {
		
	    public void run(){
	    	
	    	/**
	    	Date date = new Date();
	    	
	    	String heureDebut = date.getHours()+":"+date.getMinutes();
	    	
	    	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	    	
	    	System.out.println(heureDebut);
	    	String statut = "réservée";
	    	
	    	String sqlSstring = "SELECT * FROM placestationnement WHERE Statut = '"+statut+"'";
	    	
	    	**/
	    	
	    	try {
				PlaceStationnement.updateStatutPlaceStationnement();
				PlaceStationnement.updateDelaiAttente();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    	

	    	
     
	    	
	    }
	    
	    
	    
	    
	    
	    
	};
	
	

	
	
	
}

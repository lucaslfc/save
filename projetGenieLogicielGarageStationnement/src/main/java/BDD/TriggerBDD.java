package BDD;

import java.sql.SQLException;
import OperationPlaceStationnement.PlaceStationnement;

public class TriggerBDD {

	
	public static Runnable updateStatutPlace = new Runnable() {
		
	    public void run(){
	
	    	try {
				PlaceStationnement.updateStatutPlaceStationnement();
				PlaceStationnement.updateDelaiAttente();
				PlaceStationnement.updateNotification();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    	
	    }
  
	};
	
	

}

package OperationPlaceStationnement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import BDD.ObjBDD;

public class PlaceStationnement {
	
	
	private Integer PlaceStationnementInteger;
	private String refPlaceStationnementString;
	private String statutString;
	
	public PlaceStationnement(String refPlaceStationnementString, String statutString) {
		this.refPlaceStationnementString = refPlaceStationnementString;
		this.statutString = statutString;
	}
	
	
	

	public static void updateStatutPlaceStationnement() throws SQLException {
		
		
		Date date = new Date();
		String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	String statut = "réservée";	
    	String heureDebut = date.getHours()+":"+date.getMinutes();
    	LocalTime heureDebutTime = LocalTime.parse(heureDebut);
		LocalTime timeAdd = heureDebutTime.plusMinutes(30);
    	
    	String sqlSstring = "select * from reservation, placestationnement where DateDebut = '"+dateDebutDate+"' and HeureDebut = '"+heureDebut+"' and placestationnement.Statut = 'réservée' and reservation.RefPlaceStationnement = placestationnement.RefPlaceStationnement";
		
	
		ResultSet rs = ObjBDD.requeteSelect(sqlSstring);

		if(rs.next()) {
			int idReservation = rs.getInt("idReservation");
			String sqlSstringUpdate = "update reservation set DelaiAttente = 1, heureDelaiAttenteMax = '"+timeAdd+"' where idReservation = '"+idReservation+"'";
			ObjBDD.requeteUpdate(sqlSstringUpdate);

		}

	}
	
	public static void updateDelaiAttente() throws SQLException {
			
			
			Date date = new Date();
			String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	    	String statut = "réservée";	
	    	String heureDebut = date.getHours()+":"+date.getMinutes();
	    	
	    	String sqlSstring = "select * from reservation where DelaiAttente = 1 and DateDebut = '"+dateDebutDate+"' and heureDelaiAttenteMax = '"+heureDebut+"' ";
			
		
			ResultSet rs = ObjBDD.requeteSelect(sqlSstring);
	
			if(rs.next()) {
				int idReservation = rs.getInt("idReservation");
				String sqlSstringUpdate = "update reservation set delaiAttenteDepasse = 1  where idReservation = '"+idReservation+"'";
				ObjBDD.requeteUpdate(sqlSstringUpdate);
		
			}

		}
	


	public Integer getPlaceStationnementInteger() {
		return PlaceStationnementInteger;
	}

	public void setPlaceStationnementInteger(Integer placeStationnementInteger) {
		PlaceStationnementInteger = placeStationnementInteger;
	}

	public String getRefPlaceStationnementString() {
		return refPlaceStationnementString;
	}

	public void setRefPlaceStationnementString(String refPlaceStationnementString) {
		this.refPlaceStationnementString = refPlaceStationnementString;
	}

	public String getStatutString() {
		return statutString;
	}

	public void setStatutString(String statutString) {
		this.statutString = statutString;
	}
	

	
}

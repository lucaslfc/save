package OperationPlaceStationnement;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
	
	public static void updateNotification() throws SQLException {
		
		
		Date date = new Date();
		String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		String heureCourante = date.getHours()+":"+date.getMinutes();
    	String statut = "réservée";	
    	String sqlSstring = "select * from reservation where DateDebut = '"+dateDebutDate+"' and HeureFin < '"+heureCourante+"' and payeOuNon = 0";
		
	
		ResultSet rs = ObjBDD.requeteSelect(sqlSstring);

		if(rs.next()) {
			String refClient = rs.getString("RefClient");
			String sqlSstringSelectMail = "select mail from client where NumeroMembre = '"+refClient+"' ";
			ResultSet rsMail = ObjBDD.requeteSelect(sqlSstringSelectMail);
			if(rsMail.next()) {
				String mail = rsMail.getString("Mail");
				String msg = "Vous ne vous êtes pas présenté pendant votre intervalle reservé, veuillez régler votre réservation.";
				String sqlStringInsert = "insert into notification (Contenu, Mail) values ('"+msg+"', '"+mail+"')  ";
				
			}

		}

	}
	
	
	public static void updateDelaiAttente() throws SQLException {
			
			
			Date date = new Date();
			String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	    	String heureDebut = date.getHours()+":"+date.getMinutes();
	    	
	    	String sqlSstring = "select * from reservation where DelaiAttente = 1 and DateDebut = '"+dateDebutDate+"' and heureDelaiAttenteMax = '"+heureDebut+"' ";
			
		
			ResultSet rs = ObjBDD.requeteSelect(sqlSstring);
	
			if(rs.next()) {
				int idReservation = rs.getInt("idReservation");
				String sqlSstringUpdate = "update reservation set delaiAttenteDepasse = 1  where idReservation = '"+idReservation+"'";
				ObjBDD.requeteUpdate(sqlSstringUpdate);
		
			}

		}
	
	
	public static boolean checkPlaceDisponible() throws SQLException {
		
		String sqlStringSelect = "select * from placestationnement where Statut = 'disponible' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		if(rs.next()) {
			
			return true;
		}
		return false;
	}
	
	public static ArrayList<String> renvoiePlaceDispo() throws SQLException {
			
			ArrayList<String> listePlaceDispo = new ArrayList<String>(); 
			
			String sqlStringSelect = "select RefPlaceStationnement from placestationnement where Statut = 'disponible' ";
			ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
			while(rs.next()) {
				String placeCourante = rs.getString("RefPlaceStationnement");
				listePlaceDispo.add(placeCourante);
				
			}
			return listePlaceDispo;
			
		}
	
	
	public static boolean checkDisponibilitePlacePreciseIntervalle(ArrayList<String> listePlaceDispo, String heureDebutPlusDuree) throws SQLException {
			
			Date date = new Date();
			String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			String heureDebut = date.getHours()+":"+date.getMinutes();
			boolean placeDispo = false;
			
				for(String placeCourante : listePlaceDispo) {
					String sqlStringSelect = "SELECT * FROM reservation WHERE DateDebut = '"+dateDebutDate+"' AND HeureDebut NOT BETWEEN '"+heureDebut+"' AND '"+heureDebutPlusDuree+"' AND RefPlaceStationnement = '"+placeCourante+"' ";
					ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
					if(rs.next()) {
						placeDispo = true;
						
					}

			}
			return placeDispo;
			
}
			
	
public static String attribuePlace(ArrayList<String> listePlaceDispo, String heureDebutPlusDuree) throws SQLException {
		
		ArrayList<String> listePlaceDispoFinale = new ArrayList<String>();
		Date date = new Date();
		String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		String heureDebut = date.getHours()+":"+date.getMinutes();
		
		for(String placeCourante : listePlaceDispo) {
			String sqlStringSelect = "SELECT * FROM reservation WHERE DateDebut = '"+dateDebutDate+"' AND HeureDebut BETWEEN '"+heureDebut+"' AND '"+heureDebutPlusDuree+"' AND RefPlaceStationnement = '"+placeCourante+"' ";
			ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
			if(rs.next()==false) {
				listePlaceDispoFinale.add(placeCourante);
			}
		}
			
		if(listePlaceDispoFinale.size()> 0) {
			String resultat = listePlaceDispoFinale.get(0);
			return resultat;
		}
		return "Il n'y pas de place disponible, veuillez faire marche arrière et quitter le parking";
		 
}
	
public static String attribuePlaceLorsReservation(ArrayList<String> listePlaceDispo, String dateDebut, String heureDebut, String heureFin) throws SQLException {
		
		ArrayList<String> listePlaceDispoFinale = new ArrayList<String>();
				
		for(String placeCourante : listePlaceDispo) {
			String sqlStringSelect = "SELECT * FROM reservation WHERE DateDebut = '"+dateDebut+"' AND HeureDebut BETWEEN '"+heureDebut+"' AND '"+heureFin+"' AND RefPlaceStationnement = '"+placeCourante+"' ";
			ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
			if(rs.next()==false) {
				listePlaceDispoFinale.add(placeCourante);
			}
		}
			
		if(listePlaceDispoFinale.size()> 0) {
			String resultat = listePlaceDispoFinale.get(0);
			String sqlStringSelect = "UPDATE placestationnement SET Statut = 'réservée' WHERE  RefPlaceStationnement = '"+resultat+"' ";
			ObjBDD.requeteUpdate(sqlStringSelect);
			
			return resultat;
		}
		return "Il n'y pas de place disponible, veuillez effectuer une réservation à un autre moment.";
		 
}


	public static void updateStatutPlaceDispo(String refPlaceStationnement) {
		
		String sqlStringUpdatePlace = "update placestationnement set Statut = 'disponible' where RefPlaceStationnement = '"+refPlaceStationnement+"' ";
		ObjBDD.requeteUpdate(sqlStringUpdatePlace);
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

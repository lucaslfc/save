package OperationClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import BDD.ObjBDD;
import OperationParking.Parking;
import OperationPlaceStationnement.PlaceStationnement;
import OperationReservation.Reservation;
import OperationTarif.Tarif;
import OperationVehicule.Vehicule;
import OperationTarif.Tarif;

public class User {
	
	private Integer ClientInteger;
	private String numeroMembreString;
	private String nomString;
	private String prenomString;
	private String adresseString;
	private String numeroTel;
	private String mailString;
	private String numeroCarte;
	private String passwordString;
	
	public User(String nomString, String prenomString, String adresseString, String numeroTel, String mailString,
			String numeroCarte, String passwordString) {
		this.nomString = nomString;
		this.prenomString = prenomString;
		this.adresseString = adresseString;
		this.numeroTel = numeroTel;
		this.mailString = mailString;
		this.numeroCarte = numeroCarte;
		this.passwordString = passwordString;
	}
	
	public User(Integer ClientInteger, String nomString, String prenomString, String adresseString, String numeroTel, String mailString,
			String numeroCarte, String passwordString) {
		this.ClientInteger = ClientInteger;
		this.nomString = nomString;
		this.prenomString = prenomString;
		this.adresseString = adresseString;
		this.numeroTel = numeroTel;
		this.mailString = mailString;
		this.numeroCarte = numeroCarte;
		this.passwordString = passwordString;
	}
	
	public User(Integer ClientInteger, String numeroMembreString, String nomString, String prenomString, String adresseString, String numeroTel, String mailString,
			String numeroCarte, String passwordString) {
		this.ClientInteger = ClientInteger;
		this.numeroMembreString = numeroMembreString;
		this.nomString = nomString;
		this.prenomString = prenomString;
		this.adresseString = adresseString;
		this.numeroTel = numeroTel;
		this.mailString = mailString;
		this.numeroCarte = numeroCarte;
		this.passwordString = passwordString;
	}
	
	public static boolean checkReservation(String refClient) throws SQLException {
		Date date = new Date();
		String dateAjourdhui= new SimpleDateFormat("yyyy-MM-dd").format(date);
		String sqlStringSelect = "Select * from reservation WHERE RefClient = '"+refClient+"' AND DateDebut = '"+dateAjourdhui+"'";
				
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkNumeroMembre(String numeroMembre) throws SQLException {
		
		String sqlStringSelect = "Select * from client WHERE NumeroMembre = '"+numeroMembre+"'";
				
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public static boolean verifPlaceStationnement(String user) throws SQLException {
		boolean resultat = false;
		String requete = "SELECT idClient from Client where Nom='"+user+"'";
		ResultSet restemp = ObjBDD.requeteSelect(requete);	
		requete = "Select RefPlaceStationnement from reservation where RefPlaceStationnement="+restemp.findColumn("RefPlaceStationnement")+"'";
		restemp = ObjBDD.requeteSelect(requete);
		requete = "Select Statut from placestationnement where RefPlaceStationnement="+restemp.findColumn("Statut")+"'";
		if(requete=="libre") {
			resultat=true;
		}
		return resultat;
	}
	
	
	public static boolean InsertNewUser(String numeroMembre, String nomString, String prenomString, String adresseString, String numeroTel, String mailString,
			String numeroCarte, String passwordString) {
		
		String sqlStringInsert = "Insert into client(NumeroMembre, Nom, Prenom, Adresse, NumeroTel, Mail, NumeroCarte, Password)"
				+ "VALUES('"+numeroMembre+"','"+nomString+"','"+prenomString+"','"+adresseString+"',"+numeroTel+",'"+mailString+"',"+numeroCarte+",'"+passwordString+"')";
		if(ObjBDD.requeteInsert(sqlStringInsert)) {
			
			return true;
		}
		return false;
	}
	
	public static String genereNumeroMembre(String numeroTel) {
		
		
		String numeroMembre = "";
        for(int i=0; i<3; i++)
        {
        	Random random = new Random();
        	int val = 65 + random.nextInt(25);
        	numeroMembre += (char)val;
        }
        numeroMembre += numeroTel;
        
        return numeroMembre;
		
	}
	
	public static User checkUser(String mailString, String passwordString) throws SQLException {
		String sqlString = "Select * from client WHERE Mail = '"+mailString+"' AND Password = '"+passwordString+"'";
		ResultSet rs = ObjBDD.requeteSelect(sqlString);
		if(rs.next()) {
			return new User(rs.getInt("idClient"),rs.getString("NumeroMembre"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Adresse"), Integer.toString(rs.getInt("NumeroTel")), rs.getString("Mail"), Long.toString(rs.getLong("NumeroCarte")), rs.getString("Password"));
		}
		return null;
	}
	
	public static void sePresenterParkingSansReservation() throws SQLException {
		
		Scanner scannerCheck = new Scanner(System.in);
		
		System.out.println("Saisir la plaque d'immatriculation du véhicule : ");
		String immatriculationVehicule = scannerCheck.nextLine();
		// si le numero d'immatriculation est reconnue
		if(Vehicule.checkVehicule(immatriculationVehicule)) {
			// on cherche le proprio
			String proprioVehicule = Vehicule.checkUserVehicule(immatriculationVehicule);
			// si il n'y  a pas de réservation existnte associée au client qui possède le véhicule
			if(checkReservation(proprioVehicule)==false) {
				System.out.println("Veuillez saisir l'heure de départ prévue : ");
				String heureDepart = scannerCheck.nextLine();
				Reservation.createNewReservationImmatriculationReconnue(heureDepart, immatriculationVehicule);
			// sinon on lui dit qu'il avait bien une réservation de faite
			}else {
				System.out.println("Vous avez une réservation à ce jour.");
			}
		}
		// si le numero d'immatriculation n'est pas reconnue
		else {
			System.out.println("Numéro de plaque non reconnue, veuillez saisir votre numéro de membre : ");
			String numeroMembre = scannerCheck.nextLine();
			if(checkNumeroMembre(numeroMembre)) {
				System.out.println("Veuillez saisir une heure de départ : ");
				String heureDepart = scannerCheck.nextLine();
				Reservation.createNewReservationImmatriculationNonReconnue(heureDepart, numeroMembre);
			} else {
				System.out.print("Numéro de membre non reconnu, veuillez faire marche arrière et quitter les lieux.");
			}
			
		}
				
}
		
	

public static boolean checkRetard(String refClient) throws SQLException {
	
	Date date = new Date();
	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	String sqlStringInsert = "SELECT * from reservation where DateDebut = '"+dateDebutDate+"' and DelaiAttente = 1 and refCient = '"+refClient+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	if(ObjBDD.requeteInsert(sqlStringInsert)) {
		String refPlaceStationnement = rs.getString("RefPlaceStationnement");
		String sqlSstringUpdate = "update placeStationnement set Statut = 'occupée' where RefPlaceStationnement = '"+refPlaceStationnement+"'";
		ObjBDD.requeteUpdate(sqlSstringUpdate);
		return true;
	}
	return false;
}

public static boolean paiementSupplement(String refClient) throws SQLException {
	
	Date date = new Date();
	String heureDebut = date.getHours()+":"+date.getMinutes();
	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	String sqlStringInsert = "update reservation set payeOuNon = 1 where RefClient = '"+refClient+"' and DateDebut = '"+dateDebutDate+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	if(ObjBDD.requeteInsert(sqlStringInsert)) {
		
		return true;
	}
	return false;

			 
			  
}


public static boolean updateTarifReservation(String refClient) throws SQLException {
	
	Date date = new Date();
	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	String sqlStringInsert = "SELECT * from reservation where DateDebut = '"+dateDebutDate+"' and DelaiAttente = 1 and refCient = '"+refClient+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	if(ObjBDD.requeteInsert(sqlStringInsert)) {
		String refPlaceStationnement = rs.getString("RefPlaceStationnement");
		String sqlSstringUpdate = "update placeStationnement set Statut = 'occupée' where RefPlaceStationnement = '"+refPlaceStationnement+"'";
		ObjBDD.requeteUpdate(sqlSstringUpdate);
		return true;
	}
	return false;
}
		
public static boolean checkRetardAttenteNonDepassee(String refClient) throws SQLException {
	
	Date date = new Date();
	String heureDebut = date.getHours()+":"+date.getMinutes();
	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	String sqlStringInsert = "SELECT * from reservation where DateDebut = '"+dateDebutDate+"' and DelaiAttente = 1 and heureDelaiAttenteMax > '"+heureDebut+"' and refCient = '"+refClient+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	
	if(rs.next()) {
		Scanner scannerChoix = new Scanner(System.in);
		System.out.print("Vous êtes arrivé en retard mais avant la fin de période d'attente, vous devez donc payer un supplément si vous souhaitez maintenir la réservation : ");
		System.out.print("1) je souhaite payer le supplément");
		System.out.print("2) je ne souhaite pas payer le supplément");
		int choixSupplement = scannerChoix.nextInt();
		
		switch(choixSupplement) {
		  case 1:
			  System.out.print("Veuillez payer le supplément de " + Tarif.prixMaintien() + "€");
			  String prixAvantSupplement = rs.getString("Prix");
			  String idReservation = rs.getString("idReservation");
			  int supplementToInt = Integer.parseInt(Tarif.prixMaintien());
			  int prixToInt = Integer.parseInt(prixAvantSupplement);
			  int prixFinal = prixToInt + supplementToInt;
			  String sqlStringSelect = "UPDATE reservation SET Prix = '"+prixFinal+"' WHERE  idReservation = '"+idReservation+"' ";
			  ObjBDD.requeteUpdate(sqlStringSelect);
		    break;
		  case 2 :
			  System.out.print("Veuillez reculer et sortir du parking.");
		    break;
		  default:break;	  
		}
		
		return true;
	}
	return false;
}



public static boolean checkRetardAttenteDepassee(String refClient) throws SQLException {
	
	Date date = new Date();
	String heureDebut = date.getHours()+":"+date.getMinutes();
	String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	String sqlStringInsert = "SELECT * from reservation where DateDebut = '"+dateDebutDate+"' and delaiAttenteDepasse = 1 and heureDelaiAttenteMax < '"+heureDebut+"' and refCient = '"+refClient+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	if(rs.next()) {
		
		Scanner scannerChoix = new Scanner(System.in);
		System.out.print("Vous êtes arrivé au-delà du délai d'attente après le début de la période réservée. Veuillez nous dire combien de temps souhaitez vous rester : ");
		Integer choixDuree = scannerChoix.nextInt();
		
		LocalTime heureDebutTime = LocalTime.parse(heureDebut);
		LocalTime heureDebutPlusDuree = heureDebutTime.plusMinutes(choixDuree);
		long diffHeure = Duration.between(heureDebutTime, heureDebutPlusDuree).toMinutes();
		
		
		if (PlaceStationnement.checkPlaceDisponible() && PlaceStationnement.checkDisponibilitePlacePreciseIntervalle(PlaceStationnement.renvoiePlaceDispo(), heureDebutPlusDuree.toString())) {
			System.out.print("Veuillez vous garer à la place : "+PlaceStationnement.attribuePlace(PlaceStationnement.renvoiePlaceDispo(), heureDebutPlusDuree.toString()));
			System.out.print("Après avoir payé la somme de : " + diffHeure*Tarif.prixMinute() + "€");
			
			
		}
		
		return true;
	}
	return false;
}


public static boolean sortirDuParking() throws SQLException {
	
	boolean res = false;
	Scanner scannerNumeroReservation = new Scanner(System.in);
	System.out.print("Veuillez renseigner le numéro de réservation : ");
	String numReservation = scannerNumeroReservation.nextLine();
	
	String sqlStringInsert = "select * from reservation where NumeroReservation = '"+numReservation+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringInsert);
	
	if(rs.next()) {
		
		Date date = new Date();
		String minutes;
		
		if(date.getMinutes() < 10) {
			minutes = "0"+ String.valueOf(date.getMinutes());
		} else {
			minutes = String.valueOf(date.getMinutes());
		}
		String heureCourante = date.getHours()+":"+minutes;
		String heureFin = rs.getString("HeureFin");
		double tarifReservation = rs.getDouble("Prix");
		String refPlaceStationnement = rs.getString("RefPlaceStationnement");
		
		LocalTime heureCouranteLocalTime = LocalTime.parse(heureCourante);
		LocalTime heureFinLocalTime = LocalTime.parse(heureFin);
		long diffHeureCouranteFinReserv = Duration.between(heureFinLocalTime, heureCouranteLocalTime).toMinutes();
		
		if(heureFinLocalTime.compareTo(heureCouranteLocalTime) > 0) {
			
			System.out.println("Veuillez payer la somme de : " + tarifReservation + "€");
			// requête réservation payée
			Reservation.updatePaiementReservation(numReservation);
			// requête qui change etat de la place
			PlaceStationnement.updateStatutPlaceDispo(refPlaceStationnement);
			
			System.out.println("Merci de votre visite, à bientôt.");
			return true;
			
		}else if(heureFinLocalTime.compareTo(heureCouranteLocalTime) < 0) {
			
			if(diffHeureCouranteFinReserv < 60 ) {
				
				System.out.println("Vous n'êtes pas parti comme prévu après l'expiration de votre période réservée. Vous allez devoir payer un supplément de : " + diffHeureCouranteFinReserv*Tarif.prixDepassementMinoree() + "€");
				System.out.print("Le prix total de votre réservation vous revient donc à : " + Tarif.round(tarifReservation+(diffHeureCouranteFinReserv*Tarif.prixDepassementMinoree()), 2) + "€");
				
				// requête réservation payée
				Reservation.updatePaiementReservation(numReservation);
				// requête qui change etat de la place
				PlaceStationnement.updateStatutPlaceDispo(refPlaceStationnement);
				
			} else {
				
				System.out.println("Vous n'êtes pas parti comme prévu après l'expiration de votre période réservée. Vous allez devoir payer un supplément de : " + diffHeureCouranteFinReserv*Tarif.prixDepassementMajoree() + "€");
				System.out.println("Le prix total de votre réservation vous revient donc à : " + Tarif.round(tarifReservation+(diffHeureCouranteFinReserv*Tarif.prixDepassementMajoree()), 2) + "€");
				
				// requête réservation payée
				Reservation.updatePaiementReservation(numReservation);
				// requête qui change etat de la place
				PlaceStationnement.updateStatutPlaceDispo(refPlaceStationnement);
			
			}

			
		} else {
			
			System.out.println("Veuillez payer la somme de : " + tarifReservation + "€");
			// requête réservation payée
			Reservation.updatePaiementReservation(numReservation);
			// requête qui change etat de la place
			PlaceStationnement.updateStatutPlaceDispo(refPlaceStationnement);
			
			System.out.println("Merci de votre visite, à bientôt.");
			res = true;
			return res;
			
		}
		
		
	} else {
		System.out.print("Numéro de réservation inconnue, veuillez ressayer ou vous rendre à l'acceuil afin de déboquer la situation.");
		return false;
		
	}
	
	return res;
	
}



	
public static void sePresenterParkingAvecReservation() throws SQLException {
		
	Scanner scannerNumeroReservation = new Scanner(System.in);
		
	System.out.print("Veuillez renseigner le numéro de réservation : ");
	String numReservation = scannerNumeroReservation.nextLine();
	
	String sqlStringSelectReservation = "select * from reservation where NumeroReservation = '"+numReservation+"' ";
	ResultSet rs = ObjBDD.requeteSelect(sqlStringSelectReservation);
	
	if(rs.next()) {
		String refPlace = rs.getString("RefPlaceStationnement");
		String sqlStringSelectStatutPlace = "select Statut from placestationnement where RefPlaceStationnement = '"+refPlace+"' ";
		ResultSet rsStatut = ObjBDD.requeteSelect(sqlStringSelectStatutPlace);
		String statutPlace = rsStatut.getString("Statut");
		
		if(statutPlace=="occupée" && Parking.estComplet()) {
			System.out.println("En raison d'une surréservation, votre emplacement n'est pas disiponible. Veuillez nous excuser pour le désagrément et sortir du parking.");
			System.out.print("La facture de cette réservation ne vous sera pas appliqué.");
			Reservation.updatePaiementReservation(numReservation);
		}
		
		
	} else {
		System.out.print("La barrière va s'ouvrir, veuillez rentrer et vous garer à votre place");
	}
		
		

}
	
		
		
			
			
			
			
	

	public Integer getClientInteger() {
		return ClientInteger;
	}

	public void setClientInteger(Integer clientInteger) {
		ClientInteger = clientInteger;
	}

	public String getNomString() {
		return nomString;
	}

	public void setNomString(String nomString) {
		this.nomString = nomString;
	}

	public String getPrenomString() {
		return prenomString;
	}

	public void setPrenomString(String prenomString) {
		this.prenomString = prenomString;
	}

	public String getAdresseString() {
		return adresseString;
	}

	public void setAdresseString(String adresseString) {
		this.adresseString = adresseString;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public String getMailString() {
		return mailString;
	}

	public void setMailString(String mailString) {
		this.mailString = mailString;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public String getNumeroMembre() {
		return numeroMembreString;
	}

	public void setNumeroMembre(String numeroMembre) {
		this.numeroMembreString = numeroMembre;
	}
	
}

package OperationClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import BDD.ObjBDD;
import OperationParking.Parking;
import OperationReservation.Reservation;
import OperationVehicule.Vehicule;

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
	
	public boolean verifPlaceStationnement(String user) throws SQLException {
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

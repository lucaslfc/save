package OperationReservation;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import BDD.ObjBDD;
import OperationPlaceStationnement.PlaceStationnement;
import OperationTarif.Tarif;
import OperationVehicule.Vehicule;

public class Reservation {
	
	
	private Integer ReservationInteger;
	private String numeroReservation;
	private Date dateDebutDate;
	private Date dateFinDate;
	private Time heureDebut;
	private Time heureFin;
	private Double prixDouble;
	private String refPlaceStationnementString;
	private String refClientString;
	private String refImmatricuclationString;
	
	
	
	
	public Reservation(String numeroReservation, Date dateDebutDate, Date dateFinDate, Time heureDebut, Time heureFin, Double prixDouble, String refPlaceStationnementString,
			String refClientString, String refImmatricuclationString) {
		this.numeroReservation = numeroReservation;
		this.dateDebutDate = dateDebutDate;
		this.dateFinDate = dateFinDate;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.prixDouble = prixDouble;
		this.refPlaceStationnementString = refPlaceStationnementString;
		this.refClientString = refClientString;
		this.refImmatricuclationString = refImmatricuclationString;
	}
	
	
	public static boolean createNewReservationImmatriculationReconnue(String heureFin, String refImmatriculationString) throws SQLException {
		
		String numeroReservation = genereNumeroReservation();

		Date date = new Date();
		String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		String dateFinDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		String heureDebut = date.getHours()+":"+date.getMinutes();

		LocalTime heureDebutLocalTime = LocalTime.parse(heureDebut);
		LocalTime heureFinLocalTime = LocalTime.parse(heureFin);
		long HeureDebutFinLocalTime = Duration.between(heureDebutLocalTime, heureFinLocalTime).toMinutes();
		double prixReservation = HeureDebutFinLocalTime*Tarif.prixMinute();
		
		String refPlaceStationnementString = PlaceStationnement.attribuePlaceLorsReservation(PlaceStationnement.renvoiePlaceDispo(), dateDebutDate, heureDebut, heureFin);
		
		String sqlString = "Insert into reservation(NumeroReservation, DateDebut, DateFin, HeureDebut, HeureFin, Prix, RefPlaceStationnement, RefClient, RefImmatriculation )"
				+ "VALUES('"+numeroReservation+"','"+dateDebutDate+"','"+dateFinDate+"','"+heureDebut+"','"+heureFin+"','"+prixReservation+"','"+refPlaceStationnementString+"','"+Vehicule.checkUserVehicule(refImmatriculationString)+"','"+refImmatriculationString+"')";
		
		
		if(ObjBDD.requeteInsert(sqlString)) {
			System.out.print("Une réservation vous a été crée, voici son numéro \n\n" + numeroReservation + "\n\nNotez le soigneusement, au cas où il vous serait demandé.");
			System.out.print("Votre place de stationnement est " + refPlaceStationnementString + ".");
			return true;
		}
		return false;
	}
	
	public static boolean createNewReservationImmatriculationNonReconnue(String heureFin, String refClient) throws SQLException {
			
			String numeroReservation = genereNumeroReservation();
	
			Date date = new Date();
			String dateDebutDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			String dateFinDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			String heureDebut = date.getHours()+":"+date.getMinutes();
	
			LocalTime heureDebutLocalTime = LocalTime.parse(heureDebut);
			LocalTime heureFinLocalTime = LocalTime.parse(heureFin);
			long HeureDebutFinLocalTime = Duration.between(heureDebutLocalTime, heureFinLocalTime).toMinutes();
			double prixReservation = HeureDebutFinLocalTime*Tarif.prixMinute();
			
			String refPlaceStationnementString = PlaceStationnement.attribuePlaceLorsReservation(PlaceStationnement.renvoiePlaceDispo(), dateDebutDate, heureDebut, heureFin);
			
			String refImmatriculationString  = "";
			
			String sqlString = "Insert into reservation(NumeroReservation, DateDebut, DateFin, HeureDebut, HeureFin, Prix, RefPlaceStationnement, RefClient, RefImmatriculation )"
					+ "VALUES('"+numeroReservation+"','"+dateDebutDate+"','"+dateFinDate+"','"+heureDebut+"','"+heureFin+"','"+prixReservation+"','"+refPlaceStationnementString+"','"+refClient+"','"+refImmatriculationString+"')";
			
			
			if(ObjBDD.requeteInsert(sqlString)) {
				System.out.print("Une réservation vous a été crée, voici son numéro \n\n" + numeroReservation + "\n\nNotez le soigneusement, au cas où il vous serait demandé.");
				System.out.print("Votre place de stationnement est " + refPlaceStationnementString + ".");
				return true;
			}
			return false;
		}
	
	public static boolean insertNewReservation() throws SQLException {
				
		String numeroReservation = genereNumeroReservation();

		Scanner scannerSaisieReservation = new Scanner(System.in);
		
		System.out.println("Saisir date de début (fomat aaaa-mm-jj) : ");
		String dateDebutDate = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir date de fin (fomat aaaa-mm-jj) : ");
		String dateFinDate = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir heure de debut (fomat hh:mm) : ");
		String heureDebut = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir heure de fin (fomat hh:mm) : ");
		String heureFin = scannerSaisieReservation.nextLine();
		
		LocalTime heureDebutLocalTime = LocalTime.parse(heureDebut);
		LocalTime heureFinLocalTime = LocalTime.parse(heureFin);
		long HeureDebutFinLocalTime = Duration.between(heureDebutLocalTime, heureFinLocalTime).toMinutes();
		double prixReservation = HeureDebutFinLocalTime*Tarif.prixMinute();
		
		String refPlaceStationnementString = PlaceStationnement.attribuePlaceLorsReservation(PlaceStationnement.renvoiePlaceDispo(), dateDebutDate, heureDebut, heureFin);

		
		System.out.println("Saisir votre numéro membre : ");
		String refClientString = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir immatriculation : ");
		String refImmatriculationString = scannerSaisieReservation.nextLine();
		

		String sqlString = "Insert into reservation(NumeroReservation, DateDebut, DateFin, HeureDebut, HeureFin, Prix, RefPlaceStationnement, RefClient, RefImmatriculation )"
				+ "VALUES('"+numeroReservation+"','"+dateDebutDate+"','"+dateFinDate+"','"+heureDebut+"','"+heureFin+"','"+prixReservation+"','"+refPlaceStationnementString+"','"+refClientString+"','"+refImmatriculationString+"')";
		
		scannerSaisieReservation.close();
		
		if(ObjBDD.requeteInsert(sqlString)) {
			System.out.print("Votre réservation a été effectuée, voici son numéro \n\n" + numeroReservation + "\n\nNotez le soigneusement.");
			System.out.print("Votre place de stationnement est " + refPlaceStationnementString + ".");
			return true;
		}
		return false;
	}

	
	public static String genereNumeroReservation() {
			
		
			String numeroReservation = "";
	        for(int i=0; i<4; i++)
	        {
	        	Random random = new Random();
	        	int val = 65 + random.nextInt(25);
	        	numeroReservation += (char)val;
	        }
	        
	        Random random = new Random();
	        int nb;
	        nb = 100+random.nextInt(999-100);
	        
	        numeroReservation += nb;
	        
	        return numeroReservation;
			
	}
	
	
public static void updatePaiementReservation(String numReservation) {
		
	String sqlStringUpdatePaiement = "update reservation set estPayeeOuNon = 1 where NumeroReservation = '"+numReservation+"' ";
	ObjBDD.requeteUpdate(sqlStringUpdatePaiement);
}

	
	public Integer getReservationInteger() {
		return ReservationInteger;
	}
	public void setReservationInteger(Integer reservationInteger) {
		ReservationInteger = reservationInteger;
	}
	public Date getDateDebutDate() {
		return dateDebutDate;
	}
	public void setDateDebutDate(Date dateDebutDate) {
		this.dateDebutDate = dateDebutDate;
	}
	public Date getDateFinDate() {
		return dateFinDate;
	}
	public void setDateFinDate(Date dateFinDate) {
		this.dateFinDate = dateFinDate;
	}
	public Double getPrixDouble() {
		return prixDouble;
	}
	public void setPrixDouble(Double prixDouble) {
		this.prixDouble = prixDouble;
	}
	public String getRefPlaceStationnementString() {
		return refPlaceStationnementString;
	}
	public void setRefPlaceStationnementString(String refPlaceStationnementString) {
		this.refPlaceStationnementString = refPlaceStationnementString;
	}
	public String getRefClientString() {
		return refClientString;
	}
	public void setRefClientString(String refClientString) {
		this.refClientString = refClientString;
	}
	public String getRefImmatricuclationString() {
		return refImmatricuclationString;
	}
	public void setRefImmatricuclationString(String refImmatricuclationString) {
		this.refImmatricuclationString = refImmatricuclationString;
	}

	public String getNumeroReservation() {
		return numeroReservation;
	}

	public void setNumeroReservation(String numeroReservation) {
		this.numeroReservation = numeroReservation;
	}



	public Time getHeureDebut() {
		return heureDebut;
	}



	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}



	public Time getHeureFin() {
		return heureFin;
	}



	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}

	
	
	
	

}

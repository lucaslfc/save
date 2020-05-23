package OperationReservation;

import java.sql.Date;
import java.util.Random;
import java.util.Scanner;

import BDD.ObjBDD;

public class Reservation {
	
	
	private Integer ReservationInteger;
	private String numeroReservation;
	private Date dateDebutDate;
	private Date dateFinDate;
	private Double prixDouble;
	private String refPlaceStationnementString;
	private String refClientString;
	private String refImmatricuclationString;
	
	
	public Reservation(String numeroReservation, Date dateDebutDate, Date dateFinDate, Double prixDouble, String refPlaceStationnementString,
			String refClientString, String refImmatricuclationString) {
		this.numeroReservation = numeroReservation;
		this.dateDebutDate = dateDebutDate;
		this.dateFinDate = dateFinDate;
		this.prixDouble = prixDouble;
		this.refPlaceStationnementString = refPlaceStationnementString;
		this.refClientString = refClientString;
		this.refImmatricuclationString = refImmatricuclationString;
	}
	
	
	
	public static boolean insertNewReservation() {
		
		String numeroReservation = genereNumeroReservation();

		Scanner scannerSaisieReservation = new Scanner(System.in);
		
		System.out.println("Saisir date de début (fomat aaaa-mm-jj) : ");
		String dateDebutDate = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir date de fin (fomat aaaa-mm-jj) : ");
		String dateFinDate = scannerSaisieReservation.nextLine();
		
		Double prixReservation = 5.00;
		
		System.out.println("Saisir place de stationnement : ");
		String refPlaceStationnementString = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir votre numéro membre : ");
		String refClientString = scannerSaisieReservation.nextLine();
		
		System.out.println("Saisir immatriculation : ");
		String refImmatriculationString = scannerSaisieReservation.nextLine();
		

		
		String sqlString = "Insert into reservation(NumeroReservation, DateDebut, DateFin, Prix, RefPlaceStationnement, RefClient, RefImmatriculation )"
				+ "VALUES('"+numeroReservation+"','"+dateDebutDate+"','"+dateFinDate+"','"+prixReservation+"','"+refPlaceStationnementString+"','"+refClientString+"','"+refImmatriculationString+"')";
		
		scannerSaisieReservation.close();
		
		if(ObjBDD.requeteInsert(sqlString)) {
			System.out.print("Votre réservation a été effectuée, voici son numéro \n\n" + numeroReservation + "\n\nNotez le soigneusement.");
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
	
	
	

}

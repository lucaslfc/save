package operationReservation;

import java.sql.Date;

import BDD.ObjBDD;

public class Reservation {
	
	
	private Integer ReservationInteger;
	private Date dateDebutDate;
	private Date dateFinDate;
	private Double prixDouble;
	private String refPlaceStationnementString;
	private String refClientString;
	private String refImmatricuclationString;
	
	
	public Reservation(Date dateDebutDate, Date dateFinDate, Double prixDouble, String refPlaceStationnementString,
			String refClientString, String refImmatricuclationString) {
		this.dateDebutDate = dateDebutDate;
		this.dateFinDate = dateFinDate;
		this.prixDouble = prixDouble;
		this.refPlaceStationnementString = refPlaceStationnementString;
		this.refClientString = refClientString;
		this.refImmatricuclationString = refImmatricuclationString;
	}
	
	
	
	public static boolean insertNewReservation(Date dateDebutDate, Date dateFinDate, Double prixDouble,
			String refPlaceStationnementString, String refClientString, String refImmatricuclationString) {

		String sqlString = "Insert into reservation(DateDebut, DateFin, Prix, RefPlaceStationnement, RefClient, RefImmatriculation )"
				+ "VALUES('"+dateDebutDate+"','"+dateFinDate+"','"+prixDouble+"', '"+refPlaceStationnementString+"', '"+refClientString+"', '"+refImmatricuclationString+"')";
		if(ObjBDD.requeteInsert(sqlString)) {
			return true;
		}
		return false;
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
	
	
	

}

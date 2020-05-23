package OperationParking;

import java.sql.SQLException;

import BDD.ObjBDD;

public class Parking {

	
	private Integer ParkingInteger;
	private String nomParkinString;
	private Integer nbPlacesInteger;
	private Integer nbPlacesDispoInteger;
	private String statutString;
	
	public Parking(String nomParkinString, Integer nbPlacesInteger, Integer nbPlacesDispoInteger, String statutString) {
		this.nomParkinString = nomParkinString;
		this.nbPlacesInteger = nbPlacesInteger;
		this.nbPlacesDispoInteger = nbPlacesDispoInteger;
		this.statutString = statutString;
	}
	
	
	public static boolean estComplet() throws SQLException {
		boolean resultat = false;
		String requete = "SELECT * FROM parking where Statut = 'complet'";
		if(ObjBDD.requeteSelect(requete).next()) {
			resultat =true;
		}
		return resultat;
	}
	
	

	public Integer getParkingInteger() {
		return ParkingInteger;
	}

	public void setParkingInteger(Integer parkingInteger) {
		ParkingInteger = parkingInteger;
	}

	public String getNomParkinString() {
		return nomParkinString;
	}

	public void setNomParkinString(String nomParkinString) {
		this.nomParkinString = nomParkinString;
	}

	public Integer getNbPlacesInteger() {
		return nbPlacesInteger;
	}

	public void setNbPlacesInteger(Integer nbPlacesInteger) {
		this.nbPlacesInteger = nbPlacesInteger;
	}

	public String getStatutString() {
		return statutString;
	}

	public void setStatutString(String statutString) {
		this.statutString = statutString;
	}


	public Integer getNbPlacesDispoInteger() {
		return nbPlacesDispoInteger;
	}


	public void setNbPlacesDispoInteger(Integer nbPlacesDispoInteger) {
		this.nbPlacesDispoInteger = nbPlacesDispoInteger;
	}
	
	
	
	
	
	
}

package OperationVehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import BDD.ObjBDD;
import OperationClient.User;

public class Vehicule {

	private Integer VehiculeInteger;
	private String immatriculationString;
	private String refClientString;
	
	public Vehicule(String immatriculationString, String refClientString) {
		this.immatriculationString = immatriculationString;
		this.refClientString = refClientString;
	}

	
	public static boolean InsertNewVehicule(String refClient) {
		
		Scanner scannerSaisieImmatriculation = new Scanner(System.in);
		
		System.out.print("Veuillez saisir le num�ro de votre plaque d'immatriculation : ");
		String immatriculation = scannerSaisieImmatriculation.nextLine();
		
		String sqlString = "Insert into vehicule(Immatriculation, refClient)"
				+ "VALUES('"+immatriculation+"','"+refClient+"')";
		
		scannerSaisieImmatriculation.close();
		
		if(ObjBDD.requeteInsert(sqlString)) {
			return true;
		}
		return false;
	}

	
	public static boolean checkVehicule(String immatriculationVehicule) throws SQLException {
		String sqlStringSelect = "Select * from vehicule WHERE Immatriculation = '"+immatriculationVehicule+"'";
		
	
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	
	public static String checkUserVehicule(String immatriculationVehicule) throws SQLException {
		String sqlStringSelect = "Select * from vehicule WHERE Immatriculation = '"+immatriculationVehicule+"'";
		
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		if(rs.next()) {
			String refClient =  rs.getString("refClient");
			return refClient;
		}
		return null;
	}
	
	

	public Integer getVehiculeInteger() {
		return VehiculeInteger;
	}


	public void setVehiculeInteger(Integer vehiculeInteger) {
		VehiculeInteger = vehiculeInteger;
	}


	public String getImmatriculationString() {
		return immatriculationString;
	}


	public void setImmatriculationString(String immatriculationString) {
		this.immatriculationString = immatriculationString;
	}


	public String getRefClientInteger() {
		return refClientString;
	}


	public void setRefClientInteger(String refClientInteger) {
		this.refClientString = refClientString;
	}
	
	
	
	
	
	

	
	
	
	
	
}

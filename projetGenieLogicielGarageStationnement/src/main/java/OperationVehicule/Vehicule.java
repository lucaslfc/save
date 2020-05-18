package OperationVehicule;

import BDD.ObjBDD;

public class Vehicule {

	private Integer VehiculeInteger;
	private String immatriculationString;
	private Integer refClientInteger;
	
	public Vehicule(String immatriculationString, Integer refClientInteger) {
		this.immatriculationString = immatriculationString;
		this.refClientInteger = refClientInteger;
	}

	
	public static boolean InsertNewVehicule(String immatriculation, Integer refClient) {
		String sqlString = "Insert into vehicule(Immatriculation, refClient)"
				+ "VALUES('"+immatriculation+"','"+refClient+"')";
		if(ObjBDD.requeteInsert(sqlString)) {
			return true;
		}
		return false;
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


	public Integer getRefClientInteger() {
		return refClientInteger;
	}


	public void setRefClientInteger(Integer refClientInteger) {
		this.refClientInteger = refClientInteger;
	}
	
	
	
	
	
	

	
	
	
	
	
}

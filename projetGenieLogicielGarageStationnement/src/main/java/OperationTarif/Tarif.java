package OperationTarif;

import java.sql.ResultSet;
import java.sql.SQLException;

import BDD.ObjBDD;
import OperationClient.User;

public class Tarif {
	
	
	private Integer TarifInteger;
	private String libelleTarifString;
	private double prixDouble;
	
	public Tarif(Integer tarifInteger, String libelleTarifString, double prixDouble) {
		TarifInteger = tarifInteger;
		this.libelleTarifString = libelleTarifString;
		this.prixDouble = prixDouble;
	}
	
	public static String prixMaintien() throws SQLException {
		String prix = null;
		String sqlString = "select prix FROM tarif where libelleTarif = 'fraisMaintien' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlString);
		if(rs.next()) {
			prix = rs.getString("prix");
		}
		
		return prix;
	}
	
	public static double prixMinute() throws SQLException {
		double prix = 0;
		String sqlString = "select prix FROM tarif where libelleTarif = 'fraisMinute' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlString);
		if(rs.next()) {
			prix = rs.getDouble("prix");
		}
		
		return prix;
	}
	
	
	public Integer getTarifInteger() {
		return TarifInteger;
	}
	public void setTarifInteger(Integer tarifInteger) {
		TarifInteger = tarifInteger;
	}
	public String getLibelleTarifString() {
		return libelleTarifString;
	}
	public void setLibelleTarifString(String libelleTarifString) {
		this.libelleTarifString = libelleTarifString;
	}

	public double getPrixDouble() {
		return prixDouble;
	}

	public void setPrixDouble(double prixDouble) {
		this.prixDouble = prixDouble;
	}


}

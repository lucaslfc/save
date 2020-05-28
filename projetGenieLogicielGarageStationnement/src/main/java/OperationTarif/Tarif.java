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
	
	public static double prixDepassementMinoree() throws SQLException {
		double prix = 0;
		String sqlString = "select prix FROM tarif where libelleTarif = 'tarifDepassementMinoree' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlString);
		if(rs.next()) {
			prix = rs.getDouble("prix");
		}
		
		return prix;
	}
	
	public static double prixDepassementMajoree() throws SQLException {
		double prix = 0;
		String sqlString = "select prix FROM tarif where libelleTarif = 'tarifDepassementMajoree' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlString);
		if(rs.next()) {
			prix = rs.getDouble("prix");
		}
		
		return prix;
	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
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

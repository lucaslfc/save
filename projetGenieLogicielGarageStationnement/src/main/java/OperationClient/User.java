package OperationClient;

import java.util.Random;

import BDD.ObjBDD;

public class User {
	
	private Integer ClientInteger;
	private String numeroMembre;
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
		return numeroMembre;
	}

	public void setNumeroMembre(String numeroMembre) {
		this.numeroMembre = numeroMembre;
	}
	
}

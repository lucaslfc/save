package BDD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionValidation {
	
	public static String verificationNom(String nom) {
		String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nom);
		if(matcher.matches() && nom.length()>2 && nom.length()<100) {
			return null;
		}
		return "Nom seulement alphabet && taille entre 2 && 100";
	}
	
	public static String verificationPrenom(String prenom) {
		String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(prenom);
		if(matcher.matches() && prenom.length()>2 && prenom.length()<100) {
			return null;
		}
		return "Prenom seulement alphabet && inferieur � 100";
	}
	
	public static String verificationAdresse(String adress) {
		String regex = "^\\d+\\s[A-z]+\\s[A-z]+[,]\\s[A-z]+[,]+\\s+[0-9]{5}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(adress);
		if(matcher.matches() && adress.length()>2 && adress.length()<1000) {
			return null;
		}
		return "Adresse au formation : 61 Avenue Kennedy, Nancy, 54000";
	}
	
	public static String verificationNumTel(String num) {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(num);
		if(matcher.matches() && num.length()==10) {
			return null;
		}
		return "Numéro tel (10 chiffres)";
	}
	
	public static String verificationNumCarte(String num) {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(num);
		if(matcher.matches() && num.length()==12) {
			return null;
		}
		return "Numéro carte (12 chiffres)";
	}
	
	public static String verificationMail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches() && email.length()<150) {
			return null;
		}
		return "adresse email invalide (150 carcteres max)";
	}
	
	public static String verificationPassword(String psw) {
		if(psw.length()>6 && psw.length() <50) {
			return null;
		}
		return "mot de passe entre 6 et 50 char";
	}
}

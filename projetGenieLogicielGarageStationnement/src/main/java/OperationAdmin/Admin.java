package OperationAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BDD.ObjBDD;
import OperationClient.User;

public class Admin {

	
	public static ArrayList<User> renvoieUser() throws SQLException {
		
		ArrayList<User> listeUser = new ArrayList<User>(); 
		
		String sqlStringSelect = "SELECT * FROM client WHERE Mail != 'admin' AND Password != 'admin' ";
		ResultSet rs = ObjBDD.requeteSelect(sqlStringSelect);
		
		while(rs.next()) {
			User userAdd = new User(rs.getInt("idClient"),rs.getString("NumeroMembre"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Adresse"), Integer.toString(rs.getInt("NumeroTel")), rs.getString("Mail"), Long.toString(rs.getLong("NumeroCarte")), rs.getString("Password"));
			listeUser.add(userAdd);
			
		}
		
		return listeUser;
	}
	
	
	
	
}

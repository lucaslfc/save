package controller;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import BDD.ObjBDD;
import OperationAdmin.Admin;
import OperationClient.User;
import OperationParking.Parking;
import OperationReservation.Reservation;
import OperationTarif.Tarif;
import OperationVehicule.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConnexionController {
	
	@FXML
	private TextField mail;
	
	@FXML
	private PasswordField password;
	
	
	@FXML
	private Button SeConnecter,Inscription;
	
	@FXML
	private Text ErrorMessage;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		ErrorMessage.setText("");
	}
	
	private int refClient;
	
	@FXML
	void ConnexionCompte(ActionEvent e) throws IOException {
		if(mail.getText().length()>0 && password.getText().length()>0) {
			try {
				User user = User.checkUser(mail.getText(), password.getText());
				
				if(user!=null) {
					refClient = user.getClientInteger();
					Scene scene = SeConnecter.getScene();
					Window window = scene.getWindow();
					window.hide();
					ConnectScene(user);
				}else {
					ErrorMessage.setText("Le mail ou le mdp est incorrect");
				}
			} catch (SQLException e1) {
				//
			}
		}else {
			ErrorMessage.setText("Le mail ou le mdp est vide");
		}
	}
	
	private void ConnectScene(User user) throws IOException, SQLException {
			
		if(user.getMailString().equals("admin") && user.getPasswordString().equals("admin")) {
			
			System.out.println("Que voulez-vous faire ?\n");
			System.out.print("1) Consulter le profil des clients\n");
			System.out.println("2) Modifier le prix des différents services\n");
			
			Scanner scannerChoix = new Scanner(System.in);
			System.out.print("Votre choix : ");
			int choix = scannerChoix.nextInt();
			
			switch(choix) {
			  case 1:
				  ArrayList<User> listeUser = new ArrayList<User>(); 
				  listeUser = Admin.renvoieUser();
				  
				  for(User userCourant : listeUser) {

					  System.out.print("Client n°" + userCourant.getClientInteger() + " :\n\n");
					  System.out.println("Numéro membre : " + userCourant.getNumeroMembre());
					  System.out.println("Nom : " + userCourant.getNomString());
					  System.out.println("Prénom : " + userCourant.getPrenomString());
					  System.out.println("Adresse : " + userCourant.getAdresseString());
					  System.out.println("Portable : " + userCourant.getNumeroTel());
					  System.out.println("Numéro carte : " + userCourant.getNumeroCarte());
					  System.out.println("Mail : " + userCourant.getMailString());
					  System.out.println("Mot de passe : " + userCourant.getPasswordString());
					  System.out.print("\n");
				  }
				  
				  
			    break;
			  case 2:
				  ArrayList<Tarif> listeTarif = new ArrayList<Tarif>(); 
				  listeTarif = Tarif.afficherTarifActuel();
				  
				  for(Tarif tarifCourant : listeTarif) {

					  System.out.print(tarifCourant.getTarifInteger() + ") ");
					  System.out.println("Libellé : " + tarifCourant.getLibelleTarifString());
					  System.out.println("   Prix : " + tarifCourant.getPrixDouble() + "€");
					  System.out.print("\n");
				  }
				  
				 Scanner scannerChoixTarif = new Scanner(System.in);
				 System.out.print("De quel service voulez-vous fixer le prix ? ");
				 int choixTarif = scannerChoixTarif.nextInt();
				
				 Scanner scannerNewTarif = new Scanner(System.in);
				 System.out.print("Saisir nouveau tarif : ");
				 double newTarif = scannerChoixTarif.nextDouble();
				 
				 String sqlStringUpdatePrix = "UPDATE tarif set prix = '"+newTarif+"' WHERE idTarif = '"+choixTarif+"' ";
				 ObjBDD.requeteUpdate(sqlStringUpdatePrix);
				 System.out.print("\nChangement effectué");

			    break;
			 
			  default:break;	
			}
			
		} else {
			
			System.out.print("Que voulez-vous faire ?\n");
			System.out.print("1) Enregistrer un numéro de plaque d'immatriculation\n");
			System.out.print("2) Effectuer une réservation\n");
			System.out.print("3) Se rendre au parking, sans réservation\n");
			System.out.print("4) Se rendre au parking, avec réservation\n");
			System.out.println("5) Sortir du parking\n");
			
			Scanner scannerChoix = new Scanner(System.in);
			System.out.print("Votre choix : ");
			int choix = scannerChoix.nextInt();
			
			switch(choix) {
			  case 1:
				  Vehicule.InsertNewVehicule(user.getNumeroMembre());
				  
			    break;
			  case 2:
				  Reservation.insertNewReservation();
			    break;
			  case 3:

				  if(Parking.estComplet()) {
					  System.out.println("Il n'y a pu de places de parking ! Nous vous invitons à  faire marche arrière et revenir plus tard.\n");
				  }else {
					  User.sePresenterParkingSansReservation();
				  }
			    break;
			  case 4:
				  
				  
			    break;
			  case 5:
				  User.sortirDuParking();  
				  
				    break;
			  default:break;			  
			}
			
		}
		
		
		
	}
	
	@FXML
	void InscriptionCompte() throws IOException{
		Stage stage;
        Parent root;
        stage = (Stage) Inscription.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Inscription.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}

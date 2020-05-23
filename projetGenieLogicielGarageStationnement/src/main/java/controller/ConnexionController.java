package controller;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

import OperationClient.User;
import OperationParking.Parking;
import OperationReservation.Reservation;
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
			
		System.out.println("Que voulez-vous faire ?\n");
		System.out.println("1) Enregistrer un numéro de plaque d'immatriculation");
		System.out.println("2) Effectuer une réservation\n");
		//System.out.println("3) Se rendre au parking\n");
		

		Scanner scannerChoix = new Scanner(System.in);
		System.out.print("Votre choix : ");
		int choix = scannerChoix.nextInt();
		
		switch(choix) {
		  case 1:
			  Vehicule.InsertNewVehicule(refClient);
			  
		    break;
		  case 2:
			  Reservation.insertNewReservation();
		    break;
		  case 3:

			  /**
			  if(Parking.estComplet()) {
				  System.out.println("Il n'y a pu de places de parking ! Nous vous invitons à  faire marche arrière et revenir plus tard.\n");
			  }
		    break;
		    **/
		  default:break;
		    // code block
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

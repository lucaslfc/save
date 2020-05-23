package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import BDD.InscriptionValidation;
import OperationClient.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InscriptionController implements Initializable {
	
	@FXML
	private TextField mail, password, numTel, adresse, prenom, nom, numCarte;
	
	@FXML
	private Button RedirectionConnexion, InscrireNouveauCompte;
	
	@FXML
	private Text ErrorNumCarte, ErrorNumTel, ErrorMDP, ErrorMail, ErrorAdresse, ErrorPrenom, ErrorNom, ErrorAutre, SuccessCrea;
	
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	@FXML
	void ConnexionCompte() throws IOException{
		Stage stage;
        Parent root;
        stage = (Stage) RedirectionConnexion.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Connexion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	void InscrireCompte() throws IOException{
		int erreur = 0;
		HashMap<Text, String> validationMap = new HashMap<Text, String>();
		
		validationMap.put(ErrorNumCarte, InscriptionValidation.verificationNumCarte(numCarte.getText()));
		validationMap.put(ErrorNumTel, InscriptionValidation.verificationNumTel(numTel.getText()));
		validationMap.put(ErrorMDP, InscriptionValidation.verificationPassword(password.getText()));
		validationMap.put(ErrorMail, InscriptionValidation.verificationMail(mail.getText()));
		validationMap.put(ErrorAdresse, InscriptionValidation.verificationAdresse(adresse.getText()));
		validationMap.put(ErrorPrenom, InscriptionValidation.verificationPrenom(prenom.getText()));
		validationMap.put(ErrorNom, InscriptionValidation.verificationNom(nom.getText()));
		
		//Verification des champs
		for(Entry<Text, String> entry : validationMap.entrySet()) {
			Text key = entry.getKey();
			String value = entry.getValue();
			if(value==null) {
				key.setText("");
			}else {
				erreur++;
				key.setText(value);
			}
		}

		// tentative d'inscription dans la BDD
		if (erreur==0) {
			String numeroMembre = User.genereNumeroMembre(numTel.getText());
			if(User.InsertNewUser(numeroMembre, nom.getText(), prenom.getText(), adresse.getText(), numTel.getText(), mail.getText(), numCarte.getText(), password.getText())){
				SuccessCrea.setText("Votre compte a été crée, voici votre numéro de membre \n\n" + numeroMembre + "\n\nNotez le soigneusement.");
				ErrorAutre.setText("");
			}else {
				ErrorAutre.setText("Erreur avec la BDD, il se peut que l'adresse mail soit déjà utilisé");
				SuccessCrea.setText("");
			}
		}else {
			ErrorAutre.setText("");
			SuccessCrea.setText("");
		}

	}
	
}

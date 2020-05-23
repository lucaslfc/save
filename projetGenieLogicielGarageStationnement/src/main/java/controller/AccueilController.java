package controller;

import java.net.URL;
import java.util.ResourceBundle;

import OperationClient.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class AccueilController implements Initializable {
	
	@FXML
	private Text accueilText;
	
	private User user;
	
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@FXML
	void test(ActionEvent e){
		System.out.println("Bonjour monsieur "+user.getNomString());
	}

	
	
}

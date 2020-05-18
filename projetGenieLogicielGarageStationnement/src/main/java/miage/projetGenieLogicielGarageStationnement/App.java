package miage.projetGenieLogicielGarageStationnement;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) {
		Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(""));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
	}

	public static void main(String[] args) {
		
	}
}

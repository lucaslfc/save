package miage.genielogiciel;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import BDD.ObjBDD;
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Connexion.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
	}

	public static void main(String[] args) {
		
		
		
		try {
			if(ObjBDD.CreateConnexion()) {
				launch(args);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

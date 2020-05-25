package miage.genielogiciel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import BDD.ObjBDD;
import BDD.TriggerBDD;
import OperationClient.User;
import OperationVehicule.Vehicule;
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

	public static void main(String[] args) throws SQLException{
		
		try {
			if(ObjBDD.CreateConnexion()) {
				
				ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
				executor.scheduleAtFixedRate(TriggerBDD.updateStatutPlace, 0, 5, TimeUnit.SECONDS);
				
				launch(args);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package welcomescreen;

import boardscreen.BoardController;
import clientconnection.ClientConnection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeScreen extends Application {
    
    @Override
    public void start(Stage stage)  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));


        Scene scene = new Scene(root);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        ClientConnection.establishConnection();
        ClientConnection.forwardMsg("hello");
        } catch (IOException e) {
            Logger.getLogger(WelcomeScreen.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}

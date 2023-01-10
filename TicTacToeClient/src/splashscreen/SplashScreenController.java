package splashscreen;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class SplashScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private void loadSplashScreen() throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource(("SplashScreen.fxml")));
        anchorPane.getChildren().setAll(pane);
        FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.seconds(1), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(javafx.util.Duration.seconds(1) , pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });
        fadeOut.setOnFinished((e) -> {

            AnchorPane parentContent;
            try {
                parentContent = FXMLLoader.load(getClass().getResource(("/main.fxml")));
                anchorPane.getChildren().setAll(parentContent);

            } catch (IOException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
    }
}

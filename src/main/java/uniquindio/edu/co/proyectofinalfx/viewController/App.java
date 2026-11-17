package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CineGestView.fxml"));
        Scene scene = new Scene(loader.load(), 1440, 900);
        scene.getStylesheets().add(getClass().getResource("/cinegest.css").toExternalForm());
        stage.setTitle("CINEMAUQ | CineGest");
        stage.setMinWidth(1100);
        stage.setMinHeight(720);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}

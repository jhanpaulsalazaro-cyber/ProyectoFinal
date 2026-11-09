
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL recurso = getClass().getResource(
                "/PrincipalView.fxml"
        );

        if (recurso == null) {
            throw new IOException(
                    "No se encontró PrincipalView.fxml. " +
                            "Verifica src/main/resources."
            );
        }

        FXMLLoader loader = new FXMLLoader(recurso);

        Scene scene = new Scene(loader.load(), 1000, 650);

        stage.setTitle("CINEMAFX | Universidad del Quindío");
        stage.setMinWidth(850);
        stage.setMinHeight(550);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

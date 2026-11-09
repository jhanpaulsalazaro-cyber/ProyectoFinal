
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PrincipalViewController {

    private static final String ADMIN_VIEW =
            "/uniquindio/edu/co/proyectofinalfx/AdministradorView.fxml";

    private static final String OPERADOR_VIEW =
            "/uniquindio/edu/co/proyectofinalfx/OperadorView.fxml";

    @FXML
    private void abrirAdministrador(ActionEvent event) {
        navegar(event, ADMIN_VIEW, "CINEMAFX | Administrador");
    }

    @FXML
    private void abrirOperador(ActionEvent event) {
        navegar(event, OPERADOR_VIEW, "CINEMAFX | Operador");
    }

    private void navegar(
            ActionEvent event,
            String ruta,
            String titulo) {

        try {
            URL recurso = getClass().getResource(ruta);

            if (recurso == null) {
                throw new IOException(
                        "No se encontró el FXML: " + ruta);
            }

            Parent root = FXMLLoader.load(recurso);

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

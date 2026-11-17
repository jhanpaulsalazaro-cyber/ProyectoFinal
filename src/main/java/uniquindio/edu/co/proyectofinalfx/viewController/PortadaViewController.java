
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PortadaViewController {

    // MÉTODOS DE NAVEGACIÓN

    @FXML
    private void abrirClientes() {
        cambiarVentana("/ClientesView.fxml");
    }

    @FXML
    private void abrirPeliculas() {
        cambiarVentana("/PeliculasView.fxml");
    }

    @FXML
    private void abrirOperaciones() {
        cambiarVentana("/OperacionesReportesView.fxml");
    }

    // MÉTODO GENERAL PARA CAMBIAR DE VENTANA

    private void cambiarVentana(String rutaFXML) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(rutaFXML)
            );

            Parent root = loader.load();

            Stage stage = (Stage) javafx.stage.Window.getWindows()
                    .stream()
                    .filter(ventana -> ventana.isShowing())
                    .findFirst()
                    .orElseThrow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("CINEMAUQ");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}

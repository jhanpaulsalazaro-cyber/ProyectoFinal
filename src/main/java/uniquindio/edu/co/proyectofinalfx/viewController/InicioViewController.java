package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/** Menú de entrada: el usuario elige con qué rol trabajar. */
public class InicioViewController {

    @FXML private Button btnOperador;

    @FXML
    private void ingresarOperador() {
        UtilidadesVista.cambiarEscena(btnOperador, "/OperadorView.fxml", "CINEMAUQ | Operador");
    }

    @FXML
    private void ingresarAdministrador() {
        UtilidadesVista.cambiarEscena(btnOperador, "/AdministradorView.fxml", "CINEMAUQ | Administrador");
    }
}

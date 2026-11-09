
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class OperadorViewController {

    @FXML private TableView<String> tablaCartelera;

    @FXML private TableColumn<String, String> colPelicula;
    @FXML private TableColumn<String, String> colHorario;
    @FXML private TableColumn<String, String> colSala;

    @FXML private GridPane gridAsientos;

    @FXML private Label labelAsientos;
    @FXML private Label labelTotal;

    private final Set<String> asientosSeleccionados =
            new LinkedHashSet<>();

    private static final int FILAS = 5;
    private static final int COLUMNAS = 8;

    @FXML
    public void initialize() {

        configurarCartelera();
        construirMapaAsientos();

        labelAsientos.setText("Asientos: ninguno");
        labelTotal.setText("Total: pendiente");
    }

    private void configurarCartelera() {

        colPelicula.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(dato.getValue())
        );

        // Las columnas de horario y sala se completarán
        // al conectar las funciones reales del modelo.
    }

    private void construirMapaAsientos() {

        gridAsientos.getChildren().clear();

        String[] letras = {"A", "B", "C", "D", "E"};

        for (int fila = 0; fila < FILAS; fila++) {

            for (int columna = 0; columna < COLUMNAS; columna++) {

                String codigo =
                        letras[fila] + (columna + 1);

                Button asiento = new Button(codigo);

                asiento.setPrefSize(38, 35);
                asiento.getStyleClass().add("seat-button");

                asiento.setOnAction(event ->
                        alternarAsiento(codigo, asiento)
                );

                gridAsientos.add(
                        asiento,
                        columna,
                        fila
                );
            }
        }
    }

    private void alternarAsiento(
            String codigo,
            Button boton) {

        if (asientosSeleccionados.contains(codigo)) {

            asientosSeleccionados.remove(codigo);

            boton.getStyleClass().remove("seat-selected");

            if (!boton.getStyleClass().contains("seat-button")) {
                boton.getStyleClass().add("seat-button");
            }

        } else {

            asientosSeleccionados.add(codigo);

            boton.getStyleClass().remove("seat-button");
            boton.getStyleClass().add("seat-selected");
        }

        actualizarResumen();
    }

    private void actualizarResumen() {

        if (asientosSeleccionados.isEmpty()) {

            labelAsientos.setText("Asientos: ninguno");

        } else {

            labelAsientos.setText(
                    "Asientos: " +
                            String.join(", ", asientosSeleccionados)
            );
        }

        // El valor real dependerá de Tarifa y de la función.
        labelTotal.setText("Total: pendiente de calcular");
    }

    @FXML
    private void seleccionarFuncion() {

        String funcion = tablaCartelera.getSelectionModel()
                .getSelectedItem();

        if (funcion == null) {

            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Seleccionar función",
                    "Primero debes seleccionar una función."
            );

            return;
        }

        asientosSeleccionados.clear();
        construirMapaAsientos();
        actualizarResumen();

        mostrarMensaje(
                Alert.AlertType.INFORMATION,
                "Función seleccionada",
                "Función seleccionada: " + funcion
        );
    }

    @FXML
    private void registrarVenta() {

        if (asientosSeleccionados.isEmpty()) {

            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Venta",
                    "Debes seleccionar al menos un asiento."
            );

            return;
        }

        mostrarMensaje(
                Alert.AlertType.INFORMATION,
                "Venta pendiente de integración",
                "Asientos seleccionados: " +
                        String.join(", ", asientosSeleccionados) +
                        "\n\nLa venta deberá registrarse mediante " +
                        "ModelFactoryController y Cine."
        );
    }

    private void mostrarMensaje(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

    @FXML
    private void volverPrincipal() {

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/PrincipalView.fxml")
            );

            Stage stage = (Stage) tablaCartelera
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CINEMAFX");
            stage.centerOnScreen();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

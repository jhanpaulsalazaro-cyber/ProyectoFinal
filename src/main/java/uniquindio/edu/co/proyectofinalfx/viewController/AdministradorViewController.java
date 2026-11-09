
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdministradorViewController {

    @FXML private TableView<String> tablaPeliculas;
    @FXML private TableView<String> tablaSalas;
    @FXML private TableView<String> tablaClientes;
    @FXML private TableView<String> tablaTarifas;

    @FXML private TableColumn<String, String> colCodigoPelicula;
    @FXML private TableColumn<String, String> colNombrePelicula;
    @FXML private TableColumn<String, String> colDuracion;
    @FXML private TableColumn<String, String> colGenero;
    @FXML private TableColumn<String, String> colClasificacion;

    @FXML private TableColumn<String, String> colNumeroSala;
    @FXML private TableColumn<String, String> colCapacidad;

    @FXML private TableColumn<String, String> colIdentificacion;
    @FXML private TableColumn<String, String> colNombreCliente;
    @FXML private TableColumn<String, String> colCorreo;

    @FXML private TableColumn<String, String> colTipoTarifa;
    @FXML private TableColumn<String, String> colValorTarifa;

    @FXML
    public void initialize() {
        configurarTablas();
    }

    private void configurarTablas() {

        configurarColumna(colCodigoPelicula);
        configurarColumna(colNombrePelicula);
        configurarColumna(colDuracion);
        configurarColumna(colGenero);
        configurarColumna(colClasificacion);

        configurarColumna(colNumeroSala);
        configurarColumna(colCapacidad);

        configurarColumna(colIdentificacion);
        configurarColumna(colNombreCliente);
        configurarColumna(colCorreo);

        configurarColumna(colTipoTarifa);
        configurarColumna(colValorTarifa);
    }

    private void configurarColumna(
            TableColumn<String, String> columna) {

        if (columna != null) {
            columna.setCellValueFactory(
                    dato -> new ReadOnlyStringWrapper(dato.getValue())
            );
        }
    }

    // ==========================================
    // PELÍCULAS
    // ==========================================

    @FXML
    private void agregarPelicula() {
        agregarRegistro(tablaPeliculas, "Registrar película");
    }

    @FXML
    private void editarPelicula() {
        editarRegistro(tablaPeliculas, "Editar película");
    }

    @FXML
    private void eliminarPelicula() {
        eliminarRegistro(tablaPeliculas);
    }

    // ==========================================
    // SALAS
    // ==========================================

    @FXML
    private void agregarSala() {
        agregarRegistro(tablaSalas, "Registrar sala");
    }

    @FXML
    private void editarSala() {
        editarRegistro(tablaSalas, "Editar sala");
    }

    @FXML
    private void eliminarSala() {
        eliminarRegistro(tablaSalas);
    }

    // ==========================================
    // CLIENTES
    // ==========================================

    @FXML
    private void agregarCliente() {
        agregarRegistro(tablaClientes, "Registrar cliente");
    }

    @FXML
    private void editarCliente() {
        editarRegistro(tablaClientes, "Editar cliente");
    }

    @FXML
    private void eliminarCliente() {
        eliminarRegistro(tablaClientes);
    }

    // ==========================================
    // TARIFAS
    // ==========================================

    @FXML
    private void agregarTarifa() {
        agregarRegistro(tablaTarifas, "Registrar tarifa");
    }

    @FXML
    private void actualizarTarifa() {
        editarRegistro(tablaTarifas, "Actualizar tarifa");
    }

    @FXML
    private void eliminarTarifa() {
        eliminarRegistro(tablaTarifas);
    }

    // ==========================================
    // OPERACIONES VISUALES TEMPORALES
    // ==========================================

    private void agregarRegistro(
            TableView<String> tabla,
            String titulo) {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle(titulo);
        dialog.setHeaderText(titulo);
        dialog.setContentText("Información del registro:");

        Optional<String> resultado = dialog.showAndWait();

        resultado.map(String::trim)
                .filter(texto -> !texto.isEmpty())
                .ifPresent(texto -> tabla.getItems().add(texto));
    }

    private void editarRegistro(
            TableView<String> tabla,
            String titulo) {

        String seleccionado = tabla.getSelectionModel()
                .getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Selecciona un registro para continuar."
            );
            return;
        }

        TextInputDialog dialog =
                new TextInputDialog(seleccionado);

        dialog.setTitle(titulo);
        dialog.setHeaderText(titulo);
        dialog.setContentText("Nueva información:");

        dialog.showAndWait()
                .map(String::trim)
                .filter(texto -> !texto.isEmpty())
                .ifPresent(texto -> {
                    int indice = tabla.getSelectionModel()
                            .getSelectedIndex();

                    tabla.getItems().set(indice, texto);
                });
    }

    private void eliminarRegistro(TableView<String> tabla) {

        String seleccionado = tabla.getSelectionModel()
                .getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Selecciona un registro para eliminar."
            );
            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("Eliminar registro");
        confirmacion.setContentText(
                "¿Deseas eliminar el registro seleccionado?"
        );

        Optional<ButtonType> resultado =
                confirmacion.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            tabla.getItems().remove(seleccionado);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

    // ==========================================
    // NAVEGACIÓN
    // ==========================================

    @FXML
    private void volverPrincipal() {

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/PrincipalView.fxml")
            );

            Stage stage = (Stage) tablaPeliculas.getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CINEMAFX");
            stage.centerOnScreen();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

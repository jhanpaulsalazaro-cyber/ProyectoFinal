package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.*;

import java.io.IOException;

public class OperacionesReportesViewController {

    // ELEMENTOS GRÁFICOS

    @FXML private TextField txtCodigoVenta;

    @FXML private TableView<Venta> tablaVentas;

    @FXML private TableColumn<Venta, String> colCodigoVenta;
    @FXML private TableColumn<Venta, String> colCliente;
    @FXML private TableColumn<Venta, String> colPelicula;
    @FXML private TableColumn<Venta, String> colValor;
    @FXML private TableColumn<Venta, String> colEstado;

    @FXML private Label lblTotalEntradas;
    @FXML private Label lblTotalIngresos;
    @FXML private Label lblPeliculaMasVendida;

    @FXML private Button btnVolver;

    // COMUNICACIÓN CON LA LÓGICA

    private final ModelFactoryController modelFactoryController =
            ModelFactoryController.getInstance();

    private final CineController cineController =
            modelFactoryController.getCineController();

    // INICIALIZACIÓN

    @FXML
    public void initialize() {

        colCodigoVenta.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        String.valueOf(dato.getValue().getCodigo())
                )
        );

        colCliente.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getCliente().getNombre()
                )
        );

        colPelicula.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getFuncion().getPelicula().getNombre()
                )
        );

        colValor.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        String.valueOf(dato.getValue().getValorPagado())
                )
        );

        colEstado.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        String.valueOf(dato.getValue().getEstadoVenta())
                )
        );

        tablaVentas.getItems().setAll(cineController.obtenerVentas());
        actualizarReportes();
    }

    // BUSCAR VENTA

    @FXML
    private void buscarVenta() {

        String codigoTexto = txtCodigoVenta.getText().trim();

        if (codigoTexto.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Campo incompleto",
                    "Ingresa el código de venta.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto);

            Venta venta = cineController.buscarVenta(codigo);

            if (venta != null) {
                tablaVentas.getItems().setAll(venta);
            } else {
                tablaVentas.getItems().clear();

                mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Venta no encontrada",
                        "No existe una venta con esos datos.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Código inválido",
                    "El código de venta debe ser numérico.");
        }
    }

    // CANCELAR VENTA

    @FXML
    private void cancelarVenta() {

        String codigoTexto = txtCodigoVenta.getText().trim();

        if (codigoTexto.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Campo incompleto",
                    "Ingresa el código de venta.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto);

            boolean cancelada = cineController.cancelarVenta(codigo);

            if (cancelada) {
                mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Venta cancelada",
                        "La venta se canceló correctamente.");

                tablaVentas.getItems().setAll(
                        cineController.obtenerVentas()
                );

                actualizarReportes();

            } else {
                mostrarAlerta(Alert.AlertType.WARNING,
                        "No se pudo cancelar",
                        "Verifica los datos de la venta.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Código inválido",
                    "El código de venta debe ser numérico.");
        }
    }

    // ACTUALIZAR REPORTES

    @FXML
    private void actualizarReportes() {

        lblTotalEntradas.setText(
                "Total de entradas vendidas: "
                        + cineController.calcularTotalEntradasVendidas()
        );

        lblTotalIngresos.setText(
                "Total de ingresos: $"
                        + cineController.calcularTotalIngresos()
        );

        Pelicula pelicula = cineController.buscarPeliculaMasVendida();

        if (pelicula != null) {
            lblPeliculaMasVendida.setText(
                    "Película más vendida: " + pelicula.getNombre()
            );
        } else {
            lblPeliculaMasVendida.setText(
                    "Película más vendida: Sin datos"
            );
        }
    }

    // VOLVER A LA PORTADA

    @FXML
    private void volverPortada() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/PortadaView.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo regresar a la portada.");
        }
    }

    // MENSAJES

    private void mostrarAlerta(Alert.AlertType tipo,
                               String titulo,
                               String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}


package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.*;

import java.io.IOException;

public class PeliculasViewController {

    // ELEMENTOS GRÁFICOS

    @FXML private TextField txtBuscarPelicula;
    @FXML private TextField txtCodigoFuncion;
    @FXML private TextField txtAsiento;
    @FXML private TextField txtDocumentoCliente;
    @FXML private TableView<Pelicula> tablaPeliculas;
    @FXML private TextField txtCodigoVenta;

    @FXML private TableColumn<Pelicula, Integer> colCodigo;
    @FXML private TableColumn<Pelicula, String> colNombre;
    @FXML private TableColumn<Pelicula, Integer> colDuracion;
    @FXML private TableColumn<Pelicula, ClasificacionPelicula> colClasificacion;

    @FXML private Button btnVolver;

    // COMUNICACIÓN CON LA LÓGICA

    private final ModelFactoryController modelFactoryController =
            ModelFactoryController.getInstance();

    private final CineController cineController =
            modelFactoryController.getCineController();


    @FXML
    public void initialize() {

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colDuracion.setCellValueFactory(
                new PropertyValueFactory<>("duracion")
        );

        colClasificacion.setCellValueFactory(
                new PropertyValueFactory<>("clasificacionPelicula")
        );

        mostrarPeliculas();
    }

    @FXML
    private void mostrarPeliculas() {

        tablaPeliculas.getItems().setAll(
                cineController.obtenerPeliculas()
        );

    }
    @FXML
    private void buscarPelicula() {

        String texto = txtBuscarPelicula.getText().trim();

        if (texto.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Ingresa el código de la película."
            );
            return;
        }

        try {

            int codigo = Integer.parseInt(texto);

            Pelicula pelicula = cineController.buscarPelicula(codigo);

            if (pelicula != null) {
                tablaPeliculas.getItems().setAll(pelicula);
            } else {
                tablaPeliculas.getItems().clear();

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Película no encontrada",
                        "No existe una película con ese código."
                );
            }

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código inválido",
                    "Debes ingresar un código numérico."
            );

        }
    }


    @FXML
    private void consultarFuncion() {

        String texto = txtCodigoFuncion.getText().trim();

        if (texto.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Ingresa el código de la función."
            );
            return;
        }

        try {

            int codigo = Integer.parseInt(texto);

            Funcion funcion = cineController.buscarFuncion(codigo);

            if (funcion == null) {
                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Función no encontrada",
                        "No existe una función con ese código."
                );
                return;
            }

            if (funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Función no disponible",
                        "La función no se encuentra programada."
                );
                return;
            }

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Función encontrada",
                    "Película: " + funcion.getPelicula().getNombre()
                            + "\nFecha: " + funcion.getFecha()
                            + "\nHora: " + funcion.getHora()
                            + "\nSala: " + funcion.getSala().getNombre()
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código inválido",
                    "El código de la función debe ser numérico."
            );

        }
    }

    private Asiento buscarAsientoDisponible(int codigoFuncion, int codigoAsiento) {

        for (Asiento asiento : cineController.obtenerAsientosDisponibles(codigoFuncion)) {

            if (asiento.getCodigo() == codigoAsiento) {
                return asiento;
            }
        }

        return null;
    }
    @FXML
    private void buscarCliente() {

        String documento = txtDocumentoCliente.getText().trim();

        if (documento.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Documento requerido",
                    "Ingresa el documento del cliente."
            );
            return;
        }

        Cliente cliente = cineController.buscarCliente(documento);

        if (cliente == null) {
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Cliente no encontrado",
                    "No existe un cliente registrado con ese documento."
            );
            return;
        }

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Cliente encontrado",
                "Nombre: " + cliente.getNombre()
                        + "\nTipo de cliente: " + cliente.getTipoCliente()
        );
    }

    @FXML
    private void registrarVenta() {

        String documentoCliente = txtDocumentoCliente.getText().trim();
        String codigoFuncionTexto = txtCodigoFuncion.getText().trim();
        String codigoAsientoTexto = txtAsiento.getText().trim();
        String codigoVentaTexto = txtCodigoVenta.getText().trim();

        if (documentoCliente.isEmpty()
                || codigoFuncionTexto.isEmpty() || codigoAsientoTexto.isEmpty()
                || codigoVentaTexto.isEmpty()) {

            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos",
                    "Ingresa todos los datos solicitados.");
            return;
        }

        try {
            int codigoVenta = Integer.parseInt(codigoVentaTexto);
            int codigoFuncion = Integer.parseInt(codigoFuncionTexto);
            int codigoAsiento = Integer.parseInt(codigoAsientoTexto);

            Cliente cliente = cineController.buscarCliente(documentoCliente);
            Funcion funcion = cineController.buscarFuncion(codigoFuncion);

            if (cliente == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Datos incorrectos",
                        "Verifica que el documento del cliente esté registrado en el sistema.");
                return;
            }

            if (funcion == null || funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
                mostrarAlerta(Alert.AlertType.WARNING, "Función no disponible",
                        "La función no existe o no está programada.");
                return;
            }

            Asiento asiento = buscarAsientoDisponible(codigoFuncion, codigoAsiento);

            if (asiento == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Asiento no disponible",
                        "El asiento no existe o ya está ocupado.");
                return;
            }

            Tarifa tarifa = cineController.obtenerTarifa(cliente.getTipoCliente());

            Venta venta = cineController.registrarVenta(
                    codigoVenta,
                    funcion,
                    cliente,
                    asiento,
                    tarifa
            );

            if (venta != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Venta registrada",
                        "La venta se registró correctamente."
                                + "\nCódigo: " + venta.getCodigo()
                                + "\nCliente: " + cliente.getNombre()
                                + "\nPelícula: " + funcion.getPelicula().getNombre()
                                + "\nAsiento: " + asiento.getFila() + "-" + asiento.getNumero()
                                + "\nValor: " + venta.getValorPagado());

                txtCodigoVenta.clear();
                txtAsiento.clear();

            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Venta no registrada",
                        "No fue posible registrar la venta. Verifica los datos.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Código inválido",
                    "Los códigos de venta, función y asiento deben ser numéricos.");
        }
    }

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
                    "Error", "No se pudo regresar a la portada.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

}

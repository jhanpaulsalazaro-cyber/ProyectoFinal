
package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.Cliente;
import uniquindio.edu.co.proyectofinalfx.model.TipoCliente;

import java.io.IOException;

public class ClientesViewController {

    // COMPONENTES FXML

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<TipoCliente> cmbTipoCliente;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colDocumento;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private Button btnVolver;

    // CONEXIÓN CON LA LÓGICA

    private final ModelFactoryController modelFactoryController =
            ModelFactoryController.getInstance();

    private CineController cineController =
            modelFactoryController.getCineController();

    // LISTA OBSERVABLE

    private ObservableList<Cliente> listaClientes =
            FXCollections.observableArrayList();

    // INICIALIZACIÓN

    @FXML
    public void initialize() {

        configurarTabla();

        configurarComboBox();

        actualizarTabla();
    }

    // CONFIGURAR TABLA

    private void configurarTabla() {

        colDocumento.setCellValueFactory(
                new PropertyValueFactory<>("documentoIdentidad")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

    }

    // CONFIGURAR TIPOS DE CLIENTE

    private void configurarComboBox() {

        cmbTipoCliente.setItems(
                FXCollections.observableArrayList(
                        TipoCliente.values()
                )
        );

    }

    // REGISTRAR CLIENTE

    @FXML
    private void registrarCliente() {

        String documento = txtDocumento.getText().trim();
        String nombre = txtNombre.getText().trim();

        TipoCliente tipoCliente = cmbTipoCliente.getValue();

        if (documento.isEmpty()
                || nombre.isEmpty()
                || tipoCliente == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debes completar todos los campos."
            );

            return;
        }

        try {

            Cliente cliente = new Cliente(
                    documento,
                    nombre,
                    tipoCliente
            );

            boolean registrado =
                    cineController.registrarCliente(cliente);

            if (registrado) {

                actualizarTabla();

                limpiarCampos();

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Registro exitoso",
                        "El cliente fue registrado correctamente."
                );

            } else {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Cliente existente",
                        "No fue posible registrar el cliente. "
                                + "Verifica si el documento ya está registrado."
                );

            }

        } catch (IllegalArgumentException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de registro",
                    e.getMessage()
            );

        }
    }

    // BUSCAR CLIENTE

    @FXML
    private void buscarCliente() {

        String documento = txtDocumento.getText().trim();

        if (documento.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Documento requerido",
                    "Ingresa el documento del cliente."
            );

            return;
        }

        Cliente cliente = cineController.buscarCliente(documento);

        if (cliente != null) {

            listaClientes.setAll(cliente);

            txtNombre.setText(cliente.getNombre());

            cmbTipoCliente.setValue(cliente.getTipoCliente());

        } else {

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Cliente no encontrado",
                    "No existe un cliente con ese documento."
            );

        }
    }

    // ACTUALIZAR TABLA

    private void actualizarTabla() {

        listaClientes.setAll(
                cineController.obtenerClientes()
        );

        tablaClientes.setItems(listaClientes);

    }

    // LIMPIAR CAMPOS

    @FXML
    private void limpiarCampos() {

        txtDocumento.clear();

        txtNombre.clear();

        cmbTipoCliente.getSelectionModel().clearSelection();

        actualizarTabla();

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

            stage.setTitle("CINEMAUQ");

            stage.show();

        } catch (IOException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de navegación",
                    "No fue posible regresar a la portada."
            );

            e.printStackTrace();

        }
    }

    // MÉTODO PARA MOSTRAR ALERTAS

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);

        alerta.setHeaderText(null);

        alerta.setContentText(mensaje);

        alerta.showAndWait();

    }
}

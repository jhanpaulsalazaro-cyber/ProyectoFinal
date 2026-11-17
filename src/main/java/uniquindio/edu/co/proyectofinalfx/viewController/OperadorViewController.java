package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.*;

import java.util.ArrayList;
import java.util.List;

import static uniquindio.edu.co.proyectofinalfx.viewController.UtilidadesVista.*;

/**
 * Vista del Operador. Funcionalidades:
 * registrar ventas, cancelar ventas, consultar películas,
 * consultar funciones y consultar disponibilidad de funciones.
 */
public class OperadorViewController {

    private final CineController cine = ModelFactoryController.getInstance().getCineController();

    // Asiento elegido en el mapa de la sala para la venta en curso.
    private Asiento asientoSeleccionado;

    // ---------- MENÚ ----------
    @FXML private Label lblTituloVista, lblTotalClientes;
    @FXML private Button btnCartelera, btnDisponibilidad, btnVenta, btnCancelacion, btnCambiarRol;
    @FXML private ScrollPane scrollCartelera, scrollDisponibilidad, scrollVenta, scrollCancelacion;

    // ---------- CONSULTAR PELÍCULAS Y FUNCIONES ----------
    @FXML private FlowPane contenedorPeliculas;
    @FXML private TableView<Funcion> tablaFunciones;
    @FXML private TableColumn<Funcion, String> colFuncionPelicula, colFuncionSala, colFuncionFecha,
            colFuncionHora, colFuncionPrecio, colFuncionDisponibles;

    // ---------- CONSULTAR DISPONIBILIDAD ----------
    @FXML private ComboBox<Funcion> cmbFuncionDisponibilidad;
    @FXML private Label lblDispTotal, lblDispLibres, lblDispOcupados, lblAsientosLibres;
    @FXML private TilePane mapaDisponibilidad;

    // ---------- REGISTRAR VENTA ----------
    @FXML private ComboBox<Pelicula> cmbPeliculasVenta;
    @FXML private ComboBox<Funcion> cmbFuncionesVenta;
    @FXML private ComboBox<Cliente> cmbClientesVenta;
    @FXML private ComboBox<TipoCliente> cmbTarifasVenta;
    @FXML private Label lblAsientoSeleccionado, lblTotalVenta, lblResumenAsientos;
    @FXML private TilePane contenedorAsientos;

    // ---------- CANCELAR VENTA ----------
    @FXML private TextField txtBuscarVenta;
    @FXML private TableView<Venta> tablaVentas;
    @FXML private TableColumn<Venta, String> colVentaCodigo, colVentaFecha, colVentaCliente, colVentaFuncion,
            colVentaAsiento, colVentaValor, colVentaEstado;

    @FXML
    private void initialize() {
        configurarColumnas();

        cmbTarifasVenta.setItems(FXCollections.observableArrayList(TipoCliente.values()));
        mostrarCon(cmbPeliculasVenta, Pelicula::getNombre);
        mostrarCon(cmbClientesVenta, c -> c.getDocumentoIdentidad() + " · " + c.getNombre()
                + " (" + c.getTipoCliente() + ")");
        mostrarCon(cmbFuncionesVenta, this::descripcionFuncion);
        mostrarCon(cmbFuncionDisponibilidad, f -> f.getPelicula().getNombre() + " · " + descripcionFuncion(f));

        mostrarCartelera();
    }

    private void configurarColumnas() {
        colFuncionPelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colFuncionSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colFuncionFecha.setCellValueFactory(c -> texto(c.getValue().getFecha().toString()));
        colFuncionHora.setCellValueFactory(c -> texto(c.getValue().getHora().toString()));
        colFuncionPrecio.setCellValueFactory(c -> texto(MONEDA.format(c.getValue().getValorBase())));
        colFuncionDisponibles.setCellValueFactory(c -> texto(c.getValue().contarAsientosDisponibles()
                + " de " + c.getValue().getSala().getListSalaAsientos().size()));

        colVentaCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colVentaFecha.setCellValueFactory(c -> texto(c.getValue().getFechaHoraVenta().format(FECHA_HORA)));
        colVentaCliente.setCellValueFactory(c -> texto(c.getValue().getCliente().getNombre()));
        colVentaFuncion.setCellValueFactory(c -> texto(c.getValue().getFuncion().getCodigo() + " · "
                + c.getValue().getFuncion().getPelicula().getNombre()));
        colVentaAsiento.setCellValueFactory(c -> texto(nombreAsiento(c.getValue().getAsiento())));
        colVentaValor.setCellValueFactory(c -> texto(MONEDA.format(c.getValue().getValorPagado())));
        colVentaEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoVenta().toString()));
    }

    /** Recarga la información del modelo en todas las secciones. */
    private void actualizarDatos() {
        lblTotalClientes.setText("" + cine.obtenerClientes().size());

        contenedorPeliculas.getChildren().clear();
        for (Pelicula p : peliculasDisponibles()) {
            contenedorPeliculas.getChildren().add(crearTarjetaPelicula(p));
        }

        List<Funcion> programadas = funcionesProgramadas();
        tablaFunciones.setItems(FXCollections.observableArrayList(programadas));
        recargar(cmbFuncionDisponibilidad, programadas);
        recargar(cmbPeliculasVenta, peliculasDisponibles());
        recargar(cmbClientesVenta, cine.obtenerClientes());
        tablaVentas.setItems(FXCollections.observableArrayList(cine.obtenerVentas()));
    }

    // =====================================================================
    // NAVEGACIÓN
    // =====================================================================

    private void cambiarSeccion(String titulo, ScrollPane seccion, Button boton) {
        mostrarSeccion(seccion, boton,
                new ScrollPane[]{scrollCartelera, scrollDisponibilidad, scrollVenta, scrollCancelacion},
                new Button[]{btnCartelera, btnDisponibilidad, btnVenta, btnCancelacion});
        lblTituloVista.setText(titulo);
        actualizarDatos();
    }

    @FXML private void mostrarCartelera() { cambiarSeccion("Cartelera y Funciones", scrollCartelera, btnCartelera); }
    @FXML private void mostrarDisponibilidad() { cambiarSeccion("Disponibilidad de funciones", scrollDisponibilidad, btnDisponibilidad); consultarDisponibilidad(); }
    @FXML private void mostrarVenta() { cambiarSeccion("Vender entrada", scrollVenta, btnVenta); }
    @FXML private void mostrarCancelacion() { cambiarSeccion("Cancelar venta", scrollCancelacion, btnCancelacion); }

    @FXML
    private void cambiarRol() {
        cambiarEscena(btnCambiarRol, "/InicioView.fxml", "CINEMAUQ");
    }

    // =====================================================================
    // CONSULTAR PELÍCULAS
    // =====================================================================

    private VBox crearTarjetaPelicula(Pelicula p) {
        VBox tarjeta = new VBox(8);
        tarjeta.getStyleClass().add("panel");
        tarjeta.setPrefWidth(245);
        Label clasif = new Label(p.getClasificacionPelicula().toString());
        clasif.getStyleClass().add("accent-value");
        Label nombre = new Label(p.getNombre());
        nombre.setStyle("-fx-text-fill:white;-fx-font-size:15px;-fx-font-weight:bold;");
        Label info = new Label(p.getGeneroPelicula() + " · " + p.getDuracion() + " min");
        info.getStyleClass().add("muted-label");
        tarjeta.getChildren().addAll(clasif, nombre, info);
        return tarjeta;
    }

    // =====================================================================
    // CONSULTAR DISPONIBILIDAD
    // =====================================================================

    @FXML
    private void consultarDisponibilidad() {
        mapaDisponibilidad.getChildren().clear();
        Funcion funcion = cmbFuncionDisponibilidad.getValue();
        if (funcion == null) {
            lblDispTotal.setText("0");
            lblDispLibres.setText("0");
            lblDispOcupados.setText("0");
            lblAsientosLibres.setText("Selecciona una función para consultar sus asientos.");
            return;
        }

        List<Asiento> asientos = funcion.getSala().getListSalaAsientos();
        lblDispTotal.setText("" + asientos.size());
        lblDispLibres.setText("" + funcion.contarAsientosDisponibles());
        lblDispOcupados.setText("" + funcion.contarAsientosOcupados());

        StringBuilder libres = new StringBuilder();
        for (Asiento asiento : asientos) {
            boolean disponible = funcion.asientoEstaDisponible(asiento);
            Label celda = new Label(nombreAsiento(asiento));
            celda.getStyleClass().addAll("seat", disponible ? "seat-free" : "seat-busy");
            mapaDisponibilidad.getChildren().add(celda);
            if (disponible) libres.append(nombreAsiento(asiento)).append("  ");
        }
        lblAsientosLibres.setText(libres.isEmpty() ? "No hay asientos disponibles."
                : "Asientos disponibles: " + libres.toString().trim());
    }

    // =====================================================================
    // REGISTRAR VENTA
    // =====================================================================

    @FXML
    private void abrirRegistroCliente() {
        Dialog<ButtonType> d = new Dialog<>();
        d.setTitle("Registrar cliente");
        d.setHeaderText("Datos del nuevo cliente");
        TextField doc = new TextField();
        doc.setPromptText("Número de documento");
        TextField nombre = new TextField();
        nombre.setPromptText("Nombre completo");
        ComboBox<TipoCliente> tipo = new ComboBox<>(FXCollections.observableArrayList(TipoCliente.values()));
        tipo.setPromptText("Tipo de cliente");
        VBox caja = new VBox(9, new Label("Documento"), doc, new Label("Nombre"), nombre, new Label("Tipo"), tipo);
        caja.setPadding(new Insets(10));
        d.getDialogPane().setContent(caja);
        d.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        d.showAndWait().ifPresent(r -> {
            if (r != ButtonType.OK) return;
            if (doc.getText().isBlank() || nombre.getText().isBlank() || tipo.getValue() == null) {
                aviso("Completa documento, nombre y tipo de cliente.");
                return;
            }
            Cliente nuevo = new Cliente(doc.getText().trim(), nombre.getText().trim(), tipo.getValue());
            if (cine.registrarCliente(nuevo)) {
                actualizarDatos();
                cmbClientesVenta.setValue(nuevo);
                seleccionarClienteVenta();
                aviso("Cliente registrado correctamente.");
            } else {
                aviso("No se pudo registrar. Ya existe una persona con ese documento.");
            }
        });
    }

    @FXML
    private void seleccionarPeliculaVenta() {
        Pelicula pelicula = cmbPeliculasVenta.getValue();
        List<Funcion> funciones = new ArrayList<>();
        for (Funcion f : funcionesProgramadas()) {
            if (f.getPelicula() == pelicula) funciones.add(f);
        }
        cmbFuncionesVenta.setItems(FXCollections.observableArrayList(funciones));
        cmbFuncionesVenta.setValue(null);
        seleccionarFuncionVenta();
    }

    @FXML
    private void seleccionarFuncionVenta() {
        asientoSeleccionado = null;
        dibujarAsientosVenta();
        calcularTotalVenta();
    }

    @FXML
    private void seleccionarClienteVenta() {
        Cliente cliente = cmbClientesVenta.getValue();
        if (cliente != null) {
            // El tipo de entrada corresponde al tipo del cliente.
            cmbTarifasVenta.setValue(cliente.getTipoCliente());
        }
        calcularTotalVenta();
    }

    @FXML
    private void calcularTotalVenta() {
        Funcion funcion = cmbFuncionesVenta.getValue();
        if (funcion == null) {
            lblTotalVenta.setText(MONEDA.format(0));
            return;
        }
        Tarifa tarifa = cine.obtenerTarifa(cmbTarifasVenta.getValue());
        lblTotalVenta.setText(MONEDA.format(tarifa != null ? tarifa.valor() : funcion.getValorBase()));
    }

    /** Dibuja el mapa de la sala de la función elegida para escoger asiento. */
    private void dibujarAsientosVenta() {
        contenedorAsientos.getChildren().clear();
        lblAsientoSeleccionado.setText(asientoSeleccionado == null ? "Ninguno" : nombreAsiento(asientoSeleccionado));

        Funcion funcion = cmbFuncionesVenta.getValue();
        if (funcion == null || funcion.getSala() == null) {
            lblResumenAsientos.setText("Selecciona una función para ver los asientos");
            return;
        }

        List<Asiento> asientos = funcion.getSala().getListSalaAsientos();
        lblResumenAsientos.setText("Total: " + asientos.size()
                + "  ·  Disponibles: " + funcion.contarAsientosDisponibles()
                + "  ·  Ocupados: " + funcion.contarAsientosOcupados());

        for (Asiento asiento : asientos) {
            Button boton = new Button(nombreAsiento(asiento));
            boton.getStyleClass().add("seat");
            if (!funcion.asientoEstaDisponible(asiento)) {
                boton.getStyleClass().add("seat-busy");
                boton.setDisable(true);
            } else if (asiento == asientoSeleccionado) {
                boton.getStyleClass().add("seat-selected");
            } else {
                boton.getStyleClass().add("seat-free");
            }
            boton.setOnAction(e -> {
                asientoSeleccionado = asiento;
                dibujarAsientosVenta();
            });
            contenedorAsientos.getChildren().add(boton);
        }
    }

    @FXML
    private void registrarVenta() {
        Funcion funcion = cmbFuncionesVenta.getValue();
        Cliente cliente = cmbClientesVenta.getValue();
        TipoCliente tipo = cmbTarifasVenta.getValue();

        if (cmbPeliculasVenta.getValue() == null || funcion == null) {
            aviso("Selecciona la película y la función.");
            return;
        }
        if (cliente == null) {
            aviso("Selecciona un cliente registrado o registra uno nuevo.");
            return;
        }
        if (tipo == null) {
            aviso("Selecciona el tipo de entrada.");
            return;
        }
        if (tipo != cliente.getTipoCliente()) {
            aviso("El tipo de entrada debe corresponder al tipo del cliente (" + cliente.getTipoCliente() + ").");
            return;
        }
        if (asientoSeleccionado == null) {
            aviso("Selecciona un asiento disponible en el mapa de la sala.");
            return;
        }

        Venta venta = cine.registrarVenta(cine.generarCodigoVenta(), funcion, cliente,
                asientoSeleccionado, cine.obtenerTarifa(tipo));

        if (venta == null) {
            aviso("No se pudo registrar la venta. Verifica que la función esté programada y el asiento disponible.");
            return;
        }

        aviso("Venta registrada."
                + "\nCódigo: " + venta.getCodigo()
                + "\nFecha y hora: " + venta.getFechaHoraVenta().format(FECHA_HORA)
                + "\nCliente: " + cliente.getNombre()
                + "\nPelícula: " + funcion.getPelicula().getNombre()
                + "\nAsiento: " + nombreAsiento(venta.getAsiento())
                + "\nValor: " + MONEDA.format(venta.getValorPagado()));

        asientoSeleccionado = null;
        actualizarDatos();
        dibujarAsientosVenta();
    }

    // =====================================================================
    // CANCELAR VENTA
    // =====================================================================

    @FXML
    private void buscarVenta() {
        try {
            Venta v = cine.buscarVenta(Integer.parseInt(txtBuscarVenta.getText().trim()));
            tablaVentas.setItems(FXCollections.observableArrayList(v == null ? List.of() : List.of(v)));
            if (v == null) aviso("No existe una venta con ese código.");
        } catch (NumberFormatException e) {
            aviso("Ingresa un código de venta numérico.");
        }
    }

    @FXML
    private void mostrarVentas() {
        tablaVentas.setItems(FXCollections.observableArrayList(cine.obtenerVentas()));
    }

    @FXML
    private void cancelarVenta() {
        Venta v = tablaVentas.getSelectionModel().getSelectedItem();
        if (v == null) {
            aviso("Selecciona una venta de la tabla.");
            return;
        }
        if (cine.cancelarVenta(v.getCodigo())) {
            actualizarDatos();
            aviso("Venta cancelada. El asiento " + nombreAsiento(v.getAsiento()) + " quedó libre.");
        } else {
            aviso("No se pudo cancelar: la venta ya estaba cancelada.");
        }
    }

    // =====================================================================
    // AUXILIARES
    // =====================================================================

    private String descripcionFuncion(Funcion f) {
        return f.getFecha() + " " + f.getHora() + " · " + f.getSala().getNombre()
                + " · " + MONEDA.format(f.getValorBase());
    }

    private List<Funcion> funcionesProgramadas() {
        List<Funcion> lista = new ArrayList<>();
        for (Funcion f : cine.obtenerFunciones()) {
            if (f.getEstadoFuncion() == EstadoFuncion.PROGRAMADA) lista.add(f);
        }
        return lista;
    }

    private List<Pelicula> peliculasDisponibles() {
        List<Pelicula> lista = new ArrayList<>();
        for (Pelicula p : cine.obtenerPeliculas()) {
            if (p.getEstadoPelicula() == EstadoPelicula.DISPONIBLE) lista.add(p);
        }
        return lista;
    }
}

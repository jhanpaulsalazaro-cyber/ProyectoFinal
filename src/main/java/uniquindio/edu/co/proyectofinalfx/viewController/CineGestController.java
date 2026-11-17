package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/** Controlador de presentación de CineGest. El FXML es editable desde Scene Builder. */
public class CineGestController {

    // Las operaciones del administrador en Cine exigen su documento.
    private static final String DOCUMENTO_ADMIN = "100000001";

    private final CineController cine = ModelFactoryController.getInstance().getCineController();
    private final NumberFormat moneda = NumberFormat.getCurrencyInstance(Locale.of("es", "CO"));
    private final DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Asiento elegido en el mapa de la sala.
    private Asiento asientoSeleccionado;

    // ---------- MENÚ Y CARTELERA ----------
    @FXML private Label lblTituloVista, lblTotalClientes, lblAsientoSeleccionado, lblTotalVenta,
            lblKpiEntradas, lblKpiIngresos, lblKpiPelicula, lblResumenAsientos;
    @FXML private Button btnCartelera, btnTaquilla, btnAdmin, btnReportes;
    @FXML private ScrollPane scrollCartelera, scrollTaquilla, scrollAdmin, scrollReportes;
    @FXML private FlowPane contenedorPeliculas;
    @FXML private TableView<Funcion> tablaFuncionesCartelera, tablaReportes;
    @FXML private TableColumn<Funcion, String> colFuncionPelicula, colFuncionSala, colFuncionFecha,
            colFuncionPrecio, colFuncionDisponibilidad;
    @FXML private TableColumn<Funcion, String> colReportePelicula, colReporteFecha, colReporteSala,
            colReporteOcupados, colReporteDisponibles, colReporteOcupacion;

    // ---------- TAQUILLA ----------
    @FXML private ComboBox<Pelicula> cmbPeliculasVenta;
    @FXML private ComboBox<Funcion> cmbFuncionesVenta;
    @FXML private ComboBox<Cliente> cmbClientesVenta;
    @FXML private ComboBox<TipoCliente> cmbTarifasVenta;
    @FXML private TilePane contenedorAsientos;
    @FXML private TableView<Venta> tablaVentas;
    @FXML private TableColumn<Venta, String> colVentaCodigo, colVentaFecha, colVentaCliente, colVentaFuncion,
            colVentaAsiento, colVentaValor, colVentaEstado;
    @FXML private TextField txtBuscarVenta;

    // ---------- ADMINISTRACIÓN: PELÍCULAS ----------
    @FXML private TextField txtCodigoPelicula, txtNombrePelicula, txtDuracionPelicula;
    @FXML private ComboBox<GeneroPelicula> cmbGeneroPelicula;
    @FXML private ComboBox<ClasificacionPelicula> cmbClasificacionPelicula;
    @FXML private ComboBox<EstadoPelicula> cmbEstadoPelicula;
    @FXML private TableView<Pelicula> tablaPeliculasAdmin;
    @FXML private TableColumn<Pelicula, String> colAdminCodigo, colAdminNombre, colAdminGenero,
            colAdminDuracion, colAdminEstado;

    // ---------- ADMINISTRACIÓN: SALAS ----------
    @FXML private TextField txtCodigoSala, txtNombreSala, txtCapacidadSala;
    @FXML private ComboBox<EstadoSala> cmbEstadoSala;
    @FXML private TableView<Sala> tablaSalas;
    @FXML private TableColumn<Sala, String> colSalaCodigo, colSalaNombre, colSalaCapacidad, colSalaEstado;

    // ---------- ADMINISTRACIÓN: FUNCIONES ----------
    @FXML private TextField txtCodigoFuncion, txtHoraFuncion, txtValorFuncion;
    @FXML private ComboBox<Pelicula> cmbPeliculaFuncion;
    @FXML private ComboBox<Sala> cmbSalaFuncion;
    @FXML private DatePicker fechaFuncion;
    @FXML private TableView<Funcion> tablaFuncionesAdmin;
    @FXML private TableColumn<Funcion, String> colAdminFuncionCodigo, colAdminFuncionPelicula, colAdminFuncionSala,
            colAdminFuncionFecha, colAdminFuncionValor, colAdminFuncionEstado;

    // ---------- ADMINISTRACIÓN: TARIFAS ----------
    @FXML private ComboBox<TipoCliente> cmbTipoTarifa;
    @FXML private TextField txtValorTarifa;
    @FXML private TableView<Tarifa> tablaTarifas;
    @FXML private TableColumn<Tarifa, String> colTarifaTipo, colTarifaValor;

    // ---------- REPORTES ----------
    @FXML private DatePicker fechaReporte;

    @FXML
    private void initialize() {
        configurarColumnas();
        configurarCombos();
        fechaReporte.setValue(LocalDate.now());
        fechaFuncion.setValue(LocalDate.now());

        // Al seleccionar una fila se cargan sus datos en el formulario.
        tablaPeliculasAdmin.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, p) -> cargarPeliculaEnFormulario(p));
        tablaSalas.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, s) -> cargarSalaEnFormulario(s));

        actualizarDatos();
        mostrarCartelera();
    }

    // =====================================================================
    // CONFIGURACIÓN
    // =====================================================================

    private void configurarColumnas() {
        // Cartelera
        colFuncionPelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colFuncionSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colFuncionFecha.setCellValueFactory(c -> texto(c.getValue().getFecha() + " · " + c.getValue().getHora()));
        colFuncionPrecio.setCellValueFactory(c -> texto(moneda.format(c.getValue().getValorBase())));
        colFuncionDisponibilidad.setCellValueFactory(c -> texto(c.getValue().contarAsientosDisponibles()
                + " libres de " + c.getValue().getSala().getListSalaAsientos().size()));

        // Películas (administración)
        colAdminCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colAdminNombre.setCellValueFactory(c -> texto(c.getValue().getNombre()));
        colAdminGenero.setCellValueFactory(c -> texto(c.getValue().getGeneroPelicula() + " · "
                + c.getValue().getClasificacionPelicula()));
        colAdminDuracion.setCellValueFactory(c -> texto(c.getValue().getDuracion() + " min"));
        colAdminEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoPelicula().toString()));

        // Salas
        colSalaCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colSalaNombre.setCellValueFactory(c -> texto(c.getValue().getNombre()));
        colSalaCapacidad.setCellValueFactory(c -> texto(c.getValue().getCapacidad() + " asientos"));
        colSalaEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoSala().toString()));

        // Funciones (administración)
        colAdminFuncionCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colAdminFuncionPelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colAdminFuncionSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colAdminFuncionFecha.setCellValueFactory(c -> texto(c.getValue().getFecha() + " · " + c.getValue().getHora()));
        colAdminFuncionValor.setCellValueFactory(c -> texto(moneda.format(c.getValue().getValorBase())));
        colAdminFuncionEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoFuncion().toString()));

        // Tarifas (Tarifa es un record: se usan tipoCliente() y valor())
        colTarifaTipo.setCellValueFactory(c -> texto(c.getValue().tipoCliente().toString()));
        colTarifaValor.setCellValueFactory(c -> texto(moneda.format(c.getValue().valor())));

        // Ventas
        colVentaCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colVentaFecha.setCellValueFactory(c -> texto(c.getValue().getFechaHoraVenta().format(formatoFechaHora)));
        colVentaCliente.setCellValueFactory(c -> texto(c.getValue().getCliente().getNombre()));
        colVentaFuncion.setCellValueFactory(c -> texto(c.getValue().getFuncion().getCodigo() + " · "
                + c.getValue().getFuncion().getPelicula().getNombre()));
        colVentaAsiento.setCellValueFactory(c -> texto(nombreAsiento(c.getValue().getAsiento())));
        colVentaValor.setCellValueFactory(c -> texto(moneda.format(c.getValue().getValorPagado())));
        colVentaEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoVenta().toString()));

        // Reportes de ocupación
        colReportePelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colReporteFecha.setCellValueFactory(c -> texto(c.getValue().getFecha() + " · " + c.getValue().getHora()));
        colReporteSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colReporteOcupados.setCellValueFactory(c -> texto("" + c.getValue().contarAsientosOcupados()));
        colReporteDisponibles.setCellValueFactory(c -> texto("" + c.getValue().contarAsientosDisponibles()));
        colReporteOcupacion.setCellValueFactory(c -> texto(String.format("%.1f%%", c.getValue().calcularPorcentajeOcupacion())));
    }

    private void configurarCombos() {
        cmbGeneroPelicula.setItems(FXCollections.observableArrayList(GeneroPelicula.values()));
        cmbClasificacionPelicula.setItems(FXCollections.observableArrayList(ClasificacionPelicula.values()));
        cmbEstadoPelicula.setItems(FXCollections.observableArrayList(EstadoPelicula.values()));
        cmbEstadoSala.setItems(FXCollections.observableArrayList(EstadoSala.values()));
        cmbTarifasVenta.setItems(FXCollections.observableArrayList(TipoCliente.values()));
        cmbTipoTarifa.setItems(FXCollections.observableArrayList(TipoCliente.values()));

        // Texto legible para los objetos del modelo en los ComboBox.
        mostrarCon(cmbPeliculasVenta, Pelicula::getNombre);
        mostrarCon(cmbPeliculaFuncion, Pelicula::getNombre);
        mostrarCon(cmbSalaFuncion, s -> s.getNombre() + " (" + s.getEstadoSala() + ")");
        mostrarCon(cmbClientesVenta, c -> c.getDocumentoIdentidad() + " · " + c.getNombre()
                + " (" + c.getTipoCliente() + ")");
        mostrarCon(cmbFuncionesVenta, f -> f.getFecha() + " " + f.getHora() + " · "
                + f.getSala().getNombre() + " · " + moneda.format(f.getValorBase()));
    }

    // =====================================================================
    // DATOS Y NAVEGACIÓN
    // =====================================================================

    private void actualizarDatos() {
        lblTotalClientes.setText("" + cine.obtenerClientes().size());

        // Cartelera: películas disponibles con nombre, género, duración y clasificación.
        contenedorPeliculas.getChildren().clear();
        for (Pelicula p : cine.obtenerPeliculas()) {
            if (p.getEstadoPelicula() != EstadoPelicula.DISPONIBLE) continue;
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
            contenedorPeliculas.getChildren().add(tarjeta);
        }

        tablaFuncionesCartelera.setItems(FXCollections.observableArrayList(funcionesProgramadas()));
        tablaPeliculasAdmin.setItems(FXCollections.observableArrayList(cine.obtenerPeliculas()));
        tablaSalas.setItems(FXCollections.observableArrayList(cine.obtenerSalas()));
        tablaFuncionesAdmin.setItems(FXCollections.observableArrayList(cine.obtenerFunciones()));
        tablaTarifas.setItems(FXCollections.observableArrayList(cine.obtenerTarifas()));
        tablaVentas.setItems(FXCollections.observableArrayList(cine.obtenerVentas()));

        recargar(cmbPeliculasVenta, peliculasDisponibles());
        recargar(cmbClientesVenta, cine.obtenerClientes());
        recargar(cmbPeliculaFuncion, peliculasDisponibles());
        recargar(cmbSalaFuncion, cine.obtenerSalas());

        actualizarReportes();
    }

    private void cambiarVista(String titulo, ScrollPane visible, Button boton) {
        for (ScrollPane vista : new ScrollPane[]{scrollCartelera, scrollTaquilla, scrollAdmin, scrollReportes}) {
            boolean mostrar = vista == visible;
            vista.setVisible(mostrar);
            vista.setManaged(mostrar);
        }
        lblTituloVista.setText(titulo);
        for (Button b : new Button[]{btnCartelera, btnTaquilla, btnAdmin, btnReportes}) {
            b.getStyleClass().remove("nav-active");
        }
        boton.getStyleClass().add("nav-active");
        actualizarDatos();
    }

    @FXML private void mostrarCartelera() { cambiarVista("Cartelera y Funciones", scrollCartelera, btnCartelera); }
    @FXML private void mostrarTaquilla() { cambiarVista("Taquilla · Venta y Cancelación", scrollTaquilla, btnTaquilla); }
    @FXML private void mostrarAdministracion() { cambiarVista("Gestión del Cine", scrollAdmin, btnAdmin); }
    @FXML private void mostrarReportes() { cambiarVista("Reportes e Indicadores", scrollReportes, btnReportes); }

    // =====================================================================
    // CLIENTES
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
                // Queda seleccionado para la venta en curso.
                cmbClientesVenta.setValue(nuevo);
                seleccionarClienteVenta();
                aviso("Cliente registrado correctamente.");
            } else {
                aviso("No se pudo registrar. Ya existe una persona con ese documento.");
            }
        });
    }

    // =====================================================================
    // TAQUILLA: REGISTRAR VENTA
    // =====================================================================

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
        dibujarAsientos();
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
        TipoCliente tipo = cmbTarifasVenta.getValue();
        if (funcion == null) {
            lblTotalVenta.setText(moneda.format(0));
            return;
        }
        Tarifa tarifa = cine.obtenerTarifa(tipo);
        lblTotalVenta.setText(moneda.format(tarifa != null ? tarifa.valor() : funcion.getValorBase()));
    }

    /** Dibuja el mapa de la sala de la función elegida y muestra su disponibilidad. */
    private void dibujarAsientos() {
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
                dibujarAsientos();
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
                + "\nFecha y hora: " + venta.getFechaHoraVenta().format(formatoFechaHora)
                + "\nCliente: " + cliente.getNombre()
                + "\nPelícula: " + funcion.getPelicula().getNombre()
                + "\nAsiento: " + nombreAsiento(venta.getAsiento())
                + "\nValor: " + moneda.format(venta.getValorPagado()));

        asientoSeleccionado = null;
        actualizarDatos();
        dibujarAsientos();
    }

    // =====================================================================
    // TAQUILLA: BUSCAR Y CANCELAR VENTA
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
            dibujarAsientos();
            aviso("Venta cancelada. El asiento " + nombreAsiento(v.getAsiento()) + " quedó libre.");
        } else {
            aviso("No se pudo cancelar: la venta ya estaba cancelada.");
        }
    }

    // =====================================================================
    // ADMINISTRACIÓN: PELÍCULAS
    // =====================================================================

    @FXML
    private void guardarPelicula() {
        try {
            Pelicula p = new Pelicula(Integer.parseInt(txtCodigoPelicula.getText().trim()),
                    Integer.parseInt(txtDuracionPelicula.getText().trim()), txtNombrePelicula.getText().trim(),
                    EstadoPelicula.DISPONIBLE, cmbGeneroPelicula.getValue(), cmbClasificacionPelicula.getValue());
            if (cine.registrarPelicula(DOCUMENTO_ADMIN, p)) {
                actualizarDatos();
                limpiarFormularioPelicula();
                aviso("Película registrada.");
            } else {
                aviso("No se pudo guardar. Ya existe una película con ese código.");
            }
        } catch (IllegalArgumentException e) {
            aviso("Revisa código, duración, nombre, género y clasificación.");
        }
    }

    @FXML
    private void modificarPelicula() {
        try {
            int codigo = Integer.parseInt(txtCodigoPelicula.getText().trim());
            int duracion = Integer.parseInt(txtDuracionPelicula.getText().trim());
            boolean ok = cine.modificarPelicula(DOCUMENTO_ADMIN, codigo, duracion, txtNombrePelicula.getText(),
                    cmbEstadoPelicula.getValue(), cmbGeneroPelicula.getValue(), cmbClasificacionPelicula.getValue());
            if (ok) {
                actualizarDatos();
                aviso("Película modificada.");
            } else {
                aviso("No se pudo modificar. Verifica que el código exista y todos los campos estén completos.");
            }
        } catch (NumberFormatException e) {
            aviso("El código y la duración deben ser numéricos.");
        }
    }

    @FXML
    private void deshabilitarPelicula() {
        Pelicula p = tablaPeliculasAdmin.getSelectionModel().getSelectedItem();
        if (p == null) {
            aviso("Selecciona una película de la tabla.");
            return;
        }
        if (cine.deshabilitarPelicula(DOCUMENTO_ADMIN, p.getCodigo())) {
            actualizarDatos();
            aviso("Película deshabilitada.");
        }
    }

    private void cargarPeliculaEnFormulario(Pelicula p) {
        if (p == null) return;
        txtCodigoPelicula.setText("" + p.getCodigo());
        txtNombrePelicula.setText(p.getNombre());
        txtDuracionPelicula.setText("" + p.getDuracion());
        cmbGeneroPelicula.setValue(p.getGeneroPelicula());
        cmbClasificacionPelicula.setValue(p.getClasificacionPelicula());
        cmbEstadoPelicula.setValue(p.getEstadoPelicula());
    }

    private void limpiarFormularioPelicula() {
        txtCodigoPelicula.clear();
        txtNombrePelicula.clear();
        txtDuracionPelicula.clear();
        cmbGeneroPelicula.setValue(null);
        cmbClasificacionPelicula.setValue(null);
        cmbEstadoPelicula.setValue(null);
    }

    // =====================================================================
    // ADMINISTRACIÓN: SALAS
    // =====================================================================

    @FXML
    private void registrarSala() {
        try {
            int codigo = Integer.parseInt(txtCodigoSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidadSala.getText().trim());
            EstadoSala estado = cmbEstadoSala.getValue() == null ? EstadoSala.DISPONIBLE : cmbEstadoSala.getValue();

            Sala sala = new Sala(codigo, capacidad, txtNombreSala.getText().trim(), estado, null);
            // Se generan filas de 8 asientos (A1..A8, B1..B8, ...).
            if (capacidad > 0) sala.generarAsientos((capacidad + 7) / 8, 8);

            if (cine.registrarSala(DOCUMENTO_ADMIN, sala)) {
                actualizarDatos();
                txtCodigoSala.clear();
                txtNombreSala.clear();
                txtCapacidadSala.clear();
                aviso("Sala registrada con " + sala.getListSalaAsientos().size() + " asientos.");
            } else {
                aviso("No se pudo registrar. Revisa que el código no exista y que nombre y capacidad sean válidos.");
            }
        } catch (NumberFormatException e) {
            aviso("El código y la capacidad deben ser numéricos.");
        }
    }

    @FXML
    private void modificarSala() {
        try {
            int codigo = Integer.parseInt(txtCodigoSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidadSala.getText().trim());
            if (cine.modificarSala(DOCUMENTO_ADMIN, codigo, txtNombreSala.getText(), capacidad, cmbEstadoSala.getValue())) {
                actualizarDatos();
                aviso("Sala modificada.");
            } else {
                aviso("No se pudo modificar. Verifica el código, los datos y que no se eliminen asientos ocupados.");
            }
        } catch (NumberFormatException e) {
            aviso("El código y la capacidad deben ser numéricos.");
        }
    }

    @FXML
    private void deshabilitarSala() {
        Sala s = tablaSalas.getSelectionModel().getSelectedItem();
        if (s == null) {
            aviso("Selecciona una sala de la tabla.");
            return;
        }
        if (cine.deshabilitarSala(DOCUMENTO_ADMIN, s.getCodigo())) {
            actualizarDatos();
            aviso("La sala quedó fuera de servicio.");
        }
    }

    private void cargarSalaEnFormulario(Sala s) {
        if (s == null) return;
        txtCodigoSala.setText("" + s.getCodigo());
        txtNombreSala.setText(s.getNombre());
        txtCapacidadSala.setText("" + s.getCapacidad());
        cmbEstadoSala.setValue(s.getEstadoSala());
    }

    // =====================================================================
    // ADMINISTRACIÓN: FUNCIONES
    // =====================================================================

    @FXML
    private void programarFuncion() {
        Pelicula pelicula = cmbPeliculaFuncion.getValue();
        Sala sala = cmbSalaFuncion.getValue();
        if (pelicula == null || sala == null || fechaFuncion.getValue() == null) {
            aviso("Selecciona película, sala y fecha.");
            return;
        }
        if (sala.getEstadoSala() == EstadoSala.FUERA_DE_SERVICIO) {
            aviso("No se pueden programar funciones en una sala fuera de servicio.");
            return;
        }
        try {
            int codigo = Integer.parseInt(txtCodigoFuncion.getText().trim());
            LocalTime hora = LocalTime.parse(txtHoraFuncion.getText().trim());
            double valor = Double.parseDouble(txtValorFuncion.getText().trim());

            Funcion funcion = new Funcion(codigo, fechaFuncion.getValue(), hora, valor,
                    EstadoFuncion.PROGRAMADA, null, sala, pelicula);

            if (cine.programarFuncion(DOCUMENTO_ADMIN, funcion)) {
                actualizarDatos();
                txtCodigoFuncion.clear();
                txtHoraFuncion.clear();
                txtValorFuncion.clear();
                aviso("Función programada.");
            } else {
                aviso("No se pudo programar. Revisa que el código no exista y que la sala no tenga otra función a esa hora.");
            }
        } catch (NumberFormatException e) {
            aviso("El código y el valor deben ser numéricos.");
        } catch (DateTimeParseException e) {
            aviso("La hora debe tener el formato HH:mm (por ejemplo 18:30).");
        }
    }

    // =====================================================================
    // ADMINISTRACIÓN: TARIFAS
    // =====================================================================

    @FXML
    private void guardarTarifa() {
        if (cmbTipoTarifa.getValue() == null) {
            aviso("Selecciona el tipo de entrada.");
            return;
        }
        try {
            double valor = Double.parseDouble(txtValorTarifa.getText().trim());
            if (cine.agregarOActualizarTarifa(DOCUMENTO_ADMIN, cmbTipoTarifa.getValue(), valor)) {
                actualizarDatos();
                txtValorTarifa.clear();
                aviso("Tarifa guardada.");
            } else {
                aviso("El valor de la tarifa debe ser positivo.");
            }
        } catch (NumberFormatException e) {
            aviso("El valor debe ser numérico.");
        }
    }

    // =====================================================================
    // REPORTES
    // =====================================================================

    @FXML
    private void actualizarReportes() {
        if (tablaReportes == null) return;
        LocalDate fecha = fechaReporte == null ? null : fechaReporte.getValue();
        // Con fecha: funciones programadas de ese día. Sin fecha: todas las funciones.
        List<Funcion> funciones = fecha == null ? cine.obtenerFunciones() : cine.obtenerFuncionesPorFecha(fecha);
        tablaReportes.setItems(FXCollections.observableArrayList(funciones));
        lblKpiEntradas.setText("" + cine.calcularTotalEntradasVendidas());
        lblKpiIngresos.setText(moneda.format(cine.calcularTotalIngresos()));
        Pelicula p = cine.buscarPeliculaMasVendida();
        lblKpiPelicula.setText(p == null ? "—" : p.getNombre());
    }

    @FXML
    private void limpiarFiltroReporte() {
        fechaReporte.setValue(null);
        actualizarReportes();
    }

    // =====================================================================
    // AUXILIARES
    // =====================================================================

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

    /** Reemplaza los elementos de un ComboBox conservando la selección actual. */
    private <T> void recargar(ComboBox<T> combo, List<T> elementos) {
        T actual = combo.getValue();
        combo.setItems(FXCollections.observableArrayList(elementos));
        if (elementos.contains(actual)) combo.setValue(actual);
    }

    private <T> void mostrarCon(ComboBox<T> combo, Function<T, String> texto) {
        combo.setConverter(new StringConverter<>() {
            @Override public String toString(T objeto) { return objeto == null ? "" : texto.apply(objeto); }
            @Override public T fromString(String s) { return null; }
        });
    }

    private SimpleStringProperty texto(String valor) {
        return new SimpleStringProperty(valor);
    }

    private String nombreAsiento(Asiento asiento) {
        return asiento == null ? "" : asiento.getFila() + asiento.getNumero();
    }

    private void aviso(String texto) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("CINEMAUQ");
        a.setHeaderText(null);
        a.setContentText(texto);
        a.showAndWait();
    }
}

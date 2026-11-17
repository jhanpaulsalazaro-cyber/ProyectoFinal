package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.proyectofinalfx.controller.CineController;
import uniquindio.edu.co.proyectofinalfx.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static uniquindio.edu.co.proyectofinalfx.viewController.UtilidadesVista.*;

/**
 * Vista del Administrador. Funcionalidades:
 * gestionar películas, salas, funciones y tarifas, y generar reportes.
 */
public class AdministradorViewController {

    // Las operaciones del administrador en Cine exigen su documento.
    private static final String DOCUMENTO_ADMIN = "100000001";

    private final CineController cine = ModelFactoryController.getInstance().getCineController();

    // ---------- MENÚ ----------
    @FXML private Label lblTituloVista, lblAdministrador;
    @FXML private Button btnPeliculas, btnSalas, btnFunciones, btnTarifas, btnReportes, btnCambiarRol;
    @FXML private ScrollPane scrollPeliculas, scrollSalas, scrollFunciones, scrollTarifas, scrollReportes;

    // ---------- PELÍCULAS ----------
    @FXML private TextField txtCodigoPelicula, txtNombrePelicula, txtDuracionPelicula, txtBuscarPelicula;
    @FXML private ComboBox<GeneroPelicula> cmbGeneroPelicula;
    @FXML private ComboBox<ClasificacionPelicula> cmbClasificacionPelicula;
    @FXML private ComboBox<EstadoPelicula> cmbEstadoPelicula;
    @FXML private TableView<Pelicula> tablaPeliculas;
    @FXML private TableColumn<Pelicula, String> colPeliculaCodigo, colPeliculaNombre, colPeliculaGenero,
            colPeliculaDuracion, colPeliculaClasificacion, colPeliculaEstado;

    // ---------- SALAS ----------
    @FXML private TextField txtCodigoSala, txtNombreSala, txtCapacidadSala, txtBuscarSala;
    @FXML private ComboBox<EstadoSala> cmbEstadoSala;
    @FXML private TableView<Sala> tablaSalas;
    @FXML private TableColumn<Sala, String> colSalaCodigo, colSalaNombre, colSalaCapacidad, colSalaAsientos, colSalaEstado;

    // ---------- FUNCIONES ----------
    @FXML private TextField txtCodigoFuncion, txtHoraFuncion, txtValorFuncion;
    @FXML private ComboBox<Pelicula> cmbPeliculaFuncion;
    @FXML private ComboBox<Sala> cmbSalaFuncion;
    @FXML private DatePicker fechaFuncion;
    @FXML private TableView<Funcion> tablaFunciones;
    @FXML private TableColumn<Funcion, String> colFuncionCodigo, colFuncionPelicula, colFuncionSala,
            colFuncionFecha, colFuncionValor, colFuncionEstado;

    // ---------- TARIFAS ----------
    @FXML private ComboBox<TipoCliente> cmbTipoTarifa;
    @FXML private TextField txtValorTarifa;
    @FXML private TableView<Tarifa> tablaTarifas;
    @FXML private TableColumn<Tarifa, String> colTarifaTipo, colTarifaValor;

    // ---------- REPORTES ----------
    @FXML private Label lblKpiEntradas, lblKpiIngresos, lblKpiPelicula;
    @FXML private DatePicker fechaReporte;
    @FXML private TableView<Funcion> tablaReportes;
    @FXML private TableColumn<Funcion, String> colReportePelicula, colReporteFecha, colReporteSala,
            colReporteDisponibles, colReporteOcupados, colReporteOcupacion;

    @FXML
    private void initialize() {
        configurarColumnas();

        cmbGeneroPelicula.setItems(FXCollections.observableArrayList(GeneroPelicula.values()));
        cmbClasificacionPelicula.setItems(FXCollections.observableArrayList(ClasificacionPelicula.values()));
        cmbEstadoPelicula.setItems(FXCollections.observableArrayList(EstadoPelicula.values()));
        cmbEstadoSala.setItems(FXCollections.observableArrayList(EstadoSala.values()));
        cmbTipoTarifa.setItems(FXCollections.observableArrayList(TipoCliente.values()));
        mostrarCon(cmbPeliculaFuncion, Pelicula::getNombre);
        mostrarCon(cmbSalaFuncion, s -> s.getNombre() + " (" + s.getEstadoSala() + ")");
        fechaFuncion.setValue(LocalDate.now());
        fechaReporte.setValue(LocalDate.now());

        Administrador admin = cine.buscarAdministrador(DOCUMENTO_ADMIN);
        if (admin != null) lblAdministrador.setText("●  " + admin.getNombre());

        // Al seleccionar una fila se cargan sus datos en el formulario.
        tablaPeliculas.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, p) -> cargarPeliculaEnFormulario(p));
        tablaSalas.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, s) -> cargarSalaEnFormulario(s));

        mostrarPeliculas();
    }

    private void configurarColumnas() {
        colPeliculaCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colPeliculaNombre.setCellValueFactory(c -> texto(c.getValue().getNombre()));
        colPeliculaGenero.setCellValueFactory(c -> texto(c.getValue().getGeneroPelicula().toString()));
        colPeliculaDuracion.setCellValueFactory(c -> texto(c.getValue().getDuracion() + " min"));
        colPeliculaClasificacion.setCellValueFactory(c -> texto(c.getValue().getClasificacionPelicula().toString()));
        colPeliculaEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoPelicula().toString()));

        colSalaCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colSalaNombre.setCellValueFactory(c -> texto(c.getValue().getNombre()));
        colSalaCapacidad.setCellValueFactory(c -> texto("" + c.getValue().getCapacidad()));
        colSalaAsientos.setCellValueFactory(c -> texto("" + c.getValue().getListSalaAsientos().size()));
        colSalaEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoSala().toString()));

        colFuncionCodigo.setCellValueFactory(c -> texto("" + c.getValue().getCodigo()));
        colFuncionPelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colFuncionSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colFuncionFecha.setCellValueFactory(c -> texto(c.getValue().getFecha() + " · " + c.getValue().getHora()));
        colFuncionValor.setCellValueFactory(c -> texto(MONEDA.format(c.getValue().getValorBase())));
        colFuncionEstado.setCellValueFactory(c -> texto(c.getValue().getEstadoFuncion().toString()));

        // Tarifa es un record: se usan tipoCliente() y valor().
        colTarifaTipo.setCellValueFactory(c -> texto(c.getValue().tipoCliente().toString()));
        colTarifaValor.setCellValueFactory(c -> texto(MONEDA.format(c.getValue().valor())));

        colReportePelicula.setCellValueFactory(c -> texto(c.getValue().getPelicula().getNombre()));
        colReporteFecha.setCellValueFactory(c -> texto(c.getValue().getFecha() + " · " + c.getValue().getHora()));
        colReporteSala.setCellValueFactory(c -> texto(c.getValue().getSala().getNombre()));
        colReporteDisponibles.setCellValueFactory(c -> texto("" + c.getValue().contarAsientosDisponibles()));
        colReporteOcupados.setCellValueFactory(c -> texto("" + c.getValue().contarAsientosOcupados()));
        colReporteOcupacion.setCellValueFactory(c -> texto(String.format("%.1f%%", c.getValue().calcularPorcentajeOcupacion())));
    }

    /** Recarga la información del modelo en todas las secciones. */
    private void actualizarDatos() {
        tablaPeliculas.setItems(FXCollections.observableArrayList(cine.obtenerPeliculas()));
        tablaSalas.setItems(FXCollections.observableArrayList(cine.obtenerSalas()));
        tablaFunciones.setItems(FXCollections.observableArrayList(cine.obtenerFunciones()));
        tablaTarifas.setItems(FXCollections.observableArrayList(cine.obtenerTarifas()));
        recargar(cmbPeliculaFuncion, cine.obtenerPeliculas());
        recargar(cmbSalaFuncion, cine.obtenerSalas());
        actualizarReportes();
    }

    // =====================================================================
    // NAVEGACIÓN
    // =====================================================================

    private void cambiarSeccion(String titulo, ScrollPane seccion, Button boton) {
        mostrarSeccion(seccion, boton,
                new ScrollPane[]{scrollPeliculas, scrollSalas, scrollFunciones, scrollTarifas, scrollReportes},
                new Button[]{btnPeliculas, btnSalas, btnFunciones, btnTarifas, btnReportes});
        lblTituloVista.setText(titulo);
        actualizarDatos();
    }

    @FXML private void mostrarPeliculas() { cambiarSeccion("Gestión de películas", scrollPeliculas, btnPeliculas); }
    @FXML private void mostrarSalas() { cambiarSeccion("Gestión de salas", scrollSalas, btnSalas); }
    @FXML private void mostrarFunciones() { cambiarSeccion("Gestión de funciones", scrollFunciones, btnFunciones); }
    @FXML private void mostrarTarifas() { cambiarSeccion("Gestión de tarifas", scrollTarifas, btnTarifas); }
    @FXML private void mostrarReportes() { cambiarSeccion("Reportes e indicadores", scrollReportes, btnReportes); }

    @FXML
    private void cambiarRol() {
        cambiarEscena(btnCambiarRol, "/InicioView.fxml", "CINEMAUQ");
    }

    // =====================================================================
    // GESTIONAR PELÍCULAS
    // =====================================================================

    @FXML
    private void registrarPelicula() {
        try {
            EstadoPelicula estado = cmbEstadoPelicula.getValue() == null
                    ? EstadoPelicula.DISPONIBLE : cmbEstadoPelicula.getValue();
            Pelicula p = new Pelicula(Integer.parseInt(txtCodigoPelicula.getText().trim()),
                    Integer.parseInt(txtDuracionPelicula.getText().trim()), txtNombrePelicula.getText().trim(),
                    estado, cmbGeneroPelicula.getValue(), cmbClasificacionPelicula.getValue());
            if (cine.registrarPelicula(DOCUMENTO_ADMIN, p)) {
                actualizarDatos();
                limpiarFormularioPelicula();
                aviso("Película registrada.");
            } else {
                aviso("No se pudo registrar. Ya existe una película con ese código.");
            }
        } catch (IllegalArgumentException e) {
            aviso("Revisa código, duración, nombre, género y clasificación.");
        }
    }

    @FXML
    private void consultarPelicula() {
        try {
            Pelicula p = cine.buscarPeliculaAdministrador(DOCUMENTO_ADMIN,
                    Integer.parseInt(txtBuscarPelicula.getText().trim()));
            tablaPeliculas.setItems(FXCollections.observableArrayList(p == null ? List.of() : List.of(p)));
            if (p == null) aviso("No existe una película con ese código.");
        } catch (NumberFormatException e) {
            aviso("Ingresa un código numérico.");
        }
    }

    @FXML
    private void verTodasPeliculas() {
        txtBuscarPelicula.clear();
        tablaPeliculas.setItems(FXCollections.observableArrayList(cine.obtenerPeliculas()));
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
        Pelicula p = tablaPeliculas.getSelectionModel().getSelectedItem();
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
    // GESTIONAR SALAS
    // =====================================================================

    @FXML
    private void registrarSala() {
        try {
            int codigo = Integer.parseInt(txtCodigoSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidadSala.getText().trim());
            EstadoSala estado = cmbEstadoSala.getValue() == null ? EstadoSala.DISPONIBLE : cmbEstadoSala.getValue();

            Sala sala = new Sala(codigo, capacidad, txtNombreSala.getText().trim(), estado, null);
            // Colección de asientos en filas de 8 (A1..A8, B1..B8, ...).
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
    private void consultarSala() {
        try {
            Sala s = cine.buscarSalaAdministrador(DOCUMENTO_ADMIN, Integer.parseInt(txtBuscarSala.getText().trim()));
            tablaSalas.setItems(FXCollections.observableArrayList(s == null ? List.of() : List.of(s)));
            if (s == null) aviso("No existe una sala con ese código.");
        } catch (NumberFormatException e) {
            aviso("Ingresa un código numérico.");
        }
    }

    @FXML
    private void verTodasSalas() {
        txtBuscarSala.clear();
        tablaSalas.setItems(FXCollections.observableArrayList(cine.obtenerSalas()));
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
    // GESTIONAR FUNCIONES
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
        if (pelicula.getEstadoPelicula() != EstadoPelicula.DISPONIBLE) {
            aviso("La película está deshabilitada.");
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
    // GESTIONAR TARIFAS
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
                aviso("El valor de la tarifa no puede ser negativo.");
            }
        } catch (NumberFormatException e) {
            aviso("El valor debe ser numérico.");
        }
    }

    // =====================================================================
    // GENERAR REPORTES
    // =====================================================================

    @FXML
    private void actualizarReportes() {
        LocalDate fecha = fechaReporte.getValue();
        // Con fecha: funciones programadas de ese día. Sin fecha: todas las funciones.
        List<Funcion> funciones = fecha == null ? cine.obtenerFunciones() : cine.obtenerFuncionesPorFecha(fecha);
        tablaReportes.setItems(FXCollections.observableArrayList(funciones));
        lblKpiEntradas.setText("" + cine.calcularTotalEntradasVendidas());
        lblKpiIngresos.setText(MONEDA.format(cine.calcularTotalIngresos()));
        Pelicula p = cine.buscarPeliculaMasVendida();
        lblKpiPelicula.setText(p == null ? "—" : p.getNombre());
    }

    @FXML
    private void limpiarFiltroReporte() {
        fechaReporte.setValue(null);
        actualizarReportes();
    }
}

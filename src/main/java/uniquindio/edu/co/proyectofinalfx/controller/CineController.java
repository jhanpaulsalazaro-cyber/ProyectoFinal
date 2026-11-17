
package uniquindio.edu.co.proyectofinalfx.controller;

import uniquindio.edu.co.proyectofinalfx.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class CineController {

    // ATRIBUTO
    private Cine cine;

    // CONSTRUCTOR
    public CineController() {

        cine = new Cine(
                "CINEMAUQ",
                "000000000",
                "Armenia, Quindío"
        );

        // ADMINISTRADOR INICIAL
        Administrador administrador = new Administrador(
                "100000001",
                "Carlos Ramírez",
                "ADM001",
                LocalDate.of(2025, 1, 15),
                2500000
        );

        cine.agregarPersona(administrador);

        // OPERADOR 1
        Operador operador1 = new Operador(
                "100000002",
                "Laura Gómez",
                "OPE001",
                LocalDate.of(2025, 2, 10),
                1800000
        );

        cine.agregarPersona(operador1);

        // OPERADOR 2
        Operador operador2 = new Operador(
                "100000003",
                "Andrés Torres",
                "OPE002",
                LocalDate.of(2025, 3, 5),
                1800000
        );

        cine.agregarPersona(operador2);

        cargarDatosIniciales(administrador.getDocumentoIdentidad());
    }

    // DATOS DE PRUEBA: se registran con los mismos métodos del administrador.
    private void cargarDatosIniciales(String documentoAdmin) {

        Pelicula pelicula1 = new Pelicula(1, 125, "Dune: Parte Dos",
                EstadoPelicula.DISPONIBLE, GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12);
        Pelicula pelicula2 = new Pelicula(2, 96, "Intensamente 2",
                EstadoPelicula.DISPONIBLE, GeneroPelicula.ANIMACION,
                ClasificacionPelicula.TODOS);

        cine.registrarPelicula(documentoAdmin, pelicula1);
        cine.registrarPelicula(documentoAdmin, pelicula2);

        Sala sala1 = new Sala(1, 24, "Sala 1", EstadoSala.DISPONIBLE, null);
        sala1.generarAsientos(3, 8);
        Sala sala2 = new Sala(2, 16, "Sala 2", EstadoSala.DISPONIBLE, null);
        sala2.generarAsientos(2, 8);

        cine.registrarSala(documentoAdmin, sala1);
        cine.registrarSala(documentoAdmin, sala2);

        LocalDate hoy = LocalDate.now();

        cine.programarFuncion(documentoAdmin, new Funcion(1, hoy, LocalTime.of(18, 0),
                15000, EstadoFuncion.PROGRAMADA, null, sala1, pelicula1));
        cine.programarFuncion(documentoAdmin, new Funcion(2, hoy, LocalTime.of(16, 30),
                12000, EstadoFuncion.PROGRAMADA, null, sala2, pelicula2));

        cine.agregarOActualizarTarifa(documentoAdmin, TipoCliente.GENERAL, 15000);
        cine.agregarOActualizarTarifa(documentoAdmin, TipoCliente.ESTUDIANTE, 11000);
        cine.agregarOActualizarTarifa(documentoAdmin, TipoCliente.NINO, 9000);
        cine.agregarOActualizarTarifa(documentoAdmin, TipoCliente.ADULTO_MAYOR, 9000);
    }

    // ------------------------------ PERSONAS ------------------------------

    public boolean registrarCliente(Cliente cliente) {
        return cine.agregarPersona(cliente);
    }

    public Cliente buscarCliente(String documento) {
        return cine.buscarClienteEnCine(documento);
    }

    public Persona buscarPersona(String documento) {
        return cine.buscarPersona(documento);
    }

    public Administrador buscarAdministrador(String documento) {
        return cine.buscarAdministradorEnCine(documento);
    }

    public Operador buscarOperador(String documento) {
        return cine.buscarOperadorEnCine(documento);
    }

    public List<Cliente> obtenerClientes() {

        List<Cliente> clientes = new ArrayList<>();

        for (Persona persona : cine.getListCinePersonas()) {

            if (persona instanceof Cliente) {
                clientes.add((Cliente) persona);
            }
        }

        return clientes;
    }

    public List<Operador> obtenerOperadores() {

        List<Operador> operadores = new ArrayList<>();

        for (Persona persona : cine.getListCinePersonas()) {

            if (persona instanceof Operador) {
                operadores.add((Operador) persona);
            }
        }

        return operadores;
    }

    // ------------------------------ PELÍCULAS ------------------------------

    public List<Pelicula> obtenerPeliculas() {
        return cine.getListCinePeliculas();
    }

    public Pelicula buscarPelicula(int codigo) {
        return cine.buscarPeliculaEnCine(codigo);
    }

    // ------------------------------ ADMINISTRACIÓN DE PELÍCULAS ------------------------------

    public boolean registrarPelicula(String documentoAdministrador, Pelicula pelicula) {
        return cine.registrarPelicula(documentoAdministrador, pelicula);
    }

    public Pelicula buscarPeliculaAdministrador(String documentoAdministrador, int codigo) {
        return cine.buscarPelicula(documentoAdministrador, codigo);
    }

    public boolean modificarPelicula(String documentoAdministrador, int codigo,
                                     int duracion, String nombre,
                                     EstadoPelicula estado, GeneroPelicula genero,
                                     ClasificacionPelicula clasificacion) {

        return cine.modificarPelicula(
                documentoAdministrador,
                codigo,
                duracion,
                nombre,
                estado,
                genero,
                clasificacion
        );
    }

    public boolean deshabilitarPelicula(String documentoAdministrador, int codigo) {
        return cine.deshabilitarPelicula(documentoAdministrador, codigo);
    }

    // ------------------------------ ADMINISTRACIÓN DE SALAS ------------------------------

    public boolean registrarSala(String documentoAdministrador, Sala sala) {
        return cine.registrarSala(documentoAdministrador, sala);
    }

    public Sala buscarSalaAdministrador(String documentoAdministrador, int codigo) {
        return cine.buscarSala(documentoAdministrador, codigo);
    }

    // BÚSQUEDA DIRECTA DE SALA POR CÓDIGO
    public Sala buscarSalaEnCine(int codigo) {
        return cine.buscarSalaEnCine(codigo);
    }

    public boolean modificarSala(String documentoAdministrador, int codigo,
                                 String nombre, int capacidad, EstadoSala estado) {

        return cine.modificarSala(
                documentoAdministrador,
                codigo,
                nombre,
                capacidad,
                estado
        );
    }

    public boolean deshabilitarSala(String documentoAdministrador, int codigo) {
        return cine.deshabilitarSala(documentoAdministrador, codigo);
    }

    // ------------------------------ ADMINISTRACIÓN DE FUNCIONES ------------------------------

    public boolean programarFuncion(String documentoAdministrador, Funcion funcion) {
        return cine.programarFuncion(documentoAdministrador, funcion);
    }

    public Funcion buscarFuncionAdministrador(String documentoAdministrador, int codigo) {
        return cine.buscarFuncion(documentoAdministrador, codigo);
    }

    // ------------------------------ ADMINISTRACIÓN DE TARIFAS ------------------------------

    public Tarifa buscarTarifaAdministrador(String documentoAdministrador, TipoCliente tipo) {
        return cine.buscarTarifa(documentoAdministrador, tipo);
    }

    public boolean agregarOActualizarTarifa(String documentoAdministrador,
                                            TipoCliente tipo, double valor) {

        return cine.agregarOActualizarTarifa(
                documentoAdministrador,
                tipo,
                valor
        );
    }

    // ------------------------------ VENTAS ------------------------------

    public Venta registrarVenta(int codigoVenta, Funcion funcion, Cliente cliente,
                                Asiento asiento, Tarifa tarifa) {

        return cine.registrarVenta(codigoVenta, funcion, cliente, asiento, tarifa);
    }

    public Venta buscarVenta(int codigo) {
        return cine.buscarVenta(codigo);
    }

    public boolean cancelarVenta(int codigo) {
        return cine.cancelarVenta(codigo);
    }

    public List<Venta> obtenerVentas() {
        return cine.obtenerVentas();
    }

    // Código consecutivo para una nueva venta (mayor código + 1).
    public int generarCodigoVenta() {

        int mayor = 0;

        for (Venta venta : cine.getListCineVentas()) {
            if (venta != null && venta.getCodigo() > mayor) {
                mayor = venta.getCodigo();
            }
        }

        return mayor + 1;
    }

    // ------------------------------ REPORTES ------------------------------

    public int calcularTotalEntradasVendidas() {
        return cine.calcularTotalEntradasVendidas();
    }

    public double calcularTotalIngresos() {
        return cine.calcularTotalIngresos();
    }

    public Pelicula buscarPeliculaMasVendida() {
        return cine.buscarPeliculaMasVendida();
    }

    // ------------------------------ FUNCIONES ------------------------------

    public List<Funcion> obtenerFuncionesPorFecha(LocalDate fecha) {
        return cine.consultarFuncionesPorFecha(fecha);
    }

    public Funcion buscarFuncion(int codigo) {
        return cine.buscarFuncionEnCine(codigo);
    }

    public List<Funcion> obtenerFunciones() {
        return cine.getListCineFunciones();
    }

    public List<Sala> obtenerSalas() {
        return cine.getListCineSalas();
    }

    public List<Tarifa> obtenerTarifas() {
        return cine.getListCineTarifas();
    }

    // ------------------------------ ASIENTOS ------------------------------

    public List<Asiento> obtenerAsientosDisponibles(int codigoFuncion) {

        List<Asiento> asientosDisponibles = new ArrayList<>();

        Funcion funcion = cine.buscarFuncionEnCine(codigoFuncion);

        if (funcion == null
                || funcion.getSala() == null
                || funcion.getSala().getListSalaAsientos() == null) {
            return asientosDisponibles;
        }

        for (Asiento asiento : funcion.getSala().getListSalaAsientos()) {

            if (funcion.asientoEstaDisponible(asiento)) {
                asientosDisponibles.add(asiento);
            }
        }

        return asientosDisponibles;
    }

    // ------------------------------ TARIFAS ------------------------------

    public Tarifa obtenerTarifa(TipoCliente tipoCliente) {

        if (tipoCliente == null) {
            return null;
        }

        for (Tarifa tarifa : cine.getListCineTarifas()) {

            if (tarifa != null && tarifa.tipoCliente() == tipoCliente) {
                return tarifa;
            }
        }

        return null;
    }

    // ------------------------------ OCUPACIÓN ------------------------------

    public int obtenerAsientosOcupados(int codigoFuncion) {

        Funcion funcion = cine.buscarFuncionEnCine(codigoFuncion);

        if (funcion == null) {
            return 0;
        }

        return funcion.contarAsientosOcupados();
    }

    public int obtenerAsientosDisponiblesPorFuncion(int codigoFuncion) {

        Funcion funcion = cine.buscarFuncionEnCine(codigoFuncion);

        if (funcion == null) {
            return 0;
        }

        return funcion.contarAsientosDisponibles();
    }

    public double obtenerPorcentajeOcupacion(int codigoFuncion) {

        Funcion funcion = cine.buscarFuncionEnCine(codigoFuncion);

        if (funcion == null) {
            return 0;
        }

        return funcion.calcularPorcentajeOcupacion();
    }
}

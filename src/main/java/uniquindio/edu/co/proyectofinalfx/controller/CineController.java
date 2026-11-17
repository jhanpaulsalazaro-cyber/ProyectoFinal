package uniquindio.edu.co.proyectofinalfx.controller;

import uniquindio.edu.co.proyectofinalfx.model.*;

import java.time.LocalDate;
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
    }

// ------------------------------ CLIENTES ------------------------------

    public boolean registrarCliente(Cliente cliente) {
        return cine.agregarPersona(cliente);
    }

    public Cliente buscarCliente(String documento) {
        return cine.buscarClienteEnCine(documento);
    }

    public List<Cliente> obtenerClientes() {

        List<Cliente> clientes = new java.util.ArrayList<>();

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

    public Venta registrarVenta(String documentoOperador, int codigoVenta,
                                Funcion funcion, Cliente cliente,
                                Asiento asiento, Tarifa tarifa) {

        return cine.registrarVenta(
                documentoOperador,
                codigoVenta,
                funcion,
                cliente,
                asiento,
                tarifa
        );
    }

    public Venta buscarVenta(String documentoOperador, int codigo) {
        return cine.buscarVenta(documentoOperador, codigo);
    }

    public boolean cancelarVenta(String documentoOperador, int codigo) {
        return cine.cancelarVenta(documentoOperador, codigo);
    }

    public List<Venta> obtenerVentas(String documentoOperador) {
        return cine.obtenerVentasOperador(documentoOperador);
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

    // ------------------------------ ASIENTOS ------------------------------

    public List<Asiento> obtenerAsientosDisponibles(int codigoFuncion) {

        List<Asiento> asientosDisponibles = new java.util.ArrayList<>();

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
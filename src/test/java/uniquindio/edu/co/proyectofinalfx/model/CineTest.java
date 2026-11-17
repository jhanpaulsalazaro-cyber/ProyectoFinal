
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {

    @Test
    void agregarYBuscarPersona() {
        Cine cine = new Cine("Cine Colombia", "123", "Armenia");

        Cliente cliente = new Cliente(
                "456", "Juan", TipoCliente.GENERAL
        );

        assertTrue(cine.agregarPersona(cliente));
        assertEquals(cliente, cine.buscarPersona("456"));
        assertEquals(cliente, cine.buscarClienteEnCine("456"));
    }

    @Test
    void rechazarPersonaDuplicada() {
        Cine cine = new Cine("Cine Colombia", "123", "Armenia");

        Cliente cliente1 = new Cliente(
                "456", "Juan", TipoCliente.GENERAL
        );

        Cliente cliente2 = new Cliente(
                "456", "Carlos", TipoCliente.ESTUDIANTE
        );

        assertTrue(cine.agregarPersona(cliente1));
        assertFalse(cine.agregarPersona(cliente2));
        assertEquals(1, cine.getListCinePersonas().size());
    }

    @Test
    void calcularEntradasVendidasEIngresos() {
        Cine cine = new Cine("Cine Colombia", "123", "Armenia");

        Venta venta1 = new Venta(
                1, LocalDateTime.now(), 20000,
                EstadoVenta.ACTIVA, null, null,
                null, null, null
        );

        Venta venta2 = new Venta(
                2, LocalDateTime.now(), 15000,
                EstadoVenta.ACTIVA, null, null,
                null, null, null
        );

        Venta venta3 = new Venta(
                3, LocalDateTime.now(), 10000,
                EstadoVenta.CANCELADA, null, null,
                null, null, null
        );

        cine.getListCineVentas().add(venta1);
        cine.getListCineVentas().add(venta2);
        cine.getListCineVentas().add(venta3);

        assertEquals(2, cine.calcularTotalEntradasVendidas());
        assertEquals(35000.0, cine.calcularTotalIngresos());
    }

    @Test
    void buscarPeliculaMasVendida() {
        Cine cine = new Cine("Cine Colombia", "123", "Armenia");

        Pelicula pelicula1 = new Pelicula(
                1, 120, "Avatar",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        Pelicula pelicula2 = new Pelicula(
                2, 100, "Titanic",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.DRAMA,
                ClasificacionPelicula.TODOS
        );

        cine.getListCinePeliculas().add(pelicula1);
        cine.getListCinePeliculas().add(pelicula2);

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Funcion funcion = new Funcion(
                1, LocalDate.now(), LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, pelicula1
        );

        Venta venta1 = new Venta(
                1, LocalDateTime.now(), 20000,
                EstadoVenta.ACTIVA, null, null,
                null, null, funcion
        );

        Venta venta2 = new Venta(
                2, LocalDateTime.now(), 20000,
                EstadoVenta.ACTIVA, null, null,
                null, null, funcion
        );

        cine.getListCineVentas().add(venta1);
        cine.getListCineVentas().add(venta2);

        assertEquals(pelicula1, cine.buscarPeliculaMasVendida());
    }

    @Test
    void consultarFuncionesProgramadasPorFecha() {
        Cine cine = new Cine("Cine Colombia", "123", "Armenia");

        LocalDate fecha = LocalDate.of(2026, 11, 20);

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Pelicula pelicula = new Pelicula(
                1, 120, "Avatar",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        Funcion funcion1 = new Funcion(
                1, fecha, LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, pelicula
        );

        Funcion funcion2 = new Funcion(
                2, fecha, LocalTime.of(21, 0),
                20000, EstadoFuncion.CANCELADA,
                null, sala, pelicula
        );

        Funcion funcion3 = new Funcion(
                3, fecha.plusDays(1), LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, pelicula
        );

        cine.getListCineFunciones().add(funcion1);
        cine.getListCineFunciones().add(funcion2);
        cine.getListCineFunciones().add(funcion3);

        List<Funcion> resultado =
                cine.consultarFuncionesPorFecha(fecha);

        assertEquals(1, resultado.size());
        assertEquals(funcion1, resultado.get(0));
    }

    // ------------------------------ CLIENTES Y VENTAS ------------------------------

    private Cine cine;
    private Cliente cliente;
    private Funcion funcion;
    private Asiento asiento1;

    /** Cine con un cliente, una sala de 4 asientos y una función programada. */
    private void prepararCineConFuncion() {
        cine = new Cine("CINEMAUQ", "123", "Armenia");

        cliente = new Cliente("1094", "Ana", TipoCliente.ESTUDIANTE);
        cine.agregarPersona(cliente);

        Sala sala = new Sala(1, 4, "Sala 1", EstadoSala.DISPONIBLE, null);
        sala.generarAsientos(1, 4);
        cine.getListCineSalas().add(sala);
        asiento1 = sala.buscarAsiento(1);

        Pelicula pelicula = new Pelicula(1, 120, "Avatar",
                EstadoPelicula.DISPONIBLE, GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12);

        funcion = new Funcion(1, LocalDate.now(), LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA, null, sala, pelicula);
        cine.getListCineFunciones().add(funcion);
    }

    @Test
    void registrarCliente() {
        prepararCineConFuncion();

        Cliente nuevo = new Cliente("2000", "Luis", TipoCliente.GENERAL);

        assertTrue(cine.agregarPersona(nuevo));
        assertTrue(cine.getListCinePersonas().contains(nuevo));
    }

    @Test
    void buscarClientePorDocumento() {
        prepararCineConFuncion();

        assertEquals(cliente, cine.buscarClienteEnCine("1094"));
        // El documento se compara sin espacios sobrantes.
        assertEquals(cliente, cine.buscarClienteEnCine(" 1094 "));
        assertNull(cine.buscarClienteEnCine("9999"));
    }

    @Test
    void evitarDocumentosDuplicados() {
        prepararCineConFuncion();

        Cliente repetido = new Cliente("1094", "Otro", TipoCliente.GENERAL);

        assertFalse(cine.agregarPersona(repetido));
        assertEquals(cliente, cine.buscarClienteEnCine("1094"));
    }

    @Test
    void registrarVentaSinDocumentoDeOperador() {
        prepararCineConFuncion();
        Tarifa tarifa = new Tarifa(TipoCliente.ESTUDIANTE, 12000);

        Venta venta = cine.registrarVenta(1, funcion, cliente, asiento1, tarifa);

        assertNotNull(venta);
        assertNull(venta.getOperador());
        assertEquals(EstadoVenta.ACTIVA, venta.getEstadoVenta());
        assertEquals(12000, venta.getValorPagado());
        assertNotNull(venta.getFechaHoraVenta());
        assertEquals(EstadoAsiento.OCUPADO, asiento1.getEstadoAsiento());
        assertTrue(cine.getListCineVentas().contains(venta));
        assertTrue(funcion.getListFuncionVentas().contains(venta));
    }

    @Test
    void rechazarVentaConClienteInexistente() {
        prepararCineConFuncion();

        Cliente noRegistrado = new Cliente("5555", "Pedro", TipoCliente.GENERAL);

        assertNull(cine.registrarVenta(1, funcion, noRegistrado, asiento1, null));
        assertTrue(cine.getListCineVentas().isEmpty());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento1.getEstadoAsiento());
    }

    @Test
    void rechazarVentaConAsientoNoDisponible() {
        prepararCineConFuncion();

        Cliente otroCliente = new Cliente("3000", "Sara", TipoCliente.GENERAL);
        cine.agregarPersona(otroCliente);

        assertNotNull(cine.registrarVenta(1, funcion, cliente, asiento1, null));
        assertNull(cine.registrarVenta(2, funcion, otroCliente, asiento1, null));
        assertEquals(1, cine.getListCineVentas().size());
    }

    @Test
    void buscarVentaPorCodigo() {
        prepararCineConFuncion();

        Venta venta = cine.registrarVenta(7, funcion, cliente, asiento1, null);

        assertEquals(venta, cine.buscarVenta(7));
        assertNull(cine.buscarVenta(8));
    }

    @Test
    void cancelarVentaCorrectamente() {
        prepararCineConFuncion();

        cine.registrarVenta(1, funcion, cliente, asiento1, null);

        assertTrue(cine.cancelarVenta(1));
        assertEquals(EstadoVenta.CANCELADA, cine.buscarVenta(1).getEstadoVenta());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento1.getEstadoAsiento());
        assertTrue(funcion.asientoEstaDisponible(asiento1));
        // No se puede cancelar dos veces.
        assertFalse(cine.cancelarVenta(1));
    }
}

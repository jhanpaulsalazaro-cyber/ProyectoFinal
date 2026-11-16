
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
}

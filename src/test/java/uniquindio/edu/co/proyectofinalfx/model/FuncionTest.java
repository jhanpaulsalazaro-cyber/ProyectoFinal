
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {

    @Test
    void verificarAsientoDisponible() {
        Sala sala = new Sala(1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null);

        Asiento asiento = new Asiento(1, 1, "A",
                EstadoAsiento.DISPONIBLE);

        sala.agregarAsiento(asiento);

        Funcion funcion = new Funcion(1, LocalDate.now(),
                LocalTime.now(), 20000, EstadoFuncion.PROGRAMADA,
                null, sala, null);

        assertTrue(funcion.asientoEstaDisponible(asiento));
    }

    @Test
    void verificarAsientoConVentaActivaNoDisponible() {
        Sala sala = new Sala(1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null);

        Asiento asiento = new Asiento(1, 1, "A",
                EstadoAsiento.DISPONIBLE);

        sala.agregarAsiento(asiento);

        Funcion funcion = new Funcion(1, LocalDate.now(),
                LocalTime.now(), 20000, EstadoFuncion.PROGRAMADA,
                null, sala, null);

        Venta venta = new Venta(1, LocalDateTime.now(),
                20000, EstadoVenta.ACTIVA, null, null,
                null, asiento, funcion);

        funcion.getListFuncionVentas().add(venta);

        assertFalse(funcion.asientoEstaDisponible(asiento));
    }

    @Test
    void calcularOcupacionDeLaFuncion() {
        Sala sala = new Sala(1, 2, "Sala 1",
                EstadoSala.DISPONIBLE, null);

        Asiento asiento1 = new Asiento(1, 1, "A",
                EstadoAsiento.DISPONIBLE);

        Asiento asiento2 = new Asiento(2, 2, "A",
                EstadoAsiento.DISPONIBLE);

        sala.agregarAsiento(asiento1);
        sala.agregarAsiento(asiento2);

        Funcion funcion = new Funcion(1, LocalDate.now(),
                LocalTime.now(), 20000, EstadoFuncion.PROGRAMADA,
                null, sala, null);

        Venta venta = new Venta(1, LocalDateTime.now(),
                20000, EstadoVenta.ACTIVA, null, null,
                null, asiento1, funcion);

        funcion.getListFuncionVentas().add(venta);

        assertEquals(1, funcion.contarAsientosOcupados());
        assertEquals(1, funcion.contarAsientosDisponibles());
        assertEquals(50.0, funcion.calcularPorcentajeOcupacion());
    }

    @Test
    void calcularOcupacionConSalaVacia() {
        Sala sala = new Sala(1, 0, "Sala vacía",
                EstadoSala.DISPONIBLE, null);

        Funcion funcion = new Funcion(1, LocalDate.now(),
                LocalTime.now(), 20000, EstadoFuncion.PROGRAMADA,
                null, sala, null);

        assertEquals(0.0, funcion.calcularPorcentajeOcupacion());
    }
}

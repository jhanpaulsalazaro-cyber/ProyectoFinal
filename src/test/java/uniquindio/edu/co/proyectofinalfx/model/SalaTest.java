
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {

    @Test
    public void agregarYBuscarAsiento() {
        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        assertTrue(sala.agregarAsiento(asiento));
        assertNotNull(sala.buscarAsiento(1));
    }

    @Test
    public void rechazarAsientoDuplicado() {
        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento1 = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        Asiento asiento2 = new Asiento(
                2, 1, "A", EstadoAsiento.DISPONIBLE
        );

        sala.agregarAsiento(asiento1);

        assertFalse(sala.agregarAsiento(asiento2));
    }

    @Test
    public void eliminarAsientoDisponible() {
        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        sala.agregarAsiento(asiento);

        assertTrue(sala.eliminarAsiento(1));
        assertEquals(4, sala.getCapacidad());
        assertNull(sala.buscarAsiento(1));
    }

    @Test
    public void impedirReducirCapacidadConAsientosOcupados() {
        Sala sala = new Sala(
                1, 3, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        sala.generarAsientos(2, 2);

        sala.getListSalaAsientos().get(2)
                .setEstadoAsiento(EstadoAsiento.OCUPADO);

        assertFalse(sala.ajustarCapacidad(2));
        assertEquals(3, sala.getCapacidad());
    }
}


package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsientoTest {

    @Test
    public void crearAsientoValido() {
        Asiento asiento = new Asiento(
                1,
                5,
                "A",
                EstadoAsiento.DISPONIBLE
        );

        assertNotNull(asiento);
    }

    @Test
    public void crearAsientoConCodigoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Asiento(
                    0,
                    5,
                    "A",
                    EstadoAsiento.DISPONIBLE
            );
        });
    }

    @Test
    public void crearAsientoConNumeroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Asiento(
                    1,
                    -2,
                    "A",
                    EstadoAsiento.DISPONIBLE
            );
        });
    }

    @Test
    public void crearAsientoConFilaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Asiento(
                    1,
                    5,
                    null,
                    EstadoAsiento.DISPONIBLE
            );
        });
    }

    @Test
    public void crearAsientoConFilaVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Asiento(
                    1,
                    5,
                    "   ",
                    EstadoAsiento.DISPONIBLE
            );
        });
    }

    @Test
    public void crearAsientoConEstadoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Asiento(
                    1,
                    5,
                    "A",
                    null
            );
        });
    }
}

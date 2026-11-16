
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {

    @Test
    void crearPeliculaValida() {
        Pelicula pelicula = new Pelicula(
                1, 120, "  Avatar  ",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        assertNotNull(pelicula);
        assertEquals("Avatar", pelicula.getNombre());
    }

    @Test
    void rechazarCodigoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pelicula(
                        0, 120, "Avatar",
                        EstadoPelicula.DISPONIBLE,
                        GeneroPelicula.ACCION,
                        ClasificacionPelicula.MAYORES_12
                ));
    }

    @Test
    void rechazarDuracionInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pelicula(
                        1, 0, "Avatar",
                        EstadoPelicula.DISPONIBLE,
                        GeneroPelicula.ACCION,
                        ClasificacionPelicula.MAYORES_12
                ));
    }

    @Test
    void rechazarNombreVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pelicula(
                        1, 120, "   ",
                        EstadoPelicula.DISPONIBLE,
                        GeneroPelicula.ACCION,
                        ClasificacionPelicula.MAYORES_12
                ));
    }

    @Test
    void rechazarEstadoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pelicula(
                        1, 120, "Avatar",
                        null,
                        GeneroPelicula.ACCION,
                        ClasificacionPelicula.MAYORES_12
                ));
    }

    @Test
    void rechazarGeneroNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pelicula(
                        1, 120, "Avatar",
                        EstadoPelicula.DISPONIBLE,
                        null,
                        ClasificacionPelicula.MAYORES_12
                ));
    }
}

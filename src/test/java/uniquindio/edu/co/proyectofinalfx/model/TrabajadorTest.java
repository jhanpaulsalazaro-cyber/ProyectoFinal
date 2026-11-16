
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorTest {

    // Clase concreta para probar la clase abstracta Trabajador
    static class TrabajadorPrueba extends Trabajador {

        public TrabajadorPrueba(String documentoIdentidad, String nombre,
                                String codigoEmpleado, LocalDate fechaContratacion,
                                double salario) {

            super(documentoIdentidad, nombre, codigoEmpleado,
                    fechaContratacion, salario);
        }
    }

    @Test
    void crearTrabajadorValido() {
        Trabajador trabajador = new TrabajadorPrueba(
                "123", "Juan", " EMP01 ",
                LocalDate.of(2025, 1, 10), 2000000
        );

        assertEquals("EMP01", trabajador.getCodigoEmpleado());
    }

    @Test
    void rechazarCodigoEmpleadoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrabajadorPrueba(
                        "123", "Juan", null,
                        LocalDate.of(2025, 1, 10), 2000000
                ));
    }

    @Test
    void rechazarFechaContratacionNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrabajadorPrueba(
                        "123", "Juan", "EMP01", null, 2000000
                ));
    }

    @Test
    void rechazarSalarioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrabajadorPrueba(
                        "123", "Juan", "EMP01",
                        LocalDate.of(2025, 1, 10), -1000
                ));
    }

    @Test
    void rechazarSalarioNoFinito() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrabajadorPrueba(
                        "123", "Juan", "EMP01",
                        LocalDate.of(2025, 1, 10), Double.NaN
                ));
    }
}

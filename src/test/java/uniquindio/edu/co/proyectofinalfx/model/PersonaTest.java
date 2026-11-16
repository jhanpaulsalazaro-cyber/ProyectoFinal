package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    @Test
    void crearPersonaValida() {
        Persona persona = new Persona(" 123 ", " Juan ");

        assertEquals("123", persona.getDocumentoIdentidad());
        assertEquals("Juan", persona.getNombre());
    }

    @Test
    void rechazarDocumentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Persona(null, "Juan"));
    }

    @Test
    void rechazarDocumentoVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Persona("   ", "Juan"));
    }

    @Test
    void rechazarNombreNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Persona("123", null));
    }

    @Test
    void rechazarNombreVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Persona("123", "   "));
    }
}
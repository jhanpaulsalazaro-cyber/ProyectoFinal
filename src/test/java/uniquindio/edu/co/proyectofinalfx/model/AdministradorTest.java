
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    @Test
    void registrarPeliculaCorrectamente() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Pelicula> peliculas = new ArrayList<>();

        Pelicula pelicula = new Pelicula(
                1, 120, "  Avatar  ",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        assertTrue(administrador.registrarPelicula(peliculas, pelicula));
        assertEquals(1, peliculas.size());
        assertEquals("Avatar", pelicula.getNombre());
        assertEquals(administrador, pelicula.getAdministrador());
    }

    @Test
    void rechazarRegistroDePeliculaDuplicada() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Pelicula> peliculas = new ArrayList<>();

        Pelicula pelicula1 = new Pelicula(
                1, 120, "Avatar",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        Pelicula pelicula2 = new Pelicula(
                1, 150, "Titanic",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.DRAMA,
                ClasificacionPelicula.TODOS
        );

        administrador.registrarPelicula(peliculas, pelicula1);

        assertFalse(administrador.registrarPelicula(peliculas, pelicula2));
        assertEquals(1, peliculas.size());
    }

    @Test
    void modificarPeliculaCorrectamente() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Pelicula> peliculas = new ArrayList<>();

        Pelicula pelicula = new Pelicula(
                1, 120, "Avatar",
                EstadoPelicula.DISPONIBLE,
                GeneroPelicula.ACCION,
                ClasificacionPelicula.MAYORES_12
        );

        peliculas.add(pelicula);

        boolean resultado = administrador.modificarPelicula(
                peliculas, 1, 150, "  Avatar  ",
                EstadoPelicula.NO_DISPONIBLE,
                GeneroPelicula.DRAMA,
                ClasificacionPelicula.MAYORES_15
        );

        assertTrue(resultado);
        assertEquals(150, pelicula.getDuracion());
        assertEquals("Avatar", pelicula.getNombre());
        assertEquals(EstadoPelicula.NO_DISPONIBLE,
                pelicula.getEstadoPelicula());
    }

    @Test
    void registrarSalaCorrectamente() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Sala> salas = new ArrayList<>();

        Sala sala = new Sala(
                1, 5, "  Sala 1  ",
                EstadoSala.DISPONIBLE, null
        );

        assertTrue(administrador.registrarSala(salas, sala));
        assertEquals(1, salas.size());
        assertEquals("Sala 1", sala.getNombre());
        assertEquals(administrador, sala.getAdministrador());
    }

    @Test
    void programarFuncionCorrectamente() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Funcion> funciones = new ArrayList<>();

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

        Funcion funcion = new Funcion(
                1, LocalDate.now(), LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, pelicula
        );

        assertTrue(administrador.programarFuncion(funciones, funcion));
        assertEquals(1, funciones.size());
        assertEquals(administrador, funcion.getAdministrador());
    }

    @Test
    void rechazarFuncionConConflictoDeHorario() {
        Administrador administrador = new Administrador(
                "123", "Carlos", "ADM01",
                LocalDate.now(), 3000000
        );

        List<Funcion> funciones = new ArrayList<>();

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

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

        Funcion funcionExistente = new Funcion(
                1, LocalDate.now(), LocalTime.of(18, 0),
                20000, EstadoFuncion.PROGRAMADA,
                administrador, sala, pelicula1
        );

        Funcion nuevaFuncion = new Funcion(
                2, LocalDate.now(), LocalTime.of(19, 0),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, pelicula2
        );

        funciones.add(funcionExistente);

        assertFalse(administrador.programarFuncion(funciones, nuevaFuncion));
        assertEquals(1, funciones.size());
    }
}


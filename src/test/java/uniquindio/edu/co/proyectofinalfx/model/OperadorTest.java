
package uniquindio.edu.co.proyectofinalfx.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperadorTest {

    @Test
    public void registrarVentaCorrectamente() {
        Operador operador = new Operador(
                "123", "Carlos", "EMP01",
                LocalDate.now(), 2000000
        );

        Cliente cliente = new Cliente(
                "456", "Juan", TipoCliente.GENERAL
        );

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        sala.agregarAsiento(asiento);

        Funcion funcion = new Funcion(
                1, LocalDate.now(), LocalTime.now(),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, null
        );

        List<Venta> ventas = new ArrayList<>();

        Venta venta = operador.registrarVenta(
                ventas, 1, funcion, cliente, asiento, null
        );

        assertNotNull(venta);
        assertEquals(EstadoVenta.ACTIVA, venta.getEstadoVenta());
        assertEquals(20000, venta.getValorPagado());
        assertEquals(EstadoAsiento.OCUPADO, asiento.getEstadoAsiento());
        assertEquals(1, ventas.size());
        assertEquals(1, funcion.getListFuncionVentas().size());
        assertEquals(1, operador.getListOperadoreVentas().size());
    }

    @Test
    public void rechazarVentaConAsientoNoPertenecienteALaSala() {
        Operador operador = new Operador(
                "123", "Carlos", "EMP01",
                LocalDate.now(), 2000000
        );

        Cliente cliente = new Cliente(
                "456", "Juan", TipoCliente.GENERAL
        );

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        Funcion funcion = new Funcion(
                1, LocalDate.now(), LocalTime.now(),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, null
        );

        List<Venta> ventas = new ArrayList<>();

        Venta venta = operador.registrarVenta(
                ventas, 1, funcion, cliente, asiento, null
        );

        assertNull(venta);
        assertEquals(0, ventas.size());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }

    @Test
    public void buscarVentaExistente() {
        Operador operador = new Operador(
                "123", "Carlos", "EMP01",
                LocalDate.now(), 2000000
        );

        Venta venta = new Venta(
                1, LocalDateTime.now(), 20000,
                EstadoVenta.ACTIVA, null, operador,
                null, null, null
        );

        List<Venta> ventas = new ArrayList<>();
        ventas.add(venta);

        assertNotNull(operador.buscarVenta(ventas, 1));
        assertNull(operador.buscarVenta(ventas, 2));
    }

    @Test
    public void cancelarVentaYLiberarAsiento() {
        Operador operador = new Operador(
                "123", "Carlos", "EMP01",
                LocalDate.now(), 2000000
        );

        Cliente cliente = new Cliente(
                "456", "Juan", TipoCliente.GENERAL
        );

        Sala sala = new Sala(
                1, 5, "Sala 1",
                EstadoSala.DISPONIBLE, null
        );

        Asiento asiento = new Asiento(
                1, 1, "A", EstadoAsiento.DISPONIBLE
        );

        sala.agregarAsiento(asiento);

        Funcion funcion = new Funcion(
                1, LocalDate.now(), LocalTime.now(),
                20000, EstadoFuncion.PROGRAMADA,
                null, sala, null
        );

        List<Venta> ventas = new ArrayList<>();

        Venta venta = operador.registrarVenta(
                ventas, 1, funcion, cliente, asiento, null
        );

        assertNotNull(venta);

        assertTrue(operador.cancelarVenta(ventas, 1));

        assertEquals(EstadoVenta.CANCELADA, venta.getEstadoVenta());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }
}

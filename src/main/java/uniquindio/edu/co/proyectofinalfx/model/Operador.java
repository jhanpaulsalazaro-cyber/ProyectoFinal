package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operador extends Trabajador {
    // RELACIONES
    private List<Venta> listOperadoresVentas;

    /**
     * Método constructor de la clase Operador
     *
     * @param documentoIdentidad del operador
     * @param nombre             del operador
     * @param codigoEmpleado     del operador
     * @param fechaContratacion  del opeador
     * @param salario            del operador
     */
    public Operador(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);

        this.listOperadoresVentas= new ArrayList<>();
    }

    /**
     * Registrar venta
     */
    public Venta registrarVenta(List<Venta> ventasGenerales, int codigoVenta,
                                Funcion funcion, Cliente cliente, Asiento asiento, Tarifa tarifa) {
        if (ventasGenerales == null || codigoVenta <= 0 || funcion == null || cliente == null || asiento == null) return null;
        if (funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) return null;
        if (!funcion.asientoEstaDisponible(asiento)) return null;

        // Evita registrar dos ventas con el mismo código.
        if (buscarVenta(ventasGenerales, codigoVenta) != null) return null;

        double precio = funcion.getValorBase();
        if (tarifa != null) precio = tarifa.valor();

        Venta venta = new Venta(codigoVenta, LocalDateTime.now(), precio,
                EstadoVenta.ACTIVA, cliente, this, tarifa, asiento, funcion);

        ventasGenerales.add(venta);
        funcion.getListFuncionVentas().add(venta);
        listOperadoresVentas.add(venta);
        asiento.setEstadoAsiento(EstadoAsiento.OCUPADO);

        return venta;
    }

    /**
     * Buscar venta
     */
    public Venta buscarVenta(List<Venta> ventas, int codigo) {
        if (ventas == null) return null;
        for (Venta venta : ventas) {
            if (venta.getCodigo() == codigo) return venta;
        }
        return null;
    }

    /** Cancela una venta existente y libera el asiento de esa función. */
    public boolean cancelarVenta(List<Venta> ventasGenerales, int codigo) {
        Venta venta = buscarVenta(ventasGenerales, codigo);
        if (venta == null || venta.getEstadoVenta() == EstadoVenta.CANCELADA) return false;

        venta.setEstadoVenta(EstadoVenta.CANCELADA);

        // La disponibilidad se revisa únicamente dentro de la función de la venta.
        boolean asientoTieneOtraVentaActiva = false;
        for (Venta otraVenta : venta.getFuncion().getListFuncionVentas()) {
            if (otraVenta != venta
                    && otraVenta.getAsiento() == venta.getAsiento()
                    && otraVenta.getEstadoVenta() == EstadoVenta.ACTIVA) {
                asientoTieneOtraVentaActiva = true;
            }
        }

        if (!asientoTieneOtraVentaActiva) {
            venta.getAsiento().setEstadoAsiento(EstadoAsiento.DISPONIBLE);
        }
        return true;
    }

    public List<Venta> getListOperadoreVentas() {
        return listOperadoresVentas;
    }

    public void setListOperadoreVentas(List<Venta> listOperadoreVentas) {
        this.listOperadoresVentas = listOperadoreVentas;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "listOperadoreVentas=" + listOperadoresVentas +
                '}';
    }
}
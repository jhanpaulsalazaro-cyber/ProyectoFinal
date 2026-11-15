
package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operador extends Trabajador {

    // RELACIONES
    private List<Venta> listOperadoresVentas;

    /**
     * Constructor de la clase Operador.
     */
    public Operador(String documentoIdentidad, String nombre,
                    String codigoEmpleado, LocalDate fechaContratacion,
                    double salario) {

        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);

        this.listOperadoresVentas = new ArrayList<>();
    }

    /**
     * Registra una venta de entrada.
     */
    public Venta registrarVenta(List<Venta> ventasGenerales, int codigoVenta,
                                Funcion funcion, Cliente cliente,
                                Asiento asiento, Tarifa tarifa) {

        // Validar los datos obligatorios.
        if (ventasGenerales == null || codigoVenta <= 0
                || funcion == null || cliente == null || asiento == null) {
            return null;
        }

        // La función debe estar programada.
        if (funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
            return null;
        }

        // El asiento debe pertenecer a la sala y estar disponible
        // para esta función.
        if (!funcion.asientoEstaDisponible(asiento)) {
            return null;
        }

        // Evitar códigos de venta repetidos.
        if (buscarVenta(ventasGenerales, codigoVenta) != null) {
            return null;
        }

        // Determinar el precio de la entrada.
        double precio;

        if (tarifa == null) {

            // Si no se proporciona tarifa, se utiliza el valor base.
            precio = funcion.getValorBase();

        } else {

            // La tarifa debe corresponder al tipo de cliente.
            if (cliente.getTipoCliente() == null
                    || tarifa.tipoCliente() != cliente.getTipoCliente()) {
                return null;
            }

            precio = tarifa.valor();
        }

        // El precio debe ser válido.
        if (!Double.isFinite(precio) || precio < 0) {
            return null;
        }

        // Crear la venta.
        Venta venta = new Venta(
                codigoVenta,
                LocalDateTime.now(),
                precio,
                EstadoVenta.ACTIVA,
                cliente,
                this,
                tarifa,
                asiento,
                funcion
        );

        // Registrar la venta en las listas correspondientes.
        ventasGenerales.add(venta);
        funcion.getListFuncionVentas().add(venta);
        listOperadoresVentas.add(venta);

        // Marcar el asiento como ocupado.
        asiento.setEstadoAsiento(EstadoAsiento.OCUPADO);

        return venta;
    }

    /**
     * Busca una venta por su código.
     */
    public Venta buscarVenta(List<Venta> ventas, int codigo) {

        if (ventas == null || codigo <= 0) {
            return null;
        }

        for (Venta venta : ventas) {

            if (venta != null && venta.getCodigo() == codigo) {
                return venta;
            }
        }

        return null;
    }

    /**
     * Cancela una venta existente y libera el asiento
     * cuando no tiene otra venta activa en esa función.
     */
    public boolean cancelarVenta(List<Venta> ventasGenerales, int codigo) {

        Venta venta = buscarVenta(ventasGenerales, codigo);

        // Comprobar que la venta exista y esté activa.
        if (venta == null || venta.getEstadoVenta() != EstadoVenta.ACTIVA) {
            return false;
        }

        // Comprobar que la venta tenga función y asiento asociados.
        if (venta.getFuncion() == null || venta.getAsiento() == null) {
            return false;
        }

        List<Venta> ventasFuncion = venta.getFuncion().getListFuncionVentas();

        if (ventasFuncion == null) {
            return false;
        }

        // Cancelar la venta.
        venta.setEstadoVenta(EstadoVenta.CANCELADA);

        // Comprobar si existe otra venta activa para el mismo asiento
        // dentro de la misma función.
        boolean asientoTieneOtraVentaActiva = false;

        for (Venta otraVenta : ventasFuncion) {

            if (otraVenta != null
                    && otraVenta != venta
                    && otraVenta.getAsiento() == venta.getAsiento()
                    && otraVenta.getEstadoVenta() == EstadoVenta.ACTIVA) {

                asientoTieneOtraVentaActiva = true;
                break;
            }
        }

        // Liberar el asiento si no tiene otra venta activa.
        if (!asientoTieneOtraVentaActiva) {
            venta.getAsiento().setEstadoAsiento(EstadoAsiento.DISPONIBLE);
        }

        return true;
    }

    // GETTERS Y SETTERS

    public List<Venta> getListOperadoreVentas() {
        return listOperadoresVentas;
    }

    public void setListOperadoreVentas(List<Venta> listOperadoreVentas) {

        if (listOperadoreVentas == null) {
            this.listOperadoresVentas = new ArrayList<>();
        } else {
            this.listOperadoresVentas = new ArrayList<>(listOperadoreVentas);
        }
    }

    @Override
    public String toString() {

        return "Operador{" +
                "listOperadoresVentas=" + listOperadoresVentas +
                '}';
    }
}

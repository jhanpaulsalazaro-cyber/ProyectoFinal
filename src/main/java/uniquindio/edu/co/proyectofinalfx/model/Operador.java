package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
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





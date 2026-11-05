package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Operador extends Trabajador {
    private List<Venta> listVentaOperador;

    public Operador(List<Venta> listVentaOperador) {
        this.listVentaOperador = listVentaOperador;
    }

    public List<Venta> getListVentaOperador() {
        return listVentaOperador;
    }

    public void setListVentaOperador(List<Venta> listVentaOperador) {
        this.listVentaOperador = listVentaOperador;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "listVentaOperador=" + listVentaOperador +
                '}';
    }
}


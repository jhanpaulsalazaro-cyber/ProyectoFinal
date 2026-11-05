package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Cliente extends Persona {
    // relaciones
    private TipoCliente tipoCliente;
    private List<Venta> listVentaCliente;

    public Cliente(TipoCliente tipoCliente, List<Venta> listVentaCliente) {
        this.tipoCliente = tipoCliente;
        this.listVentaCliente = listVentaCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Venta> getListVentaCliente() {
        return listVentaCliente;
    }

    public void setListVentaCliente(List<Venta> listVentaCliente) {
        this.listVentaCliente = listVentaCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "tipoCliente=" + tipoCliente +
                ", listVentaCliente=" + listVentaCliente +
                '}';
    }
}

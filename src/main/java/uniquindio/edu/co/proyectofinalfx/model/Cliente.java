package uniquindio.edu.co.proyectofinalfx.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    // RELACIONES
    private TipoCliente tipoCliente;

    /**
     * Método constructor de la clase Cliente
     * @param documentoIdentidad del cliente
     * @param nombre del cliente
     * @param tipoCliente del cliente
     */
    public Cliente(String documentoIdentidad, String nombre, TipoCliente tipoCliente) {
        super(documentoIdentidad, nombre);
        this.tipoCliente = tipoCliente;

    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "tipoCliente=" + tipoCliente + "\n" +
                '}';
    }
}

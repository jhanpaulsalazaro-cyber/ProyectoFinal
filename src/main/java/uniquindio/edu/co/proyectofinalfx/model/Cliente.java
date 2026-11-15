
package uniquindio.edu.co.proyectofinalfx.model;

public class Cliente extends Persona {

    // RELACIONES
    private TipoCliente tipoCliente;

    // CONSTRUCTOR
    public Cliente(String documentoIdentidad, String nombre,
                   TipoCliente tipoCliente) {

        super(documentoIdentidad, nombre);

        if (tipoCliente == null) {
            throw new IllegalArgumentException("El tipo de cliente es obligatorio.");
        }

        this.tipoCliente = tipoCliente;
    }

    // GETTERS Y SETTERS

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        if (tipoCliente != null) {
            this.tipoCliente = tipoCliente;
        }
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Cliente{" +
                "documentoIdentidad='" + getDocumentoIdentidad() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", tipoCliente=" + tipoCliente +
                '}';
    }
}

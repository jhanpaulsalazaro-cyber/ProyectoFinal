
package uniquindio.edu.co.proyectofinalfx.model;

public class Asiento {

    // ATRIBUTOS
    private int codigo, numero;
    private String fila;

    // RELACIONES
    private EstadoAsiento estadoAsiento;

    /**
     * Constructor de la clase Asiento.
     */
    public Asiento(int codigo, int numero, String fila, EstadoAsiento estadoAsiento) {

        if (codigo <= 0 || numero <= 0) {
            throw new IllegalArgumentException("El código y el número del asiento deben ser positivos.");
        }

        if (fila == null || fila.isBlank()) {
            throw new IllegalArgumentException("La fila del asiento es obligatoria.");
        }

        if (estadoAsiento == null) {
            throw new IllegalArgumentException("El estado del asiento es obligatorio.");
        }

        this.codigo = codigo;
        this.numero = numero;
        this.fila = fila.trim();
        this.estadoAsiento = estadoAsiento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        }
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        if (fila != null && !fila.isBlank()) {
            this.fila = fila.trim();
        }
    }

    public EstadoAsiento getEstadoAsiento() {
        return estadoAsiento;
    }

    public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
        if (estadoAsiento != null) {
            this.estadoAsiento = estadoAsiento;
        }
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "codigo=" + codigo +
                ", numero=" + numero +
                ", fila='" + fila + '\'' +
                ", estadoAsiento=" + estadoAsiento +
                '}';
    }
}

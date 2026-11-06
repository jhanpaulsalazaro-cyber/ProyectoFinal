package uniquindio.edu.co.proyectofinalfx.model;

import java.util.ArrayList;
import java.util.List;

public class Asiento {
    // ATRIBUTOS
    private int codigo, numero;
    private String fila;

    // RELACIONES
    private EstadoAsiento estadoAsiento;

    /**
     * Método constructor de la clase Asiento
     * @param codigo        del asiento
     * @param numero        del asiento
     * @param fila          del asiento
     * @param estadoAsiento del asiento
     */
    public Asiento(int codigo, int numero, String fila, EstadoAsiento estadoAsiento) {
        this.codigo = codigo;
        this.numero = numero;
        this.fila = fila;
        this.estadoAsiento = estadoAsiento;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public EstadoAsiento getEstadoAsiento() {
        return estadoAsiento;
    }

    public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "codigo=" + codigo + "\n" +
                ", numero=" + numero + "\n" +
                ", fila='" + fila + '\'' + "\n" +
                ", estadoAsiento=" + estadoAsiento + "\n" +
                '}';
    }
}

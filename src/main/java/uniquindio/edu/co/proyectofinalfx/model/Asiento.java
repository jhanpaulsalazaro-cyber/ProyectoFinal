package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Asiento {

    // atributos
    private int codigo,numero;
    private String fila;

    // relaciones
    private EstadoAsiento estadoAsiento;
     private List<Venta> listVentaAsiento;

    public Asiento(int codigo, int numero, String fila, EstadoAsiento estadoAsiento, List<Venta> listVentaAsiento) {
        this.codigo = codigo;
        this.numero = numero;
        this.fila = fila;
        this.estadoAsiento = estadoAsiento;
        this.listVentaAsiento = listVentaAsiento;
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

    public List<Venta> getListVentaAsiento() {
        return listVentaAsiento;
    }

    public void setListVentaAsiento(List<Venta> listVentaAsiento) {
        this.listVentaAsiento = listVentaAsiento;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "codigo=" + codigo +
                ", numero=" + numero +
                ", fila='" + fila + '\'' +
                ", estadoAsiento=" + estadoAsiento +
                ", listVentaAsiento=" + listVentaAsiento +
                '}';
    }
}


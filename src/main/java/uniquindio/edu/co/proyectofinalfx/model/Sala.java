package uniquindio.edu.co.proyectofinalfx.model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    // ATRIBUTOS
    private int codigo, capacidad;
    private String nombre;

    // RELACIONES
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsientos;
    private Administrador administrador;

    /**
     * Método constructor de la clase Asiento
     * @param codigo del aiento
     * @param capacidad del asiento
     * @param nombre del asiento
     * @param estadoSala de la sala
     * @param administrador de la sala o funcion
     */
    public Sala(int codigo, int capacidad, String nombre, EstadoSala estadoSala, Administrador administrador) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.estadoSala = estadoSala;
        this.administrador = administrador;

        this.listSalaAsientos= new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }

    public List<Asiento> getListSalaAsientos() {
        return listSalaAsientos;
    }

    public void setListSalaAsientos(List<Asiento> listSalaAsientos) {
        this.listSalaAsientos = listSalaAsientos;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo=" + codigo + "\n" +
                ", capacidad=" + capacidad + "\n" +
                ", nombre='" + nombre + "\n" +
                ", estadoSala=" + estadoSala + "\n" +
                ", listSalaAsientos=" + listSalaAsientos + "\n" +
                ", administrador=" + administrador + "\n" +
                '}';
    }
}


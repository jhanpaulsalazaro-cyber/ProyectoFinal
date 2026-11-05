package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Sala {
    // atributos
    private int codigo,capacidad;
    private String nombre;

    // relaciones
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsiento;
    private Administrador theAdministradorSala;

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

    public List<Asiento> getListSalaAsiento() {
        return listSalaAsiento;
    }

    public void setListSalaAsiento(List<Asiento> listSalaAsiento) {
        this.listSalaAsiento = listSalaAsiento;
    }

    public Administrador getTheAdministradorSala() {
        return theAdministradorSala;
    }

    public void setTheAdministradorSala(Administrador theAdministradorSala) {
        this.theAdministradorSala = theAdministradorSala;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo=" + codigo +
                ", capacidad=" + capacidad +
                ", nombre='" + nombre + '\'' +
                ", estadoSala=" + estadoSala +
                ", listSalaAsiento=" + listSalaAsiento +
                ", theAdministradorSala=" + theAdministradorSala +
                '}';
    }

    public Sala(int codigo, int capacidad, String nombre, EstadoSala estadoSala, List<Asiento> listSalaAsiento, Administrador theAdministradorSala) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.estadoSala = estadoSala;
        this.listSalaAsiento = listSalaAsiento;
        this.theAdministradorSala = theAdministradorSala;


    }
}


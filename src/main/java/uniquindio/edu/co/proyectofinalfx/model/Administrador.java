package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Trabajador {
    //RELACIONES
    private List<Tarifa> listTarifas;
    private List<Pelicula> listAdministradorPeliculas;

    /**
     * Método constructor de la clase Administrador
     * @param documentoIdentidad del administrador
     * @param nombre             del administrador
     * @param codigoEmpleado     del administrador
     * @param fechaContratacion  del administrador
     * @param salario            del administrador
     */
    public Administrador(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
        this.listAdministradorPeliculas = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
    }

    public List<Pelicula> getListAdministradorPeliculas() {
        return listAdministradorPeliculas;
    }

    public void setListAdministradorPeliculas(List<Pelicula> listAdministradorPeliculas) {
        this.listAdministradorPeliculas = listAdministradorPeliculas;
    }
}


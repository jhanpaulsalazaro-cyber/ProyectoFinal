
package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {

    // ATRIBUTOS
    private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;

    // RELACIONES
    private EstadoFuncion estadoFuncion;
    private Administrador administrador;
    private Sala sala;
    private Pelicula pelicula;
    private List<Venta> listFuncionVentas;

    /**
     * Constructor de la clase Funcion.
     */
    public Funcion(int codigo, LocalDate fecha, LocalTime hora,
                   double valorBase, EstadoFuncion estadoFuncion,
                   Administrador administrador, Sala sala,
                   Pelicula pelicula) {

        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.administrador = administrador;
        this.sala = sala;
        this.pelicula = pelicula;

        this.listFuncionVentas = new ArrayList<>();
    }

    /**
     * Verifica si un asiento está disponible para esta función.
     */
    public boolean asientoEstaDisponible(Asiento asiento) {

        if (asiento == null || sala == null ||
                sala.getListSalaAsientos() == null) {
            return false;
        }

        if (!sala.getListSalaAsientos().contains(asiento)) {
            return false;
        }

        for (Venta venta : listFuncionVentas) {

            if (venta != null &&
                    venta.getAsiento() == asiento &&
                    venta.getEstadoVenta() == EstadoVenta.ACTIVA) {
                return false;
            }
        }

        return true;
    }

    /**
     * Cuenta los asientos ocupados en esta función.
     */
    public int contarAsientosOcupados() {

        int total = 0;

        for (Venta venta : listFuncionVentas) {

            if (venta != null &&
                    venta.getEstadoVenta() == EstadoVenta.ACTIVA) {
                total++;
            }
        }

        return total;
    }

    /**
     * Cuenta los asientos disponibles en esta función.
     */
    public int contarAsientosDisponibles() {

        if (sala == null || sala.getListSalaAsientos() == null) {
            return 0;
        }

        int disponibles = sala.getListSalaAsientos().size()
                - contarAsientosOcupados();

        return Math.max(disponibles, 0);
    }

    /**
     * Calcula el porcentaje de ocupación de la función.
     */
    public double calcularPorcentajeOcupacion() {

        if (sala == null || sala.getListSalaAsientos() == null ||
                sala.getListSalaAsientos().isEmpty()) {
            return 0;
        }

        return (double) contarAsientosOcupados() * 100
                / sala.getListSalaAsientos().size();
    }

    // GETTERS Y SETTERS

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Venta> getListFuncionVentas() {
        return listFuncionVentas;
    }

    public void setListFuncionVentas(List<Venta> listFuncionVentas) {
        this.listFuncionVentas = (listFuncionVentas == null)
                ? new ArrayList<>()
                : listFuncionVentas;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo=" + codigo +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", valorBase=" + valorBase +
                ", estadoFuncion=" + estadoFuncion +
                ", administrador=" + administrador +
                ", sala=" + sala +
                ", pelicula=" + pelicula +
                ", listFuncionVentas=" + listFuncionVentas +
                '}';
    }
}

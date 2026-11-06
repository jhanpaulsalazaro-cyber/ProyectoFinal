package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {

    // atributos
    private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;

    // relaciones
    private EstadoFuncion estadoFuncion;
    private Administrador administrador;
    private List<Venta> listFuncionVentas;

    /**
     * metodo constructor de la clase funcion
     *
     * @param codigo        de la funcion
     * @param fecha         de la funcion
     * @param hora          de la funcion
     * @param valorBase     de la funcion
     * @param estadoFuncion de la funcion
     * @param administrador de la funcion
     */

    public Funcion(int codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion estadoFuncion, Administrador administrador) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.administrador = administrador;

        this.listFuncionVentas = new ArrayList<>();
    }

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

    public List<Venta> getListFuncionVentas() {
        return listFuncionVentas;
    }

    public void setListFuncionVentas(List<Venta> listFuncionVentas) {
        this.listFuncionVentas = listFuncionVentas;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo=" + codigo + "\n" +
                ", fecha=" + fecha + "\n" +
                ", hora=" + hora + "\n" +
                ", valorBase=" + valorBase + "\n" +
                ", estadoFuncion=" + estadoFuncion + "\n" +
                ", administrador=" + administrador + "\n" +
                ", listFuncionVentas=" + listFuncionVentas + "\n" +
                '}';
    }
}
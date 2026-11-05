package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Funcion {

    // atributos
    private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;

    // relaciones
    private EstadoFuncion estadoFuncion;
    private Administrador theAdministradorFuncion;
    private List<Venta> listVentaFuncion;

    public Funcion(int codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion estadoFuncion, Administrador theAdministradorFuncion, List<Venta> listVentaFuncion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.theAdministradorFuncion = theAdministradorFuncion;
        this.listVentaFuncion = listVentaFuncion;
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

    public Administrador getTheAdministradorFuncion() {
        return theAdministradorFuncion;
    }

    public void setTheAdministradorFuncion(Administrador theAdministradorFuncion) {
        this.theAdministradorFuncion = theAdministradorFuncion;
    }

    public List<Venta> getListVentaFuncion() {
        return listVentaFuncion;
    }

    public void setListVentaFuncion(List<Venta> listVentaFuncion) {
        this.listVentaFuncion = listVentaFuncion;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo=" + codigo +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", valorBase=" + valorBase +
                ", estadoFuncion=" + estadoFuncion +
                ", theAdministradorFuncion=" + theAdministradorFuncion +
                ", listVentaFuncion=" + listVentaFuncion +
                '}';
    }
}


package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;

public class Venta {
    // atributos
    private int codigo;
    private LocalDate fechaHoraVenta;
    private double valorPagado;

    // relaciones
    private EstadoVenta estadoVenta;
    private Cliente theClienteVenta;
    private Operador theOperadorVenta;
    private Tarifa theTarifaVenta;
    private Asiento theAsientoVenta;
    private Funcion theFuncionVenta;

    public Venta(int codigo, LocalDate fechaHoraVenta, double valorPagado, EstadoVenta estadoVenta, Cliente theClienteVenta, Operador theOperadorVenta, Tarifa theTarifaVenta, Asiento theAsientoVenta, Funcion theFuncionVenta) {
        this.codigo = codigo;
        this.fechaHoraVenta = fechaHoraVenta;
        this.valorPagado = valorPagado;
        this.estadoVenta = estadoVenta;
        this.theClienteVenta = theClienteVenta;
        this.theOperadorVenta = theOperadorVenta;
        this.theTarifaVenta = theTarifaVenta;
        this.theAsientoVenta = theAsientoVenta;
        this.theFuncionVenta = theFuncionVenta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(LocalDate fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Cliente getTheClienteVenta() {
        return theClienteVenta;
    }

    public void setTheClienteVenta(Cliente theClienteVenta) {
        this.theClienteVenta = theClienteVenta;
    }

    public Operador getTheOperadorVenta() {
        return theOperadorVenta;
    }

    public void setTheOperadorVenta(Operador theOperadorVenta) {
        this.theOperadorVenta = theOperadorVenta;
    }

    public Tarifa getTheTarifaVenta() {
        return theTarifaVenta;
    }

    public void setTheTarifaVenta(Tarifa theTarifaVenta) {
        this.theTarifaVenta = theTarifaVenta;
    }

    public Asiento getTheAsientoVenta() {
        return theAsientoVenta;
    }

    public void setTheAsientoVenta(Asiento theAsientoVenta) {
        this.theAsientoVenta = theAsientoVenta;
    }

    public Funcion getTheFuncionVenta() {
        return theFuncionVenta;
    }

    public void setTheFuncionVenta(Funcion theFuncionVenta) {
        this.theFuncionVenta = theFuncionVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo=" + codigo +
                ", fechaHoraVenta=" + fechaHoraVenta +
                ", valorPagado=" + valorPagado +
                ", estadoVenta=" + estadoVenta +
                ", theClienteVenta=" + theClienteVenta +
                ", theOperadorVenta=" + theOperadorVenta +
                ", theTarifaVenta=" + theTarifaVenta +
                ", theAsientoVenta=" + theAsientoVenta +
                ", theFuncionVenta=" + theFuncionVenta +
                '}';
    }
}


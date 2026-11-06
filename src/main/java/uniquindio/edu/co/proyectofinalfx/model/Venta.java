package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;

public class Venta {
    // atributos
    private int codigo;
    private LocalDate fechaHoraVenta;
    private double valorPagado;

    // relaciones
    private EstadoVenta estadoVenta;
    private Cliente cliente;
    private Operador operador;
    private Tarifa tarifa;
    private Asiento asiento;
    private Funcion funcion;

    /**
     * metodo constructor de la clase Venta
     *
     * @param codigo         de la venta
     * @param fechaHoraVenta de la venta
     * @param valorPagado    de la venta
     * @param estadoVenta    de la venta
     * @param cliente        de la venta
     * @param operador       de la venta
     * @param tarifa         de la venta
     * @param asiento        de la venta
     * @param funcion        de la venta
     */

    public Venta(int codigo, LocalDate fechaHoraVenta, double valorPagado, EstadoVenta estadoVenta, Cliente cliente, Operador operador, Tarifa tarifa, Asiento asiento, Funcion funcion) {
        this.codigo = codigo;
        this.fechaHoraVenta = fechaHoraVenta;
        this.valorPagado = valorPagado;
        this.estadoVenta = estadoVenta;
        this.cliente = cliente;
        this.operador = operador;
        this.tarifa = tarifa;
        this.asiento = asiento;
        this.funcion = funcion;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo=" + codigo + "\n" +
                ", fechaHoraVenta=" + fechaHoraVenta + "\n" +
                ", valorPagado=" + valorPagado + "\n" +
                ", estadoVenta=" + estadoVenta + "\n" +
                ", cliente=" + cliente + "\n" +
                ", operador=" + operador + "\n" +
                ", tarifa=" + tarifa + "\n" +
                ", asiento=" + asiento + "\n" +
                ", funcion=" + funcion + "\n" +
                '}';
    }
}
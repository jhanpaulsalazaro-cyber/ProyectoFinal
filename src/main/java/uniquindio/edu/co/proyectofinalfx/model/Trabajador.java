package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;

public abstract  class Trabajador extends Persona {
    // ATRIBUTOS
    private String codigoEmpleado;
    private LocalDate fechaContratacion;
    private double salario;

    /**
     * Método constructor de la clase trabajador
     * @param documentoIdentidad del trabajador
     * @param nombre             del trabajador
     * @param codigoEmpleado     del trabajador
     * @param fechaContratacion  del trabajador
     * @param salario            del trabajador
     */
    public Trabajador(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        super(documentoIdentidad, nombre);
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "codigoEmpleado='" + codigoEmpleado + "\n"  +
                ", fechaContratacion=" + fechaContratacion + "\n" +
                ", salario=" + salario +
                '}';
    }
}

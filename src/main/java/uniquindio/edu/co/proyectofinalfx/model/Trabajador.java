package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;

public class Trabajador extends Persona {
    // atributos
    private String codigoEmpleado;
    private LocalDate fechaContratacion;
    private double salario;

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
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                ", fechaContratacion=" + fechaContratacion +
                ", salario=" + salario +
                '}';
    }
}

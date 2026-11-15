
package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;

public abstract class Trabajador extends Persona {

    // ATRIBUTOS
    private String codigoEmpleado;
    private LocalDate fechaContratacion;
    private double salario;

    // CONSTRUCTOR
    public Trabajador(String documentoIdentidad, String nombre,
                      String codigoEmpleado, LocalDate fechaContratacion,
                      double salario) {

        super(documentoIdentidad, nombre);

        if (codigoEmpleado == null || codigoEmpleado.isBlank()) {
            throw new IllegalArgumentException("El código del empleado es obligatorio.");
        }

        if (fechaContratacion == null) {
            throw new IllegalArgumentException("La fecha de contratación es obligatoria.");
        }

        if (!Double.isFinite(salario) || salario < 0) {
            throw new IllegalArgumentException("El salario debe ser un valor válido y no negativo.");
        }

        this.codigoEmpleado = codigoEmpleado.trim();
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

    // GETTERS Y SETTERS

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        if (codigoEmpleado != null && !codigoEmpleado.isBlank()) {
            this.codigoEmpleado = codigoEmpleado.trim();
        }
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion != null) {
            this.fechaContratacion = fechaContratacion;
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (Double.isFinite(salario) && salario >= 0) {
            this.salario = salario;
        }
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Trabajador{" +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                ", fechaContratacion=" + fechaContratacion +
                ", salario=" + salario +
                '}';
    }
}

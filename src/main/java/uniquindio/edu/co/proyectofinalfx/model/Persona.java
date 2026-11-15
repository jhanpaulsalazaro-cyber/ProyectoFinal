
package uniquindio.edu.co.proyectofinalfx.model;

public class Persona {

    // ATRIBUTOS
    private String documentoIdentidad;
    private String nombre;

    // CONSTRUCTOR
    public Persona(String documentoIdentidad, String nombre) {

        if (documentoIdentidad == null || documentoIdentidad.isBlank()) {
            throw new IllegalArgumentException("El documento de identidad es obligatorio.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        this.documentoIdentidad = documentoIdentidad.trim();
        this.nombre = nombre.trim();
    }

    // GETTERS Y SETTERS

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        if (documentoIdentidad != null && !documentoIdentidad.isBlank()) {
            this.documentoIdentidad = documentoIdentidad.trim();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Persona{" +
                "documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

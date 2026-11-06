package uniquindio.edu.co.proyectofinalfx.model;

public  class Persona {

    // atributos
    private String documentoIdentidad, nombre;

    /**
     * metodo constructor de la clase Persona
     *
     * @param documentoIdentidad de la persona
     * @param nombre             de la persona
     */

    public Persona(String documentoIdentidad, String nombre) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "documentoIdentidad='" + documentoIdentidad + "\n" +
                ", nombre='" + nombre + "\n"  +
                '}';
    }
}

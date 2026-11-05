package uniquindio.edu.co.proyectofinalfx.model;

public class Persona {
    private String documentoIdentidad,nombre;

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
}

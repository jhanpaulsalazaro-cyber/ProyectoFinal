
package uniquindio.edu.co.proyectofinalfx.model;

public class Pelicula {

    // ATRIBUTOS
    private int codigo;
    private int duracion;
    private String nombre;

    // RELACIONES
    private EstadoPelicula estadoPelicula;
    private GeneroPelicula generoPelicula;
    private ClasificacionPelicula clasificacionPelicula;
    private Administrador administrador;

    // CONSTRUCTOR
    public Pelicula(int codigo, int duracion, String nombre,
                    EstadoPelicula estadoPelicula,
                    GeneroPelicula generoPelicula,
                    ClasificacionPelicula clasificacionPelicula) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser positivo.");
        }

        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser positiva.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la película es obligatorio.");
        }

        if (estadoPelicula == null || generoPelicula == null
                || clasificacionPelicula == null) {
            throw new IllegalArgumentException("Los datos de la película no pueden ser nulos.");
        }

        this.codigo = codigo;
        this.duracion = duracion;
        this.nombre = nombre.trim();
        this.estadoPelicula = estadoPelicula;
        this.generoPelicula = generoPelicula;
        this.clasificacionPelicula = clasificacionPelicula;
    }

    // GETTERS Y SETTERS

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
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

    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        if (estadoPelicula != null) {
            this.estadoPelicula = estadoPelicula;
        }
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        if (generoPelicula != null) {
            this.generoPelicula = generoPelicula;
        }
    }

    public ClasificacionPelicula getClasificacionPelicula() {
        return clasificacionPelicula;
    }

    public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) {
        if (clasificacionPelicula != null) {
            this.clasificacionPelicula = clasificacionPelicula;
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo=" + codigo +
                ", duracion=" + duracion +
                ", nombre='" + nombre + '\'' +
                ", estadoPelicula=" + estadoPelicula +
                ", generoPelicula=" + generoPelicula +
                ", clasificacionPelicula=" + clasificacionPelicula +
                ", administrador=" + administrador +
                '}';
    }
}

package uniquindio.edu.co.proyectofinalfx.model;

public class Pelicula {

    // atributos
    private int codigo, duracion;
    private String nombre;


    // relaciones
    private EstadoPelicula estadoPelicula;
    private GeneroPelicula generoPelicula;
    private ClasificacionPelicula clasificacionPelicula;
    private Administrador administrador;

    /**
     * metodo constructor de la clase Pelicula
     * @param codigo de la pelicula
     * @param duracion de la pelicula
     * @param nombre de la pelicula
     * @param estadoPelicula de la pelicula
     * @param generoPelicula de la pelicula
     * @param clasificacionPelicula de la pelicula
     */

    public Pelicula(int codigo, int duracion, String nombre, EstadoPelicula estadoPelicula, GeneroPelicula generoPelicula, ClasificacionPelicula clasificacionPelicula) {
        this.codigo = codigo;
        this.duracion = duracion;
        this.nombre = nombre;
        this.estadoPelicula = estadoPelicula;
        this.generoPelicula = generoPelicula;
        this.clasificacionPelicula = clasificacionPelicula;
        this.administrador = administrador;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public ClasificacionPelicula getClasificacionPelicula() {
        return clasificacionPelicula;
    }

    public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) {
        this.clasificacionPelicula = clasificacionPelicula;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo=" + codigo + "\n" +
                ", duracion=" + duracion + "\n" +
                ", nombre='" + nombre + "\n"  +
                ", estadoPelicula=" + estadoPelicula + "\n" +
                ", generoPelicula=" + generoPelicula + "\n" +
                ", clasificacionPelicula=" + clasificacionPelicula + "\n" +
                ", administrador=" + administrador + "\n" +
                '}';
    }
}




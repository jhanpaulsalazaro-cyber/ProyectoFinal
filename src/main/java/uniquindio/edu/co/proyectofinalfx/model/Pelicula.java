package uniquindio.edu.co.proyectofinalfx.model;

public class Pelicula {

    // atributos
    private int codigo,duracion;
    private String nombre;


    // relaciones
    private EstadoPelicula estadoPelicula;
    private GeneroPelicula generoPelicula;
    private ClasificacionPelicula clasificacionPelicula;
    private Administrador theAdministradorPelicula;

    public Pelicula(int codigo, int duracion, String nombre, EstadoPelicula estadoPelicula, GeneroPelicula generoPelicula, ClasificacionPelicula clasificacionPelicula, Administrador theAdministradorPelicula) {
        this.codigo = codigo;
        this.duracion = duracion;
        this.nombre = nombre;
        this.estadoPelicula = estadoPelicula;
        this.generoPelicula = generoPelicula;
        this.clasificacionPelicula = clasificacionPelicula;
        this.theAdministradorPelicula = theAdministradorPelicula;
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

    public Administrador getTheAdministradorPelicula() {
        return theAdministradorPelicula;
    }

    public void setTheAdministradorPelicula(Administrador theAdministradorPelicula) {
        this.theAdministradorPelicula = theAdministradorPelicula;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo=" + codigo +
                ", duracion=" + duracion +
                ", nombre='" + nombre + '\'' +
                ", estadoPelicula=" + estadoPelicula +
                ", generoPelicula=" + generoPelicula +
                ", clasificacionPelicula=" + clasificacionPelicula +
                ", theAdministradorPelicula=" + theAdministradorPelicula +
                '}';
    }
}


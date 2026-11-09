package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Pelicula {
    // ATRIBUTOS
    private int codigo, duracion;
    private String nombre;


    // RELACIONES
    private EstadoPelicula estadoPelicula;
    private GeneroPelicula generoPelicula;
    private ClasificacionPelicula clasificacionPelicula;
    private Administrador administrador;

    /**
     * Método constructor de la clase Pelicula
     *
     * @param codigo
     * @param duracion
     * @param nombre
     * @param estadoPelicula
     * @param generoPelicula
     * @param clasificacionPelicula
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

    //  ==========================  CRUD PELICULA   ==========================
    /**
     * Método para registrar una pelicula
     * @param listCinePeliculas de peliculas
     * @param codigo de la pelicula
     * @param duracion de la pelicula
     * @param nombre de la pelicula
     * @param estadoPelicula de la pelicula
     * @param generoPelicula de la pelicula
     * @param clasificacionPelicula de la pelicula
     * @return
     */
    public boolean registrarPelicula(List<Pelicula> listCinePeliculas, int codigo, int duracion, String nombre, EstadoPelicula estadoPelicula, GeneroPelicula generoPelicula, ClasificacionPelicula clasificacionPelicula) {
        boolean existe = false;
        Pelicula pelicula = buscarPelicula(listCinePeliculas,codigo);

        if (pelicula == null) {
            Pelicula pelicula1 = new Pelicula(codigo, duracion, nombre, estadoPelicula, generoPelicula, clasificacionPelicula);
            listCinePeliculas.add(pelicula1);
            existe= true;
        }
        return existe;
    }

    /**
     * Método para buscar una pelicula
     * @param listCinePeliculas de las peliculas
     * @param codigo de la pelicula
     * @return
     */
    public Pelicula buscarPelicula(List<Pelicula> listCinePeliculas, int codigo){
        Pelicula encontrada= null;

        for(int i=0; i<listCinePeliculas.size(); i++){
            Pelicula pelicula= listCinePeliculas.get(i);
            if(pelicula.getCodigo()== codigo){
                encontrada= pelicula;
            }
        }
        return encontrada;
    }

    /**
     * Método para consulta peliculas
     * @param listCinePeliculas de peliculas
     * @return
     */

    public List<Pelicula> consultarPeliculas(List<Pelicula> listCinePeliculas){
        return  listCinePeliculas;
    }

    /**
     * Método para modificar una pelicula
     * @param listCinePeliculas de las peliculas
     * @param codigo de la pelicula
     * @param duracion de la pelicula
     * @param nombre de la pelicula
     * @param estadoPelicula de la pelicula
     * @param generoPelicula de la pelicula
     * @param clasificacionPelicula de la pelicula
     * @return
     */
    public boolean modificarPelicula(List<Pelicula> listCinePeliculas,int codigo,int duracion, String nombre, EstadoPelicula estadoPelicula, GeneroPelicula generoPelicula, ClasificacionPelicula clasificacionPelicula){
        boolean modificado= false;

        Pelicula pelicula = buscarPelicula(listCinePeliculas, codigo);

        if (pelicula != null) {
            pelicula.setDuracion(duracion);
            pelicula.setNombre(nombre);
            pelicula.setEstadoPelicula(estadoPelicula);
            pelicula.setGeneroPelicula(generoPelicula);
            pelicula.setClasificacionPelicula(clasificacionPelicula);

            modificado= true;
        }

        return modificado;
    }

    /**
     * Método para deshabilitar una pelicula
     * @param listCinePeliculas de las peliculas
     * @param codigo de las peliculas
     * @return
     */

    public boolean deshabilitarPelicula(List<Pelicula> listCinePeliculas, int codigo) {

        boolean deshabilitado= false;

        Pelicula pelicula = buscarPelicula(listCinePeliculas, codigo);

        if (pelicula != null) {
            pelicula.setEstadoPelicula(EstadoPelicula.NO_DISPONIBLE);
            return true;
        }
        return deshabilitado;
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




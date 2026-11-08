package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Trabajador {
    /**
     * metodo constructor de la clase Administrador
     *
     * @param documentoIdentidad del administrador
     * @param nombre             del administrador
     * @param codigoEmpleado     del administrador
     * @param fechaContratacion  del administrador
     * @param salario            del administrador
     */

    public Administrador(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
    }

    /**
     * metodo para registrar una pelicula
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
     * metodo para buscar una pelicula
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
     * metodo para consulat peliculas
     * @param listCinePeliculas de peliculas
     * @return
     */

    public List<Pelicula> consultarPeliculas(List<Pelicula> listCinePeliculas){
        return  listCinePeliculas;
    }

    /**
     * metodo para modificar una pelicula
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
     * metodo para deshabilitar una pelicula
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





}


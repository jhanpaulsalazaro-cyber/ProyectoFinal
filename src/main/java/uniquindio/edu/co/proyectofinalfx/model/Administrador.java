package uniquindio.edu.co.proyectofinalfx.model;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Administrador extends Trabajador {
    /**
     * Método constructor de la clase Administrador
     * @param documentoIdentidad del administrador
     * @param nombre             del administrador
     * @param codigoEmpleado     del administrador
     * @param fechaContratacion  del administrador
     * @param salario            del administrador
     */
    public Administrador(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
    }

    // -------------------- PELÍCULAS --------------------
    /**
     * Registra la película con verificación de atributos son válidos.
     */
    public boolean registrarPelicula(List<Pelicula> peliculas, Pelicula pelicula) {
        //Pelicula no existe, entonces false
        if (peliculas == null || pelicula == null){
            return false;
        }
        //Pelicula tiene algún atributo vacío, entonces false
        if (pelicula.getCodigo() <= 0 || pelicula.getDuracion() <= 0 || pelicula.getNombre() == null || pelicula.getEstadoPelicula() == null
                || pelicula.getGeneroPelicula() == null || pelicula.getClasificacionPelicula() == null) {
            return false;
        }
        //Pelicula existe en código, entonces false
        if (buscarPelicula(peliculas, pelicula.getCodigo()) != null) {
            return false;
        }

        //Pelicula entra en a la lista de película y de administrador(this)
        pelicula.setAdministrador(this);
        peliculas.add(pelicula);

        return true;
    }

    /**
     * Busca una película por su código.
     */
    public Pelicula buscarPelicula(List<Pelicula> peliculas, int codigo) {
        if (peliculas == null) {
            return null;
        }

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getCodigo() == codigo) {
                return pelicula;
            }
        }
        return null;
    }

    /**
     * Modifica los datos de una película existente.
     */
    public boolean modificarPelicula(List<Pelicula> peliculas, int codigo, int duracion,
                                     String nombre, EstadoPelicula estado,
                                     GeneroPelicula genero, ClasificacionPelicula clasificacion) {

        //Busca la pelicula por codigo en la lista peliculas
        Pelicula pelicula = buscarPelicula(peliculas, codigo);

        //Confirma si la película existe
        if (pelicula == null || duracion <= 0 || nombre == null
                || estado == null || genero == null || clasificacion == null) {
            return false;
        }

        //Vuelve a establecer los mismos atributos del objeto para no alterarlo
        pelicula.setDuracion(duracion);
        pelicula.setNombre(nombre);
        pelicula.setEstadoPelicula(estado);
        pelicula.setGeneroPelicula(genero);
        pelicula.setClasificacionPelicula(clasificacion);
        return true;
    }

    /**
     * Deshabilita una película sin borrarla del historial.
     */
    public boolean deshabilitarPelicula(List<Pelicula> peliculas, int codigo) {
        //Primero busca la película
        Pelicula pelicula = buscarPelicula(peliculas, codigo);

        //Si la película no existe entonces false
        if (pelicula == null) {
            return false;
        }

        //Agrega el estado de No_Disponible
        pelicula.setEstadoPelicula(EstadoPelicula.NO_DISPONIBLE);
        return true;
    }

    // -------------------- SALAS --------------------
    /**
     * Registrar Salas
     */
    public boolean registrarSala(List<Sala> salas, Sala sala) {
        //Verifica que exista
        if (salas == null || sala == null || sala.getCodigo() <= 0 || sala.getCapacidad() <= 0
                || sala.getNombre() == null || sala.getNombre().isBlank() || sala.getEstadoSala() == null
                || buscarSala(salas, sala.getCodigo()) != null) {
            return false;
        }

        //Vuelve a establecer el objeto en administrador y crea la sala
        sala.setAdministrador(this);
        salas.add(sala);

        return true;
    }

    public Sala buscarSala(List<Sala> salas, int codigo) {
        // Verifica si existe la sala
        if (salas == null){
            return null;
        }
        // Objeter el código de la sala
        for (Sala sala : salas) {
            if (sala.getCodigo() == codigo) {
                return sala;
            }
        }
        return null;
    }

    public boolean modificarSala(List<Sala> salas, int codigo, String nombre,
                                 int capacidad, EstadoSala estado) {
        //Busca la sala
        Sala sala = buscarSala(salas, codigo);

        //Verifica que exista la sala
        if (sala == null || nombre == null || nombre.isBlank() || capacidad <= 0 || estado == null) {
            return false;
        }

        if (!sala.ajustarCapacidad(capacidad)) {
            return false;
        }

        //Establece los nuevos datoss
        sala.setNombre(nombre);
        sala.setEstadoSala(estado);
        return true;
    }

    public boolean deshabilitarSala(List<Sala> salas, int codigo) {
        //Busca el código de la sala
        Sala sala = buscarSala(salas, codigo);
        if (sala == null) {
            return false;
        }
        //Establece el estado como fuera de servicio
        sala.setEstadoSala(EstadoSala.FUERA_DE_SERVICIO);
        return true;
    }

    // -------------------- FUNCIONES --------------------
    public boolean programarFuncion(List<Funcion> funciones, Funcion funcion) {
        //Verifica si existe la funcion
        if (funciones == null || funcion == null || funcion.getCodigo() <= 0 || funcion.getFecha() == null
                || funcion.getHora() == null || funcion.getValorBase() < 0 || funcion.getEstadoFuncion() == null){
            return false;
        }
        //Busca la función si existe
        if (buscarFuncion(funciones, funcion.getCodigo()) != null) {
            return false;
        }
        //Verifica que el estado de la sala sea disponible
        if (funcion.getSala() == null || funcion.getSala().getEstadoSala() != EstadoSala.DISPONIBLE) {
            return false;
        }
        //Verifica el estado de la pelicula para agregarla
        if (funcion.getPelicula() == null || funcion.getPelicula().getEstadoPelicula() != EstadoPelicula.DISPONIBLE) {
            return false;
        }

        //Establece en administrador la nueva funcion y agrega a la list funcion lo programado
        funcion.setAdministrador(this);
        funciones.add(funcion);
        return true;
    }

    /**
     * Buscar función
     */
    public Funcion buscarFuncion(List<Funcion> funciones, int codigo) {
        //Verifica que exista función
        if (funciones == null) {
            return null;
        }
        //Busca la función por código
        for (Funcion funcion : funciones) {
            if (funcion.getCodigo() == codigo) return funcion;
        }
        return null;
    }

    // -------------------- TARIFAS --------------------
    /**
     * Buscar tarifa
     */
    public Tarifa buscarTarifa(List<Tarifa> tarifas, TipoCliente tipo) {
        //Verifica que tarifas exista
        if (tarifas == null || tipo == null) {
            return null;
        }
        //Busca tarifa por la lista
        for (Tarifa tarifa : tarifas) {
            if (tarifa.tipoCliente() == tipo) return tarifa;
        }
        return null;
    }

    /** Actualiza una tarifa existente o la agrega si todavía no existe. */
    public boolean agregarOActualizarTarifa(List<Tarifa> tarifas, TipoCliente tipo, double valor) {
        if (tarifas == null || tipo == null || valor < 0) return false;

        Tarifa tarifaAnterior = buscarTarifa(tarifas, tipo);
        if (tarifaAnterior != null) tarifas.remove(tarifaAnterior);

        tarifas.add(new Tarifa(tipo, valor));
        return true;
    }

}
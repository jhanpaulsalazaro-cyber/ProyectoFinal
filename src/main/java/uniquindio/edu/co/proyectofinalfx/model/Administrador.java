
package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Administrador extends Trabajador {

    /**
     * Constructor de la clase Administrador.
     */
    public Administrador(String documentoIdentidad, String nombre,
                         String codigoEmpleado, LocalDate fechaContratacion,
                         double salario) {

        super(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
    }

    // -------------------- PELÍCULAS --------------------

    /**
     * Registra una película.
     */
    public boolean registrarPelicula(List<Pelicula> peliculas, Pelicula pelicula) {

        if (peliculas == null || pelicula == null) {
            return false;
        }

        if (pelicula.getCodigo() <= 0
                || pelicula.getDuracion() <= 0
                || pelicula.getNombre() == null
                || pelicula.getNombre().isBlank()
                || pelicula.getEstadoPelicula() == null
                || pelicula.getGeneroPelicula() == null
                || pelicula.getClasificacionPelicula() == null) {
            return false;
        }

        if (buscarPelicula(peliculas, pelicula.getCodigo()) != null) {
            return false;
        }

        pelicula.setNombre(pelicula.getNombre().trim());
        pelicula.setAdministrador(this);
        peliculas.add(pelicula);

        return true;
    }

    /**
     * Busca una película por su código.
     */
    public Pelicula buscarPelicula(List<Pelicula> peliculas, int codigo) {

        if (peliculas == null || codigo <= 0) {
            return null;
        }

        for (Pelicula pelicula : peliculas) {

            if (pelicula != null && pelicula.getCodigo() == codigo) {
                return pelicula;
            }
        }

        return null;
    }

    /**
     * Modifica los datos de una película existente.
     */
    public boolean modificarPelicula(List<Pelicula> peliculas, int codigo,
                                     int duracion, String nombre,
                                     EstadoPelicula estado,
                                     GeneroPelicula genero,
                                     ClasificacionPelicula clasificacion) {

        Pelicula pelicula = buscarPelicula(peliculas, codigo);

        if (pelicula == null
                || duracion <= 0
                || nombre == null
                || nombre.isBlank()
                || estado == null
                || genero == null
                || clasificacion == null) {
            return false;
        }

        pelicula.setDuracion(duracion);
        pelicula.setNombre(nombre.trim());
        pelicula.setEstadoPelicula(estado);
        pelicula.setGeneroPelicula(genero);
        pelicula.setClasificacionPelicula(clasificacion);

        return true;
    }

    /**
     * Deshabilita una película sin eliminarla del historial.
     */
    public boolean deshabilitarPelicula(List<Pelicula> peliculas, int codigo) {

        Pelicula pelicula = buscarPelicula(peliculas, codigo);

        if (pelicula == null) {
            return false;
        }

        pelicula.setEstadoPelicula(EstadoPelicula.NO_DISPONIBLE);

        return true;
    }

    // -------------------- SALAS --------------------

    /**
     * Registra una sala.
     */
    public boolean registrarSala(List<Sala> salas, Sala sala) {

        if (salas == null || sala == null
                || sala.getCodigo() <= 0
                || sala.getCapacidad() <= 0
                || sala.getNombre() == null
                || sala.getNombre().isBlank()
                || sala.getEstadoSala() == null) {
            return false;
        }

        if (buscarSala(salas, sala.getCodigo()) != null) {
            return false;
        }

        sala.setNombre(sala.getNombre().trim());
        sala.setAdministrador(this);
        salas.add(sala);

        return true;
    }

    /**
     * Busca una sala por su código.
     */
    public Sala buscarSala(List<Sala> salas, int codigo) {

        if (salas == null || codigo <= 0) {
            return null;
        }

        for (Sala sala : salas) {

            if (sala != null && sala.getCodigo() == codigo) {
                return sala;
            }
        }

        return null;
    }

    /**
     * Modifica los datos de una sala.
     */
    public boolean modificarSala(List<Sala> salas, int codigo,
                                 String nombre, int capacidad,
                                 EstadoSala estado) {

        Sala sala = buscarSala(salas, codigo);

        if (sala == null
                || nombre == null
                || nombre.isBlank()
                || capacidad <= 0
                || estado == null) {
            return false;
        }

        // Ajustar la capacidad antes de modificar los demás datos.
        if (!sala.ajustarCapacidad(capacidad)) {
            return false;
        }

        sala.setNombre(nombre.trim());
        sala.setEstadoSala(estado);

        return true;
    }

    /**
     * Deshabilita una sala sin eliminarla.
     */
    public boolean deshabilitarSala(List<Sala> salas, int codigo) {

        Sala sala = buscarSala(salas, codigo);

        if (sala == null) {
            return false;
        }

        sala.setEstadoSala(EstadoSala.FUERA_DE_SERVICIO);

        return true;
    }

    // -------------------- FUNCIONES --------------------

    /**
     * Programa una función si sus datos son válidos
     * y no existe un conflicto de horario en la sala.
     */
    public boolean programarFuncion(List<Funcion> funciones, Funcion funcion) {

        if (funciones == null || funcion == null
                || funcion.getCodigo() <= 0
                || funcion.getFecha() == null
                || funcion.getHora() == null
                || !Double.isFinite(funcion.getValorBase())
                || funcion.getValorBase() < 0
                || funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
            return false;
        }

        // Evitar códigos de función repetidos.
        if (buscarFuncion(funciones, funcion.getCodigo()) != null) {
            return false;
        }

        // La sala debe existir y estar disponible.
        if (funcion.getSala() == null
                || funcion.getSala().getEstadoSala() != EstadoSala.DISPONIBLE) {
            return false;
        }

        // La película debe existir y estar disponible.
        if (funcion.getPelicula() == null
                || funcion.getPelicula().getEstadoPelicula() != EstadoPelicula.DISPONIBLE
                || funcion.getPelicula().getDuracion() <= 0) {
            return false;
        }

        // Comprobar que no exista otra función en ese horario y sala.
        if (hayConflictoHorario(funciones, funcion)) {
            return false;
        }

        funcion.setAdministrador(this);
        funciones.add(funcion);

        return true;
    }

    /**
     * Comprueba si una función se cruza en horario
     * con otra función programada en la misma sala.
     */
    private boolean hayConflictoHorario(List<Funcion> funciones,
                                        Funcion nuevaFuncion) {

        LocalDateTime inicioNuevo = LocalDateTime.of(
                nuevaFuncion.getFecha(),
                nuevaFuncion.getHora()
        );

        LocalDateTime finNuevo = inicioNuevo.plusMinutes(
                nuevaFuncion.getPelicula().getDuracion()
        );

        for (Funcion existente : funciones) {

            if (existente == null || existente == nuevaFuncion) {
                continue;
            }

            if (existente.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
                continue;
            }

            if (existente.getSala() != nuevaFuncion.getSala()
                    || existente.getFecha() == null
                    || existente.getHora() == null
                    || existente.getPelicula() == null
                    || existente.getPelicula().getDuracion() <= 0) {
                continue;
            }

            LocalDateTime inicioExistente = LocalDateTime.of(
                    existente.getFecha(),
                    existente.getHora()
            );

            LocalDateTime finExistente = inicioExistente.plusMinutes(
                    existente.getPelicula().getDuracion()
            );

            boolean seCruzan = inicioNuevo.isBefore(finExistente)
                    && inicioExistente.isBefore(finNuevo);

            if (seCruzan) {
                return true;
            }
        }

        return false;
    }

    /**
     * Busca una función por su código.
     */
    public Funcion buscarFuncion(List<Funcion> funciones, int codigo) {

        if (funciones == null || codigo <= 0) {
            return null;
        }

        for (Funcion funcion : funciones) {

            if (funcion != null && funcion.getCodigo() == codigo) {
                return funcion;
            }
        }

        return null;
    }

    // -------------------- TARIFAS --------------------

    /**
     * Busca una tarifa según el tipo de cliente.
     */
    public Tarifa buscarTarifa(List<Tarifa> tarifas, TipoCliente tipo) {

        if (tarifas == null || tipo == null) {
            return null;
        }

        for (Tarifa tarifa : tarifas) {

            if (tarifa != null && tarifa.tipoCliente() == tipo) {
                return tarifa;
            }
        }

        return null;
    }

    /**
     * Actualiza una tarifa existente o agrega una nueva.
     */
    public boolean agregarOActualizarTarifa(List<Tarifa> tarifas,
                                            TipoCliente tipo, double valor) {

        if (tarifas == null
                || tipo == null
                || !Double.isFinite(valor)
                || valor < 0) {
            return false;
        }

        Tarifa tarifaAnterior = buscarTarifa(tarifas, tipo);

        if (tarifaAnterior != null) {
            tarifas.remove(tarifaAnterior);
        }

        tarifas.add(new Tarifa(tipo, valor));

        return true;
    }
}

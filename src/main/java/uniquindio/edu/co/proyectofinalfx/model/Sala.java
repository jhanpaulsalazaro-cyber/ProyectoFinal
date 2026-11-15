
package uniquindio.edu.co.proyectofinalfx.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sala {

    // ATRIBUTOS
    private int codigo, capacidad;
    private String nombre;

    // RELACIONES
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsientos;
    private Administrador administrador;

    /**
     * Constructor de la clase Sala.
     */
    public Sala(int codigo, int capacidad, String nombre,
                EstadoSala estadoSala, Administrador administrador) {

        this.codigo = codigo;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.estadoSala = estadoSala;
        this.administrador = administrador;

        this.listSalaAsientos = new ArrayList<>();
    }

    /**
     * Agrega un asiento si cumple las condiciones de la sala.
     */
    public boolean agregarAsiento(Asiento asiento) {

        if (asiento == null || listSalaAsientos == null) {
            return false;
        }

        if (listSalaAsientos.size() >= capacidad) {
            return false;
        }

        if (buscarAsiento(asiento.getCodigo()) != null) {
            return false;
        }

        for (Asiento asientoExistente : listSalaAsientos) {

            if (asientoExistente.getNumero() == asiento.getNumero()
                    && asientoExistente.getFila().equalsIgnoreCase(asiento.getFila())) {
                return false;
            }
        }

        listSalaAsientos.add(asiento);
        return true;
    }

    /**
     * Busca un asiento por su código.
     */
    public Asiento buscarAsiento(int codigo) {

        if (listSalaAsientos == null) {
            return null;
        }

        for (Asiento asiento : listSalaAsientos) {

            if (asiento != null && asiento.getCodigo() == codigo) {
                return asiento;
            }
        }

        return null;
    }

    /**
     * Elimina un asiento únicamente si no está ocupado.
     */
    public boolean eliminarAsiento(int codigo) {

        Asiento asiento = buscarAsiento(codigo);

        if (asiento == null) {
            return false;
        }

        if (asiento.getEstadoAsiento() == EstadoAsiento.OCUPADO) {
            return false;
        }

        listSalaAsientos.remove(asiento);
        capacidad--;

        return true;
    }

    /**
     * Ajusta la capacidad de la sala.
     * No permite eliminar asientos ocupados.
     */
    public boolean ajustarCapacidad(int nuevaCapacidad) {

        if (nuevaCapacidad <= 0 || listSalaAsientos == null) {
            return false;
        }

        // Reducir capacidad
        if (nuevaCapacidad < listSalaAsientos.size()) {

            for (int i = nuevaCapacidad; i < listSalaAsientos.size(); i++) {

                Asiento asiento = listSalaAsientos.get(i);

                if (asiento.getEstadoAsiento() == EstadoAsiento.OCUPADO) {
                    return false;
                }
            }

            while (listSalaAsientos.size() > nuevaCapacidad) {
                listSalaAsientos.remove(listSalaAsientos.size() - 1);
            }
        }

        // Aumentar capacidad
        else if (nuevaCapacidad > listSalaAsientos.size()) {

            while (listSalaAsientos.size() < nuevaCapacidad) {

                int indice = listSalaAsientos.size();

                int numero = (indice % 8) + 1;
                int numeroFila = indice / 8;

                String fila = obtenerNombreFila(numeroFila);
                int nuevoCodigo = obtenerSiguienteCodigo();

                Asiento nuevoAsiento = new Asiento(
                        nuevoCodigo,
                        numero,
                        fila,
                        EstadoAsiento.DISPONIBLE
                );

                listSalaAsientos.add(nuevoAsiento);
            }
        }

        capacidad = nuevaCapacidad;
        return true;
    }

    /**
     * Genera los asientos de acuerdo con las filas y los asientos por fila.
     */
    public boolean generarAsientos(int cantidadFilas, int asientosPorFila) {

        if (listSalaAsientos == null || !listSalaAsientos.isEmpty()) {
            return false;
        }

        if (capacidad <= 0 || cantidadFilas <= 0 || asientosPorFila <= 0) {
            return false;
        }

        if ((long) cantidadFilas * asientosPorFila < capacidad) {
            return false;
        }

        List<Asiento> nuevosAsientos = new ArrayList<>();

        int codigoAsiento = 1;

        for (int fila = 0; fila < cantidadFilas && codigoAsiento <= capacidad; fila++) {

            String nombreFila = obtenerNombreFila(fila);

            for (int numero = 1;
                 numero <= asientosPorFila && codigoAsiento <= capacidad;
                 numero++) {

                nuevosAsientos.add(new Asiento(
                        codigoAsiento,
                        numero,
                        nombreFila,
                        EstadoAsiento.DISPONIBLE
                ));

                codigoAsiento++;
            }
        }

        if (nuevosAsientos.size() != capacidad) {
            return false;
        }

        listSalaAsientos.addAll(nuevosAsientos);
        return true;
    }

    /**
     * Obtiene el siguiente código disponible para un asiento.
     */
    private int obtenerSiguienteCodigo() {

        int mayorCodigo = 0;

        for (Asiento asiento : listSalaAsientos) {

            if (asiento.getCodigo() > mayorCodigo) {
                mayorCodigo = asiento.getCodigo();
            }
        }

        return mayorCodigo + 1;
    }

    /**
     * Convierte el número de fila en letras: A, B, C... Z, AA, AB...
     */
    private String obtenerNombreFila(int indice) {

        StringBuilder nombreFila = new StringBuilder();

        do {
            nombreFila.insert(0, (char) ('A' + indice % 26));
            indice = (indice / 26) - 1;
        } while (indice >= 0);

        return nombreFila.toString();
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        ajustarCapacidad(capacidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        if (estadoSala != null) {
            this.estadoSala = estadoSala;
        }
    }

    public List<Asiento> getListSalaAsientos() {
        return listSalaAsientos;
    }

    public void setListSalaAsientos(List<Asiento> listSalaAsientos) {

        if (listSalaAsientos == null) {
            this.listSalaAsientos = new ArrayList<>();
            return;
        }

        if (listSalaAsientos.size() > capacidad) {
            return;
        }

        Set<Integer> codigos = new HashSet<>();
        Set<String> posiciones = new HashSet<>();

        for (Asiento asiento : listSalaAsientos) {

            if (asiento == null || asiento.getEstadoAsiento() == null) {
                return;
            }

            if (!codigos.add(asiento.getCodigo())) {
                return;
            }

            String posicion = asiento.getFila().toUpperCase()
                    + "-" + asiento.getNumero();

            if (!posiciones.add(posicion)) {
                return;
            }
        }

        this.listSalaAsientos = new ArrayList<>(listSalaAsientos);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {

        return "Sala{" +
                "codigo=" + codigo +
                ", capacidad=" + capacidad +
                ", nombre='" + nombre + '\'' +
                ", estadoSala=" + estadoSala +
                ", listSalaAsientos=" + listSalaAsientos +
                ", administrador=" + administrador +
                '}';
    }
}

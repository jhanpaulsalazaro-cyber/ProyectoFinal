package uniquindio.edu.co.proyectofinalfx.model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    // ATRIBUTOS
    private int codigo, capacidad;
    private String nombre;

    // RELACIONES
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsientos;
    private Administrador administrador;

    /**
     * Método constructor de la clase Asiento
     * @param codigo del aiento
     * @param capacidad del asiento
     * @param nombre del asiento
     * @param estadoSala de la sala
     * @param administrador de la sala o funcion
     */
    public Sala(int codigo, int capacidad, String nombre, EstadoSala estadoSala, Administrador administrador) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.estadoSala = estadoSala;
        this.administrador = administrador;

        this.listSalaAsientos= new ArrayList<>();
    }

    /**
     * Agregar Asiento
     */
    public boolean agregarAsiento(Asiento asiento) {
        if (asiento == null || buscarAsiento(asiento.getCodigo()) != null || listSalaAsientos.size() >= capacidad) return false;
        listSalaAsientos.add(asiento);
        return true;
    }

    /**
     * Busacr Asiento
     */
    public Asiento buscarAsiento(int codigo) {
        for (Asiento asiento : listSalaAsientos) {
            if (asiento.getCodigo() == codigo) return asiento;
        }
        return null;
    }

    /**
     * Eliminar Asiento
     */
    public boolean eliminarAsiento(int codigo) {
        Asiento asiento = buscarAsiento(codigo);
        if (asiento == null || asiento.getEstadoAsiento() == EstadoAsiento.OCUPADO) return false;
        listSalaAsientos.remove(asiento);
        capacidad--;
        return true;
    }

    /**
     * Cambia la capacidad sin permitir eliminar asientos ocupados.
     * Devuelve false si la nueva capacidad no es posible.
     */
    public boolean ajustarCapacidad(int nuevaCapacidad) {
        if (nuevaCapacidad <= 0) return false;

        if (nuevaCapacidad < listSalaAsientos.size()) {
            for (int i = listSalaAsientos.size() - 1; i >= nuevaCapacidad; i--) {
                if (listSalaAsientos.get(i).getEstadoAsiento() == EstadoAsiento.OCUPADO) return false;
            }
            while (listSalaAsientos.size() > nuevaCapacidad) {
                listSalaAsientos.remove(listSalaAsientos.size() - 1);
            }
        } else {
            for (int codigoAsiento = listSalaAsientos.size() + 1; codigoAsiento <= nuevaCapacidad; codigoAsiento++) {
                int fila = (codigoAsiento - 1) / 8;
                int numero = ((codigoAsiento - 1) % 8) + 1;
                String letra = "" + (char) ('A' + fila);
                listSalaAsientos.add(new Asiento(codigoAsiento, numero, letra, EstadoAsiento.DISPONIBLE));
            }
        }

        capacidad = nuevaCapacidad;
        return true;
    }

    /** Genera los asientos una sola vez, de acuerdo con la capacidad de la sala. */
    public boolean generarAsientos(int cantidadFilas, int asientosPorFila) {
        if (!listSalaAsientos.isEmpty()) return false;
        if (cantidadFilas <= 0 || asientosPorFila <= 0) return false;
        if (cantidadFilas * asientosPorFila < capacidad) return false;

        int codigoAsiento = 1;
        for (int fila = 0; fila < cantidadFilas && codigoAsiento <= capacidad; fila++) {
            String letra = "" + (char) ('A' + fila);
            for (int numero = 1; numero <= asientosPorFila && codigoAsiento <= capacidad; numero++) {
                agregarAsiento(new Asiento(codigoAsiento, numero, letra, EstadoAsiento.DISPONIBLE));
                codigoAsiento++;
            }
        }
        return listSalaAsientos.size() == capacidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }

    public List<Asiento> getListSalaAsientos() {
        return listSalaAsientos;
    }

    public void setListSalaAsientos(List<Asiento> listSalaAsientos) {
        this.listSalaAsientos = listSalaAsientos;
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
                "codigo=" + codigo + "\n" +
                ", capacidad=" + capacidad + "\n" +
                ", nombre='" + nombre + "\n" +
                ", estadoSala=" + estadoSala + "\n" +
                ", listSalaAsientos=" + listSalaAsientos + "\n" +
                ", administrador=" + administrador + "\n" +
                '}';
    }
}


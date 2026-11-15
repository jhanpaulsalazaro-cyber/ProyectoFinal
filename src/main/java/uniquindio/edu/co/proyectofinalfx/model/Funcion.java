package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {
    // ATRIBUTOS
    private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;

    // RELACIONES
    private EstadoFuncion estadoFuncion;
    private Administrador administrador;
    private Sala sala;
    private Pelicula pelicula;
    private List<Venta> listFuncionVentas;

    /**
     * Método constructor de la clase funcion
     *
     * @param codigo        de la funcion
     * @param fecha         de la funcion
     * @param hora          de la funcion
     * @param valorBase     de la funcion
     * @param estadoFuncion de la funcion
     * @param administrador de la funcion
     * @param sala
     * @param pelicula
     */
    public Funcion(int codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion estadoFuncion, Administrador administrador, Sala sala, Pelicula pelicula) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.administrador = administrador;

        this.listFuncionVentas = new ArrayList<>();
    }

    /**
     * Asiento Disponible en la función
     */
    public boolean asientoEstaDisponible(Asiento asiento) {
        if (asiento == null || sala == null || !sala.getListSalaAsientos().contains(asiento)) return false;
        for (Venta venta : listFuncionVentas) {
            if (venta.getAsiento() == asiento && venta.getEstadoVenta() == EstadoVenta.ACTIVA) return false;
        }
        return true;
    }

    /**
     * Contar Asientos Ocupados
     */
    public int contarAsientosOcupados() {
        int total = 0;
        for (Venta venta : listFuncionVentas) {
            if (venta.getEstadoVenta() == EstadoVenta.ACTIVA) total++;
        }
        return total;
    }

    /**
     * Contar Asiento Disponible
     */
    public int contarAsientosDisponibles() {
        if (sala == null) return 0;
        return sala.getListSalaAsientos().size() - contarAsientosOcupados();
    }

    /**
     * Calcular Porcentaje Ocupación
     */
    public double calcularPorcentajeOcupacion() {
        if (sala == null || sala.getListSalaAsientos().isEmpty()) return 0;
        return (double) contarAsientosOcupados() * 100 / sala.getListSalaAsientos().size();
    }


    public int getCodigo() {return codigo;}

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Venta> getListFuncionVentas() {
        return listFuncionVentas;
    }

    public void setListFuncionVentas(List<Venta> listFuncionVentas) {
        this.listFuncionVentas = listFuncionVentas;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo=" + codigo + "\n" +
                ", fecha=" + fecha + "\n" +
                ", hora=" + hora + "\n" +
                ", valorBase=" + valorBase + "\n" +
                ", estadoFuncion=" + estadoFuncion + "\n" +
                ", administrador=" + administrador + "\n" +
                ", listFuncionVentas=" + listFuncionVentas + "\n" +
                '}';
    }
}
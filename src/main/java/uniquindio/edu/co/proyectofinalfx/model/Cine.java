package uniquindio.edu.co.proyectofinalfx.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cine {
    // ATRIBUTOS
    private String nombre, nit, direccion;

    // RELACIONES
    private List<Persona> listCinePersonas;
    private List<Venta> listCineVentas;
    private List<Sala> listCineSalas;
    private List<Asiento> listCineAsientos;
    private List<Funcion> listCineFunciones;
    private List<Pelicula> listCinePeliculas;
    private List<Tarifa> listCineTarifas;

    /**
     * Método constructor de la clase Cine
     * @param nombre    del cine
     * @param nit       del cine
     * @param direccion del cine
     */
    public Cine(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listCinePersonas = new ArrayList<>();
        this.listCineVentas = new ArrayList<>();
        this.listCineSalas = new ArrayList<>();
        this.listCineAsientos = new ArrayList<>();
        this.listCineFunciones = new ArrayList<>();
        this.listCinePeliculas = new ArrayList<>();
        this.listCineTarifas= new ArrayList<>();

    }

    /**
     * Agregar Persona
     */
    public boolean agregarPersona(Persona persona) {
        if (persona == null || buscarPersona(persona.getDocumentoIdentidad()) != null) return false;
        listCinePersonas.add(persona);
        return true;
    }

    // ------------------------------ BUSCAR ------------------------------
    /**
     * Buscar Persona
     */
    public Persona buscarPersona(String documento) {
        if (documento == null) return null;
        for (Persona persona : listCinePersonas) {
            if (persona.getDocumentoIdentidad().equals(documento)) return persona;
        }
        return null;
    }

    /**
     * Buscar administrador
     */
    public Administrador buscarAdministradorEnCine(String documento) {
        Persona persona = buscarPersona(documento);
        if (persona != null && persona.getClass() == Administrador.class) return (Administrador) persona;
        return null;
    }

    /**
     * Buscar cliente
     */
    public Cliente buscarClienteEnCine(String documento) {
        Persona persona = buscarPersona(documento);
        if (persona != null && persona.getClass() == Cliente.class) return (Cliente) persona;
        return null;
    }

    /**
     * Buscar operador
     */
    public Operador buscarOperadorEnCine(String documento) {
        Persona persona = buscarPersona(documento);
        if (persona != null && persona.getClass() == Operador.class) return (Operador) persona;
        return null;
    }

    /**
     * Buscar Venta
     */
    public Venta buscarVentaEnCine(int codigo) {
        for (Venta venta : listCineVentas) if (venta.getCodigo() == codigo) return venta;
        return null;
    }

    /**
     * Buscar Funcion
     */
    public Funcion buscarFuncionEnCine(int codigo) {
        for (Funcion funcion : listCineFunciones) if (funcion.getCodigo() == codigo) return funcion;
        return null;
    }

    /**
     * Buscar Sala
     */
    public Sala buscarSalaEnCine(int codigo) {
        for (Sala sala : listCineSalas) if (sala.getCodigo() == codigo) return sala;
        return null;
    }

    /**
     * Buscar pelicula
     */
    public Pelicula buscarPeliculaEnCine(int codigo) {
        for (Pelicula pelicula : listCinePeliculas) if (pelicula.getCodigo() == codigo) return pelicula;
        return null;
    }

    // ------------------------------ OTRAS FUNCIONES ------------------------------
    /**
     * Calcular Total Entradas Vendidas
     */
    public int calcularTotalEntradasVendidas() {
        int total = 0;
        for (Venta venta : listCineVentas) if (venta.getEstadoVenta() == EstadoVenta.ACTIVA) total++;
        return total;
    }

    /**
     * Calcular Total Ingresos
     */
    public double calcularTotalIngresos() {
        double total = 0;
        for (Venta venta : listCineVentas) {
            if (venta.getEstadoVenta() == EstadoVenta.ACTIVA) total += venta.getValorPagado();
        }
        return total;
    }

    /**
     * Buscar pelicula más vendida
     */
    public Pelicula buscarPeliculaMasVendida() {
        Pelicula masVendida = null;
        int mayorCantidad = 0;
        for (Pelicula pelicula : listCinePeliculas) {
            int cantidad = 0;
            for (Venta venta : listCineVentas) {
                if (venta.getEstadoVenta() == EstadoVenta.ACTIVA
                        && venta.getFuncion().getPelicula() == pelicula) cantidad++;
            }
            if (cantidad > mayorCantidad) {
                mayorCantidad = cantidad;
                masVendida = pelicula;
            }
        }
        return masVendida;
    }

    /**
     * Consultar funciones por fecha
     */
    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha) {
        // Esta lista temporal es necesaria porque el resultado contiene solo las funciones de la fecha pedida.
        // No se modifica la lista general de funciones que pertenece a Cine.
        List<Funcion> funcionesEncontradas = new ArrayList<>();
        for (Funcion funcion : listCineFunciones) {
            if (funcion.getFecha().equals(fecha) && funcion.getEstadoFuncion() == EstadoFuncion.PROGRAMADA) {
                funcionesEncontradas.add(funcion);
            }
        }
        return funcionesEncontradas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Persona> getListCinePersonas() {
        return listCinePersonas;
    }

    public void setListCinePersonas(List<Persona> listCinePersonas) {
        this.listCinePersonas = listCinePersonas;
    }

    public List<Venta> getListCineVentas() {
        return listCineVentas;
    }

    public void setListCineVentas(List<Venta> listCineVentas) {
        this.listCineVentas = listCineVentas;
    }

    public List<Sala> getListCineSalas() {
        return listCineSalas;
    }

    public void setListCineSalas(List<Sala> listCineSalas) {
        this.listCineSalas = listCineSalas;
    }

    public List<Asiento> getListCineAsientos() {
        return listCineAsientos;
    }

    public void setListCineAsientos(List<Asiento> listCineAsientos) {
        this.listCineAsientos = listCineAsientos;
    }

    public List<Funcion> getListCineFunciones() {
        return listCineFunciones;
    }

    public void setListCineFunciones(List<Funcion> listCineFunciones) {
        this.listCineFunciones = listCineFunciones;
    }

    public List<Pelicula> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Pelicula> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas;
    }

    public List<Tarifa> getListCineTarifas() {
        return listCineTarifas;
    }

    public void setListCineTarifas(List<Tarifa> listCineTarifas) {
        this.listCineTarifas = listCineTarifas;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + "\n" +
                ", nit='" + nit + "\n"  +
                ", direccion='" + direccion + "\n"  +
                ", listCinePersonas=" + listCinePersonas + "\n" +
                ", listCineVentas=" + listCineVentas + "\n" +
                ", listCineSalas=" + listCineSalas + "\n" +
                ", listCineAsientos=" + listCineAsientos + "\n" +
                ", listCineFunciones=" + listCineFunciones + "\n" +
                ", listCinePeliculas=" + listCinePeliculas + "\n" +
                ", listCineTarifas=" + listCineTarifas +  "\n" +
                '}';
    }
}


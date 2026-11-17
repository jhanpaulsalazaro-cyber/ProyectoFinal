
package uniquindio.edu.co.proyectofinalfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cine {

    // ATRIBUTOS
    private String nombre;
    private String nit;
    private String direccion;

    // RELACIONES
    private List<Persona> listCinePersonas;
    private List<Venta> listCineVentas;
    private List<Sala> listCineSalas;
    private List<Asiento> listCineAsientos;
    private List<Funcion> listCineFunciones;
    private List<Pelicula> listCinePeliculas;
    private List<Tarifa> listCineTarifas;

    // CONSTRUCTOR
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
        this.listCineTarifas = new ArrayList<>();
    }

    // ------------------------------ PERSONAS ------------------------------

    public boolean agregarPersona(Persona persona) {

        if (persona == null
                || persona.getDocumentoIdentidad() == null
                || persona.getDocumentoIdentidad().isBlank()) {
            return false;
        }

        if (buscarPersona(persona.getDocumentoIdentidad()) != null) {
            return false;
        }

        listCinePersonas.add(persona);
        return true;
    }

    public Persona buscarPersona(String documento) {

        if (documento == null || documento.isBlank()) {
            return null;
        }

        // Persona guarda el documento sin espacios; se compara igual.
        String documentoBuscado = documento.trim();

        for (Persona persona : listCinePersonas) {

            if (persona != null
                    && persona.getDocumentoIdentidad() != null
                    && persona.getDocumentoIdentidad().equals(documentoBuscado)) {

                return persona;
            }
        }

        return null;
    }

    public Administrador buscarAdministradorEnCine(String documento) {

        Persona persona = buscarPersona(documento);

        if (persona instanceof Administrador) {
            return (Administrador) persona;
        }

        return null;
    }

    public Cliente buscarClienteEnCine(String documento) {

        Persona persona = buscarPersona(documento);

        if (persona instanceof Cliente) {
            return (Cliente) persona;
        }

        return null;
    }

    public Operador buscarOperadorEnCine(String documento) {

        Persona persona = buscarPersona(documento);

        if (persona instanceof Operador) {
            return (Operador) persona;
        }

        return null;
    }

    // ------------------------------ BÚSQUEDAS ------------------------------

    public Venta buscarVentaEnCine(int codigo) {

        if (codigo <= 0) {
            return null;
        }

        for (Venta venta : listCineVentas) {

            if (venta != null && venta.getCodigo() == codigo) {
                return venta;
            }
        }

        return null;
    }

    public Funcion buscarFuncionEnCine(int codigo) {

        if (codigo <= 0) {
            return null;
        }

        for (Funcion funcion : listCineFunciones) {

            if (funcion != null && funcion.getCodigo() == codigo) {
                return funcion;
            }
        }

        return null;
    }

    public Sala buscarSalaEnCine(int codigo) {

        if (codigo <= 0) {
            return null;
        }

        for (Sala sala : listCineSalas) {

            if (sala != null && sala.getCodigo() == codigo) {
                return sala;
            }
        }

        return null;
    }

    public Pelicula buscarPeliculaEnCine(int codigo) {

        if (codigo <= 0) {
            return null;
        }

        for (Pelicula pelicula : listCinePeliculas) {

            if (pelicula != null && pelicula.getCodigo() == codigo) {
                return pelicula;
            }
        }

        return null;
    }

    // ------------------------------ REPORTES ------------------------------

    public int calcularTotalEntradasVendidas() {

        int total = 0;

        for (Venta venta : listCineVentas) {

            if (venta != null
                    && venta.getEstadoVenta() == EstadoVenta.ACTIVA) {

                total++;
            }
        }

        return total;
    }

    public double calcularTotalIngresos() {

        double total = 0;

        for (Venta venta : listCineVentas) {

            if (venta != null
                    && venta.getEstadoVenta() == EstadoVenta.ACTIVA) {

                total += venta.getValorPagado();
            }
        }

        return total;
    }

    public Pelicula buscarPeliculaMasVendida() {

        Pelicula masVendida = null;
        int mayorCantidad = 0;

        for (Pelicula pelicula : listCinePeliculas) {

            if (pelicula == null) {
                continue;
            }

            int cantidad = 0;

            for (Venta venta : listCineVentas) {

                if (venta == null
                        || venta.getEstadoVenta() != EstadoVenta.ACTIVA
                        || venta.getFuncion() == null
                        || venta.getFuncion().getPelicula() == null) {

                    continue;
                }

                if (venta.getFuncion().getPelicula() == pelicula) {
                    cantidad++;
                }
            }

            if (cantidad > mayorCantidad) {
                mayorCantidad = cantidad;
                masVendida = pelicula;
            }
        }

        return masVendida;
    }

    // ------------------------------ FUNCIONES POR FECHA ------------------------------

    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha) {

        List<Funcion> funcionesEncontradas = new ArrayList<>();

        if (fecha == null) {
            return funcionesEncontradas;
        }

        for (Funcion funcion : listCineFunciones) {

            if (funcion != null
                    && funcion.getFecha() != null
                    && funcion.getFecha().equals(fecha)
                    && funcion.getEstadoFuncion() == EstadoFuncion.PROGRAMADA) {

                funcionesEncontradas.add(funcion);
            }
        }

        return funcionesEncontradas;
    }

    // ------------------------------ OPERACIONES DEL ADMINISTRADOR ------------------------------

    public boolean registrarPelicula(String documentoAdministrador, Pelicula pelicula) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.registrarPelicula(listCinePeliculas, pelicula);
    }

    public Pelicula buscarPelicula(String documentoAdministrador, int codigo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return null;
        }

        return administrador.buscarPelicula(listCinePeliculas, codigo);
    }

    public boolean modificarPelicula (String documentoAdministrador, int codigo,
                                     int duracion, String nombre,
                                     EstadoPelicula estado, GeneroPelicula genero,
                                     ClasificacionPelicula clasificacion) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.modificarPelicula(
                listCinePeliculas, codigo, duracion, nombre,
                estado, genero, clasificacion
        );
    }

    public boolean deshabilitarPelicula(String documentoAdministrador, int codigo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.deshabilitarPelicula(listCinePeliculas, codigo);
    }

    public boolean registrarSala(String documentoAdministrador, Sala sala) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.registrarSala(listCineSalas, sala);
    }

    public Sala buscarSala(String documentoAdministrador, int codigo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return null;
        }

        return administrador.buscarSala(listCineSalas, codigo);
    }

    public boolean modificarSala(String documentoAdministrador, int codigo,
                                 String nombre, int capacidad, EstadoSala estado) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.modificarSala(
                listCineSalas, codigo, nombre, capacidad, estado
        );
    }

    public boolean deshabilitarSala(String documentoAdministrador, int codigo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.deshabilitarSala(listCineSalas, codigo);
    }

    public boolean programarFuncion(String documentoAdministrador, Funcion funcion) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.programarFuncion(listCineFunciones, funcion);
    }

    public Funcion buscarFuncion(String documentoAdministrador, int codigo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return null;
        }

        return administrador.buscarFuncion(listCineFunciones, codigo);
    }

    public Tarifa buscarTarifa(String documentoAdministrador, TipoCliente tipo) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return null;
        }

        return administrador.buscarTarifa(listCineTarifas, tipo);
    }

    public boolean agregarOActualizarTarifa(String documentoAdministrador,
                                            TipoCliente tipo, double valor) {
        Administrador administrador = buscarAdministradorEnCine(documentoAdministrador);

        if (administrador == null) {
            return false;
        }

        return administrador.agregarOActualizarTarifa(listCineTarifas, tipo, valor);
    }

    // ------------------------------ VENTAS ------------------------------

    /**
     * Registra una venta sin exigir el documento del operador.
     * Verifica que el cliente y la función estén registrados en el cine,
     * que la función esté programada y que el asiento esté disponible.
     */
    public Venta registrarVenta(int codigoVenta, Funcion funcion, Cliente cliente,
                                Asiento asiento, Tarifa tarifa) {

        if (codigoVenta <= 0 || funcion == null || cliente == null || asiento == null) {
            return null;
        }

        // El cliente y la función deben existir en el cine.
        if (buscarClienteEnCine(cliente.getDocumentoIdentidad()) != cliente
                || buscarFuncionEnCine(funcion.getCodigo()) != funcion) {
            return null;
        }

        if (funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
            return null;
        }

        // El asiento debe pertenecer a la sala y estar libre en esta función.
        if (!funcion.asientoEstaDisponible(asiento)) {
            return null;
        }

        // Evitar códigos de venta repetidos.
        if (buscarVentaEnCine(codigoVenta) != null) {
            return null;
        }

        // Valor de la entrada: tarifa del tipo de cliente o valor base de la función.
        double precio;

        if (tarifa == null) {
            precio = funcion.getValorBase();
        } else {
            if (tarifa.tipoCliente() != cliente.getTipoCliente()) {
                return null;
            }
            precio = tarifa.valor();
        }

        if (!Double.isFinite(precio) || precio < 0) {
            return null;
        }

        // La venta se registra sin operador asociado (operador = null).
        Venta venta = new Venta(
                codigoVenta,
                LocalDateTime.now(),
                precio,
                EstadoVenta.ACTIVA,
                cliente,
                null,
                tarifa,
                asiento,
                funcion
        );

        listCineVentas.add(venta);
        funcion.getListFuncionVentas().add(venta);
        asiento.setEstadoAsiento(EstadoAsiento.OCUPADO);

        return venta;
    }

    public Venta buscarVenta(int codigo) {
        return buscarVentaEnCine(codigo);
    }

    /**
     * Cancela una venta activa y libera su asiento.
     */
    public boolean cancelarVenta(int codigo) {

        Venta venta = buscarVentaEnCine(codigo);

        if (venta == null
                || venta.getEstadoVenta() != EstadoVenta.ACTIVA
                || venta.getAsiento() == null) {
            return false;
        }

        venta.setEstadoVenta(EstadoVenta.CANCELADA);

        // Liberar el asiento solo si no tiene otra venta activa.
        for (Venta otraVenta : listCineVentas) {
            if (otraVenta != null
                    && otraVenta.getAsiento() == venta.getAsiento()
                    && otraVenta.getEstadoVenta() == EstadoVenta.ACTIVA) {
                return true;
            }
        }

        venta.getAsiento().setEstadoAsiento(EstadoAsiento.DISPONIBLE);
        return true;
    }

    public List<Venta> obtenerVentas() {
        return new ArrayList<>(listCineVentas);
    }

    public List<Venta> obtenerVentasOperador(String documentoOperador) {
        Operador operador = buscarOperadorEnCine(documentoOperador);

        if (operador == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(operador.getListOperadoreVentas());
    }

    // ------------------------------ GETTERS Y SETTERS ------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        if (nit != null && !nit.isBlank()) {
            this.nit = nit.trim();
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.isBlank()) {
            this.direccion = direccion.trim();
        }
    }

    public List<Persona> getListCinePersonas() {
        return listCinePersonas;
    }

    public void setListCinePersonas(List<Persona> listCinePersonas) {
        this.listCinePersonas = listCinePersonas == null
                ? new ArrayList<>()
                : new ArrayList<>(listCinePersonas);
    }

    public List<Venta> getListCineVentas() {
        return listCineVentas;
    }

    public void setListCineVentas(List<Venta> listCineVentas) {
        this.listCineVentas = listCineVentas == null
                ? new ArrayList<>()
                : new ArrayList<>(listCineVentas);
    }

    public List<Sala> getListCineSalas() {
        return listCineSalas;
    }

    public void setListCineSalas(List<Sala> listCineSalas) {
        this.listCineSalas = listCineSalas == null
                ? new ArrayList<>()
                : new ArrayList<>(listCineSalas);
    }

    public List<Asiento> getListCineAsientos() {
        return listCineAsientos;
    }

    public void setListCineAsientos(List<Asiento> listCineAsientos) {
        this.listCineAsientos = listCineAsientos == null
                ? new ArrayList<>()
                : new ArrayList<>(listCineAsientos);
    }

    public List<Funcion> getListCineFunciones() {
        return listCineFunciones;
    }

    public void setListCineFunciones(List<Funcion> listCineFunciones) {
        this.listCineFunciones = listCineFunciones == null
                ? new ArrayList<>()
                : new ArrayList<>(listCineFunciones);
    }

    public List<Pelicula> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Pelicula> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas == null
                ? new ArrayList<>()
                : new ArrayList<>(listCinePeliculas);
    }

    public List<Tarifa> getListCineTarifas() {
        return listCineTarifas;
    }

    public void setListCineTarifas(List<Tarifa> listCineTarifas) {
        this.listCineTarifas = listCineTarifas == null
                ? new ArrayList<>()
                : new ArrayList<>(listCineTarifas);
    }

    // ------------------------------ TOSTRING ------------------------------

    @Override
    public String toString() {

        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listCinePersonas=" + listCinePersonas +
                ", listCineVentas=" + listCineVentas +
                ", listCineSalas=" + listCineSalas +
                ", listCineAsientos=" + listCineAsientos +
                ", listCineFunciones=" + listCineFunciones +
                ", listCinePeliculas=" + listCinePeliculas +
                ", listCineTarifas=" + listCineTarifas +
                '}';
    }
}

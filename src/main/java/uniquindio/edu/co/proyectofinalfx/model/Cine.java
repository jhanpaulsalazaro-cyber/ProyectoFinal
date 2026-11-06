package uniquindio.edu.co.proyectofinalfx.model;


import java.time.LocalDate;
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
    //  ==========================  Crear, Buscar, Borrar CLIENTE   ==========================
    /**
     * Método para agregar un cliente al Cine
     * @param documentoIdentidad del cliente
     * @param nombre             del cliente
     * @param tipoCliente        cliente
     * @return
     */
    public boolean agregarClienteAcine(String documentoIdentidad, String nombre, TipoCliente tipoCliente) {

        boolean existe = false;
        Cliente cliente = buscarClienteEnCine(documentoIdentidad);

        if (cliente == null) {
            Cliente cliente1 = new Cliente(documentoIdentidad, nombre, tipoCliente);
            listCinePersonas.add(cliente1);
            existe = true;
        }
        return existe;
    }

    /**
     * Método para buscar un cliente
     * @param documentoIdentidad del cliente
     * @return
     */
    public Cliente buscarClienteEnCine(String documentoIdentidad) {
        Cliente encontrado = null;

        for (int i = 0; i < listCinePersonas.size(); i++) {
            Persona cliente = listCinePersonas.get(i);

            if (cliente.getDocumentoIdentidad().equals(documentoIdentidad)) {
                encontrado = (Cliente) cliente;
            }
        }
        return encontrado;
    }

    /**
     * Método para eliminar un cliente del cine
     * @param documentoIdentidad del cliente
     * @return
     */

    public boolean eliminarCliente(String documentoIdentidad){
        boolean eliminado= false;
        Cliente cliente= buscarClienteEnCine(documentoIdentidad);

        if(cliente!=null){
            listCinePersonas.remove(cliente);
            eliminado= true;
        }
        return eliminado;

    }

    //  ==========================  Crear y Borrar OPERADOR   ==========================
    /**
     * Método para agregar un operario al cine
     * @param documentoIdentidad del operario
     * @param nombre del operario
     * @param codigoEmpleado operario
     * @param fechaContratacion operario
     * @param salario operario
     * @return
     */
    public boolean agregarOperario(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario) {
        boolean existe = false;
        Operador operador = buscarOperadorEnCine(documentoIdentidad);

        if(operador==null){
            Operador operador1= new Operador(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
            listCinePersonas.add(operador1);
            existe= true;
        }
        return  existe;
    }

    /**
     * Método para buscar un operador en el cine
     * @param documentoIdentidad del operador
     * @return
     */
    public Operador buscarOperadorEnCine(String documentoIdentidad){
        Operador encontrado= null;

        for(int i=0; i<listCinePersonas.size(); i++){
            Persona operador= listCinePersonas.get(i);

            if(operador.getDocumentoIdentidad().equals(documentoIdentidad)){
                encontrado= (Operador) operador;
            }
        }
        return  encontrado;
    }

    //  ==========================  Agregar y Buscar ADMINISTRADOR   ==========================
    /**
     * Método para agregar un administrador al cine
     * @param documentoIdentidad del administrador
     * @param nombre del administrador
     * @param codigoEmpleado del administrador
     * @param fechaContratacion del administrador
     * @param salario del administrador
     * @return
     */
    public boolean agregaAdministradoAlCine(String documentoIdentidad, String nombre, String codigoEmpleado, LocalDate fechaContratacion, double salario){
        boolean existe= false;
        Administrador administrador= buscarAdministradorEnCine(documentoIdentidad);

        if(administrador== null){
            Administrador administrador1= new Administrador(documentoIdentidad, nombre, codigoEmpleado, fechaContratacion, salario);
            listCinePersonas.add(administrador1);
            existe= true;
        }
        return existe;
    }

    /**
     * Método para buscar un administrador en el cine
     * @param documentoIdentidad del administrador
     * @return
     */

    public Administrador buscarAdministradorEnCine(String documentoIdentidad){
        Administrador encontrado= null;

        for(int i=0; i<listCinePersonas.size(); i++){
            Persona administrador= listCinePersonas.get(i);

            if(administrador.getDocumentoIdentidad().equals(documentoIdentidad)){
                encontrado= (Administrador) administrador;
            }
        }
        return encontrado;

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


package uniquindio.edu.co.proyectofinalfx.model;


import java.util.List;

public class Cine {
    // atributos
    private String nombre,nit,direccion;

    // relaciones
    private List<Persona> listCinePersona;
    private List<Venta> listCineVenta;
    private List<Sala> listCineSalas;
    private List<Asiento> listCineAsiento;
    private List<Funcion> listCineFuncion;
    private List<Pelicula> listCinePeliculas;

    public Cine(String nombre, String nit, String direccion, List<Persona> listCinePersona, List<Venta> listCineVenta, List<Sala> listCineSalas, List<Asiento> listCineAsiento, List<Funcion> listCineFuncion, List<Pelicula> listCinePeliculas) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listCinePersona = listCinePersona;
        this.listCineVenta = listCineVenta;
        this.listCineSalas = listCineSalas;
        this.listCineAsiento = listCineAsiento;
        this.listCineFuncion = listCineFuncion;
        this.listCinePeliculas = listCinePeliculas;
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

    public List<Persona> getListCinePersona() {
        return listCinePersona;
    }

    public void setListCinePersona(List<Persona> listCinePersona) {
        this.listCinePersona = listCinePersona;
    }

    public List<Venta> getListCineVenta() {
        return listCineVenta;
    }

    public void setListCineVenta(List<Venta> listCineVenta) {
        this.listCineVenta = listCineVenta;
    }

    public List<Sala> getListCineSalas() {
        return listCineSalas;
    }

    public void setListCineSalas(List<Sala> listCineSalas) {
        this.listCineSalas = listCineSalas;
    }

    public List<Asiento> getListCineAsiento() {
        return listCineAsiento;
    }

    public void setListCineAsiento(List<Asiento> listCineAsiento) {
        this.listCineAsiento = listCineAsiento;
    }

    public List<Funcion> getListCineFuncion() {
        return listCineFuncion;
    }

    public void setListCineFuncion(List<Funcion> listCineFuncion) {
        this.listCineFuncion = listCineFuncion;
    }

    public List<Pelicula> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Pelicula> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listCinePersona=" + listCinePersona +
                ", listCineVenta=" + listCineVenta +
                ", listCineSalas=" + listCineSalas +
                ", listCineAsiento=" + listCineAsiento +
                ", listCineFuncion=" + listCineFuncion +
                ", listCinePeliculas=" + listCinePeliculas +
                '}';
    }
}
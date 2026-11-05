package uniquindio.edu.co.proyectofinalfx.model;

import java.util.List;

public class Administrador extends Trabajador {
    // relaciones
    private List<Tarifa> listTarifas;
    private List<Sala> listSalasAdministrador;
    private List<Funcion> listFuncionAdministrador;
    private List<Pelicula> listPeliculaAdministrador;

    public Administrador(List<Tarifa> listTarifas, List<Sala> listSalasAdministrador, List<Funcion> listFuncionAdministrador, List<Pelicula> listPeliculaAdministrador) {
        this.listTarifas = listTarifas;
        this.listSalasAdministrador = listSalasAdministrador;
        this.listFuncionAdministrador = listFuncionAdministrador;
        this.listPeliculaAdministrador = listPeliculaAdministrador;
    }

    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    public void setListTarifas(List<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    public List<Sala> getListSalasAdministrador() {
        return listSalasAdministrador;
    }

    public void setListSalasAdministrador(List<Sala> listSalasAdministrador) {
        this.listSalasAdministrador = listSalasAdministrador;
    }

    public List<Funcion> getListFuncionAdministrador() {
        return listFuncionAdministrador;
    }

    public void setListFuncionAdministrador(List<Funcion> listFuncionAdministrador) {
        this.listFuncionAdministrador = listFuncionAdministrador;
    }

    public List<Pelicula> getListPeliculaAdministrador() {
        return listPeliculaAdministrador;
    }

    public void setListPeliculaAdministrador(List<Pelicula> listPeliculaAdministrador) {
        this.listPeliculaAdministrador = listPeliculaAdministrador;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "listTarifas=" + listTarifas +
                ", listSalasAdministrador=" + listSalasAdministrador +
                ", listFuncionAdministrador=" + listFuncionAdministrador +
                ", listPeliculaAdministrador=" + listPeliculaAdministrador +
                '}';
    }
}

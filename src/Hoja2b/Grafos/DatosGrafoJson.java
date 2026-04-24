package Hoja2b.Grafos;

import java.util.List;

public class DatosGrafoJson {
    private List<String> tipos;
    private List<TripletaJson> tripletas;

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    public List<TripletaJson> getTripletas() {
        return tripletas;
    }

    public void setTripletas(List<TripletaJson> tripletas) {
        this.tripletas = tripletas;
    }
}

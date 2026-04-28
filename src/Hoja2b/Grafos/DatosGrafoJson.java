package Hoja2b.Grafos;

import java.util.List;

// Clase que representa los datos del JSON del grafo
public class DatosGrafoJson {

    // Lista de tipos de nodos
    private List<String> tipos;

    // Lista de tripletas (relaciones)
    private List<TripletaJson> tripletas;

    // Getter de tipos
    public List<String> getTipos() {
        return tipos;
    }

    // Setter de tipos
    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    // Getter de tripletas
    public List<TripletaJson> getTripletas() {
        return tripletas;
    }

    // Setter de tripletas
    public void setTripletas(List<TripletaJson> tripletas) {
        this.tripletas = tripletas;
    }
}
package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;


// Clase que representa los datos del JSON del grafo
public class DatosGrafoJson {

    // Array de tipos de nodos
    private String[] tipos;

    // Array de tripletas (relaciones)
    private TripletaJson[] tripletas;

    // Getter de tipos
    public String[] getTipos() {
        return tipos;
    }

    // Setter de tipos
    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }

    // Getter de tripletas
    public TripletaJson[] getTripletas() {
        return tripletas;
    }

    // Setter de tripletas
    public void setTripletas(TripletaJson[] tripletas) {
        this.tripletas = tripletas;
    }
}
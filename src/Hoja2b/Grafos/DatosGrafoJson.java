package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;


// Clase que representa los datos del JSON del grafo
public class DatosGrafoJson {

    // Lista de tipos de nodos
    private ListaSimplementeEnlazada<String> tipos;

    // Lista de tripletas (relaciones)
    private ListaSimplementeEnlazada<TripletaJson> tripletas;

    // Getter de tipos
    public ListaSimplementeEnlazada<String> getTipos() {
        return tipos;
    }

    // Setter de tipos
    public void setTipos(ListaSimplementeEnlazada<String> tipos) {
        this.tipos = tipos;
    }

    // Getter de tripletas
    public ListaSimplementeEnlazada<TripletaJson> getTripletas() {
        return tripletas;
    }

    // Setter de tripletas
    public void setTripletas(ListaSimplementeEnlazada<TripletaJson> tripletas) {
        this.tripletas = tripletas;
    }
}
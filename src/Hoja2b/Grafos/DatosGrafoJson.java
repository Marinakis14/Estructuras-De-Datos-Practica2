package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;


// Clase que representa los datos del JSON del grafo
public class DatosGrafoJson {

    // Lista de tripletas (relaciones)
    private ListaSimplementeEnlazada<TripletaJson> tripletas;

    // Getter de tripletas
    public ListaSimplementeEnlazada<TripletaJson> getTripletas() {
        return tripletas;
    }

    // Setter de tripletas
    public void setTripletas(ListaSimplementeEnlazada<TripletaJson> tripletas) {
        this.tripletas = tripletas;
    }
}
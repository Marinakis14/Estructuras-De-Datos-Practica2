package Hoja2b.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    // Lista que almacena todos los nodos del grafo
    private List<Nodo> nodos;

    // Lista que almacenará las aristas
    private List<Arista> aristas;

    // Constructor: inicializa las listas vacías
    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    // Añade un nodo al grafo si no está ya incluido
    public void agregarNodo(Nodo nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public void agregarArista(String idOrigen, String predicado, String idDestino) {

        Nodo origen = new Nodo(idOrigen);
        Nodo destino = new Nodo(idDestino);

        agregarNodo(origen);
        agregarNodo(destino);

        Arista arista = new Arista(origen, predicado, destino);
        aristas.add(arista);
    }

    public void imprimirGrafo() {
        for (Arista arista : aristas) {
            System.out.println(arista);
        }
    }
}
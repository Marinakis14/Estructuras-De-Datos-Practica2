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

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos){
        this.nodos = nodos;
    }

    public List<Arista> getAristas(){
        return aristas;
    }
    public void setAristas(List<Arista> aristas){
        this.aristas = aristas;
    }


    // Añade un nodo al grafo si no está ya incluido
    public void addNodo(Nodo nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public void addArista(Arista arista){
        addNodo(arista.getOrigen());
        addNodo(arista.getDestino());
        aristas.add(arista);
    }

    public void imprimirGrafo() {
        for (Arista arista : aristas) {
            System.out.println(arista);
        }
    }
}
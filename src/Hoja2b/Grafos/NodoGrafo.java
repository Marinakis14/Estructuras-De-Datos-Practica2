package Hoja2b.Grafos;

import java.util.Objects;

// Nodo del grafo identificado por un id

public class NodoGrafo<T extends Comparable<T>> implements Comparable<NodoGrafo<T>> {

    // Identificador del nodo
    private String id;

    // Constructor
    public NodoGrafo(String id) {
        this.id = id;
    }

    // Getter y setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Compara nodos por id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NodoGrafo)) {
            return false;
        }

        NodoGrafo<T> nodo = (NodoGrafo<T>) obj;

        if (id == null && nodo.id == null) {
            return true;
        }
        if (id == null || nodo.id == null) {
            return false;
        }

        return id.equals(nodo.id);
    }

    // Devuelve el id como texto
    @Override
    public String toString() {
        return id;
    }

    @Override
    public int compareTo(NodoGrafo otro) {
        return CharSequence.compare(this.id, otro.id);
    }
}
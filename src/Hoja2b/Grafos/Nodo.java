package Hoja2b.Grafos;

import java.util.Objects;

// Nodo del grafo identificado por un id
public class Nodo {

    // Identificador del nodo
    private String id;

    // Constructor
    public Nodo(String id) {
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
        if (!(obj instanceof Nodo)) {
            return false;
        }
        Nodo nodo = (Nodo) obj;
        return Objects.equals(id, nodo.id);
    }

    // Genera hash basado en id
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    // Devuelve el id como texto
    @Override
    public String toString() {
        return id;
    }
}
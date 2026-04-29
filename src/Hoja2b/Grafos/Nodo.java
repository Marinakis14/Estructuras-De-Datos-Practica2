package Hoja2b.Grafos;

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
}
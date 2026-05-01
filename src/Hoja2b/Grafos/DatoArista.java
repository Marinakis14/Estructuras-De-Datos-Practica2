package Hoja2b.Grafos;

public class DatoArista implements Comparable<DatoArista> {
    private String dato;

    public DatoArista(String predicado) {
        this.dato = predicado;
    }

    public String getDato() { return dato; }

    public boolean esValido() {
        return dato != null && !dato.isEmpty();
    }

    public String toString() {
        return "--(" + dato + ")-->";
    }

    // Para poder hacer una lista de aristas deben ser comparables
    @Override
    public int compareTo(DatoArista o) {
        return 0;
    }
}

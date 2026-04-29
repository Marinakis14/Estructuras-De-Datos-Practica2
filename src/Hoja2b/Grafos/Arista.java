package Hoja2b.Grafos;

// Arista que conecta dos nodos con una relación
public class Arista<T extends Comparable<T>> implements Comparable<Arista<T>> {

    // Nodo inicial
    private NodoGrafo<T> origen;

    // Tipo de relación
    private String predicado;

    // Nodo final
    private NodoGrafo<T> destino;

    // Constructor
    public Arista(NodoGrafo<T> origen, String predicado, NodoGrafo<T> destino) {
        this.origen = origen;
        this.predicado = predicado;
        this.destino = destino;
    }

    // Getters y setters
    public NodoGrafo<T> getOrigen(){
        return origen;
    }
    public void setOrigen(NodoGrafo<T> origen){
        this.origen = origen;
    }
    public String getPredicado(){
        return predicado;
    }
    public void setPredicado(String predicado){
        this.predicado = predicado;
    }
    public NodoGrafo<T> getDestino(){
        return destino;
    }
    public void setDestino(NodoGrafo<T> destino){
        this.destino = destino;
    }

    // Devuelve la arista en formato texto
    @Override
    public String toString(){
        return  origen + " ---[" + predicado + "]---> " + destino;
    }

    @Override
    public int compareTo(Arista o) {
        return 0;
    }
}
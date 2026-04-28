package Hoja2b.Grafos;

// Arista que conecta dos nodos con una relación
public class Arista {

    // Nodo inicial
    private Nodo origen;

    // Tipo de relación
    private String predicado;

    // Nodo final
    private Nodo destino;

    // Constructor
    public Arista(Nodo origen, String predicado, Nodo destino) {
        this.origen = origen;
        this.predicado = predicado;
        this.destino = destino;
    }

    // Getters y setters
    public Nodo getOrigen(){
        return origen;
    }
    public void setOrigen(Nodo origen){
        this.origen = origen;
    }
    public String getPredicado(){
        return predicado;
    }
    public void setPredicado(String predicado){
        this.predicado = predicado;
    }
    public Nodo getDestino(){
        return destino;
    }
    public void setDestino(Nodo destino){
        this.destino = destino;
    }

    // Devuelve la arista en formato texto
    @Override
    public String toString(){
        return  origen + " ---[" + predicado + "]---> " + destino;
    }
}
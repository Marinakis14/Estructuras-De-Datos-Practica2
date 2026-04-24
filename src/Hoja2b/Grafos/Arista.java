package Hoja2b.Grafos;

public class Arista {
    private Nodo origen;
    private String predicado;
    private Nodo destino;

    public Arista(Nodo origen, String predicado, Nodo destino) {
        this.origen = origen;
        this.predicado = predicado;
        this.destino = destino;
    }

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

    @Override
    public String toString(){
        return  origen + " ---[" + predicado + "]---> " + destino;
    }
}

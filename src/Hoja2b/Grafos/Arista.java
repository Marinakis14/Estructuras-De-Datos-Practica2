package Hoja2b.Grafos;

// Arista que conecta dos nodos con una relación
public class Arista<DN, DA> implements Comparable<Arista<DN, DA>> {

    // identificador
    private long id;

    // Nodo inicial
    private NodoGrafo<DN> origen;

    // Tipo de relación
    private DA predicado;

    // Nodo final
    private NodoGrafo<DN> destino;

    // Constructor
    protected Arista(long id, NodoGrafo<DN> origen, DA predicado, NodoGrafo<DN> destino) {
        this.id = id;
        this.origen = origen;
        this.predicado = predicado;
        this.destino = destino;
    }

    // Getters y setters
    protected long getId() { return id; }

    protected NodoGrafo<DN> getOrigen(){
        return origen;
    }

    protected void setOrigen(NodoGrafo<DN> origen){
        this.origen = origen;
    }

    protected DA getPredicado(){
        return predicado;
    }

    public void setPredicado(DA predicado) {
        this.predicado = predicado;
    }

    public NodoGrafo<DN> getDestino() {
        return destino;
    }

    public void setDestino(NodoGrafo<DN> destino) {
        this.destino = destino;
    }

    // Devuelve la arista en formato texto
    @Override
    public String toString(){
        return "  " + origen.getId() + " --(" + predicado + ")--> " + destino.getId();
    }

    @Override
    public int compareTo(Arista o) {
        return Long.compare(this.id, o.id);
    }
}
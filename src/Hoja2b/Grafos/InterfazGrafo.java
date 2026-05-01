package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public interface InterfazGrafo<DN, DA> {
    // -- Getters y Setters --
    ListaSimplementeEnlazada<NodoGrafo<DN>> getNodos();

    ListaSimplementeEnlazada<Arista<DN, DA>> getAristas();

    ListaSimplementeEnlazada<String> getTipos();

    // -- Metodos para añadir cosas al grafo --
    void addNodo(NodoGrafo<DN> nodo);

    void addArista(Arista<DN, DA> arista);

    void addArista(NodoGrafo<DN> origen, DA predicado, NodoGrafo<DN> destino);

    void addTipo(String tipo);

    // -- Metodos para trabajar con el grafo --
    NodoGrafo<DN> buscarNodoPorId(long id);

    NodoGrafo<DN> buscarNodoPorNombre(String nombreBusqueda);

    NodoGrafo<DN> buscarNodoPorTipo(String tipo);

    int NumeroNodosTipo(String tipo);

    ListaSimplementeEnlazada<String> getTiposDeNodos();

    ListaSimplementeEnlazada<NodoGrafo<DN>> getVecinos(NodoGrafo<DN> nodo);

    ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimo(long idOrigen, long idDestino);

    ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimoAmbosSentidos(long idOrigen, long idDestino);

    ListaSimplementeEnlazada<NodoGrafo<DN>> getVecinosNoDirigidos(NodoGrafo<DN> nodo);

    boolean esDisjunto();

    ListaSimplementeEnlazada<NodoGrafo<DN>> getDestinosPorPredicado(NodoGrafo<DN> origen, DA predicado);

    ListaSimplementeEnlazada<NodoGrafo<DN>> getOrigenesPorPredicadoYDestino(DA predicado, NodoGrafo<DN> destino);

    ListaSimplementeEnlazada<NodoGrafo<DN>> personasMismaCiudadQue(String tipoPersona);

    ListaSimplementeEnlazada<NodoGrafo<DN>> fisicosMismaCiudadQue(String tipoPersona);

    ListaSimplementeEnlazada<NodoGrafo<DN>> lugaresNacimientoPremiosNobel();

    String toString();
}

package Hoja2a.arbolesbinarios;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public interface InterfazArbol <T extends Comparable<T>> {

    // --- Getters y Setters ---
    Nodo<T> getRaiz();
    void setRaiz(Nodo<T> raiz);

    // --- Operaciones del Árbol ---
    boolean isEmpty();
    Nodo<T> getIzquierda(Nodo<T> nodo);
    Nodo<T> getDerecha(Nodo<T> nodo);
    T getDato(Nodo<T> nodo);
    ListaSimplementeEnlazada<T> getDatos(Nodo<T> nodo);
    int getRepeticionesNodo(Nodo<T> nodo);
    int getRepeticionesNodo(T dato);
    int getGrado();
    boolean isNodoInArbol(T dato);
    int getAltura();
    int getNivel(T dato);
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaDatosNivel(int nivel);
    ListaSimplementeEnlazada<Nodo<T>> getListaNodosNivel(int nivel);
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getNodosHoja();
    Nodo<T> getNodoMasPequeño();
    Nodo<T> getNodoMasGrande();
    void ADD(T dato);
    void DEL(T dato);
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getCaminoDatos(T dato);
    ListaSimplementeEnlazada<Nodo<T>> getCaminoNodos(T dato);

    // --- Estructura del Árbol ---
    boolean isArbolHomogeneo1();
    boolean isArbolHomogeneo2();
    boolean isArbolCompleto();
    boolean isArbolCasiCompleto();
    boolean isEquilibrado();
    ArbolBinarioDeBusqueda<T> getSubArbolIzquierda();
    ArbolBinarioDeBusqueda<T> getSubArbolDerecha();

    // --- Recorridos ---
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaOrdenCentral();
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPreOrden();
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPostOrden();
}
package Hoja2a.arbolesbinarios;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public interface InterfazArbol <T extends Comparable<T>> {

    // --- Getters y Setters ---
    Nodo<T> getRaiz();
    void setRaiz(Nodo<T> raiz);

    // --- Operaciones del Árbol ---
    boolean isEmpty();
    int getGrado();
    boolean isNodoInArbol(T dato);
    int getAltura();
    int getNivel(T dato);
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaDatosNivel(int nivel);
    void ADD(T dato);
    void DEL(T dato);
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getCamino();

    // --- Estructura del Árbol ---
    boolean isArbolHomogeneo1();
    boolean isArbolHomogeneo2();
    void isArbolCompleto();
    void isArbolCasiCompleto();
    boolean isEquilibrado();
    ArbolBinarioDeBusqueda<T> getSubArbolIzquierda();
    ArbolBinarioDeBusqueda<T> getSubArbolDerecha();

    // --- Recorridos ---
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaOrdenCentral();
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPreOrden();
    ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPostOrden();
}
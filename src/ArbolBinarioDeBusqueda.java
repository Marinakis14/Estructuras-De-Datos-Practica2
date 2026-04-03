import java.util.ArrayList;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {

    // variables privadas de la clase
    private Nodo<T> raiz;

    // Constructores
    ArbolBinarioDeBusqueda() { // al principio el arbol esta vacio
        this.raiz = null;
    }

    ArbolBinarioDeBusqueda(Nodo<T> raiz) { // para crear un arbol con una raiz dada
        this.raiz = raiz;
    }

    ArbolBinarioDeBusqueda(T dato) { // para crear un arbol con un elemento dado
        Nodo<T> nuevaRaiz = new Nodo<>(dato);
        this.raiz = nuevaRaiz;
    }

    // Getters y Setters para la variable raiz
    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Todas las "preguntas" a las que puede responder el arbol
     */

    // Metodo para obtener el grado del arbol de manera recursiva
    protected int getGrado() {
        if (raiz == null) { // si el arbol esta vacio el grado es 0
            return 0;
        } else {
            return raiz.getGrado();
        }
    }

    // Metodo para obtener la profundidad o altura del arbol de manera recursiva
    protected int getAltura() {
        if (raiz == null) { // si el arbol estaba vacio la altura es 0
            return 0;
        } else {
            return raiz.getAltura();
        }
    }

    /**
     * Operaciones que se pueden hacer sobre el arbol
     */

    // Metodo para añadir elemento al arbol
    protected void ADD(T dato) {
        if (raiz == null) { // si el arbol estaba vacio se crea un nuevo arbol con el elemento dado como raiz
            ArbolBinarioDeBusqueda<T> nuevoArbol = new ArbolBinarioDeBusqueda<>(dato);
        } else { // si ya habia algun elemento lo añadimos a traves de la clase nodo
            raiz.add(dato);
        }
    }

    // Metodo para obtener el subarbol de la parte izquierda del arbol principal
    protected ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        Nodo<T> nuevaRaiz = raiz.getIzquierda();
        ArbolBinarioDeBusqueda subArbol = new ArbolBinarioDeBusqueda(nuevaRaiz);
        return subArbol;
    }

    // Metodo para obtener el subarbol de la parte derecha del arbol principal
    protected ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        Nodo<T> nuevaRaiz = raiz.getDerecha();
        ArbolBinarioDeBusqueda subArbol = new ArbolBinarioDeBusqueda(nuevaRaiz);
        return subArbol;
    }

    /**
     * 3 tipos de listas de datos para representar los datos que contiene el arbol con distinto orden
     */

    // Muestra por pantalla los elementos del arbol con orden central
    public ArrayList<T> getListaOrdenCentral() { // mostrando los elementos con orden central
        ArrayList<T> elementosArbol = new ArrayList<>();
        raiz.ordenCentral(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con preorden
    public ArrayList<T> getListaPreOrden() { // mostrando los elementos con orden central
        ArrayList<T> elementosArbol = new ArrayList<>();
        raiz.preOrden(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con postorden
    public ArrayList<T> getListaPostOrden() { // mostrando los elementos con orden central
        ArrayList<T> elementosArbol = new ArrayList<>();
        raiz.postOrden(elementosArbol);
        return elementosArbol;
    }
}

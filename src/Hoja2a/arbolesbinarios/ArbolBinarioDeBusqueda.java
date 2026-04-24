package Hoja2a.arbolesbinarios;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> implements InterfazArbol<T> {

    // variables privadas de la clase
    private Nodo<T> raiz;

    // Constructores
    ArbolBinarioDeBusqueda() { // al principio el arbol esta vacio
        this.raiz = null;
    }

    ArbolBinarioDeBusqueda(Nodo<T> raiz) { // para crear un arbol con una raiz dada
        if (raiz == null) {
            this.raiz = raiz;
        }
    }

    ArbolBinarioDeBusqueda(T dato) { // para crear un arbol con un elemento dado
        if (dato == null) {
            Nodo<T> nuevaRaiz = new Nodo<>(dato);
            this.raiz = nuevaRaiz;
        }
    }

    // Getters y Setters para la variable raiz
    @Override
    public Nodo<T> getRaiz() {
        return raiz;
    }

    @Override
    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Todas las "preguntas" a las que puede responder el arbol
     */

    // Metodo para saber si el arbol esta vacio o no
    @Override
    public boolean isEmpty() {
        return raiz == null; // devuelve true si el arbol esta vacio
    }

    // Metodo para obtener el grado del arbol de manera recursiva
    @Override
    public int getGrado() {
        if (raiz == null) { // si el arbol esta vacio el grado es 0
            return 0;
        } else {
            return raiz.getGrado();
        }
    }

    // Metodo para obtener si un nodo pertenece a un arbol o no, (se aplica a la raiz del arbol)
    @Override
    public boolean isNodoInArbol(T dato) {
        if (raiz == null || dato == null) { // si el arbol esta vacio el nodo no puede pertenecer al arbol
            return false;
        } else {
            return raiz.isNodoInArbol(dato);
        }
    }

    // Metodo para obtener la profundidad o altura del arbol de manera recursiva
    @Override
    public int getAltura() {
        if (raiz == null) { // si el arbol estaba vacio la altura es 0
            return -1;
        } else {
            return raiz.getAltura();
        }
    }

    // Metodo para obtener el nivel de un nodo
    @Override
    public int getNivel(T dato) {
        if (raiz == null || dato == null) { // si el arbol estaba vacio el nivel es 0
            return -1;
        } else {
            return raiz.getNivel(dato);
        }
    }

    // Metodo para obtener una lista de todos los datos de un nivel dado
    @Override
    public ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaDatosNivel(int nivel) {
        // Creamos una lista para devolver
        ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementos = new ListaSimplementeEnlazada<>();

        // Comprobamos que el nivel dado es valido
        if (nivel > raiz.getAltura() || nivel < 0) {
            return null;
        } else if (nivel == 0) { // el nivel 0 del arbol es la raiz
            elementos.addEnd(raiz.getDatos());
            return elementos;
        } else {
            // Utilizamos el metodo que tenemos en la clase Hoja2a.arbolesbinarios.Nodo para añadir todos los elementos del nivel dado
            raiz.getListaDatosNivel(elementos, nivel);
            return elementos;
        }
    }

    // Metodo que devuelve si el arbol es homogeneo o no con recursividad a traves de los subarboles
    @Override
    public boolean isArbolHomogeneo1() {
        int numeroHijos = raiz.numeroHijos();
        // queremos ver que todos los subarboles tienen el mismo numero de hijos
        ArbolBinarioDeBusqueda<T> arbolActual = new ArbolBinarioDeBusqueda<>(raiz);

        // creamos variables para ver si los subarboles tienen el mismo numero de hijos o no
        boolean izquierda = true;
        boolean derecha = true;

        if (numeroHijos != 0) { // si es 0 y no tiene hijos no hay problema
            ArbolBinarioDeBusqueda<T> subarbolIzquierda = arbolActual.getSubArbolIzquierda();
            if (subarbolIzquierda.getRaiz() != null) { // comprobar que el subarbol no este vacio
                int HijosIzquierda = subarbolIzquierda.getRaiz().numeroHijos();
                if (HijosIzquierda == 0) { // si es una hoja y no tiene hijos
                    izquierda = true;
                } else if (numeroHijos != HijosIzquierda) { // ya no es homogeneo
                    izquierda = false;
                } else { // si el numero de hijos coincide repetimos el proceso
                    izquierda = subarbolIzquierda.isArbolHomogeneo1();
                }
            }

            // lo mismo para el subarbol derecho
            ArbolBinarioDeBusqueda<T> subarbolDerecha = arbolActual.getSubArbolDerecha();
            if (subarbolDerecha.getRaiz() != null) { // comprobar que el subarbol no este vacio
                int HijosDerecha = subarbolDerecha.getRaiz().numeroHijos(); // miramos cuantos hijos tiene el subarbol
                if (HijosDerecha == 0) { // si es una hoja y no tiene hijos
                    derecha = true;
                } else if (numeroHijos != HijosDerecha) { // ya no es homogeneo
                    derecha = false;
                } else { // si el numero de hijos coincide repetimos el proceso
                    derecha = subarbolDerecha.isArbolHomogeneo1();
                }
            }
        }
        // para que sea homogeneo ambas ramas tienen que ser "true" por lo que tenemos la estructura de una puerta and
        return (izquierda && derecha);
    }

    // Metodo que devuelve si el arbol es homogeneo o no con recursividad a traves de los nodos
    @Override
    public boolean isArbolHomogeneo2() {
        // ultilizamos el metodo recursivo que tenemos en la clase Hoja2a.arbolesbinarios.Nodo
        return raiz.isArbolHomogeneo(raiz);
    }

    // Metodo que devuelve si el arbol es completo o no
    @Override
    public void isArbolCompleto() {

    }

    // Metodo que devuelve si el arbol es casi completo o no
    @Override
    public void isArbolCasiCompleto() {

    }

    // Metodo para saber si un arbol esta equilibrado o no
    @Override
    public boolean isEquilibrado() {
        int alturaIzq = 0;
        int alturaDer = 0;

        // calcular altura izquierda
        if (getSubArbolIzquierda() != null) {
            alturaIzq = getSubArbolIzquierda().getAltura();
        }

        // calcular altura derecha
        if (getSubArbolDerecha() != null) {
            alturaDer = getSubArbolDerecha().getAltura();
        }

        // variable para calcular el grado de desequilibrio
        // si desequilibrio > 1 -> esta desequilibrado
        int desequilibrio = Math.abs(alturaIzq - alturaDer);

        // Caso base
        if (desequilibrio > 1) {
            return false;
        }
        // resto de casos
        // miramos rama de la izquierda
        if (getSubArbolIzquierda() != null) {
            if (!getSubArbolIzquierda().isEquilibrado()) { // si el arbol izquierdo esta desequilibrado
                return false;
            }
        }
        // miramos rama de la derecha
        if (getSubArbolDerecha() != null) {
            if (!getSubArbolDerecha().isEquilibrado()) { // si el arbol derecho esta desequilibrado
                return false;
            }
        }
        // si todo esta bien
        return true;
    }

    /**
     * Operaciones que se pueden hacer sobre el arbol
     */

    // Metodo para añadir elemento al arbol
    @Override
    public void ADD(T dato) {
        if (dato == null) { // si no se introduce un dato valido
            return;
        }
        if (raiz == null ) { // si el arbol estaba vacio se crea un nuevo arbol con el elemento dado como raiz
            this.raiz = new Nodo<>(dato);
        } else { // si ya habia algun elemento lo añadimos a traves de la clase nodo
            raiz.ADD(dato);
        }
    }

    @Override
    public void DEL(T dato) {

    }

    @Override
    public ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getCamino() {
        return null;
    }

    // Metodo para obtener el subarbol de la parte izquierda del arbol principal
    @Override
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        if (raiz.getIzquierda() != null) {
            Nodo<T> nuevaRaiz = raiz.getIzquierda();
            ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>(nuevaRaiz);
            return subArbol;
        } else {
            return null;
        }
    }

    // Metodo para obtener el subarbol de la parte derecha del arbol principal
    @Override
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        if (raiz.getDatos() != null) {
            Nodo<T> nuevaRaiz = raiz.getDerecha();
            ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>(nuevaRaiz);
            return subArbol;
        } else {
            return null;
        }
    }

    /**
     * 3 tipos de listas de datos para representar los datos que contiene el arbol con distinto orden
     */

    // Muestra por pantalla los elementos del arbol con orden central
    public ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaOrdenCentral() { // mostrando los elementos con orden central
        ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol = new ListaSimplementeEnlazada<>();
        raiz.ordenCentral(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con preorden
    public ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPreOrden() { // mostrando los elementos con orden central
        ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol = new ListaSimplementeEnlazada<>();
        raiz.preOrden(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con postorden
    public ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> getListaPostOrden() { // mostrando los elementos con orden central
        ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol = new ListaSimplementeEnlazada<>();
        raiz.postOrden(elementosArbol);
        return elementosArbol;
    }
}

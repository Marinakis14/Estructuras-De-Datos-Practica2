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

    // Metodo para obtener si un nodo pertenece a un arbol o no, (se aplica a la raiz del arbol)
    protected boolean isNodoInArbol(T dato) {
        if (raiz == null || dato == null) { // si el arbol esta vacio el nodo no puede pertenecer al arbol
            return false;
        } else {
            return raiz.isNodoInArbol(dato);
        }
    }

    // Metodo para obtener la profundidad o altura del arbol de manera recursiva
    protected int getAltura() {
        if (raiz == null) { // si el arbol estaba vacio la altura es 0
            return -1;
        } else {
            return raiz.getAltura();
        }
    }

    // Metodo para obtener el nivel de un nodo
    protected int getNivel(T dato) {
        if (raiz == null) { // si el arbol estaba vacio el nivel es 0
            return -1;
        } else {
            return raiz.getNivel(dato);
        }
    }

    // Metodo para obtener una lista de todos los datos de un nivel dado
    protected ArrayList<ArrayList<T>> getListaDatosNivel(int nivel) {
        // Creamos una lista para devolver
        ArrayList<ArrayList<T>> elementos = new ArrayList<>();

        // Comprobamos que el nivel dado es valido
        if (nivel > raiz.getAltura() || nivel < 0) {
            return null;
        } else if (nivel == 0) { // el nivel 0 del arbol es la raiz
            elementos.add(raiz.getDatos());
            return elementos;
        } else {
            // Utilizamos el metodo que tenemos en la clase Nodo para añadir todos los elementos del nivel dado
            raiz.getListaDatosNivel(elementos, nivel);
            return elementos;
        }
    }

    // Metodo que devuelve si el arbol es homogeneo o no con recursividad a traves de los subarboles
    protected boolean isArbolHomogeneo1() {
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
    protected boolean isArbolHomogeneo2() {
        // ultilizamos el metodo recursivo que tenemos en la clase Nodo
        return raiz.isArbolHomogeneo(raiz);
    }

    // Metodo que devuelve si el arbol es completo o no
    protected void isArbolCompleto() {

    }

    // Metodo que devuelve si el arbol es casi completo o no
    protected void isArbolCasiCompleto() {

    }

    /**
     * Operaciones que se pueden hacer sobre el arbol
     */

    // Metodo para añadir elemento al arbol
    protected void ADD(T dato) {
        if (raiz == null) { // si el arbol estaba vacio se crea un nuevo arbol con el elemento dado como raiz
            ArbolBinarioDeBusqueda<T> nuevoArbol = new ArbolBinarioDeBusqueda<>(dato);
        } else { // si ya habia algun elemento lo añadimos a traves de la clase nodo
            raiz.ADD(dato);
        }
    }

    // Metodo para obtener el subarbol de la parte izquierda del arbol principal
    protected ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        if (raiz.getIzquierda() != null) {
            Nodo<T> nuevaRaiz = raiz.getIzquierda();
            ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>(nuevaRaiz);
            return subArbol;
        } else {
            return null;
        }
    }

    // Metodo para obtener el subarbol de la parte derecha del arbol principal
    protected ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
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
    public ArrayList<ArrayList<T>> getListaOrdenCentral() { // mostrando los elementos con orden central
        ArrayList<ArrayList<T>> elementosArbol = new ArrayList<>();
        raiz.ordenCentral(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con preorden
    public ArrayList<ArrayList<T>> getListaPreOrden() { // mostrando los elementos con orden central
        ArrayList<ArrayList<T>> elementosArbol = new ArrayList<>();
        raiz.preOrden(elementosArbol);
        return elementosArbol;
    }

    // Muestra por pantalla los elementos del arbol con postorden
    public ArrayList<ArrayList<T>> getListaPostOrden() { // mostrando los elementos con orden central
        ArrayList<ArrayList<T>> elementosArbol = new ArrayList<>();
        raiz.postOrden(elementosArbol);
        return elementosArbol;
    }
}

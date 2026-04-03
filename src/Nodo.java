import java.util.ArrayList;

public class Nodo<T extends Comparable<T>> {

    // variables privadas de la clase
    private Nodo<T> izquierda;
    private Nodo<T> derecha;
    private T dato;

    // Constructor
    Nodo() {  // el primer nodo estera vacio (nodo raiz)
        izquierda = null;
        derecha = null;
        dato = null;
    }

    Nodo(Nodo<T> izquierda, Nodo<T> derecha, T dato) { // constructor dados todos los parametros
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.dato = dato;
    }

    Nodo(T dato) { // constructor dados solo el dato del nodo (para los "nodos hoja" del arbol)
        this.dato = dato;
    }

    // Metodos
    // Getter para el nodo izquierda
    protected Nodo<T> getIzquierda() {
        return izquierda;
    }

    // Getter para el nodo derecha
    protected Nodo<T> getDerecha() {
        return derecha;
    }

    // Getter para el nodo dato
    protected T getDato() {
        return dato;
    }

    // Setter para el nodo izquierda
    protected void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    // Setter para el nodo derecha
    protected void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }

    // Setter para el nodo dato
    protected void setDato(T dato) {
        this.dato = dato;
    }

    // Metodo para obtener el grado del arbol de manera recursiva
    protected int getGrado() {
        int gradoactual = 0;

        if (izquierda != null && derecha != null) {
            // en el momento en el que algun nodo tenga dos hijos el grado es 2 porque el arbol es binario
            gradoactual = 2;
        } else if (izquierda != null || derecha != null) {
            // si tenemos un hijo el grado puede ser 1 o 2, si mas alante hay algun nodo con 2 hijos
            gradoactual = 1;
        }
        // miramos el grado de la rama izquierda
        int gradoizquierda = 0;
        if (izquierda != null) {
            gradoizquierda = izquierda.getGrado(); // miramos si la izquierda hay algun nodo con dos hijos
        }
        // miramos el grado de la rama derecha
        int gradoderecha = 0;
        if (derecha != null) {
            gradoderecha = derecha.getGrado(); // miramos si a la derecha hay algun nodo con dos hijos
        }
        // calculamos el grado maximo de la rama derecha y de la rama izquierda y la comparamos con el grado del nodo
        // actual hasta llegar a la raiz
        return Math.max(gradoactual, Math.max(gradoizquierda, gradoderecha));
        // si no hay ningun nodo a parte del nodo raiz salta todos los if y el grado es 0 porque no hay ningun nodo hijo
    }

    // Metodo para obtener la profundidad o altura del arbol de manera recursiva
    protected int getAltura() {
        int profundidadIzquierda = 0;
        int profundidadDerecha = 0;

        if (izquierda != null) {
            profundidadIzquierda = izquierda.getAltura();
        }
        if (derecha != null) {
            profundidadDerecha = derecha.getAltura();
        }
        return Math.max(profundidadIzquierda, profundidadDerecha) + 1;
    }

    // Metodo para añadir un nuevo elemento al arbol de manera recursiva
    protected void add(T dato) {
        if (dato.compareTo(this.dato) < 0) {
            if (izquierda == null) {
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                izquierda = nuevoNodo;
                return;
            }
            izquierda.add(dato);
        } else if (dato.compareTo(this.dato) > 0) {
            if (derecha == null) {
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                derecha = nuevoNodo;
                return;
            }
            derecha.add(dato);
        }
        // si el dato es igual que un dato ya existente en el arbol no hace falta añadirlo
    }

    // Metodo para obtener los elementos con orden central
    protected void ordenCentral(ArrayList<T> elementosArbol) {
        if (izquierda != null) {
            izquierda.ordenCentral(elementosArbol);
        }
        elementosArbol.add(dato);
        if (derecha != null) {
            derecha.ordenCentral(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con preorden
    protected void preOrden(ArrayList<T> elementosArbol) {
        elementosArbol.add(dato);
        if (izquierda != null) {
            izquierda.preOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.preOrden(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con postorden
    protected void postOrden(ArrayList<T> elementosArbol) {
        if (izquierda != null) {
            izquierda.postOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.postOrden(elementosArbol);
        }
        elementosArbol.add(dato);
    }

    /**
     * Devuelve una cadena visual del nodo
     */
    public String toString() {
        if (izquierda != null && derecha != null) {
            return "(TIENE IZQUIERDA) <- (NODO= " + dato + " ) -> (TIENE DERECHA)";
        } else if (izquierda != null && derecha == null) {
            return "(TIENE IZQUIERDA) <- (NODO= " + dato + " ) -/->";
        } else if (izquierda == null && derecha != null) {
            return "<-/- (NODO= " + dato + " ) -> (TIENE DERECHA)";
        } else {
            return "<-/- (NODO= " + dato + " ) -/->";
        }
    }
}

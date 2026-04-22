import java.util.ArrayList;

public class Nodo<T extends Comparable<T>> {

    // variables privadas de la clase
    private Nodo<T> izquierda;
    private Nodo<T> derecha;
    private ArrayList<T> datos;
    // Vamos a utilizar una lista de datos en vez de un dato unico para poder almacenar varias veces el mismo dato
    // asi por ejemplo si introducimos un mismo elemento varias veces podemos saber cuantas veces lo hemos metido

    // Constructor
    Nodo() {  // el primer nodo estera vacio (nodo raiz)
        this.izquierda = null;
        this.derecha = null;
        this.datos = new ArrayList<>(); // creamos una lista vacia
    }

    Nodo(Nodo<T> izquierda, Nodo<T> derecha, T dato) { // constructor dados todos los parametros
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.datos = new ArrayList<>();
        this.datos.add(dato);
    }

    Nodo(T dato) { // constructor dados solo el dato del nodo (para los "nodos hoja" del arbol)
        this.datos = new ArrayList<>();
        this.datos.add(dato);
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
    protected ArrayList<T> getDatos() {
        return datos;
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

    // Metodo para obtener si un nodo pertenece a un arbol o no, (se aplica a la raiz del arbol)
    protected boolean isNodoInArbol(T dato) {
        // Caso 'base' = encontramos el nodo
        if (this.getDatos().get(0).equals(dato)) { // lo comparamos con un elemento de la lista
            return true;
        }

        // Resto de casos con recursividad
        // Buscar en subárbol izquierdo
        if (izquierda != null && izquierda.isNodoInArbol(dato)) {
            return true;
        }

        // Buscar en subárbol derecho
        if (derecha != null && derecha.isNodoInArbol(dato)) {
            return true;
        }

        return false;
    }

    // Metodo para obtener el nivel de un nodo
    protected int getNivel(T dato) {
        // Hay que comprobar primero si el dato esta en el arbol o no
        if (isNodoInArbol(dato)) {

            // Caso base, encontramos el nodo que queremos
            if (this.getDatos().get(0).equals(dato)) {
                return 1;
            }

            // Resto de casos con recursividad
            if (izquierda != null && izquierda.isNodoInArbol(dato)) { // Si el nodo esta en el subarbol izquierdo nos metemos ahi
                return izquierda.getNivel(dato) + 1;
            }
            if (derecha != null && derecha.isNodoInArbol(dato)) { // Si el nodo esta en el subarbol derecho nos metemos ahi
                return derecha.getNivel(dato) + 1;
            }
        }
        // Si el dato no esta en el arbol devolveremos -1
        return -1;
    }

    // Metodo para añadir todos los elementos de un cierto nivel del arbol a una lista dada
    protected void getListaDatosNivel(ArrayList<ArrayList<T>> elementosArbol, int nivel) {
        // Caso base
        if (nivel == 0) {
            elementosArbol.add(datos);
            // cuando el nivel sea 0 entonces es el nivel que buscamos
            return; // porque ya no sirve de nada seguir bajando por el arbol si ya hemos llegado al nivel que buscamos
        }

        // Resto de casos con recursividad
        if (nivel > 0) {
            if (izquierda != null) {
                izquierda.getListaDatosNivel(elementosArbol, nivel - 1);
                // al subir un nivel en el arbol el nivel que buscamo esta un escalon mas cerca y por eso restamos 1
                // cuando el nivel sea 0 entonces es el nivel que buscamos
            }
            if (derecha != null) {
                // igual que hemos hecho con las ramas de la izquierda
                derecha.getListaDatosNivel(elementosArbol, nivel - 1);
            }
        }
    }

    // Metodo que devuelve un numero en funcion de los nodos hijos que tiene
    protected int numeroHijos() {
        int numero = 0;
        if (izquierda != null) {
            numero += 1;
        }
        if (derecha != null) {
            numero += 1;
        }
        return numero;
    }

    // Metodo que devuelve si el arbol es homogeneo o no con recursividad a traves de los nodos
    protected boolean isArbolHomogeneo(Nodo<T> nodoActual) {
        int hijos = nodoActual.numeroHijos();

        // Caso base
        if (hijos == 0) { // si el nodo es una hoja entonces no hay problema
            return true;
        }

        // Resto de casos con recursividad
        boolean ramaIzquierda = true;
        boolean ramaDerecha = true;

        if (nodoActual.izquierda != null) {
            int hijosIzquierda = nodoActual.izquierda.numeroHijos();
            if (hijos == hijosIzquierda || hijosIzquierda == 0) {
                ramaIzquierda = nodoActual.izquierda.isArbolHomogeneo(nodoActual.izquierda);
            } else {
                ramaIzquierda = false;
            }
        }
        if (nodoActual.derecha != null) {
            int hijosDerecha = nodoActual.derecha.numeroHijos();
            if (hijos == hijosDerecha || hijosDerecha == 0) {
                ramaDerecha = nodoActual.derecha.isArbolHomogeneo(nodoActual.derecha);
            } else {
                ramaDerecha = false;
            }
        }
        return (ramaIzquierda && ramaDerecha);
    }

    // Metodo para añadir un nuevo elemento al arbol de manera recursiva
    protected void ADD(T dato) {
        if (dato.compareTo(this.datos.get(0)) < 0) { // Lo comparamos con el primer elemento de la lista de datos porque son todos iguales
            if (izquierda == null) {
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                izquierda = nuevoNodo;
                return;
            }
            izquierda.ADD(dato);
        } else if (dato.compareTo(this.datos.get(0)) > 0) { // Lo comparamos con el primer elemento de la lista de datos porque son todos iguales
            if (derecha == null) {
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                derecha = nuevoNodo;
                return;
            }
            derecha.ADD(dato);
        } else if (dato.compareTo(this.datos.get(0)) == 0) { // Si el dato es igual a un dato actual lo metemos en la lista de ese nodo
            this.datos.add(dato);
        }
    }

    // Metodo para obtener los elementos con orden central
    protected void ordenCentral(ArrayList<ArrayList<T>> elementosArbol) {
        if (izquierda != null) {
            izquierda.ordenCentral(elementosArbol);
        }
        elementosArbol.add(datos);
        if (derecha != null) {
            derecha.ordenCentral(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con preorden
    protected void preOrden(ArrayList<ArrayList<T>> elementosArbol) {
        elementosArbol.add(datos);
        if (izquierda != null) {
            izquierda.preOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.preOrden(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con postorden
    protected void postOrden(ArrayList<ArrayList<T>> elementosArbol) {
        if (izquierda != null) {
            izquierda.postOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.postOrden(elementosArbol);
        }
        elementosArbol.add(datos);
    }

    /**
     * Devuelve una cadena visual del nodo
     */
    public String toString() {
        if (izquierda != null && derecha != null) {
            return "(TIENE IZQUIERDA) <- (NODO= " + datos + " ) -> (TIENE DERECHA)";
        } else if (izquierda != null && derecha == null) {
            return "(TIENE IZQUIERDA) <- (NODO= " + datos + " ) -/->";
        } else if (izquierda == null && derecha != null) {
            return "<-/- (NODO= " + datos + " ) -> (TIENE DERECHA)";
        } else {
            return "<-/- (NODO= " + datos + " ) -/->";
        }
    }
}

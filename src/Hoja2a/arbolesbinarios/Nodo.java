package Hoja2a.arbolesbinarios;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public class Nodo<T extends Comparable<T>> implements Comparable<Nodo<T>> {

    // variables privadas de la clase
    private Nodo<T> izquierda;
    private Nodo<T> derecha;
    private ListaSimplementeEnlazada<T> datos;
    // Vamos a utilizar una lista de datos en vez de un dato unico para poder almacenar varias veces el mismo dato
    // asi por ejemplo si introducimos un mismo elemento varias veces podemos saber cuantas veces lo hemos metido

    // Constructor
    Nodo() {  // el primer nodo estera vacio (nodo raiz)
        this.izquierda = null;
        this.derecha = null;
        this.datos = new ListaSimplementeEnlazada<>(); // creamos una lista vacia
    }

    Nodo(Nodo<T> izquierda, Nodo<T> derecha, T dato) { // constructor dados todos los parametros
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.datos = new ListaSimplementeEnlazada<>();
        this.datos.addEnd(dato);
    }

    Nodo(T dato) { // constructor dados solo el dato del nodo (para los "nodos hoja" del arbol)
        this.datos = new ListaSimplementeEnlazada<>();
        this.datos.addEnd(dato);
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
    protected ListaSimplementeEnlazada<T> getDatos() {
        return datos;
    }

    // Metodo que devuelve cuantas veces se ha introducido un mismo dato dado el dato
    protected int getRepeticionesNodo(T dato) {
        // Caso base -> encontramos el nodo
        if (datos.get(0).compareTo(dato) == 0) {
            return datos.getSize();
        }
        // Resto de casos con recursividad
        if (datos.get(0).compareTo(dato) > 0 && izquierda != null) {
            return izquierda.getRepeticionesNodo(dato);
        }
        if (datos.get(0).compareTo(dato) < 0 && derecha != null) {
            return derecha.getRepeticionesNodo(dato);
        }
        // Si el dato no esta en el arbol
        return 0;
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
        if (datos.get(0).equals(dato)) { // lo comparamos con un elemento de la lista
            return true;
        }

        // Resto de casos con recursividad
        // Buscar en subárbol izquierdo
        if (dato.compareTo(this.datos.get(0)) < 0 && izquierda != null) {
            return izquierda.isNodoInArbol(dato);
        }

        // Buscar en subárbol derecho
        if (dato.compareTo(this.datos.get(0)) > 0 && derecha != null) {
            return derecha.isNodoInArbol(dato);
        }

        // Si no encontramos el nodo
        return false;
    }

    // Metodo para obtener el nivel de un nodo
    protected int getNivel(T dato) {
        // Caso base, encontramos el nodo que queremos
        if (this.datos.get(0).equals(dato)) {
            return 0; // La raiz tiene nivel 0
        }

        // Resto de casos con recursividad
        if (dato.compareTo(this.datos.get(0)) < 0 && izquierda != null) { // Si el nodo esta a la izquierda nos metemos ahi
            int nivelIzquierda = izquierda.getNivel(dato);
            if (nivelIzquierda != -1) return nivelIzquierda + 1;
        } else if (dato.compareTo(this.datos.get(0)) > 0 && derecha != null) { // Si el nodo esta a la derecha nos metemos ahi
            int nivelDerecha = derecha.getNivel(dato);
            if (nivelDerecha != -1) return nivelDerecha + 1;
        }
        // Si el dato no esta en el arbol devolveremos -1
        return -1;
    }

    // Metodo para añadir todos los elementos de un cierto nivel del arbol a una lista dada
    protected void getListaDatosNivel(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol, int nivel) {
        // Caso base
        if (nivel == 0) {
            elementosArbol.addEnd(datos);
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

    // Metodo para añadir todos los nodos de un cierto nivel del arbol a una lista dada
    protected void getListaNodosNivel(ListaSimplementeEnlazada<Nodo<T>> elementosArbol, int nivel) {
        // Caso base
        if (nivel == 0) {
            elementosArbol.addEnd(this);
            // cuando el nivel sea 0 entonces es el nivel que buscamos
            return; // porque ya no sirve de nada seguir bajando por el arbol si ya hemos llegado al nivel que buscamos
        }

        // Resto de casos con recursividad
        if (nivel > 0) {
            if (izquierda != null) {
                izquierda.getListaNodosNivel(elementosArbol, nivel - 1);
                // al subir un nivel en el arbol el nivel que buscamo esta un escalon mas cerca y por eso restamos 1
                // cuando el nivel sea 0 entonces es el nivel que buscamos
            }
            if (derecha != null) {
                // igual que hemos hecho con las ramas de la izquierda
                derecha.getListaNodosNivel(elementosArbol, nivel - 1);
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

    // Metodo que devuelve una lista de los nodos hoja de un subarbol a partir de un determinado nodo
    protected void getNodosHoja(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> hijos) {
        // Caso base -> llegamos a un nodo hoja -> lo añadimos a la lista
        if (izquierda == null && derecha == null) {
            hijos.addEnd(this.datos);
        }

        // Resto de casos con recursividad
        if (izquierda != null) {
            izquierda.getNodosHoja(hijos);
        }

        if (derecha != null) {
            derecha.getNodosHoja(hijos);
        }
    }

    // Metodo que devuelve el nodo mas pequeño de un arbol
    protected Nodo<T> getNodoMasPequeño() {
        // Si hay izquierda nos metemos a la izquierda
        if (izquierda != null) {
            return izquierda.getNodoMasPequeño();
        }
        // Cuando ya no nos podamos meter mas estamos en el dato mas pequeño
        return this;
    }

    // Metodo que devuelve el nodo mas grande de un arbol
    protected Nodo<T> getNodoMasGrande() {
        // Si hay izquierda nos metemos a la izquierda
        if (derecha != null) {
            return derecha.getNodoMasGrande();
        }
        // Cuando ya no nos podamos meter mas estamos en el dato mas grande
        return this;
    }

    // Metodo para comprobar que todas las hojas coinciden con un nivel de referencia
    protected boolean isArbolCompleto(int nivelActual, int nivelReferencia) {
        // Empezamos como si el arbol fuera completo
        boolean ramaIzquierda = true;
        boolean ramaDerecha = true;

        // Caso base -> llegamos a una hoja -> comprobamos que el nivel es igual al de referencia
        if (izquierda == null && derecha == null) {
            return nivelActual == nivelReferencia;
        }
        // Resto de casos con recursividad
        // Si hay alguna rama que tiene solo izquierda o solo derecha el arbol no puede ser completo
        if (izquierda == null || derecha == null) {
            return false;
        }
        // Si existen ambas ramas comprobamos que ambas cumplen las condiciones
        // Rama izquierda
        ramaIzquierda = izquierda.isArbolCompleto(nivelActual + 1, nivelReferencia); // Como bajamos un nivel: nivelActual + 1
        // Rama derecha
        ramaDerecha = derecha.isArbolCompleto(nivelActual + 1, nivelReferencia); // Como bajamos un nivel: nivelActual + 1

        // Para que sea completo ambas ramas deben de ser true
        return (ramaIzquierda && ramaDerecha);
    }

    // Metodo para comprobar que las hojas mas a la izquierda coinciden con un nivel de referencia
    // Y a partir de una cierta hoja todas tienen un nivel menos
    protected boolean isArbolCasiCompleto(int nivelActual, int nivelReferencia) {
        // Empezamos como si el arbol fuera casi completo
        boolean ramaIzquierda = true;
        boolean ramaDerecha = true;
        // Va a haber dos fases, una para comprobar que el nivel es igual al nivel de Referencia
        // y otra fase para ver que es un nivel menos
        int fase = 1;

        // Caso base -> llegamos a una hoja -> comprobamos que el nivel es igual al de referencia
        if (izquierda == null && derecha == null && fase == 1) {
            return nivelActual == nivelReferencia;
        }
        // Segundo caso base -> llegamos a una hoja -> comprobamos que el nivel es uno menos que el de referencia
        if (izquierda == null && derecha == null && fase == 2) {
            return nivelActual == nivelReferencia - 1;
        }

        // Resto de casos con recursividad
        // Si hay rama izquierda nos metemos a la izquierda
        if (izquierda != null) {
            ramaIzquierda = izquierda.isArbolCompleto(nivelActual + 1, nivelReferencia); // Como bajamos un nivel: nivelActual + 1
        }
        // Si hay rama derecha nos metemos a la derecha
        if (derecha != null) {
            ramaDerecha = derecha.isArbolCompleto(nivelActual + 1, nivelReferencia); // Como bajamos un nivel: nivelActual + 1
        }
        // Si hay alguna rama que tiene solo izquierda o solo derecha pasamos a la segunda fase
        if (izquierda == null || derecha == null) {
            fase = 2;
        }

        // Para que sea completo ambas ramas deben de ser true
        return (ramaIzquierda && ramaDerecha);
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

    // Metodo para saber si un arbol esta equilibrado o no
    protected void equilibrar() {
        // Bajamos hasta las hojas del arbol
        if (izquierda != null) {
            izquierda.equilibrar();
        }
        if (derecha != null) {
            derecha.equilibrar();
        }
        // Y ahora subimos de abajo a arriba comprobando si el arbol esta equilibrado o no

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
            this.datos.addEnd(dato);
        }
    }

    // Metodo para añadir un nuevo nodo al arbol de manera recursiva
    protected void ADD(Nodo<T> nodo) {
        if (nodo.getDatos().get(0).compareTo(this.datos.get(0)) < 0) {
            if (izquierda == null) {
                izquierda = nodo;
                return;
            }
            izquierda.ADD(nodo);
        } else if (nodo.getDatos().get(0).compareTo(this.datos.get(0)) > 0) {
            if (derecha == null) {
                derecha = nodo;
                return;
            }
            derecha.ADD(nodo);
        } else if (nodo.getDatos().get(0).compareTo(this.datos.get(0)) == 0) { // Si el nodo es igual a un nodo actual los "juntamos"
            for (int i = 0; i < nodo.getDatos().getSize(); i++) {
                this.datos.addEnd(nodo.getDatos().get(0));
            }
        }
    }

    // Metodo para borrar un elemento del arbol
    protected Nodo<T> DEL(T dato) {
        // Caso base -> encontramos el nodo -> lo eliminamos
        if (dato.compareTo(this.datos.get(0)) == 0) {
            // Miramos cuantos "elementos del mismo dato" hay
            if (this.datos.getSize() > 1) { // Solo hay que quitar un elemento de la lista
                this.datos.delFirst();
                return this;
            } else { // Si solo hay un elemento y hay que borrar el nodo entero
                return ProcesoBorrado();
            }
        }
        // Resto de casos con recursividad
        else if (datos.get(0).compareTo(dato) > 0 && izquierda != null) {
            izquierda = izquierda.DEL(dato);
        }
        else if (datos.get(0).compareTo(dato) < 0 && derecha != null) {
            derecha = derecha.DEL(dato);
        }
        // Si no encontramos el dato a borrar se queda igual
        return this;
    }

    // Metodo auxiliar para borrar un elemento de un arbol
    protected Nodo<T> ProcesoBorrado() {
        // Si es un nodo hoja, solo hay que decirle al padre que su hijo ahora es null
        if (izquierda == null && derecha == null) {
            return null; // El dato ahora es null
        }
        // Si no se da esto
        // miramos si tiene izquierda para sustituirlo
        if (derecha != null) {
            if (izquierda != null) {
                // Si habia mas nodos a la derecha lo que hacemos es buscar el elemento mas pequeño de la rama derecha
                // Y subirlo a donde estaba nuestro dato
                Nodo<T> nodoMasGrande = derecha.getNodoMasPequeño();
                // Cambiamos los datos de este nodo por el nodo que queremos borrar
                this.datos = nodoMasGrande.datos;
                // Borramos el nodo que hemos cambiado de su posicion anterior
                this.derecha = derecha.DEL(nodoMasGrande.datos.get(0));
                // Una vez que hemos hecho todos los cambios devolvemos la rama
                return this;
            }
            // Conectamos al padre directamente con el hijo de la izquierda
            return derecha;
        }
        // Si no hay izquierda lo hacemos con la derecha
        else {
            // Conectamos al padre directamente con el hijo de la derecha
            return izquierda;
        }
    }

    // Metodo para añadir a una lista la sucesion de datos que hay que recorrer hasta llegar a un dato dado
    protected boolean getCaminoDatos(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> camino, T dato) {
        // Creamos una variable para ver si hemos encontrado el dato o no
        boolean encontrado = false;

        // Caso base -> encontramos el dato
        if (this.datos.get(0).compareTo(dato) == 0) {
            encontrado = true;
        }
        // Resto de casos con recursividad
        else if (this.datos.get(0).compareTo(dato) > 0 && izquierda != null) {
            encontrado = izquierda.getCaminoDatos(camino, dato);
        }
        else if (this.datos.get(0).compareTo(dato) < 0 && derecha != null) {
            encontrado = derecha.getCaminoDatos(camino, dato);
        }

        // Si hemos encontrado el dato añadimos el dato a la lista
        if (encontrado == true) {
            camino.addEnd(this.datos);
        }
        return encontrado;
    }

    // Metodo para añadir a una lista la sucesion de nodos que hay que recorrer hasta llegar a un dato dado
    protected boolean getCaminoNodos(ListaSimplementeEnlazada<Nodo<T>> camino, T dato) {
        // Creamos una variable para ver si hemos encontrado el dato o no
        boolean encontrado = false;

        // Caso base -> encontramos el dato
        if (this.datos.get(0).compareTo(dato) == 0) {
            encontrado = true;
        }
        // Resto de casos con recursividad
        else if (this.datos.get(0).compareTo(dato) > 0 && izquierda != null) {
            encontrado = izquierda.getCaminoNodos(camino, dato);
        }
        else if (this.datos.get(0).compareTo(dato) < 0 && derecha != null) {
            encontrado = derecha.getCaminoNodos(camino, dato);
        }

        // Si hemos encontrado el dato añadimos el nodo a la lista
        if (encontrado == true) {
            camino.addEnd(this);
        }
        return encontrado;
    }

    // Metodo para obtener los elementos con orden central
    protected void ordenCentral(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol) {
        if (izquierda != null) {
            izquierda.ordenCentral(elementosArbol);
        }
        elementosArbol.addEnd(datos);
        if (derecha != null) {
            derecha.ordenCentral(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con preorden
    protected void preOrden(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol) {
        elementosArbol.addEnd(datos);
        if (izquierda != null) {
            izquierda.preOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.preOrden(elementosArbol);
        }
    }

    // Metodo para obtener los elementos con postorden
    protected void postOrden(ListaSimplementeEnlazada<ListaSimplementeEnlazada<T>> elementosArbol) {
        if (izquierda != null) {
            izquierda.postOrden(elementosArbol);
        }
        if (derecha != null) {
            derecha.postOrden(elementosArbol);
        }
        elementosArbol.addEnd(datos);
    }

    /**
     * Rotaciones para equilibrar los árboles
     */
    protected void rotacionHaciaIzquierda(Nodo<T> nodo) {
        // Tenemos un hilo de tres nodos seguidos hacia la derecha y tenemos que rotarlos hacia la izquierda
        // Comprobamos que el nodo es valido
        if (nodo != null && nodo.derecha != null && nodo.derecha.derecha != null) {
            // Realizamos el giro
            Nodo<T> nodoActual = nodo;
            // El nodo del medio pasa a la posicion del nodo actual
            nodo = nodo.derecha;
            // Los demas se colocan como hijos
            nodo.derecha.izquierda = nodoActual;
            // El otro ya esta a la derecha asi que se queda igual
        }
    }

    protected void rotacionHaciaDerecha(Nodo<T> nodo) {
        // Tenemos un hilo de tres nodos seguidos hacia la izquierda y tenemos que rotarlos hacia la derecha
        // Comprobamos que el nodo es valido
        if (nodo != null && nodo.izquierda != null && nodo.izquierda.izquierda != null) {
            // Realizamos el giro
            Nodo<T> nodoActual = nodo;
            // El nodo del medio pasa a la posicion del nodo actual
            nodo = nodo.izquierda;
            // Los demas se colocan como hijos
            nodo.izquierda.derecha = nodoActual;
            // El otro ya esta a la izquierda asi que se queda igual
        }
    }

    protected void ajusteHaciaIzquierda(Nodo<T> nodo) {
        // Tenemos un hilo de tres nodos en zig-zag izquierda-derecha-izquierda
        // Comprobamos que el nodo es valido
        if (nodo != null && nodo.derecha != null && nodo.derecha.izquierda != null) {
            // Realizamos una modificacion para llegar a uno de los casos anteriores
            Nodo<T> nodoDerecha = nodo.derecha;
            nodo.derecha = nodo.derecha.izquierda;
            // colocamos el nodo que hemos cambiado como hijo
            nodo.derecha.derecha = nodoDerecha;
            // Ahora usamos el caso anterior
            rotacionHaciaIzquierda(nodo);
        }
    }

    protected void ajusteHaciaDerecha(Nodo<T> nodo) {
        // Tenemos un hilo de tres nodos en zig-zag dercha-izquierda-derecha
        // Comprobamos que el nodo es valido
        if (nodo != null && nodo.izquierda != null && nodo.izquierda.derecha != null) {
            // Realizamos una modificacion para llegar a uno de los casos anteriores
            Nodo<T> nodoIzquierda = nodo.izquierda;
            nodo.izquierda = nodo.izquierda.derecha;
            // colocamos el nodo que hemos cambiado como hijo
            nodo.izquierda.izquierda = nodoIzquierda;
            // Ahora usamos el caso anterior
            rotacionHaciaDerecha(nodo);
        }
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

    @Override
    public int compareTo(Nodo<T> o) {
        return 0;
    }
}

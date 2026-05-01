package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public class Grafo<DN, DA> implements InterfazGrafo<DN, DA> {

    // Lista de nodos del grafo
    private ListaSimplementeEnlazada<NodoGrafo<DN>> nodos;

    // Lista de aristas del grafo
    private ListaSimplementeEnlazada<Arista<DN, DA>> aristas;

    // Lista de tipos de los nodos
    private ListaSimplementeEnlazada<String> tipos;

    // Constructor
    public Grafo() {
        this.nodos = new ListaSimplementeEnlazada<>();
        this.aristas = new ListaSimplementeEnlazada<>();
        this.tipos = new ListaSimplementeEnlazada<>();
    }

    // Getters y Setters
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getNodos() {
        return nodos;
    }

    @Override
    public ListaSimplementeEnlazada<Arista<DN, DA>> getAristas() {
        return aristas;
    }

    @Override
    public ListaSimplementeEnlazada<String> getTipos() {
        return tipos;
    }

    // Añade un nodo si no existe
    @Override
    public void addNodo(NodoGrafo<DN> nodo) {
        if (nodo != null && !nodos.contains(nodo)) {
            for (int i = 0; i < nodos.getSize(); i++) {
                NodoGrafo<DN> nodoExistente = nodos.get(i);
                if (nodoExistente.getDatos().equals(nodo.getDatos())) {
                    return; // Ya esta en el grafo
                }
            }
            // Sino estaba lo añadimos
            nodos.addEnd(nodo);

            // aprovechamos para añadir el tipo del nodo
            if (nodo.getDatos() instanceof InterfazDatosNodo) {
                addTipo(((InterfazDatosNodo) nodo.getDatos()).getTipo());
            }
        }
    }

    @Override
    public void addNodo(DN datos) {
        addNodo(new NodoGrafo<>(datos));
    }

    // Añade una arista al grafo
    @Override
    public void addArista(Arista<DN, DA> arista) {
        if (arista != null) {
            for (int i = 0; i < nodos.getSize(); i++) {
                NodoGrafo<DN> nodoExistente1 = nodos.get(i);
                if (nodoExistente1.getDatos().equals(arista.getOrigen().getDatos())) {
                    arista.setOrigen(nodoExistente1); // Señalamos la arista al nodo ya existente
                }
                NodoGrafo<DN> nodoExistente2 = nodos.get(i);
                if (nodoExistente2.getDatos().equals(arista.getDestino().getDatos())) {
                    arista.setDestino(nodoExistente2); // Señalamos la arista al nodo ya existente
                }
            }
            // Añadimos la arista
            addNodo(arista.getOrigen());
            addNodo(arista.getDestino());
            aristas.addEnd(arista);
        }
    }

    // Crea y añade una arista
    @Override
    public void addArista(NodoGrafo<DN> origen, DA dato, NodoGrafo<DN> destino) {
        addArista(new Arista<>(origen, dato, destino));
    }

    // Añade un tipo al grafo
    @Override
    public void addTipo(String tipo) {
        if (tipo == null) return;
        for (int i = 0; i < tipos.getSize(); i++) {
            if (tipo.equals(tipos.get(i))) {
                return;
            }
        }
        // si no estaba lo añadimos
        tipos.addEnd(tipo);
    }

    // Metodo que devuelve una lista solamente con los nodos que cumplen la propiedad especial del json
    // es decir que tienen tipo y nombre -> usamos la interfaz que hemos creado para garantizar que cumplen esto
    @Override
    public ListaSimplementeEnlazada<InterfazDatosNodo> getNodosValidos() {
        ListaSimplementeEnlazada<InterfazDatosNodo> listaEspecial = new ListaSimplementeEnlazada<>();
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> nodo = nodos.get(i);
            if (nodo.getDatos() instanceof InterfazDatosNodo) { // si los datos cumplen con la estructura
                listaEspecial.addEnd((InterfazDatosNodo) nodo.getDatos());
            }
        }
        return listaEspecial;
    }

    // Mismo metodo para las aristas
    @Override
    public ListaSimplementeEnlazada<InterfazDatosArista> getAristasValidas() {
        ListaSimplementeEnlazada<InterfazDatosArista> listaEspecial = new ListaSimplementeEnlazada<>();
        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getDato() instanceof InterfazDatosArista && arista.getOrigen().getDatos() instanceof InterfazDatosNodo && arista.getDestino().getDatos() instanceof InterfazDatosNodo) {
                // tiene que cumplirse que los nodos que conecta sean validos y el dato que lleva tambien
                listaEspecial.addEnd((InterfazDatosArista) arista.getDato());
            }
        }
        return listaEspecial;
    }

    // Busca un nodo por su id
    @Override
    public NodoGrafo<DN> buscarNodoPorId(long id) {
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> nodo = nodos.get(i);
            if (nodo.getId() == id) {
                return nodo;
            }
        }
        return null;
    }

    // Mismo metodo para cuando se introduce

    @Override
    public NodoGrafo<DN> buscarNodoPorTipo(String tipo) {
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> nodo = nodos.get(i);
            if (nodo.getDatos() instanceof InterfazDatosNodo) {
                if (((InterfazDatosNodo) nodo.getDatos()).getTipo().equals(tipo)) {
                    return nodo;
                }
            }
        }
        return null;
    }

    @Override
    public NodoGrafo<DN> buscarNodoPorNombre(String nombreBusqueda) {
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> nodo = nodos.get(i);
            // Comprobamos que tiene un nombre mirando si es del tipo InterfazDatosNodo
            if (nodo.getDatos() instanceof InterfazDatosNodo) {
                if (((InterfazDatosNodo) nodo.getDatos()).getNombre().equals(nombreBusqueda)) {
                    return nodo;
                }
            }
        }
        return null;
    }

    @Override
    public int NumeroNodosTipo(String tipo) {
        int cantidad = 0;
        for (int i = 0; i < nodos.getSize(); i++) {
            DN datos = nodos.get(i).getDatos();
            if (datos instanceof InterfazDatosNodo) {
                if (((InterfazDatosNodo) datos).getTipo().equals(tipo)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    // Devuelve los tipos de nodos conocidos
    @Override
    public ListaSimplementeEnlazada<String> getTiposDeNodos() {
        // devolvemos el atributo tipos que se actualiza en addNodo
        return this.tipos;
    }

    // Devuelve los vecinos salientes
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getVecinos(NodoGrafo<DN> nodo) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getOrigen().getId() == nodo.getId()) {
                vecinos.addEnd(arista.getDestino());
            }
        }
        return vecinos;
    }

    // Calcula el camino mínimo
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimo(long idOrigen, long idDestino) {
        NodoGrafo<DN> origen = buscarNodoPorId(idOrigen);
        NodoGrafo<DN> destino = buscarNodoPorId(idDestino);

        // Si alguno de los datos no esta no se puede hacer un camino entre ambos
        ListaSimplementeEnlazada<NodoGrafo<DN>> camino = new ListaSimplementeEnlazada<>();
        if (origen == null || destino == null) {
            return camino;
        }

        // Vamos a hacer una busqueda en anchura para encontrar el camino minimo
        // Lista para gestionar el orden de busqueda
        ListaSimplementeEnlazada<NodoGrafo<DN>> nodosPorExplorar = new ListaSimplementeEnlazada<>();
        // Lista para saber que nodos se han visitado ya y no entrar en bucles
        ListaSimplementeEnlazada<NodoGrafo<DN>> visitados = new ListaSimplementeEnlazada<>();
        // Lista para reconstruir el camino si encontramos el nodo destino
        ListaSimplementeEnlazada<NodoGrafo<DN>> anteriores = new ListaSimplementeEnlazada<>();

        nodosPorExplorar.addEnd(origen);
        visitados.addEnd(origen);
        anteriores.addEnd(null);

        int indice = 0;
        boolean encontrado = false;

        // El bucle termina si encontramos el dato o si ya no quedan mas nodos por explorar
        while (indice < nodosPorExplorar.getSize() && !encontrado) {
            NodoGrafo<DN> actual = nodosPorExplorar.get(indice);

            // Si encontramos el destino terminamos el bucle
            if (actual.equals(destino)) {
                encontrado = true;
            } else { // sino exploramos todos los vecinos
                ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = getVecinos(actual);

                // Hay que asegurarse que no repitamos los nodos por los que ya hemos pasado
                for (int i = 0; i < vecinos.getSize(); i++) {
                    NodoGrafo<DN> vecino = vecinos.get(i);
                    boolean visitado = false;
                    for (int j = 0; j < visitados.getSize(); j++) {
                        // si el nodo vecino ya lo habiamos explorado -> esta en visitados
                        if (vecino.equals(visitados.get(j))) {
                            visitado = true;
                        }
                    }
                    if (!visitado) { // si el nodo no lo habiamos explorado
                        nodosPorExplorar.addEnd(vecino);
                        visitados.addEnd(vecino);
                        anteriores.addEnd(actual);
                    }
                }

                indice++;
            }
        }

        // Si hemos encontrado el nodo destino reconstruimos el camino
        if (encontrado) {
            NodoGrafo<DN> pasoActual = destino;
            while (pasoActual != null) {
                camino.addStart(pasoActual); // Insertamos al inicio para que el orden sea Origen -> Destino
                int pos = visitados.getPosicion(pasoActual);
                pasoActual = anteriores.get(pos);
            }
        }

        return camino;
    }

    // Calcula el camino mínimo
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimoAmbosSentidos(long idOrigen, long idDestino) {
        NodoGrafo<DN> origen = buscarNodoPorId(idOrigen);
        NodoGrafo<DN> destino = buscarNodoPorId(idDestino);

        // Si alguno de los datos no esta no se puede hacer un camino entre ambos
        ListaSimplementeEnlazada<NodoGrafo<DN>> camino = new ListaSimplementeEnlazada<>();
        if (origen == null || destino == null) {
            return camino;
        }

        // Vamos a hacer una busqueda en anchura para encontrar el camino minimo
        // Lista para gestionar el orden de busqueda
        ListaSimplementeEnlazada<NodoGrafo<DN>> nodosPorExplorar = new ListaSimplementeEnlazada<>();
        // Lista para saber que nodos se han visitado ya y no entrar en bucles
        ListaSimplementeEnlazada<NodoGrafo<DN>> visitados = new ListaSimplementeEnlazada<>();
        // Lista para reconstruir el camino si encontramos el nodo destino
        ListaSimplementeEnlazada<NodoGrafo<DN>> anteriores = new ListaSimplementeEnlazada<>();

        nodosPorExplorar.addEnd(origen);
        visitados.addEnd(origen);
        anteriores.addEnd(null);

        int indice = 0;
        boolean encontrado = false;

        // El bucle termina si encontramos el dato o si ya no quedan mas nodos por explorar
        while (indice < nodosPorExplorar.getSize() && !encontrado) {
            NodoGrafo<DN> actual = nodosPorExplorar.get(indice);

            // Si encontramos el destino terminamos el bucle
            if (actual.equals(destino)) {
                encontrado = true;
            } else { // sino exploramos todos los vecinos
                ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = getVecinosNoDirigidos(actual);

                // Hay que asegurarse que no repitamos los nodos por los que ya hemos pasado
                for (int i = 0; i < vecinos.getSize(); i++) {
                    NodoGrafo<DN> vecino = vecinos.get(i);
                    boolean visitado = false;
                    for (int j = 0; j < visitados.getSize(); j++) {
                        // si el nodo vecino ya lo habiamos explorado -> esta en visitados
                        if (vecino.equals(visitados.get(j))) {
                            visitado = true;
                        }
                    }
                    if (!visitado) { // si el nodo no lo habiamos explorado
                        nodosPorExplorar.addEnd(vecino);
                        visitados.addEnd(vecino);
                        anteriores.addEnd(actual);
                    }
                }

                indice++;
            }
        }

        // Si hemos encontrado el nodo destino reconstruimos el camino
        if (encontrado) {
            NodoGrafo<DN> pasoActual = destino;
            while (pasoActual != null) {
                camino.addStart(pasoActual); // Insertamos al inicio para que el orden sea Origen -> Destino
                int pos = visitados.getPosicion(pasoActual);
                pasoActual = anteriores.get(pos);
            }
        }

        return camino;
    }

    // Metodo para mostrar el camino solo con los ids
    @Override
    public String mostrarIdsCamino(ListaSimplementeEnlazada<NodoGrafo<DN>> camino) {
        String ids = "";
        for (int i = 0; i < camino.getSize(); i++) {
            if (i != camino.getSize() - 1) {
                ids += camino.get(i).getId() + " -> ";
            } else {
                ids += camino.get(i).getId();
            }
        }
        return ids;
    }

    // Mismo metodo para cuando introducen Strings
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimo(String nombreOrigen, String nombreDestino) {
        // Buscamos los nodos por su nombre/dato
        NodoGrafo<DN> nodoO = buscarNodoPorNombre(nombreOrigen);
        NodoGrafo<DN> nodoD = buscarNodoPorNombre(nombreDestino);

        // Si ambos existen, usamos sus IDs para llamar al metodo principal
        if (nodoO != null && nodoD != null) {
            return caminoMinimo(nodoO.getId(), nodoD.getId());
        }

        // Si alguno no existe, devolvemos lista vacía
        System.err.println("No se encontró alguno de los nodos: " + nombreOrigen + " o " + nombreDestino);
        return new ListaSimplementeEnlazada<>();
    }

    // Mismo metodo para cuando introducen Strings
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> caminoMinimoAmbosSentidos(String nombreOrigen, String nombreDestino) {
        // Buscamos los nodos por su nombre/dato
        NodoGrafo<DN> nodoO = buscarNodoPorNombre(nombreOrigen);
        NodoGrafo<DN> nodoD = buscarNodoPorNombre(nombreDestino);

        // Si ambos existen, usamos sus IDs para llamar al metodo principal
        if (nodoO != null && nodoD != null) {
            return caminoMinimoAmbosSentidos(nodoO.getId(), nodoD.getId());
        }

        // Si alguno no existe, devolvemos lista vacía
        System.err.println("No se encontró alguno de los nodos: " + nombreOrigen + " o " + nombreDestino);
        return new ListaSimplementeEnlazada<>();
    }

    // Devuelve vecinos en ambos sentidos
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getVecinosNoDirigidos(NodoGrafo<DN> nodo) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getOrigen().getId() == nodo.getId() && !vecinos.contains(arista.getDestino())) {
                vecinos.addEnd(arista.getDestino());
            }

            if (arista.getDestino().getId() == nodo.getId() && !vecinos.contains(arista.getOrigen())) {
                vecinos.addEnd(arista.getOrigen());
            }
        }

        return vecinos;
    }

    // Comprueba si el grafo está separado
    @Override
    public boolean esDisjunto() {
        if (nodos.isEmpty()) {
            return false;
        }

        // Creamos dos listas de nuevo para hacer una busqueda en anchura
        ListaSimplementeEnlazada<NodoGrafo<DN>> visitados = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<DN>> pendientes = new ListaSimplementeEnlazada<>();

        NodoGrafo<DN> inicio = nodos.get(0);
        pendientes.addEnd(inicio);
        visitados.addEnd(inicio);

        int indice = 0;

        // Seguimos hasta que nos quedemos sin nodos por explorar
        while (indice < pendientes.getSize()) {
            NodoGrafo<DN> actual = pendientes.get(indice);
            ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = getVecinosNoDirigidos(actual);

            // Comprobamos que los nodos de la busqueda en anchura no los habiamos explorado antes
            for (int i = 0; i < vecinos.getSize(); i++) {
                NodoGrafo<DN> vecino = vecinos.get(i);
                if (!visitados.contains(vecino)) {
                    // Si no los habiamos visitado los añadimos a ambas listas
                    visitados.addEnd(vecino);
                    pendientes.addEnd(vecino);
                }
            }

            indice++;
        }

        // Si el numero total de nodos visitados en el bucle coincide con los nodos totales es que todos estan conectados
        // si no coincide es porque hay algun nodo al que no se ha llegado porque no estaba conectado con el grupo de
        // nodos que exploramos y por tanto el grafo es disjunto
        return visitados.getSize() != nodos.getSize();
    }

    // Busca destinos por dato
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getDestinosPorPredicado(NodoGrafo<DN> origen, DA dato) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> destinos = new ListaSimplementeEnlazada<>();

        // Añade a la lista todos los nodos que esten conectados al nodo origen por un dato especifico
        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getOrigen().getId() == origen.getId()) { // si el nodo es igual
                if (arista.getDato().toString().equals(dato.toString())) { // Y el dato es el que buscamos
                    destinos.addEnd(arista.getDestino()); // lo añadimos a la lista
                }
            }
        }

        return destinos;
    }

    // Mismo metodo para cuando se introduce un String
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getDestinosPorPredicado(NodoGrafo<DN> origen, String dato) {
        return getDestinosPorPredicado(origen, (DA) dato);
    }

    // Busca orígenes por dato y destino
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getOrigenesPorPredicadoYDestino(DA dato, NodoGrafo<DN> destino) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> origenes = new ListaSimplementeEnlazada<>();

        // Mismo metodo que antes pero teniendo el destino en vez de el origen
        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getDestino().getId() == destino.getId()) {
                if (arista.getDato().toString().equals(dato.toString())) {
                    origenes.addEnd(arista.getOrigen());
                }
            }
        }

        return origenes;
    }

    // Mismo metodo para cuando se introduce un String
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getOrigenesPorPredicadoYDestino(String dato, NodoGrafo<DN> destino) {
        return getOrigenesPorPredicadoYDestino((DA) dato, destino);
    }

    // Busca personas nacidas en la misma ciudad
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> personasMismaCiudadQue(String nombre) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> resultado = new ListaSimplementeEnlazada<>();

        NodoGrafo<DN> persona = buscarNodoPorNombre(nombre);

        if (persona == null) {
            return resultado;
        }

        ListaSimplementeEnlazada<NodoGrafo<DN>> lugares = getDestinosPorPredicado(persona, "nace_en");

        if (lugares.isEmpty()) {
            return resultado;
        }

        NodoGrafo<DN> lugarNacimiento = lugares.get(0);

        ListaSimplementeEnlazada<NodoGrafo<DN>> personas = getOrigenesPorPredicadoYDestino("nace_en", lugarNacimiento);

        for (int i = 0; i < personas.getSize(); i++) {
            NodoGrafo<DN> otraPersona = personas.get(i);
            if (otraPersona.getId() != persona.getId()) {
                resultado.addEnd(otraPersona);
            }
        }

        return resultado;
    }

    // Busca fisicos nacidos en la misma ciudad que una persona
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> fisicosMismaCiudadQue(String nombre) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> resultado = new ListaSimplementeEnlazada<>();
        // Reutilizamos el metodo de antes
        ListaSimplementeEnlazada<NodoGrafo<DN>> personas = personasMismaCiudadQue(nombre);

        // Comprobamos cuantas de esas personas son fisicos
        for (int i = 0; i < personas.getSize(); i++) {
            NodoGrafo<DN> persona = personas.get(i);
            ListaSimplementeEnlazada<NodoGrafo<DN>> profesiones = getDestinosPorPredicado(persona, "profesion");

            for (int j = 0; j < profesiones.getSize(); j++) {
                DN datosProfesion = profesiones.get(j).getDatos();
                // Comprobamos si en los datos dice "fisico" o "físico"
                if (datosProfesion.toString().toLowerCase().contains("fisico")) {
                    resultado.addEnd(persona);
                    break;
                }
            }
        }

        return resultado;
    }

    // Busca lugares de nacimiento de premios Nobel
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> lugaresNacimientoPremiosNobel() {
        ListaSimplementeEnlazada<NodoGrafo<DN>> lugares = new ListaSimplementeEnlazada<>();
        NodoGrafo<DN> premioNobel = buscarNodoPorNombre("Nobel");

        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> persona = nodos.get(i);
            ListaSimplementeEnlazada<NodoGrafo<DN>> premios = getDestinosPorPredicado(persona, "premio");
            ListaSimplementeEnlazada<NodoGrafo<DN>> nobelesAntiguos = getDestinosPorPredicado(persona, "Nobel");

            if ((premioNobel != null && premios.contains(premioNobel)) || !nobelesAntiguos.isEmpty()) {
                ListaSimplementeEnlazada<NodoGrafo<DN>> nacimientos = getDestinosPorPredicado(persona, "nace_en");

                for (int j = 0; j < nacimientos.getSize(); j++) {
                    NodoGrafo<DN> lugar = nacimientos.get(j);
                    if (!lugares.contains(lugar)) {
                        lugares.addEnd(lugar);
                    }
                }
            }
        }

        return lugares;
    }

    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> Dijkstra(NodoGrafo<DN> nodo) {
        /**
         * No nos ha dado tiempo a terminar esta parte pero nos gustaria añadirla en un futuro y por eso no hemos
         * borrado la estructura
         */
        return null;
    }

    // Metodo que devuelve una cadena de texto para poder visualizar el grafo
    @Override
    public String toString() {
        String resultado = "=== GRAFO DE CONOCIMIENTO ===\n";

        // Mostramos el numero de tipos que hay
        resultado += "TIPOS (" + this.tipos.getSize() + "):\n";
        // Mostramos cada tipo
        for (int i = 0; i < tipos.getSize(); i++) {
            resultado += "  * " + tipos.get(i) + "\n";
        }

        // Mostramos el numero de nodos
        resultado += "\nNODOS (" + this.nodos.getSize() + "):\n";
        // Mostramos la informacion de cada nodo
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> n = nodos.get(i);
            // Suponiendo que el tipo y el id son Strings
            resultado += "  * " + n.toString() + "\n";
        }

        // Hacemos lo mismo con las aristas
        resultado += "\nARISTAS (" + this.aristas.getSize() + "):\n";

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> a = aristas.get(i);
            // Formato: Sujeto --(Predicado)--> Objeto
            resultado += "  " + a.getOrigen().getId() + " --(" + a.getDato() + ")--> " + a.getDestino().getId() + "\n";
        }

        resultado += "=============================";
        return resultado;
    }
}
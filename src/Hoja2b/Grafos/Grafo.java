package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public class Grafo<DN extends InterfazDatosGrafo,DA> implements InterfazGrafo<DN, DA> {

    // Lista de nodos del grafo
    private ListaSimplementeEnlazada<NodoGrafo<DN>> nodos;

    // Lista de aristas del grafo
    private ListaSimplementeEnlazada<Arista<DN, DA>> aristas;

    // Lista de tipos únicos encontrados (Añadido)
    private ListaSimplementeEnlazada<String> tipos;

    // Contadores para los ids de los nodos y las aristas
    private long contadorNodos;
    private long contadorAristas;

    // Constructor
    public Grafo() {
        this.nodos = new ListaSimplementeEnlazada<>();
        this.aristas = new ListaSimplementeEnlazada<>();
        this.tipos = new ListaSimplementeEnlazada<>();
        this.contadorNodos = 0;
        this.contadorAristas = 0;
    }

    // Getters y Setters
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getNodos() {
        return nodos;
    }

    @Override
    public void setNodos(ListaSimplementeEnlazada<NodoGrafo<DN>> nodos) {
        this.nodos = nodos;
    }

    @Override
    public ListaSimplementeEnlazada<Arista<DN, DA>> getAristas() {
        return aristas;
    }

    @Override
    public void setAristas(ListaSimplementeEnlazada<Arista<DN, DA>> aristas) {
        this.aristas = aristas;
    }


    @Override
    public ListaSimplementeEnlazada<String> getTipos() {
        return tipos;
    }

    @Override
    public void setTipos(ListaSimplementeEnlazada<String> tipos) {
        this.tipos = tipos;
    }

    // Añade un nodo si no existe
    @Override
    public void addNodo(NodoGrafo<DN> nodo) {
        if (nodo != null && !nodos.contains(nodo)) {
            nodos.addEnd(nodo);

            if (nodo.getDatos() instanceof DatoNodo) {
                addTipo(((DatoNodo) nodo.getDatos()).getTipo());
            }
        }
    }

    // Añade una arista al grafo
    @Override
    public void addArista(Arista<DN, DA> arista) {
        if (arista != null) {
            addNodo(arista.getOrigen());
            addNodo(arista.getDestino());
            aristas.addEnd(arista);
        }
    }

    // Crea y añade una arista
    @Override
    public void addArista(NodoGrafo<DN> origen, DA predicado, NodoGrafo<DN> destino) {
        addArista(new Arista<>(contadorAristas++, origen, predicado, destino));
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
            if (nodo.getDatos() instanceof DatoNodo) {
                if (((DatoNodo) nodo.getDatos()).getTipo().equals(tipo)) {
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
            // Usamos toString() de los datos para ver si coinciden con lo que buscamos
            if (nodo.getDatos().toString().equalsIgnoreCase(nombreBusqueda)) {
                return nodo;
            }
        }
        return null;
    }

    @Override
    public int NumeroNodosTipo(String tipo) {
        int cantidad = 0;
        for (int i = 0; i < nodos.getSize(); i++) {
            DN datos = nodos.get(i).getDatos();
            if (datos instanceof DatoNodo) {
                if (((DatoNodo) datos).getTipo().equals(tipo)) {
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

        ListaSimplementeEnlazada<NodoGrafo<DN>> camino = new ListaSimplementeEnlazada<>();
        if (origen == null || destino == null) {
            return camino;
        }

        ListaSimplementeEnlazada<NodoGrafo<DN>> cola = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<DN>> visitados = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<DN>> anteriores = new ListaSimplementeEnlazada<>();

        cola.addEnd(origen);
        visitados.addEnd(origen);
        anteriores.addEnd(null);

        int indice = 0;
        boolean encontrado = false;

        while ( indice < cola.getSize() && !encontrado) {
            NodoGrafo<DN> actual = cola.get(indice);

            if (actual.equals(destino)) {
                encontrado = true;
            } else {
                ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = getVecinos(actual);

                for (int i = 0; i < vecinos.getSize(); i++) {
                    NodoGrafo<DN> vecino = vecinos.get(i);
                    if (!visitados.contains(vecino)) {
                        visitados.addEnd(vecino);
                        cola.addEnd(vecino);
                        anteriores.addEnd(actual);
                    }
                }

                indice++;
            }
        }

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

    // Mismo metodo para cuando introducen Strings
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

    // Devuelve vecinos en ambos sentidos
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getVecinosNoDirigidos(NodoGrafo<DN> nodo) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getOrigen().getId() == nodo.getId() && !vecinos.contains(arista.getDestino())) {
                vecinos.addEnd(arista.getDestino());
            }

            if (arista.getOrigen().getId() == nodo.getId() && !vecinos.contains(arista.getOrigen())) {
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

        ListaSimplementeEnlazada<NodoGrafo<DN>> visitados = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<DN>> pendientes = new ListaSimplementeEnlazada<>();

        NodoGrafo<DN> inicio = nodos.get(0);
        pendientes.addEnd(inicio);
        visitados.addEnd(inicio);

        int indice = 0;

        while (indice < pendientes.getSize()) {
            NodoGrafo<DN> actual = pendientes.get(indice);
            ListaSimplementeEnlazada<NodoGrafo<DN>> vecinos = getVecinosNoDirigidos(actual);

            for (int i = 0; i < vecinos.getSize(); i++) {
                NodoGrafo<DN> vecino = vecinos.get(i);
                if (!visitados.contains(vecino)) {
                    visitados.addEnd(vecino);
                    pendientes.addEnd(vecino);
                }
            }

            indice++;
        }

        return visitados.getSize() != nodos.getSize();
    }

    // Busca destinos por predicado
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getDestinosPorPredicado(NodoGrafo<DN> origen, DA predicado) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> destinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getOrigen().getId() == origen.getId()) {
                if (arista.getPredicado().equals(predicado)) {
                    destinos.addEnd(arista.getDestino());
                }
            }
        }

        return destinos;
    }
    // Mismo metodo para cuando se introduce un String
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getDestinosPorPredicado(NodoGrafo<DN> origen, String predicadoStr) {
        return getDestinosPorPredicado(origen, (DA) predicadoStr);
    }

    // Busca orígenes por predicado y destino
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getOrigenesPorPredicadoYDestino(DA predicado, NodoGrafo<DN> destino) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> origenes = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> arista = aristas.get(i);
            if (arista.getDestino().getId() == destino.getId()) {
                if (arista.getPredicado().equals(predicado)) {
                    origenes.addEnd(arista.getOrigen());
                }
            }
        }

        return origenes;
    }

    // Mismo metodo para cuando se introduce un String
    public ListaSimplementeEnlazada<NodoGrafo<DN>> getOrigenesPorPredicadoYDestino(String predicadostr, NodoGrafo<DN> destino) {
        return getOrigenesPorPredicadoYDestino((DA) predicadostr, destino);
    }

    // Busca personas nacidas en la misma ciudad
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> personasMismaCiudadQue(long idPersona) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> resultado = new ListaSimplementeEnlazada<>();

        NodoGrafo<DN> persona = buscarNodoPorId(idPersona);

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

    // Mismo metodo para cuando introducen String
    public ListaSimplementeEnlazada<NodoGrafo<DN>> personasMismaCiudadQue(String idPersona) {
        // Buscamos el nodo por su nombre/dato
        NodoGrafo<DN> nodo = buscarNodoPorNombre(idPersona);

        // Si ambos existen, usamos sus IDs para llamar al metodo principal
        if (nodo != null) {
            return personasMismaCiudadQue(nodo.getId());
        }

        // Si alguno no existe, devolvemos lista vacía
        System.err.println("No se encontró alguno el nodo: " + idPersona);
        return new ListaSimplementeEnlazada<>();
    }

    // Busca fisicos nacidos en la misma ciudad que una persona
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> fisicosMismaCiudadQue(long idPersona) {
        ListaSimplementeEnlazada<NodoGrafo<DN>> resultado = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<DN>> personas = personasMismaCiudadQue(idPersona);

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

    // Mismo metodo para cuando introduces un String
    public ListaSimplementeEnlazada<NodoGrafo<DN>> fisicosMismaCiudadQue(String idPersona) {
        // Buscamos el nodo por su nombre/dato
        NodoGrafo<DN> nodo = buscarNodoPorNombre(idPersona);

        // Si ambos existen, usamos sus IDs para llamar al metodo principal
            if (nodo != null) {
            return fisicosMismaCiudadQue(nodo.getId());
        }

        // Si alguno no existe, devolvemos lista vacía
            System.err.println("No se encontró alguno el nodo: " + idPersona);
            return new ListaSimplementeEnlazada<>();
    }

    // Busca lugares de nacimiento de premios Nobel
    @Override
    public ListaSimplementeEnlazada<NodoGrafo<DN>> lugaresNacimientoPremiosNobel() {
        ListaSimplementeEnlazada<NodoGrafo<DN>> lugares = new ListaSimplementeEnlazada<NodoGrafo<DN>>();
        NodoGrafo<DN> premioNobel = buscarNodoPorNombre("premio:Nobel");

        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> persona = nodos.get(i);
            ListaSimplementeEnlazada<NodoGrafo<DN>> premios = getDestinosPorPredicado(persona, "premio");
            ListaSimplementeEnlazada<NodoGrafo<DN>> nobelesAntiguos = getDestinosPorPredicado(persona, "premio:Nobel");

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

    // Metodo que devuelve una cadena de texto para poder visualizar el grafo
    @Override
    public String toString() {
        String resultado = "=== GRAFO DE CONOCIMIENTO ===\n";

        // Mostramos el numero de nodos
        resultado += "NODOS (" + this.nodos.getSize() + "):\n";
        // Mostramos la informacion de cada nodo
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<DN> n = nodos.get(i);
            // Suponiendo que el tipo y el id son Strings
            resultado += "  • " + n.toString() + n.getId() + "\n";
        }

        // Hacemos lo mismo con las aristas
        resultado += "\nARISTAS (" + this.aristas.getSize() + "):\n";

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<DN, DA> a = aristas.get(i);
            // Formato: Sujeto --(Predicado)--> Objeto
            resultado += "  " + a.getOrigen().getId() + " --(" + a.getPredicado() + ")--> " + a.getDestino().getId() + "\n";
        }

        resultado += "=============================";
        return resultado;
    }
}
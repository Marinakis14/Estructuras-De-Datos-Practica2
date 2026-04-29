package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

public class Grafo<T extends Comparable<T>> {

    // Lista de tipos de nodos del grafo
    private ListaSimplementeEnlazada<String> tipos;

    // Lista de nodos del grafo
    private ListaSimplementeEnlazada<NodoGrafo<T>> nodos;

    // Lista de aristas del grafo
    private ListaSimplementeEnlazada<Arista<T>> aristas;

    // Constructor
    public Grafo() {
        ListaSimplementeEnlazada<T> tipos = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<T> nodos = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<T> aristas = new ListaSimplementeEnlazada<>();
    }

    public ListaSimplementeEnlazada<String> getTipos() {
        return tipos;
    }

    public void setTipos(ListaSimplementeEnlazada<String> tipos) {
        this.tipos = tipos;
    }

    public ListaSimplementeEnlazada<NodoGrafo<T>> getNodos() {
        return nodos;
    }

    public void setNodos(ListaSimplementeEnlazada<NodoGrafo<T>> nodos) {
        this.nodos = nodos;
    }

    public ListaSimplementeEnlazada<Arista<T>> getAristas() {
        return aristas;
    }

    public void setAristas(ListaSimplementeEnlazada<Arista<T>> aristas) {
        this.aristas = aristas;
    }

    // Anade un tipo si no existe
    public void addTipo(String tipo) {
        if (!tipos.contains(tipo)) {
            tipos.addEnd(tipo);
        }
    }

    // Anade un nodo si no existe
    public void addNodo(NodoGrafo<T> nodo) {
        if (!nodos.contains(nodo)) {
            nodos.addEnd(nodo);
        }
    }

    // Anade una arista al grafo
    public void addArista(Arista<T> arista) {
        addNodo(arista.getOrigen());
        addNodo(arista.getDestino());
        aristas.addEnd(arista);
    }

    // Crea y anade una arista
    public void addArista(NodoGrafo<T> origen, String predicado, NodoGrafo<T> destino) {
        addArista(new Arista<>(origen, predicado, destino));
    }

    // Busca un nodo por su id
    public NodoGrafo<T> buscarNodoPorId(String id) {
        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<T> nodo = nodos.get(i);
            if (nodo.getId().equals(id)) {
                return nodo;
            }
        }
        return null;
    }

    // Devuelve los tipos de nodos conocidos
    public ListaSimplementeEnlazada<String> getTiposDeNodos() {
        ListaSimplementeEnlazada<String> resultado = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < tipos.getSize(); i++) {
            String tipo = tipos.get(i);
            if (!resultado.contains(tipo)) {
                resultado.addEnd(tipo);
            }
        }

        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<T> nodo = nodos.get(i);
            String id = nodo.getId();

            if (id.contains(":")) {
                String tipo = id.substring(0, id.indexOf(":"));

                if (!resultado.contains(tipo)) {
                    resultado.addEnd(tipo);
                }
            }
        }

        return resultado;
    }

    // Devuelve los vecinos salientes
    public ListaSimplementeEnlazada<NodoGrafo<T>> getVecinos(NodoGrafo<T> nodo) {
        ListaSimplementeEnlazada<NodoGrafo<T>> vecinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<T> arista = aristas.get(i);
            if (arista.getOrigen().equals(nodo)) {
                vecinos.addEnd(arista.getDestino());
            }
        }
        return vecinos;
    }

    // Calcula el camino mínimo
    public ListaSimplementeEnlazada<NodoGrafo<T>> caminoMinimo(String idOrigen, String idDestino) {
        NodoGrafo<T> origen = buscarNodoPorId(idOrigen);
        NodoGrafo<T> destino = buscarNodoPorId(idDestino);

        ListaSimplementeEnlazada<NodoGrafo<T>> camino = new ListaSimplementeEnlazada<>();
        if (origen == null || destino == null) {
            return camino;
        }

        ListaSimplementeEnlazada<NodoGrafo<T>> cola = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<T>> visitados = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<T>> anteriores = new ListaSimplementeEnlazada<>();

        cola.addEnd(origen);
        visitados.addEnd(origen);
        anteriores.addEnd(null);

        int indice = 0;
        boolean encontrado = false;

        while ( indice < cola.getSize() && !encontrado) {
            NodoGrafo<T> actual = cola.get(indice);

            if (actual.equals(destino)) {
                encontrado = true;
            } else {
                ListaSimplementeEnlazada<NodoGrafo<T>> vecinos = getVecinos(actual);

                for (int i = 0; i < vecinos.getSize(); i++) {
                    NodoGrafo<T> vecino = vecinos.get(i);
                    if (!visitados.contains(vecino)) {
                        visitados.addEnd(vecino);
                        cola.addEnd(vecino);
                        anteriores.addEnd(actual);
                    }
                }

                indice++;
            }
        }

        if (!visitados.contains(destino)) {
            return camino;
        }

        NodoGrafo<T> actual = destino;

        while (actual != null) {
            camino.addStart(actual);

            int posicion = visitados.getPosicion(actual);
            actual = anteriores.get(posicion);
        }

        return camino;
    }

    // Devuelve vecinos en ambos sentidos
    public ListaSimplementeEnlazada<NodoGrafo<T>> getVecinosNoDirigidos(NodoGrafo<T> nodo) {
        ListaSimplementeEnlazada<NodoGrafo<T>> vecinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<T> arista = aristas.get(i);
            if (arista.getOrigen().equals(nodo) && !vecinos.contains(arista.getDestino())) {
                vecinos.addEnd(arista.getDestino());
            }

            if (arista.getDestino().equals(nodo) && !vecinos.contains(arista.getOrigen())) {
                vecinos.addEnd(arista.getOrigen());
            }
        }

        return vecinos;
    }

    // Comprueba si el grafo está separado
    public boolean esDisjunto() {
        if (nodos.isEmpty()) {
            return false;
        }

        ListaSimplementeEnlazada<NodoGrafo<T>> visitados = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<T>> pendientes = new ListaSimplementeEnlazada<>();

        NodoGrafo<T> inicio = nodos.get(0);
        pendientes.addEnd(inicio);
        visitados.addEnd(inicio);

        int indice = 0;

        while (indice < pendientes.getSize()) {
            NodoGrafo<T> actual = pendientes.get(indice);
            ListaSimplementeEnlazada<NodoGrafo<T>> vecinos = getVecinosNoDirigidos(actual);

            for (int i = 0; i < vecinos.getSize(); i++) {
                NodoGrafo<T> vecino = vecinos.get(i);
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
    public ListaSimplementeEnlazada<NodoGrafo<T>> getDestinosPorPredicado(NodoGrafo<T> origen, String predicado) {
        ListaSimplementeEnlazada<NodoGrafo<T>> destinos = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<T> arista = aristas.get(i);
            if (arista.getOrigen().equals(origen) && arista.getPredicado().equals(predicado)) {
                destinos.addEnd(arista.getDestino());
            }
        }

        return destinos;
    }

    // Busca orígenes por predicado y destino
    public ListaSimplementeEnlazada<NodoGrafo<T>> getOrigenesPorPredicadoYDestino(String predicado, NodoGrafo<T> destino) {
        ListaSimplementeEnlazada<NodoGrafo<T>> origenes = new ListaSimplementeEnlazada<>();

        for (int i = 0; i < aristas.getSize(); i++) {
            Arista<T> arista = aristas.get(i);
            if (arista.getPredicado().equals(predicado) && arista.getDestino().equals(destino)) {
                origenes.addEnd(arista.getOrigen());
            }
        }

        return origenes;
    }

    // Busca personas nacidas en la misma ciudad
    public ListaSimplementeEnlazada<NodoGrafo<T>> personasMismaCiudadQue(String idPersona) {
        ListaSimplementeEnlazada<NodoGrafo<T>> resultado = new ListaSimplementeEnlazada<>();

        NodoGrafo<T> persona = buscarNodoPorId(idPersona);

        if (persona == null) {
            return resultado;
        }

        ListaSimplementeEnlazada<NodoGrafo<T>> lugares = getDestinosPorPredicado(persona, "nace_en");

        if (lugares.isEmpty()) {
            return resultado;
        }

        NodoGrafo<T> lugarNacimiento = lugares.get(0);

        ListaSimplementeEnlazada<NodoGrafo<T>> personas = getOrigenesPorPredicadoYDestino("nace_en", lugarNacimiento);

        for (int i = 0; i < personas.getSize(); i++) {
            NodoGrafo<T> otraPersona = personas.get(i);
            if (!otraPersona.equals(persona)) {
                resultado.addEnd(otraPersona);
            }
        }

        return resultado;
    }

    // Busca fisicos nacidos en la misma ciudad que una persona
    public ListaSimplementeEnlazada<NodoGrafo<T>> fisicosMismaCiudadQue(String idPersona) {
        ListaSimplementeEnlazada<NodoGrafo<T>> resultado = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<T>> personas = personasMismaCiudadQue(idPersona);

        for (int i = 0; i < personas.getSize(); i++) {
            NodoGrafo<T> persona = personas.get(i);
            ListaSimplementeEnlazada<NodoGrafo<T>> profesiones = getDestinosPorPredicado(persona, "profesion");

            for (int j = 0; j < profesiones.getSize(); j++) {
                NodoGrafo<T> profesion = profesiones.get(j);
                if (profesion.getId().equals("profesion:fisico") || profesion.getId().equals("fisico")) {
                    resultado.addEnd(persona);
                    break;
                }
            }
        }

        return resultado;
    }

    // Busca lugares de nacimiento de premios Nobel
    public ListaSimplementeEnlazada<NodoGrafo<T>> lugaresNacimientoPremiosNobel() {
        ListaSimplementeEnlazada<NodoGrafo<T>> lugares = new ListaSimplementeEnlazada<NodoGrafo<T>>();
        NodoGrafo<T> premioNobel = buscarNodoPorId("premio:Nobel");

        for (int i = 0; i < nodos.getSize(); i++) {
            NodoGrafo<T> persona = nodos.get(i);
            ListaSimplementeEnlazada<NodoGrafo<T>> premios = getDestinosPorPredicado(persona, "premio");
            ListaSimplementeEnlazada<NodoGrafo<T>> nobelesAntiguos = getDestinosPorPredicado(persona, "premio:Nobel");

            if ((premioNobel != null && premios.contains(premioNobel)) || !nobelesAntiguos.isEmpty()) {
                ListaSimplementeEnlazada<NodoGrafo<T>> nacimientos = getDestinosPorPredicado(persona, "nace_en");

                for (int j = 0; j < nacimientos.getSize(); j++) {
                    NodoGrafo<T> lugar = nacimientos.get(j);
                    if (!lugares.contains(lugar)) {
                        lugares.addEnd(lugar);
                    }
                }
            }
        }

        return lugares;
    }
}
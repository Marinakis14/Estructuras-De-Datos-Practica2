package Hoja2b.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    // Lista de nodos del grafo
    private List<Nodo> nodos;

    // Lista de aristas del grafo
    private List<Arista> aristas;

    // Constructor
    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }

    // Añade un nodo si no existe
    public void addNodo(Nodo nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    // Añade una arista al grafo
    public void addArista(Arista arista) {
        addNodo(arista.getOrigen());
        addNodo(arista.getDestino());
        aristas.add(arista);
    }

    // Crea y añade una arista
    public void addArista(Nodo origen, String predicado, Nodo destino) {
        addArista(new Arista(origen, predicado, destino));
    }

    // Busca un nodo por su id
    public Nodo buscarNodoPorId(String id) {
        for (Nodo nodo : nodos) {
            if (nodo.getId().equals(id)) {
                return nodo;
            }
        }
        return null;
    }

    // Devuelve los vecinos salientes
    public List<Nodo> getVecinos(Nodo nodo) {
        List<Nodo> vecinos = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(nodo)) {
                vecinos.add(arista.getDestino());
            }
        }
        return vecinos;
    }

    // Calcula el camino mínimo
    public List<Nodo> caminoMinimo(String idOrigen, String idDestino) {
        Nodo origen = buscarNodoPorId(idOrigen);
        Nodo destino = buscarNodoPorId(idDestino);

        List<Nodo> camino = new ArrayList<>();
        if (origen == null || destino == null) {
            return camino;
        }

        List<Nodo> cola = new ArrayList<>();
        List<Nodo> visitados = new ArrayList<>();
        List<Nodo> anteriores = new ArrayList<>();

        cola.add(origen);
        visitados.add(origen);
        anteriores.add(null);

        int indice = 0;
        boolean encontrado = false;

        while ( indice < cola.size() && !encontrado) {
            Nodo actual = cola.get(indice);

            if (actual.equals(destino)) {
                encontrado = true;
            } else {
                List<Nodo> vecinos = getVecinos(actual);

                for (Nodo vecino : vecinos) {
                    if (!visitados.contains(vecino)) {
                        visitados.add(vecino);
                        cola.add(vecino);
                        anteriores.add(actual);
                    }
                }

                indice++;
            }
        }

        if (!visitados.contains(destino)) {
            return camino;
        }

        Nodo actual = destino;

        while (actual != null) {
            camino.add(0, actual);

            int pos = visitados.indexOf(actual);
            actual = anteriores.get(pos);
        }

        return camino;
    }

    // Devuelve vecinos en ambos sentidos
    public List<Nodo> getVecinosNoDirigidos(Nodo nodo) {
        List<Nodo> vecinos = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(nodo) && !vecinos.contains(arista.getDestino())) {
                vecinos.add(arista.getDestino());
            }

            if (arista.getDestino().equals(nodo) && !vecinos.contains(arista.getOrigen())) {
                vecinos.add(arista.getOrigen());
            }
        }

        return vecinos;
    }

    // Comprueba si el grafo está separado
    public boolean esDisjunto() {
        if (nodos.isEmpty()) {
            return false;
        }

        List<Nodo> visitados = new ArrayList<>();
        List<Nodo> pendientes = new ArrayList<>();

        Nodo inicio = nodos.get(0);
        pendientes.add(inicio);
        visitados.add(inicio);

        int indice = 0;

        while (indice < pendientes.size()) {
            Nodo actual = pendientes.get(indice);
            List<Nodo> vecinos = getVecinosNoDirigidos(actual);

            for (Nodo vecino : vecinos) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    pendientes.add(vecino);
                }
            }

            indice++;
        }

        return visitados.size() != nodos.size();
    }

    // Busca destinos por predicado
    public List<Nodo> getDestinosPorPredicado(Nodo origen, String predicado) {
        List<Nodo> destinos = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(origen) && arista.getPredicado().equals(predicado)) {
                destinos.add(arista.getDestino());
            }
        }

        return destinos;
    }

    // Busca orígenes por predicado y destino
    public List<Nodo> getOrigenesPorPredicadoYDestino(String predicado, Nodo destino) {
        List<Nodo> origenes = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getPredicado().equals(predicado) && arista.getDestino().equals(destino)) {
                origenes.add(arista.getOrigen());
            }
        }

        return origenes;
    }

    // Busca personas nacidas en la misma ciudad
    public List<Nodo> personasMismaCiudadQue(String idPersona) {
        List<Nodo> resultado = new ArrayList<>();

        Nodo persona = buscarNodoPorId(idPersona);

        if (persona == null) {
            return resultado;
        }

        List<Nodo> lugares = getDestinosPorPredicado(persona, "nace_en");

        if (lugares.isEmpty()) {
            return resultado;
        }

        Nodo lugarNacimiento = lugares.get(0);

        List<Nodo> personas = getOrigenesPorPredicadoYDestino("nace_en", lugarNacimiento);

        for (Nodo otraPersona : personas) {
            if (!otraPersona.equals(persona)) {
                resultado.add(otraPersona);
            }
        }

        return resultado;
    }

    // Busca lugares de nacimiento de premios Nobel
    public List<Nodo> lugaresNacimientoPremiosNobel() {
        List<Nodo> lugares = new ArrayList<>();

        for (Nodo persona : nodos) {
            List<Nodo> nobeles = getDestinosPorPredicado(persona, "premio:Nobel");

            if (!nobeles.isEmpty()) {
                List<Nodo> nacimientos = getDestinosPorPredicado(persona, "nace_en");

                for (Nodo lugar : nacimientos) {
                    if (!lugares.contains(lugar)) {
                        lugares.add(lugar);
                    }
                }
            }
        }

        return lugares;
    }
}
package Hoja2b.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    // Lista que almacena todos los nodos del grafo
    private List<Nodo> nodos;

    // Lista que almacenará las aristas
    private List<Arista> aristas;

    // Constructor: inicializa las listas vacías
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


    // Añade un nodo al grafo si no está ya incluido
    public void addNodo(Nodo nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public void addArista(Arista arista) {
        addNodo(arista.getOrigen());
        addNodo(arista.getDestino());
        aristas.add(arista);
    }


    public void addArista(Nodo origen, String predicado, Nodo destino) {
        addArista(new Arista(origen, predicado, destino));
    }

    public Nodo buscarNodoPorId(String id) {
        for (Nodo nodo : nodos) {
            if (nodo.getId().equals(id)) {
                return nodo;
            }
        }
        return null;
    }
    public List<Nodo> getVecinos(Nodo nodo) {
        List<Nodo> vecinos = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(nodo)) {
                vecinos.add(arista.getDestino());
            }
        }
        return vecinos;
    }
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

    public List<Nodo> getDestinosPorPredicado(Nodo origen, String predicado) {
        List<Nodo> destinos = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(origen) && arista.getPredicado().equals(predicado)) {
                destinos.add(arista.getDestino());
            }
        }

        return destinos;
    }

    public List<Nodo> getOrigenesPorPredicadoYDestino(String predicado, Nodo destino) {
        List<Nodo> origenes = new ArrayList<>();

        for (Arista arista : aristas) {
            if (arista.getPredicado().equals(predicado) && arista.getDestino().equals(destino)) {
                origenes.add(arista.getOrigen());
            }
        }

        return origenes;
    }

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
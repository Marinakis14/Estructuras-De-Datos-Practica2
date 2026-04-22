package Hoja2b.Grafos;

public class MainGrafo {
    public static void main(String[] args) {
        Grafo grafo = LectorGrafoJson.cargarDesdeJson("datos.json");

        System.out.println("Nodos:");
        System.out.println(grafo.getNodos());

        System.out.println("Aristas:");
        System.out.println(grafo.getAristas());

        System.out.println("Camino mínimo de Einstein a Ulm:");
        System.out.println(grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm"));

        System.out.println("¿El grafo es disjunto?");
        System.out.println(grafo.esDisjunto());

        Nodo ulm = grafo.buscarNodoPorId("lugar:Ulm");
        System.out.println("Personas nacidas en Ulm:");
        System.out.println(grafo.getOrigenesPorPredicadoYDestino("nace_en", ulm));

        System.out.println("Personas nacidas en la misma ciudad que Einstein:");
        System.out.println(grafo.personasMismaCiudadQue("persona:Albert Einstein"));

        System.out.println(grafo.lugaresNacimientoPremiosNobel());

        Grafo grafo1 = LectorGrafoJson.cargarDesdeJson("disjunto.json");
        System.out.println("¿El grafo disjunto es disjunto?");
        System.out.println(grafo1.esDisjunto());

        Grafo grafo2 = LectorGrafoJson.cargarDesdeJson("no_disjunto.json");
        System.out.println("¿El grafo no disjunto es disjunto?");
        System.out.println(grafo2.esDisjunto());
    }

}
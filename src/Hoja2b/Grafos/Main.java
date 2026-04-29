package Hoja2b.Grafos;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== PRUEBA CON datos.json ===");
        Grafo<String> grafo = LectorGrafoJson.cargarDesdeJson("datos.json");

        System.out.println("Tipos de nodos:");
        System.out.println(grafo.getTiposDeNodos());

        System.out.println("Nodos:");
        System.out.println(grafo.getNodos());

        System.out.println("Aristas:");
        System.out.println(grafo.getAristas());

        System.out.println("\nCamino minimo entre Albert Einstein y Ulm:");
        System.out.println(grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm"));

        System.out.println("\nPersonas nacidas en la misma ciudad que Einstein:");
        System.out.println(grafo.personasMismaCiudadQue("persona:Albert Einstein"));

        System.out.println("\nFisicos nacidos en la misma ciudad que Einstein:");
        System.out.println(grafo.fisicosMismaCiudadQue("persona:Albert Einstein"));

        System.out.println("\nLugares de nacimiento de los premios Nobel:");
        System.out.println(grafo.lugaresNacimientoPremiosNobel());

        System.out.println("\n¿El grafo de datos.json es disjunto?");
        System.out.println(grafo.esDisjunto());


        System.out.println("\n=== PRUEBA CON disjunto.json ===");
        Grafo<String> grafoDisjunto = LectorGrafoJson.cargarDesdeJson("disjunto.json");

        System.out.println("Nodos:");
        System.out.println(grafoDisjunto.getNodos());

        System.out.println("Aristas:");
        System.out.println(grafoDisjunto.getAristas());

        System.out.println("¿Es disjunto?");
        System.out.println(grafoDisjunto.esDisjunto());


        System.out.println("\n=== PRUEBA CON nodisjunto.json ===");
        Grafo<String> grafoNoDisjunto = LectorGrafoJson.cargarDesdeJson("nodisjunto.json");

        System.out.println("Nodos:");
        System.out.println(grafoNoDisjunto.getNodos());

        System.out.println("Aristas:");
        System.out.println(grafoNoDisjunto.getAristas());

        System.out.println("¿Es disjunto?");
        System.out.println(grafoNoDisjunto.esDisjunto());
    }
}
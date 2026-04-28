package Hoja2b.Grafos;

public class Main {
    public static void main(String[] args) {

        Grafo grafo = LectorGrafoJson.cargarDesdeJson("datos.json");

        System.out.println("Nodos:");
        System.out.println(grafo.getNodos());

        System.out.println("Aristas:");
        System.out.println(grafo.getAristas());

        System.out.println("Camino mínimo entre Einstein y Ulm:");
        System.out.println(grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm"));

        System.out.println("Personas nacidas en la misma ciudad que Einstein:");
        System.out.println(grafo.personasMismaCiudadQue("persona:Albert Einstein"));

        System.out.println("Lugares de nacimiento de los premios Nobel:");
        System.out.println(grafo.lugaresNacimientoPremiosNobel());

        Grafo grafoDisjunto = LectorGrafoJson.cargarDesdeJson("disjunto.json");
        System.out.println("¿disjunto.json es disjunto?");
        System.out.println(grafoDisjunto.esDisjunto());

        Grafo grafoNoDisjunto = LectorGrafoJson.cargarDesdeJson("nodisjunto.json");
        System.out.println("¿nodisjunto.json es disjunto?");
        System.out.println(grafoNoDisjunto.esDisjunto());
    }
}

package Hoja2b.Grafos;

public class Main {
    public static void main(String[] args) {

        Grafo grafo = LectorGrafoJson.cargarDesdeJson("datos.json");
        System.out.println(grafo);
        System.out.println();

        System.out.println("Tipos de nodos:");
        System.out.println(grafo.getTiposDeNodos());
        System.out.println();

        System.out.println("Nodos:");
        System.out.println(grafo.getNodos());
        System.out.println();

        System.out.println("Aristas:");
        System.out.println(grafo.getAristas());
        System.out.println();

        System.out.println("Camino minimo entre Einstein y Alemania:");
        System.out.println(grafo.caminoMinimo("persona:Albert Einstein", "pais:Alemania"));
        System.out.println();

        System.out.println("Personas nacidas en la misma ciudad que Einstein:");
        System.out.println(grafo.personasMismaCiudadQue("persona:Albert Einstein"));
        System.out.println();

        System.out.println("Fisicos nacidos en la misma ciudad que Einstein:");
        System.out.println(grafo.fisicosMismaCiudadQue("persona:Albert Einstein"));
        System.out.println();

        grafo.addArista(
                new NodoGrafo(1024L,"persona:Antonio"),
                "nace_en",
                new NodoGrafo(1025L,"lugar:Villarrubia de los Caballeros")
        );
        System.out.println();

        System.out.println("Lugares de nacimiento de los premios Nobel:");
        System.out.println(grafo.lugaresNacimientoPremiosNobel());
        System.out.println();

        Grafo grafoDisjunto = LectorGrafoJson.cargarDesdeJson("disjunto.json");
        System.out.println(grafoDisjunto);
        System.out.println("disjunto.json es disjunto?");
        System.out.println(grafoDisjunto.esDisjunto());
        System.out.println();

        Grafo grafoNoDisjunto = LectorGrafoJson.cargarDesdeJson("nodisjunto.json");
        System.out.println("nodisjunto.json es disjunto?");
        System.out.println(grafoNoDisjunto.esDisjunto());
        System.out.println();
    }
}

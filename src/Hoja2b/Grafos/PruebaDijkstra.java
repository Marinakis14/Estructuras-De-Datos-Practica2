package Hoja2b.Grafos;

public class PruebaDijkstra {
    static void main(String[] args) {
        // Generamos un grafo para probar Dijkstra
        Grafo<String, DatoAristaConPeso> grafo = new Grafo<>();

        NodoGrafo<String> madrid = new NodoGrafo<>("Madrid");
        NodoGrafo<String> barcelona = new NodoGrafo<>("Barcelona");
        NodoGrafo<String> valencia = new NodoGrafo<>("Valencia");
        NodoGrafo<String> sevilla = new NodoGrafo<>("Sevilla");
        NodoGrafo<String> bilbao = new NodoGrafo<>("Bilbao");
        NodoGrafo<String> zaragoza = new NodoGrafo<>("Zaragoza");
        NodoGrafo<String> malaga = new NodoGrafo<>("Malaga");
        NodoGrafo<String> coruña = new NodoGrafo<>("A Coruña");
        NodoGrafo<String> valladolid = new NodoGrafo<>("Valladolid");
        NodoGrafo<String> badajoz = new NodoGrafo<>("Badajoz");

        grafo.addNodo(madrid);
        grafo.addNodo(barcelona);
        grafo.addNodo(valencia);
        grafo.addNodo(sevilla);
        grafo.addNodo(bilbao);
        grafo.addNodo(zaragoza);
        grafo.addNodo(malaga);
        grafo.addNodo(coruña);
        grafo.addNodo(valladolid);
        grafo.addNodo(badajoz);

        DatoAristaConPeso a2_mad_zar = new DatoAristaConPeso(315, "A-2");
        grafo.addArista(new Arista<>(madrid, a2_mad_zar, zaragoza));

        DatoAristaConPeso a3_mad_val = new DatoAristaConPeso(355, "A-3");
        grafo.addArista(new Arista<>(madrid, a3_mad_val, valencia));

        DatoAristaConPeso a4_mad_sev = new DatoAristaConPeso(530, "A-4");
        grafo.addArista(new Arista<>(madrid, a4_mad_sev, sevilla));

        DatoAristaConPeso a5_mad_bad = new DatoAristaConPeso(400, "A-5");
        grafo.addArista(new Arista<>(madrid, a5_mad_bad, badajoz));

        DatoAristaConPeso a6_mad_val = new DatoAristaConPeso(190, "A-6");
        grafo.addArista(new Arista<>(madrid, a6_mad_val, valladolid));

        DatoAristaConPeso a1_mad_bil = new DatoAristaConPeso(400, "A-1");
        grafo.addArista(new Arista<>(madrid, a1_mad_bil, bilbao));

        DatoAristaConPeso ap2_zar_bar = new DatoAristaConPeso(300, "AP-2");
        grafo.addArista(new Arista<>(zaragoza, ap2_zar_bar, barcelona));

        DatoAristaConPeso ap7_bar_val = new DatoAristaConPeso(350, "AP-7");
        grafo.addArista(new Arista<>(barcelona, ap7_bar_val, valencia));

        DatoAristaConPeso a45_sev_mal = new DatoAristaConPeso(205, "A-45");
        grafo.addArista(new Arista<>(sevilla, a45_sev_mal, malaga));

        DatoAristaConPeso a6_val_cor = new DatoAristaConPeso(450, "A-6");
        grafo.addArista(new Arista<>(valladolid, a6_val_cor, coruña));

        DatoAristaConPeso a68_bil_zar = new DatoAristaConPeso(300, "A-68");
        grafo.addArista(new Arista<>(bilbao, a68_bil_zar, zaragoza));

        DatoAristaConPeso a66_bad_sev = new DatoAristaConPeso(215, "A-66");
        grafo.addArista(new Arista<>(badajoz, a66_bad_sev, sevilla));

        /**
         * No nos ha dado tiempo a terminar esta parte pero nos gustaria añadirla en un futuro y por eso no hemos
         * borrado la estructura
         */
    }
}

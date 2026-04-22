package Hoja2b.Grafos;

public class MainGrafo {
    public static void main(String[] args) {

        Grafo g = new Grafo();

        g.agregarArista("persona:Albert Einstein", "nace_en", "lugar:Ulm");
        g.agregarArista("persona:Albert Einstein", "premio:Nobel", "1921");

        g.imprimirGrafo();
    }
}
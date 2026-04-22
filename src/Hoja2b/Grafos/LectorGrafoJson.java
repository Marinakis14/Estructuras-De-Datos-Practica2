package Hoja2b.Grafos;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class LectorGrafoJson {

    public static Grafo cargarDesdeJson(String ruta) {
        Grafo grafo = new Grafo();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {
            DatosGrafoJson datos = gson.fromJson(reader, DatosGrafoJson.class);

            for (TripletaJson tripleta : datos.getTripletas()) {
                Nodo origen = new Nodo(tripleta.getS());
                Nodo destino = new Nodo(tripleta.getO());
                String predicado = tripleta.getP();

                grafo.addArista(origen, predicado, destino);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return grafo;
    }
}

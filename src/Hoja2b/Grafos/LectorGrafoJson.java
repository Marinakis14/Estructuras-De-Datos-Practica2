package Hoja2b.Grafos;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.FileReader;
import java.io.IOException;

public class LectorGrafoJson {

    public static Grafo<String, String> cargarDesdeJson(String ruta) {
        Grafo<String, String> grafo = new Grafo<String, String>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {
            DatosGrafoJson datos = gson.fromJson(reader, DatosGrafoJson.class);

            // comprobamos que el json no esta vacio
            if (datos == null) {
                return grafo; // si lo esta devolvemos un grafo vacio
            }

            if (datos.getTripletas() != null) {
                for (TripletaJson tripleta : datos.getTripletas()) {
                    // Tenemos que tener en cuenta que todos los datos tienen el formato
                    // 's': "tipo: id"
                    // 'p': "id"
                    // 'o': "tipo: id"

                    // Vemos primero el sujeto
                    String[] partesS = tripleta.getS().split(":"); // separamos ambos terminos
                    String tipoS = partesS[0];
                    String idS = partesS[1];

                    // miramos el id, si ya estaba usamos el que ya teniamos, si no estaba creamos uno nuevo
                    NodoGrafo<String> sujeto = grafo.buscarNodoPorId(idS);
                    if (sujeto == null) { // si no estaba
                        sujeto = new NodoGrafo<>(idS, tipoS);
                        grafo.addNodo(sujeto); // añadimos el nodo al grafo
                    }
                    // Hacemos lo mismo con el origen
                    String[] partesO = tripleta.getO().split(":"); // separamos ambos terminos
                    String tipoO = partesO[0];
                    String idO = partesO[1];

                    NodoGrafo<String> origen = grafo.buscarNodoPorId(idO);
                    if (origen == null) { // si no estaba
                        origen = new NodoGrafo<>(idO, tipoO);
                        grafo.addNodo(origen); // añadimos el nodo al grafo
                    }
                    // Por ultimo creamos la arista conectando ambos nodos
                    String predicado = tripleta.getP();
                    grafo.addArista(sujeto,predicado,origen);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return grafo;
    }
}
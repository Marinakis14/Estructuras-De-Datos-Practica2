package Hoja2b.Grafos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Clase que lee un JSON y construye un grafo
public class LectorGrafoJson {

    // Carga el grafo desde un archivo JSON
    public static Grafo cargarDesdeJson(String ruta) {
        Grafo grafo = new Grafo();

        try {
            // Lee todas las líneas del archivo
            List<String> lineas = Files.readAllLines(Paths.get(ruta));

            // Variables para la tripleta
            String s = null;
            String p = null;
            String o = null;

            // Recorre cada línea
            for (String linea : lineas) {
                linea = linea.trim();

                // Detecta sujeto
                if (linea.startsWith("\"s\"")) {
                    s = extraerValor(linea);
                    // Detecta predicado
                } else if (linea.startsWith("\"p\"")) {
                    p = extraerValor(linea);
                    // Detecta objeto
                } else if (linea.startsWith("\"o\"")) {
                    o = extraerValor(linea);
                }

                // Cuando tiene la tripleta completa, crea la arista
                if (s != null && p != null && o != null) {
                    Nodo origen = new Nodo(s);
                    Nodo destino = new Nodo(o);
                    grafo.addArista(origen, p, destino);

                    // Reinicia para la siguiente tripleta
                    s = null;
                    p = null;
                    o = null;
                }
            }

        } catch (IOException e) {
            // Error al leer el archivo
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Devuelve el grafo construido
        return grafo;
    }

    // Extrae el valor de una línea tipo "clave": "valor"
    private static String extraerValor(String linea) {
        int posDosPuntos = linea.indexOf(":");
        String valor = linea.substring(posDosPuntos + 1).trim();

        // Quita coma final
        if (valor.endsWith(",")) {
            valor = valor.substring(0, valor.length() - 1);
        }

        // Quita comillas
        if (valor.startsWith("\"") && valor.endsWith("\"")) {
            valor = valor.substring(1, valor.length() - 1);
        }

        return valor;
    }
}
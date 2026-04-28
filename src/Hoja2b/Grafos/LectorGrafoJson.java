package Hoja2b.Grafos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LectorGrafoJson {

    public static Grafo cargarDesdeJson(String ruta) {
        Grafo grafo = new Grafo();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(ruta));

            String s = null;
            String p = null;
            String o = null;

            for (String linea : lineas) {
                linea = linea.trim();

                if (linea.startsWith("\"s\"")) {
                    s = extraerValor(linea);
                } else if (linea.startsWith("\"p\"")) {
                    p = extraerValor(linea);
                } else if (linea.startsWith("\"o\"")) {
                    o = extraerValor(linea);
                }

                if (s != null && p != null && o != null) {
                    Nodo origen = new Nodo(s);
                    Nodo destino = new Nodo(o);
                    grafo.addArista(origen, p, destino);

                    s = null;
                    p = null;
                    o = null;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return grafo;
    }

    private static String extraerValor(String linea) {
        int posDosPuntos = linea.indexOf(":");
        String valor = linea.substring(posDosPuntos + 1).trim();

        if (valor.endsWith(",")) {
            valor = valor.substring(0, valor.length() - 1);
        }

        if (valor.startsWith("\"") && valor.endsWith("\"")) {
            valor = valor.substring(1, valor.length() - 1);
        }

        return valor;
    }
}

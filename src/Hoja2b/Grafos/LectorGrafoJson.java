package Hoja2b.Grafos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorGrafoJson {

    public static Grafo<String> cargarDesdeJson(String ruta) {
        Grafo<String> grafo = new Grafo<>();

        try {
            String contenido = Files.readString(Paths.get(ruta));
            String[] lineas = contenido.split("\n");

            String s = null;
            String p = null;
            String o = null;

            for (int i = 0; i < lineas.length; i++) {
                String linea = lineas[i].trim();

                if (linea.startsWith("\"s\"")) {
                    s = sacarValor(linea);
                } else if (linea.startsWith("\"p\"")) {
                    p = sacarValor(linea);
                } else if (linea.startsWith("\"o\"")) {
                    o = sacarValor(linea);
                }

                if (s != null && p != null && o != null) {
                    NodoGrafo<String> origen = new NodoGrafo<>(s);
                    NodoGrafo<String> destino = new NodoGrafo<>(o);
                    grafo.addArista(origen, p, destino);

                    s = null;
                    p = null;
                    o = null;
                }

                if (linea.contains("\"tipos\"")) {
                    while (!linea.contains("]")) {
                        i++;
                        linea = lineas[i].trim();

                        if (linea.contains("\"")) {
                            String tipo = sacarValorTipo(linea);
                            if (tipo != null && !tipo.equals("")) {
                                grafo.addTipo(tipo);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return grafo;
    }

    private static String sacarValor(String linea) {
        int primero = linea.indexOf("\"", linea.indexOf(":")) + 1;
        int ultimo = linea.lastIndexOf("\"");
        return linea.substring(primero, ultimo);
    }

    private static String sacarValorTipo(String linea) {
        int primero = linea.indexOf("\"") + 1;
        int ultimo = linea.lastIndexOf("\"");

        if (primero <= 0 || ultimo <= primero) {
            return "";
        }

        return linea.substring(primero, ultimo).replace(",", "");
    }
}
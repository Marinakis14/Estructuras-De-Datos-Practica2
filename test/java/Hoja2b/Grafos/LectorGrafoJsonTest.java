package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class LectorGrafoJsonTest {

    @Test
    public void testCargarDesdeJsonCorrectamente() throws IOException {
        Path archivoTemporal = Files.createTempFile("grafo_test", ".json");

        String contenidoJson = """
                {
                  "tipos": [
                    "persona",
                    "premio",
                    "lugar",
                    "pais",
                    "profesion"
                  ],
                  "tripletas": [
                    {
                      "s": "persona:Albert Einstein",
                      "p": "nace_en",
                      "o": "lugar:Ulm"
                    },
                    {
                      "s": "persona:Albert Einstein",
                      "p": "premio",
                      "o": "premio:Nobel"
                    },
                    {
                      "s": "persona:Ventura Pacheco",
                      "p": "nace_en",
                      "o": "lugar:Ulm"
                    }
                  ]
                }
                """;

        try (FileWriter writer = new FileWriter(archivoTemporal.toFile())) {
            writer.write(contenidoJson);
        }

        Grafo<String> grafo = LectorGrafoJson.cargarDesdeJson(archivoTemporal.toString());

        assertNotNull(grafo);

        assertNotNull(grafo.getTipos());
        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());

        assertTrue(grafo.getTipos().contains("persona"));
        assertTrue(grafo.getTipos().contains("premio"));
        assertTrue(grafo.getTipos().contains("lugar"));
        assertTrue(grafo.getTipos().contains("pais"));
        assertTrue(grafo.getTipos().contains("profesion"));

        assertEquals(4, grafo.getNodos().getSize());
        assertEquals(3, grafo.getAristas().getSize());

        assertNotNull(grafo.buscarNodoPorId("persona:Albert Einstein"));
        assertNotNull(grafo.buscarNodoPorId("lugar:Ulm"));
        assertNotNull(grafo.buscarNodoPorId("premio:Nobel"));
        assertNotNull(grafo.buscarNodoPorId("persona:Ventura Pacheco"));

        Arista<String> primera = grafo.getAristas().get(0);
        assertEquals("persona:Albert Einstein", primera.getOrigen().getId());
        assertEquals("nace_en", primera.getDato());
        assertEquals("lugar:Ulm", primera.getDestino().getId());
    }

    @Test
    public void testCargarDesdeJsonRutaIncorrecta() {
        Grafo<String> grafo = LectorGrafoJson.cargarDesdeJson("archivo_que_no_existe.json");

        assertNotNull(grafo);
        assertNotNull(grafo.getTipos());
        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());

        assertTrue(grafo.getTipos().isEmpty());
        assertTrue(grafo.getNodos().isEmpty());
        assertTrue(grafo.getAristas().isEmpty());
    }
}
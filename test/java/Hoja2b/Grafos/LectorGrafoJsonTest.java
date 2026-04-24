package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LectorGrafoJsonTest {

    @Test
    public void testCargarDesdeJsonCorrectamente() throws IOException {
        Path archivoTemporal = Files.createTempFile("grafo_test", ".json");

        String contenidoJson = """
                {
                  "tipos": ["tipo", "persona", "premio", "lugar"],
                  "tripletas": [
                    {
                      "s": "persona:Albert Einstein",
                      "p": "nace_en",
                      "o": "lugar:Ulm"
                    },
                    {
                      "s": "persona:Albert Einstein",
                      "p": "premio:Nobel",
                      "o": "1921"
                    },
                    {
                      "s": "persona:Antonio",
                      "p": "nace_en",
                      "o": "lugar:Villarrubia de los Caballeros"
                    }
                  ]
                }
                """;

        try (FileWriter writer = new FileWriter(archivoTemporal.toFile())) {
            writer.write(contenidoJson);
        }

        Grafo grafo = LectorGrafoJson.cargarDesdeJson(archivoTemporal.toString());

        assertNotNull(grafo);
        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());

        assertEquals(5, grafo.getNodos().size());
        assertEquals(3, grafo.getAristas().size());

        assertNotNull(grafo.buscarNodoPorId("persona:Albert Einstein"));
        assertNotNull(grafo.buscarNodoPorId("lugar:Ulm"));
        assertNotNull(grafo.buscarNodoPorId("1921"));
        assertNotNull(grafo.buscarNodoPorId("persona:Antonio"));
        assertNotNull(grafo.buscarNodoPorId("lugar:Villarrubia de los Caballeros"));

        List<Arista> aristas = grafo.getAristas();

        assertEquals("persona:Albert Einstein", aristas.get(0).getOrigen().getId());
        assertEquals("nace_en", aristas.get(0).getPredicado());
        assertEquals("lugar:Ulm", aristas.get(0).getDestino().getId());
    }

    @Test
    public void testCargarDesdeJsonConRutaIncorrecta() {
        Grafo grafo = LectorGrafoJson.cargarDesdeJson("archivo_que_no_existe.json");

        assertNotNull(grafo);
        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());
        assertTrue(grafo.getNodos().isEmpty());
        assertTrue(grafo.getAristas().isEmpty());
    }
}
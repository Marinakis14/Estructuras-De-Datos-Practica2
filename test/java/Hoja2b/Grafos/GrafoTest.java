package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {

    @Test
    public void testConstructor() {
        Grafo grafo = new Grafo();

        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());
        assertTrue(grafo.getNodos().isEmpty());
        assertTrue(grafo.getAristas().isEmpty());
    }

    @Test
    public void testSettersYGetters() {
        Grafo grafo = new Grafo();

        List<Nodo> nodos = new ArrayList<>();
        List<Arista> aristas = new ArrayList<>();

        Nodo n1 = new Nodo("persona:Albert Einstein");
        Nodo n2 = new Nodo("lugar:Ulm");
        Arista a1 = new Arista(n1, "nace_en", n2);

        nodos.add(n1);
        nodos.add(n2);
        aristas.add(a1);

        grafo.setNodos(nodos);
        grafo.setAristas(aristas);

        assertEquals(nodos, grafo.getNodos());
        assertEquals(aristas, grafo.getAristas());
    }

    @Test
    public void testAddNodo() {
        Grafo grafo = new Grafo();
        Nodo nodo = new Nodo("persona:Albert Einstein");

        grafo.addNodo(nodo);
        grafo.addNodo(nodo);

        assertEquals(1, grafo.getNodos().size());
        assertTrue(grafo.getNodos().contains(nodo));
    }

    @Test
    public void testAddAristaConObjetoArista() {
        Grafo grafo = new Grafo();
        Nodo origen = new Nodo("persona:Albert Einstein");
        Nodo destino = new Nodo("lugar:Ulm");
        Arista arista = new Arista(origen, "nace_en", destino);

        grafo.addArista(arista);

        assertEquals(2, grafo.getNodos().size());
        assertEquals(1, grafo.getAristas().size());
        assertTrue(grafo.getNodos().contains(origen));
        assertTrue(grafo.getNodos().contains(destino));
        assertTrue(grafo.getAristas().contains(arista));
    }

    @Test
    public void testAddAristaConParametros() {
        Grafo grafo = new Grafo();
        Nodo origen = new Nodo("persona:Albert Einstein");
        Nodo destino = new Nodo("lugar:Ulm");

        grafo.addArista(origen, "nace_en", destino);

        assertEquals(2, grafo.getNodos().size());
        assertEquals(1, grafo.getAristas().size());
        assertEquals("persona:Albert Einstein", grafo.getAristas().get(0).getOrigen().getId());
        assertEquals("nace_en", grafo.getAristas().get(0).getPredicado());
        assertEquals("lugar:Ulm", grafo.getAristas().get(0).getDestino().getId());
    }

    @Test
    public void testBuscarNodoPorIdExistente() {
        Grafo grafo = new Grafo();
        Nodo nodo = new Nodo("persona:Albert Einstein");
        grafo.addNodo(nodo);

        Nodo encontrado = grafo.buscarNodoPorId("persona:Albert Einstein");

        assertEquals(nodo, encontrado);
    }

    @Test
    public void testBuscarNodoPorIdNoExistente() {
        Grafo grafo = new Grafo();

        Nodo encontrado = grafo.buscarNodoPorId("persona:Marie Curie");

        assertNull(encontrado);
    }

    @Test
    public void testGetVecinos() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo premio = new Nodo("1921");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(einstein, "premio:Nobel", premio);

        List<Nodo> vecinos = grafo.getVecinos(einstein);

        assertEquals(2, vecinos.size());
        assertTrue(vecinos.contains(ulm));
        assertTrue(vecinos.contains(premio));
    }

    @Test
    public void testCaminoMinimoExistente() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo alemania = new Nodo("pais:Alemania");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ulm, "esta_en", alemania);

        List<Nodo> camino = grafo.caminoMinimo("persona:Albert Einstein", "pais:Alemania");

        assertEquals(3, camino.size());
        assertEquals(einstein, camino.get(0));
        assertEquals(ulm, camino.get(1));
        assertEquals(alemania, camino.get(2));
    }

    @Test
    public void testCaminoMinimoConOrigenODestinoNoExistente() {
        Grafo grafo = new Grafo();

        List<Nodo> camino = grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testCaminoMinimoSinCamino() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo antonio = new Nodo("persona:Antonio");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addNodo(antonio);

        List<Nodo> camino = grafo.caminoMinimo("persona:Albert Einstein", "persona:Antonio");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testGetVecinosNoDirigidos() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);

        List<Nodo> vecinosEinstein = grafo.getVecinosNoDirigidos(einstein);
        List<Nodo> vecinosUlm = grafo.getVecinosNoDirigidos(ulm);

        assertEquals(1, vecinosEinstein.size());
        assertTrue(vecinosEinstein.contains(ulm));

        assertEquals(1, vecinosUlm.size());
        assertTrue(vecinosUlm.contains(einstein));
    }

    @Test
    public void testEsDisjuntoEnGrafoVacio() {
        Grafo grafo = new Grafo();

        assertFalse(grafo.esDisjunto());
    }

    @Test
    public void testEsDisjuntoTrue() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo antonio = new Nodo("persona:Antonio");
        Nodo villarrubia = new Nodo("lugar:Villarrubia");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(antonio, "nace_en", villarrubia);

        assertTrue(grafo.esDisjunto());
    }

    @Test
    public void testEsDisjuntoFalse() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo antonio = new Nodo("persona:Antonio");
        Nodo ulm = new Nodo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(antonio, "nace_en", ulm);

        assertFalse(grafo.esDisjunto());
    }

    @Test
    public void testGetDestinosPorPredicado() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo premio = new Nodo("1921");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(einstein, "premio:Nobel", premio);

        List<Nodo> destinosNacimiento = grafo.getDestinosPorPredicado(einstein, "nace_en");
        List<Nodo> destinosPremio = grafo.getDestinosPorPredicado(einstein, "premio:Nobel");

        assertEquals(1, destinosNacimiento.size());
        assertTrue(destinosNacimiento.contains(ulm));

        assertEquals(1, destinosPremio.size());
        assertTrue(destinosPremio.contains(premio));
    }

    @Test
    public void testGetOrigenesPorPredicadoYDestino() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ventura = new Nodo("persona:Ventura Pacheco");
        Nodo ulm = new Nodo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ventura, "nace_en", ulm);

        List<Nodo> origenes = grafo.getOrigenesPorPredicadoYDestino("nace_en", ulm);

        assertEquals(2, origenes.size());
        assertTrue(origenes.contains(einstein));
        assertTrue(origenes.contains(ventura));
    }

    @Test
    public void testPersonasMismaCiudadQue() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo ventura = new Nodo("persona:Ventura Pacheco");
        Nodo marcos = new Nodo("persona:Marcos Castro");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo varsovia = new Nodo("lugar:Varsovia");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ventura, "nace_en", ulm);
        grafo.addArista(marcos, "nace_en", varsovia);

        List<Nodo> resultado = grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(ventura));
        assertFalse(resultado.contains(einstein));
        assertFalse(resultado.contains(marcos));
    }

    @Test
    public void testPersonasMismaCiudadQuePersonaNoExiste() {
        Grafo grafo = new Grafo();

        List<Nodo> resultado = grafo.personasMismaCiudadQue("persona:NoExiste");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testPersonasMismaCiudadQueSinNacimiento() {
        Grafo grafo = new Grafo();
        Nodo persona = new Nodo("persona:Albert Einstein");
        grafo.addNodo(persona);

        List<Nodo> resultado = grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLugaresNacimientoPremiosNobel() {
        Grafo grafo = new Grafo();
        Nodo einstein = new Nodo("persona:Albert Einstein");
        Nodo marcos = new Nodo("persona:Marcos Castro");
        Nodo antonio = new Nodo("persona:Antonio");
        Nodo ulm = new Nodo("lugar:Ulm");
        Nodo varsovia = new Nodo("lugar:Varsovia");
        Nodo villarrubia = new Nodo("lugar:Villarrubia de los Caballeros");

        grafo.addArista(einstein, "premio:Nobel", new Nodo("1921"));
        grafo.addArista(einstein, "nace_en", ulm);

        grafo.addArista(marcos, "premio:Nobel", new Nodo("2005"));
        grafo.addArista(marcos, "nace_en", varsovia);

        grafo.addArista(antonio, "nace_en", villarrubia);

        List<Nodo> lugares = grafo.lugaresNacimientoPremiosNobel();

        assertEquals(2, lugares.size());
        assertTrue(lugares.contains(ulm));
        assertTrue(lugares.contains(varsovia));
        assertFalse(lugares.contains(villarrubia));
    }

    public static class AristaTest {

        @Test
        public void testConstructorYGetters() {
            Nodo origen = new Nodo("persona:Albert Einstein");
            Nodo destino = new Nodo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            assertEquals(origen, arista.getOrigen());
            assertEquals("nace_en", arista.getPredicado());
            assertEquals(destino, arista.getDestino());
        }

        @Test
        public void testSetOrigen() {
            Nodo origenInicial = new Nodo("persona:Albert Einstein");
            Nodo destino = new Nodo("lugar:Ulm");
            Arista arista = new Arista(origenInicial, "nace_en", destino);

            Nodo nuevoOrigen = new Nodo("persona:Marie Curie");
            arista.setOrigen(nuevoOrigen);

            assertEquals(nuevoOrigen, arista.getOrigen());
        }

        @Test
        public void testSetPredicado() {
            Nodo origen = new Nodo("persona:Albert Einstein");
            Nodo destino = new Nodo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            arista.setPredicado("vive_en");

            assertEquals("vive_en", arista.getPredicado());
        }

        @Test
        public void testSetDestino() {
            Nodo origen = new Nodo("persona:Albert Einstein");
            Nodo destinoInicial = new Nodo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destinoInicial);

            Nodo nuevoDestino = new Nodo("lugar:Varsovia");
            arista.setDestino(nuevoDestino);

            assertEquals(nuevoDestino, arista.getDestino());
        }

        @Test
        public void testToString() {
            Nodo origen = new Nodo("persona:Albert Einstein");
            Nodo destino = new Nodo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            String esperado = "persona:Albert Einstein ---[nace_en]---> lugar:Ulm";

            assertEquals(esperado, arista.toString());
        }
    }

    static class NodoTest {

        @Test
        void testCrearNodo() {
            Nodo nodo = new Nodo("persona:Albert Einstein");

            assertEquals("persona:Albert Einstein", nodo.getId());
        }

        @Test
        void testToString() {
            Nodo nodo = new Nodo("lugar:Ulm");

            assertTrue(nodo.toString().contains("Ulm"));
        }

        public static class AristaTest {

            @Test
            public void testConstructorYGetters() {
                Nodo origen = new Nodo("persona:Albert Einstein");
                Nodo destino = new Nodo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                assertEquals(origen, arista.getOrigen());
                assertEquals("nace_en", arista.getPredicado());
                assertEquals(destino, arista.getDestino());
            }

            @Test
            public void testSetOrigen() {
                Nodo origenInicial = new Nodo("persona:Albert Einstein");
                Nodo destino = new Nodo("lugar:Ulm");
                Arista arista = new Arista(origenInicial, "nace_en", destino);

                Nodo nuevoOrigen = new Nodo("persona:Marie Curie");
                arista.setOrigen(nuevoOrigen);

                assertEquals(nuevoOrigen, arista.getOrigen());
            }

            @Test
            public void testSetPredicado() {
                Nodo origen = new Nodo("persona:Albert Einstein");
                Nodo destino = new Nodo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                arista.setPredicado("vive_en");

                assertEquals("vive_en", arista.getPredicado());
            }

            @Test
            public void testSetDestino() {
                Nodo origen = new Nodo("persona:Albert Einstein");
                Nodo destinoInicial = new Nodo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destinoInicial);

                Nodo nuevoDestino = new Nodo("lugar:Varsovia");
                arista.setDestino(nuevoDestino);

                assertEquals(nuevoDestino, arista.getDestino());
            }

            @Test
            public void testToString() {
                Nodo origen = new Nodo("persona:Albert Einstein");
                Nodo destino = new Nodo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                String esperado = "persona:Albert Einstein ---[nace_en]---> lugar:Ulm";

                assertEquals(esperado, arista.toString());
            }
        }
    }
}
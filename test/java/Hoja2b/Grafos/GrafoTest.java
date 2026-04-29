package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;
import org.junit.jupiter.api.Test;

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

        ListaSimplementeEnlazada<NodoGrafo<T>> nodos = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<Arista> aristas = new ListaSimplementeEnlazada<>();

        NodoGrafo n1 = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo n2 = new NodoGrafo("lugar:Ulm");
        Arista a1 = new Arista(n1, "nace_en", n2);

        nodos.addEnd(n1);
        nodos.addEnd(n2);
        aristas.addEnd(a1);

        grafo.setNodos(nodos);
        grafo.setAristas(aristas);

        assertEquals(nodos, grafo.getNodos());
        assertEquals(aristas, grafo.getAristas());
    }

    @Test
    public void testAddNodo() {
        Grafo grafo = new Grafo();
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        grafo.addNodo(nodo);
        grafo.addNodo(nodo);

        assertEquals(1, grafo.getNodos().getSize());
        assertTrue(grafo.getNodos().contains(nodo));
    }

    @Test
    public void testAddAristaConObjetoArista() {
        Grafo grafo = new Grafo();
        NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo destino = new NodoGrafo("lugar:Ulm");
        Arista arista = new Arista(origen, "nace_en", destino);

        grafo.addArista(arista);

        assertEquals(2, grafo.getNodos().getSize());
        assertEquals(1, grafo.getAristas().getSize());
        assertTrue(grafo.getNodos().contains(origen));
        assertTrue(grafo.getNodos().contains(destino));
        assertTrue(grafo.getAristas().contains(arista));
    }

    @Test
    public void testAddAristaConParametros() {
        Grafo grafo = new Grafo();
        NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo destino = new NodoGrafo("lugar:Ulm");

        grafo.addArista(origen, "nace_en", destino);

        assertEquals(2, grafo.getNodos().getSize());
        assertEquals(1, grafo.getAristas().getSize());
        assertEquals("persona:Albert Einstein", grafo.getAristas().get(0).getOrigen().getId());
        assertEquals("nace_en", grafo.getAristas().get(0).getPredicado());
        assertEquals("lugar:Ulm", grafo.getAristas().get(0).getDestino().getId());
    }

    @Test
    public void testBuscarNodoPorIdExistente() {
        Grafo grafo = new Grafo();
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");
        grafo.addNodo(nodo);

        NodoGrafo encontrado = grafo.buscarNodoPorId("persona:Albert Einstein");

        assertEquals(nodo, encontrado);
    }

    @Test
    public void testBuscarNodoPorIdNoExistente() {
        Grafo grafo = new Grafo();

        NodoGrafo encontrado = grafo.buscarNodoPorId("persona:Marie Curie");

        assertNull(encontrado);
    }

    @Test
    public void testGetVecinos() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo premio = new NodoGrafo("1921");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(einstein, "premio:Nobel", premio);

        List<NodoGrafo> vecinos = grafo.getVecinos(einstein);

        assertEquals(2, vecinos.size());
        assertTrue(vecinos.contains(ulm));
        assertTrue(vecinos.contains(premio));
    }

    @Test
    public void testCaminoMinimoExistente() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo alemania = new NodoGrafo("pais:Alemania");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ulm, "esta_en", alemania);

        List<NodoGrafo> camino = grafo.caminoMinimo("persona:Albert Einstein", "pais:Alemania");

        assertEquals(3, camino.size());
        assertEquals(einstein, camino.get(0));
        assertEquals(ulm, camino.get(1));
        assertEquals(alemania, camino.get(2));
    }

    @Test
    public void testCaminoMinimoConOrigenODestinoNoExistente() {
        Grafo grafo = new Grafo();

        List<NodoGrafo> camino = grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testCaminoMinimoSinCamino() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo antonio = new NodoGrafo("persona:Antonio");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addNodo(antonio);

        List<NodoGrafo> camino = grafo.caminoMinimo("persona:Albert Einstein", "persona:Antonio");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testGetVecinosNoDirigidos() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);

        List<NodoGrafo> vecinosEinstein = grafo.getVecinosNoDirigidos(einstein);
        List<NodoGrafo> vecinosUlm = grafo.getVecinosNoDirigidos(ulm);

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
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo antonio = new NodoGrafo("persona:Antonio");
        NodoGrafo villarrubia = new NodoGrafo("lugar:Villarrubia");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(antonio, "nace_en", villarrubia);

        assertTrue(grafo.esDisjunto());
    }

    @Test
    public void testEsDisjuntoFalse() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo antonio = new NodoGrafo("persona:Antonio");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(antonio, "nace_en", ulm);

        assertFalse(grafo.esDisjunto());
    }

    @Test
    public void testGetDestinosPorPredicado() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo premio = new NodoGrafo("1921");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(einstein, "premio:Nobel", premio);

        List<NodoGrafo> destinosNacimiento = grafo.getDestinosPorPredicado(einstein, "nace_en");
        List<NodoGrafo> destinosPremio = grafo.getDestinosPorPredicado(einstein, "premio:Nobel");

        assertEquals(1, destinosNacimiento.size());
        assertTrue(destinosNacimiento.contains(ulm));

        assertEquals(1, destinosPremio.size());
        assertTrue(destinosPremio.contains(premio));
    }

    @Test
    public void testGetOrigenesPorPredicadoYDestino() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ventura = new NodoGrafo("persona:Ventura Pacheco");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ventura, "nace_en", ulm);

        List<NodoGrafo> origenes = grafo.getOrigenesPorPredicadoYDestino("nace_en", ulm);

        assertEquals(2, origenes.size());
        assertTrue(origenes.contains(einstein));
        assertTrue(origenes.contains(ventura));
    }

    @Test
    public void testPersonasMismaCiudadQue() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo ventura = new NodoGrafo("persona:Ventura Pacheco");
        NodoGrafo marcos = new NodoGrafo("persona:Marcos Castro");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo varsovia = new NodoGrafo("lugar:Varsovia");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ventura, "nace_en", ulm);
        grafo.addArista(marcos, "nace_en", varsovia);

        List<NodoGrafo> resultado = grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(ventura));
        assertFalse(resultado.contains(einstein));
        assertFalse(resultado.contains(marcos));
    }

    @Test
    public void testPersonasMismaCiudadQuePersonaNoExiste() {
        Grafo grafo = new Grafo();

        List<NodoGrafo> resultado = grafo.personasMismaCiudadQue("persona:NoExiste");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testPersonasMismaCiudadQueSinNacimiento() {
        Grafo grafo = new Grafo();
        NodoGrafo persona = new NodoGrafo("persona:Albert Einstein");
        grafo.addNodo(persona);

        List<NodoGrafo> resultado = grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLugaresNacimientoPremiosNobel() {
        Grafo grafo = new Grafo();
        NodoGrafo einstein = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo marcos = new NodoGrafo("persona:Marcos Castro");
        NodoGrafo antonio = new NodoGrafo("persona:Antonio");
        NodoGrafo ulm = new NodoGrafo("lugar:Ulm");
        NodoGrafo varsovia = new NodoGrafo("lugar:Varsovia");
        NodoGrafo villarrubia = new NodoGrafo("lugar:Villarrubia de los Caballeros");

        grafo.addArista(einstein, "premio:Nobel", new NodoGrafo("1921"));
        grafo.addArista(einstein, "nace_en", ulm);

        grafo.addArista(marcos, "premio:Nobel", new NodoGrafo("2005"));
        grafo.addArista(marcos, "nace_en", varsovia);

        grafo.addArista(antonio, "nace_en", villarrubia);

        List<NodoGrafo> lugares = grafo.lugaresNacimientoPremiosNobel();

        assertEquals(2, lugares.size());
        assertTrue(lugares.contains(ulm));
        assertTrue(lugares.contains(varsovia));
        assertFalse(lugares.contains(villarrubia));
    }

    public static class AristaTest {

        @Test
        public void testConstructorYGetters() {
            NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
            NodoGrafo destino = new NodoGrafo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            assertEquals(origen, arista.getOrigen());
            assertEquals("nace_en", arista.getPredicado());
            assertEquals(destino, arista.getDestino());
        }

        @Test
        public void testSetOrigen() {
            NodoGrafo origenInicial = new NodoGrafo("persona:Albert Einstein");
            NodoGrafo destino = new NodoGrafo("lugar:Ulm");
            Arista arista = new Arista(origenInicial, "nace_en", destino);

            NodoGrafo nuevoOrigen = new NodoGrafo("persona:Marie Curie");
            arista.setOrigen(nuevoOrigen);

            assertEquals(nuevoOrigen, arista.getOrigen());
        }

        @Test
        public void testSetPredicado() {
            NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
            NodoGrafo destino = new NodoGrafo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            arista.setPredicado("vive_en");

            assertEquals("vive_en", arista.getPredicado());
        }

        @Test
        public void testSetDestino() {
            NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
            NodoGrafo destinoInicial = new NodoGrafo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destinoInicial);

            NodoGrafo nuevoDestino = new NodoGrafo("lugar:Varsovia");
            arista.setDestino(nuevoDestino);

            assertEquals(nuevoDestino, arista.getDestino());
        }

        @Test
        public void testToString() {
            NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
            NodoGrafo destino = new NodoGrafo("lugar:Ulm");
            Arista arista = new Arista(origen, "nace_en", destino);

            String esperado = "persona:Albert Einstein ---[nace_en]---> lugar:Ulm";

            assertEquals(esperado, arista.toString());
        }
    }

    static class NodoTest {

        @Test
        void testCrearNodo() {
            NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

            assertEquals("persona:Albert Einstein", nodo.getId());
        }

        @Test
        void testToString() {
            NodoGrafo nodo = new NodoGrafo("lugar:Ulm");

            assertTrue(nodo.toString().contains("Ulm"));
        }

        public static class AristaTest {

            @Test
            public void testConstructorYGetters() {
                NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
                NodoGrafo destino = new NodoGrafo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                assertEquals(origen, arista.getOrigen());
                assertEquals("nace_en", arista.getPredicado());
                assertEquals(destino, arista.getDestino());
            }

            @Test
            public void testSetOrigen() {
                NodoGrafo origenInicial = new NodoGrafo("persona:Albert Einstein");
                NodoGrafo destino = new NodoGrafo("lugar:Ulm");
                Arista arista = new Arista(origenInicial, "nace_en", destino);

                NodoGrafo nuevoOrigen = new NodoGrafo("persona:Marie Curie");
                arista.setOrigen(nuevoOrigen);

                assertEquals(nuevoOrigen, arista.getOrigen());
            }

            @Test
            public void testSetPredicado() {
                NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
                NodoGrafo destino = new NodoGrafo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                arista.setPredicado("vive_en");

                assertEquals("vive_en", arista.getPredicado());
            }

            @Test
            public void testSetDestino() {
                NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
                NodoGrafo destinoInicial = new NodoGrafo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destinoInicial);

                NodoGrafo nuevoDestino = new NodoGrafo("lugar:Varsovia");
                arista.setDestino(nuevoDestino);

                assertEquals(nuevoDestino, arista.getDestino());
            }

            @Test
            public void testToString() {
                NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
                NodoGrafo destino = new NodoGrafo("lugar:Ulm");
                Arista arista = new Arista(origen, "nace_en", destino);

                String esperado = "persona:Albert Einstein ---[nace_en]---> lugar:Ulm";

                assertEquals(esperado, arista.toString());
            }
        }
    }
}
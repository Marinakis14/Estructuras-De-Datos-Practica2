package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {

    @Test
    public void testConstructor() {
        Grafo<String> grafo = new Grafo<>();

        assertNotNull(grafo.getTipos());
        assertNotNull(grafo.getNodos());
        assertNotNull(grafo.getAristas());
        assertTrue(grafo.getTipos().isEmpty());
        assertTrue(grafo.getNodos().isEmpty());
        assertTrue(grafo.getAristas().isEmpty());
    }

    @Test
    public void testSettersYGetters() {
        Grafo<String> grafo = new Grafo<>();

        ListaSimplementeEnlazada<String> tipos = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<NodoGrafo<String>> nodos = new ListaSimplementeEnlazada<>();
        ListaSimplementeEnlazada<Arista<String>> aristas = new ListaSimplementeEnlazada<>();

        NodoGrafo<String> n1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> n2 = new NodoGrafo<>("lugar:Ulm");
        Arista<String> a1 = new Arista<>(n1, "nace_en", n2);

        tipos.addEnd("persona");
        tipos.addEnd("lugar");
        nodos.addEnd(n1);
        nodos.addEnd(n2);
        aristas.addEnd(a1);

        grafo.setTipos(tipos);
        grafo.setNodos(nodos);
        grafo.setAristas(aristas);

        assertEquals(tipos, grafo.getTipos());
        assertEquals(nodos, grafo.getNodos());
        assertEquals(aristas, grafo.getAristas());
    }

    @Test
    public void testAddTipo() {
        Grafo<String> grafo = new Grafo<>();

        grafo.addTipo("persona");
        grafo.addTipo("persona");
        grafo.addTipo("lugar");

        assertEquals(2, grafo.getTipos().getSize());
        assertTrue(grafo.getTipos().contains("persona"));
        assertTrue(grafo.getTipos().contains("lugar"));
    }

    @Test
    public void testAddNodo() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        grafo.addNodo(nodo);
        grafo.addNodo(nodo);

        assertEquals(1, grafo.getNodos().getSize());
        assertTrue(grafo.getNodos().contains(nodo));
    }

    @Test
    public void testAddAristaConObjeto() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> origen = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> destino = new NodoGrafo<>("lugar:Ulm");
        Arista<String> arista = new Arista<>(origen, "nace_en", destino);

        grafo.addArista(arista);

        assertEquals(2, grafo.getNodos().getSize());
        assertEquals(1, grafo.getAristas().getSize());
        assertTrue(grafo.getNodos().contains(origen));
        assertTrue(grafo.getNodos().contains(destino));
        assertTrue(grafo.getAristas().contains(arista));
    }

    @Test
    public void testAddAristaConParametros() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> origen = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> destino = new NodoGrafo<>("lugar:Ulm");

        grafo.addArista(origen, "nace_en", destino);

        assertEquals(2, grafo.getNodos().getSize());
        assertEquals(1, grafo.getAristas().getSize());
        assertEquals(origen, grafo.getAristas().get(0).getOrigen());
        assertEquals("nace_en", grafo.getAristas().get(0).getPredicado());
        assertEquals(destino, grafo.getAristas().get(0).getDestino());
    }

    @Test
    public void testBuscarNodoPorIdExistente() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        grafo.addNodo(nodo);

        assertEquals(nodo, grafo.buscarNodoPorId("persona:Albert Einstein"));
    }

    @Test
    public void testBuscarNodoPorIdNoExistente() {
        Grafo<String> grafo = new Grafo<>();

        assertNull(grafo.buscarNodoPorId("persona:NoExiste"));
    }

    @Test
    public void testGetTiposDeNodos() {
        Grafo<String> grafo = new Grafo<>();
        grafo.addTipo("persona");

        NodoGrafo<String> n1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> n2 = new NodoGrafo<>("lugar:Ulm");

        grafo.addNodo(n1);
        grafo.addNodo(n2);

        ListaSimplementeEnlazada<String> resultado = grafo.getTiposDeNodos();

        assertTrue(resultado.contains("persona"));
        assertTrue(resultado.contains("lugar"));
    }

    @Test
    public void testGetVecinos() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> einstein = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> ulm = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> premio = new NodoGrafo<>("1921");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(einstein, "premio:Nobel", premio);

        ListaSimplementeEnlazada<NodoGrafo<String>> vecinos = grafo.getVecinos(einstein);

        assertEquals(2, vecinos.getSize());
        assertTrue(vecinos.contains(ulm));
        assertTrue(vecinos.contains(premio));
    }

    @Test
    public void testCaminoMinimoExistente() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> einstein = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> ulm = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> alemania = new NodoGrafo<>("pais:Alemania");

        grafo.addArista(einstein, "nace_en", ulm);
        grafo.addArista(ulm, "esta_en", alemania);

        ListaSimplementeEnlazada<NodoGrafo<String>> camino =
                grafo.caminoMinimo("persona:Albert Einstein", "pais:Alemania");

        assertEquals(3, camino.getSize());
        assertEquals(einstein, camino.get(0));
        assertEquals(ulm, camino.get(1));
        assertEquals(alemania, camino.get(2));
    }

    @Test
    public void testCaminoMinimoSinOrigenODestino() {
        Grafo<String> grafo = new Grafo<>();

        ListaSimplementeEnlazada<NodoGrafo<String>> camino =
                grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testCaminoMinimoSinConexion() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> a = new NodoGrafo<>("persona:Antonio");

        grafo.addArista(e, "nace_en", u);
        grafo.addNodo(a);

        ListaSimplementeEnlazada<NodoGrafo<String>> camino =
                grafo.caminoMinimo("persona:Albert Einstein", "persona:Antonio");

        assertTrue(camino.isEmpty());
    }

    @Test
    public void testGetVecinosNoDirigidos() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");

        grafo.addArista(e, "nace_en", u);

        ListaSimplementeEnlazada<NodoGrafo<String>> vecinosE = grafo.getVecinosNoDirigidos(e);
        ListaSimplementeEnlazada<NodoGrafo<String>> vecinosU = grafo.getVecinosNoDirigidos(u);

        assertTrue(vecinosE.contains(u));
        assertTrue(vecinosU.contains(e));
    }

    @Test
    public void testEsDisjuntoEnVacio() {
        Grafo<String> grafo = new Grafo<>();

        assertFalse(grafo.esDisjunto());
    }

    @Test
    public void testEsDisjuntoTrue() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> a = new NodoGrafo<>("persona:Antonio");
        NodoGrafo<String> v = new NodoGrafo<>("lugar:Villarrubia");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(a, "nace_en", v);

        assertTrue(grafo.esDisjunto());
    }

    @Test
    public void testEsDisjuntoFalse() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> a = new NodoGrafo<>("persona:Antonio");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(a, "nace_en", u);

        assertFalse(grafo.esDisjunto());
    }

    @Test
    public void testGetDestinosPorPredicado() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> premio = new NodoGrafo<>("premio:Nobel");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(e, "premio", premio);

        ListaSimplementeEnlazada<NodoGrafo<String>> destinos =
                grafo.getDestinosPorPredicado(e, "nace_en");

        assertEquals(1, destinos.getSize());
        assertTrue(destinos.contains(u));
    }

    @Test
    public void testGetOrigenesPorPredicadoYDestino() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> v = new NodoGrafo<>("persona:Ventura Pacheco");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(v, "nace_en", u);

        ListaSimplementeEnlazada<NodoGrafo<String>> origenes =
                grafo.getOrigenesPorPredicadoYDestino("nace_en", u);

        assertEquals(2, origenes.getSize());
        assertTrue(origenes.contains(e));
        assertTrue(origenes.contains(v));
    }

    @Test
    public void testPersonasMismaCiudadQue() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> v = new NodoGrafo<>("persona:Ventura Pacheco");
        NodoGrafo<String> m = new NodoGrafo<>("persona:Marcos Castro");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> va = new NodoGrafo<>("lugar:Varsovia");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(v, "nace_en", u);
        grafo.addArista(m, "nace_en", va);

        ListaSimplementeEnlazada<NodoGrafo<String>> resultado =
                grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertEquals(1, resultado.getSize());
        assertTrue(resultado.contains(v));
        assertFalse(resultado.contains(e));
    }

    @Test
    public void testPersonasMismaCiudadQuePersonaNoExiste() {
        Grafo<String> grafo = new Grafo<>();

        ListaSimplementeEnlazada<NodoGrafo<String>> resultado =
                grafo.personasMismaCiudadQue("persona:NoExiste");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testPersonasMismaCiudadQueSinNacimiento() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        grafo.addNodo(e);

        ListaSimplementeEnlazada<NodoGrafo<String>> resultado =
                grafo.personasMismaCiudadQue("persona:Albert Einstein");

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testFisicosMismaCiudadQue() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> e = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> v = new NodoGrafo<>("persona:Ventura Pacheco");
        NodoGrafo<String> m = new NodoGrafo<>("persona:Marcos Castro");
        NodoGrafo<String> u = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> fisico = new NodoGrafo<>("profesion:fisico");
        NodoGrafo<String> matematico = new NodoGrafo<>("profesion:matematico");

        grafo.addArista(e, "nace_en", u);
        grafo.addArista(v, "nace_en", u);
        grafo.addArista(m, "nace_en", u);
        grafo.addArista(v, "profesion", fisico);
        grafo.addArista(m, "profesion", matematico);

        ListaSimplementeEnlazada<NodoGrafo<String>> resultado =
                grafo.fisicosMismaCiudadQue("persona:Albert Einstein");

        assertEquals(1, resultado.getSize());
        assertTrue(resultado.contains(v));
        assertFalse(resultado.contains(m));
    }

    @Test
    public void testLugaresNacimientoPremiosNobelConPremioNormal() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> einstein = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> marcos = new NodoGrafo<>("persona:Marcos Castro");
        NodoGrafo<String> antonio = new NodoGrafo<>("persona:Antonio");
        NodoGrafo<String> ulm = new NodoGrafo<>("lugar:Ulm");
        NodoGrafo<String> varsovia = new NodoGrafo<>("lugar:Varsovia");
        NodoGrafo<String> villarrubia = new NodoGrafo<>("lugar:Villarrubia");
        NodoGrafo<String> premioNobel = new NodoGrafo<>("premio:Nobel");

        grafo.addNodo(premioNobel);

        grafo.addArista(einstein, "premio", premioNobel);
        grafo.addArista(einstein, "nace_en", ulm);

        grafo.addArista(marcos, "premio", premioNobel);
        grafo.addArista(marcos, "nace_en", varsovia);

        grafo.addArista(antonio, "nace_en", villarrubia);

        ListaSimplementeEnlazada<NodoGrafo<String>> lugares =
                grafo.lugaresNacimientoPremiosNobel();

        assertEquals(2, lugares.getSize());
        assertTrue(lugares.contains(ulm));
        assertTrue(lugares.contains(varsovia));
        assertFalse(lugares.contains(villarrubia));
    }

    @Test
    public void testLugaresNacimientoPremiosNobelConPredicadoAntiguo() {
        Grafo<String> grafo = new Grafo<>();
        NodoGrafo<String> einstein = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> ulm = new NodoGrafo<>("lugar:Ulm");

        grafo.addArista(einstein, "premio:Nobel", new NodoGrafo<>("1921"));
        grafo.addArista(einstein, "nace_en", ulm);

        ListaSimplementeEnlazada<NodoGrafo<String>> lugares =
                grafo.lugaresNacimientoPremiosNobel();

        assertEquals(1, lugares.getSize());
        assertTrue(lugares.contains(ulm));
    }
}
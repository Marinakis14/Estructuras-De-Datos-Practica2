package HOJA2bTests;

import Hoja2b.Grafos.Nodo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodoTest {

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
}
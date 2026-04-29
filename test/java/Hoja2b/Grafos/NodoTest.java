package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodoTest {

    @Test
    public void testConstructorYGetId() {
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.getId());
    }

    @Test
    public void testSetId() {
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        nodo.setId("persona:Marie Curie");

        assertEquals("persona:Marie Curie", nodo.getId());
    }

    @Test
    public void testEqualsMismoObjeto() {
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        assertTrue(nodo.equals(nodo));
    }

    @Test
    public void testEqualsObjetoNoNodo() {
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        assertFalse(nodo.equals("esto no es un nodo"));
    }

    @Test
    public void testEqualsNodosIguales() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("persona:Albert Einstein");

        assertEquals(nodo1, nodo2);
    }

    @Test
    public void testEqualsNodosDistintos() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("persona:Marie Curie");

        assertNotEquals(nodo1, nodo2);
    }

    @Test
    public void testEqualsConIdNullEnAmbos() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>(null);
        NodoGrafo<String> nodo2 = new NodoGrafo<>(null);

        assertEquals(nodo1, nodo2);
    }

    @Test
    public void testEqualsConUnIdNullYOtroNo() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>(null);
        NodoGrafo<String> nodo2 = new NodoGrafo<>("persona:Albert Einstein");

        assertNotEquals(nodo1, nodo2);
    }

    @Test
    public void testToString() {
        NodoGrafo<String> nodo = new NodoGrafo<>("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.toString());
    }

    @Test
    public void testCompareToIguales() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("persona:Albert Einstein");

        assertEquals(0, nodo1.compareTo(nodo2));
    }

    @Test
    public void testCompareToDistintos() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("persona:Albert Einstein");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("persona:Marie Curie");

        assertTrue(nodo1.compareTo(nodo2) < 0 || nodo1.compareTo(nodo2) > 0);
    }
}
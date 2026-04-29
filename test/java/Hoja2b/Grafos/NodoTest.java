package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodoTest {

    @Test
    public void testConstructorYGetId() {
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.getId());
    }

    @Test
    public void testSetId() {
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        nodo.setId("persona:Marie Curie");

        assertEquals("persona:Marie Curie", nodo.getId());
    }

    @Test
    public void testEqualsMismoObjeto() {
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        assertTrue(nodo.equals(nodo));
    }

    @Test
    public void testEqualsObjetoNoNodo() {
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        assertFalse(nodo.equals("esto no es un nodo"));
    }

    @Test
    public void testEqualsNodosIguales() {
        NodoGrafo nodo1 = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo nodo2 = new NodoGrafo("persona:Albert Einstein");

        assertEquals(nodo1, nodo2);
    }

    @Test
    public void testEqualsNodosDistintos() {
        NodoGrafo nodo1 = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo nodo2 = new NodoGrafo("persona:Marie Curie");

        assertNotEquals(nodo1, nodo2);
    }

    @Test
    public void testHashCodeNodosIguales() {
        NodoGrafo nodo1 = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo nodo2 = new NodoGrafo("persona:Albert Einstein");

        assertEquals(nodo1.hashCode(), nodo2.hashCode());
    }

    @Test
    public void testToString() {
        NodoGrafo nodo = new NodoGrafo("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.toString());
    }
}
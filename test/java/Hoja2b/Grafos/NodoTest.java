package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodoTest {

    @Test
    public void testConstructorYGetId() {
        Nodo nodo = new Nodo("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.getId());
    }

    @Test
    public void testSetId() {
        Nodo nodo = new Nodo("persona:Albert Einstein");

        nodo.setId("persona:Marie Curie");

        assertEquals("persona:Marie Curie", nodo.getId());
    }

    @Test
    public void testEqualsMismoObjeto() {
        Nodo nodo = new Nodo("persona:Albert Einstein");

        assertTrue(nodo.equals(nodo));
    }

    @Test
    public void testEqualsObjetoNoNodo() {
        Nodo nodo = new Nodo("persona:Albert Einstein");

        assertFalse(nodo.equals("esto no es un nodo"));
    }

    @Test
    public void testEqualsNodosIguales() {
        Nodo nodo1 = new Nodo("persona:Albert Einstein");
        Nodo nodo2 = new Nodo("persona:Albert Einstein");

        assertEquals(nodo1, nodo2);
    }

    @Test
    public void testEqualsNodosDistintos() {
        Nodo nodo1 = new Nodo("persona:Albert Einstein");
        Nodo nodo2 = new Nodo("persona:Marie Curie");

        assertNotEquals(nodo1, nodo2);
    }

    @Test
    public void testHashCodeNodosIguales() {
        Nodo nodo1 = new Nodo("persona:Albert Einstein");
        Nodo nodo2 = new Nodo("persona:Albert Einstein");

        assertEquals(nodo1.hashCode(), nodo2.hashCode());
    }

    @Test
    public void testToString() {
        Nodo nodo = new Nodo("persona:Albert Einstein");

        assertEquals("persona:Albert Einstein", nodo.toString());
    }
}
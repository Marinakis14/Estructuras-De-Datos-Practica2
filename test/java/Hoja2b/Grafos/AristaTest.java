package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AristaTest {

    @Test
    public void testConstructorYGetters() {
        NodoGrafo origen = new NodoGrafo("persona:Albert Einstein");
        NodoGrafo destino = new NodoGrafo("lugar:Ulm");
        Arista arista = new Arista(origen, "nace_en", destino);

        assertEquals(origen, arista.getOrigen());
        assertEquals("nace_en", arista.getDato());
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

        arista.setDato("vive_en");

        assertEquals("vive_en", arista.getDato());
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
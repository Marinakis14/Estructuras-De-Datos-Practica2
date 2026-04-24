package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AristaTest {

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
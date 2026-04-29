package Hoja2b.Grafos;

import MisEstructurasDeDatos.ListaSimplementeEnlazada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatosGrafoJsonTest {

    @Test
    public void testGetTripletasInicialmenteNull() {
        DatosGrafoJson datos = new DatosGrafoJson();

        assertNull(datos.getTripletas());
    }

    @Test
    public void testSetYGetTripletas() {
        DatosGrafoJson datos = new DatosGrafoJson();
        ListaSimplementeEnlazada<TripletaJson> tripletas = new ListaSimplementeEnlazada<>();

        TripletaJson tripleta1 = new TripletaJson();
        tripleta1.setS("persona:Albert Einstein");
        tripleta1.setP("nace_en");
        tripleta1.setO("lugar:Ulm");

        TripletaJson tripleta2 = new TripletaJson();
        tripleta2.setS("persona:Albert Einstein");
        tripleta2.setP("premio");
        tripleta2.setO("premio:Nobel");

        tripletas.addEnd(tripleta1);
        tripletas.addEnd(tripleta2);

        datos.setTripletas(tripletas);

        assertSame(tripletas, datos.getTripletas());
        assertEquals(2, datos.getTripletas().getSize());

        assertEquals("persona:Albert Einstein", datos.getTripletas().get(0).getS());
        assertEquals("nace_en", datos.getTripletas().get(0).getP());
        assertEquals("lugar:Ulm", datos.getTripletas().get(0).getO());

        assertEquals("persona:Albert Einstein", datos.getTripletas().get(1).getS());
        assertEquals("premio", datos.getTripletas().get(1).getP());
        assertEquals("premio:Nobel", datos.getTripletas().get(1).getO());
    }

    @Test
    public void testSetTripletasNull() {
        DatosGrafoJson datos = new DatosGrafoJson();
        ListaSimplementeEnlazada<TripletaJson> tripletas = new ListaSimplementeEnlazada<>();

        datos.setTripletas(tripletas);
        assertNotNull(datos.getTripletas());

        datos.setTripletas(null);
        assertNull(datos.getTripletas());
    }
}

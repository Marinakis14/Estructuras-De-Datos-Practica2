package Hoja2b.Grafos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatosGrafoJsonTest {

    @Test
    public void testSetYGetTipos() {
        DatosGrafoJson datos = new DatosGrafoJson();
        List<String> tipos = new ArrayList<>();

        tipos.add("persona");
        tipos.add("lugar");
        tipos.add("premio");

        datos.setTipos(tipos);

        assertEquals(tipos, datos.getTipos());
        assertEquals(3, datos.getTipos().size());
        assertTrue(datos.getTipos().contains("persona"));
        assertTrue(datos.getTipos().contains("lugar"));
        assertTrue(datos.getTipos().contains("premio"));
    }

    @Test
    public void testSetYGetTripletas() {
        DatosGrafoJson datos = new DatosGrafoJson();
        List<TripletaJson> tripletas = new ArrayList<>();

        TripletaJson t1 = new TripletaJson();
        t1.setS("persona:Albert Einstein");
        t1.setP("nace_en");
        t1.setO("lugar:Ulm");

        TripletaJson t2 = new TripletaJson();
        t2.setS("persona:Albert Einstein");
        t2.setP("premio:Nobel");
        t2.setO("1921");

        tripletas.add(t1);
        tripletas.add(t2);

        datos.setTripletas(tripletas);

        assertEquals(tripletas, datos.getTripletas());
        assertEquals(2, datos.getTripletas().size());
        assertEquals("persona:Albert Einstein", datos.getTripletas().get(0).getS());
        assertEquals("nace_en", datos.getTripletas().get(0).getP());
        assertEquals("lugar:Ulm", datos.getTripletas().get(0).getO());
    }

    @Test
    public void testGetTiposInicialmenteNull() {
        DatosGrafoJson datos = new DatosGrafoJson();

        assertNull(datos.getTipos());
    }

    @Test
    public void testGetTripletasInicialmenteNull() {
        DatosGrafoJson datos = new DatosGrafoJson();

        assertNull(datos.getTripletas());
    }
}
package Hoja2a.arbolesbinarios;

public class Main {
    public static void main(String[] args) {

        Nodo<Integer> n1 = new Nodo<>(3);

        ArbolBinarioDeBusqueda<Integer> a1 = new ArbolBinarioDeBusqueda<>(n1);
        a1.ADD(5);
        a1.ADD(7);
        a1.ADD(7);
        a1.ADD(6);
        a1.ADD(2);
        a1.ADD(4);

        System.out.println(a1.getListaPostOrden());
        System.out.println(a1.getListaOrdenCentral());
        System.out.println(a1.getListaPreOrden());
        System.out.println("el grado es:");
        System.out.println(a1.getGrado());
        System.out.println("la raiz es:");
        System.out.println(a1.getRaiz());
        System.out.println("la profundidad es:");
        System.out.println(a1.getAltura());
        System.out.println();

        ArbolBinarioDeBusqueda a2 = a1.getSubArbolDerecha();
        System.out.println("La raíz del subárbol derecho es: " + a2.getDato(a2.getRaiz()));

        ArbolBinarioDeBusqueda a3 = a1.getSubArbolIzquierda();
        System.out.println("La raíz del subárbol izquierdo es: " + a3.getDato(a3.getRaiz()));
        System.out.println();

        System.out.println(a3.getListaOrdenCentral());
        System.out.println();

        System.out.println(a2.getListaPostOrden());
        System.out.println(a2.getListaOrdenCentral());
        System.out.println(a2.getListaPreOrden());
        System.out.println();

        System.out.println(a3.getRaiz());
        System.out.println();

        System.out.println(a1.getListaDatosNivel(0));
        System.out.println(a1.getListaDatosNivel(1));
        System.out.println(a1.getListaDatosNivel(2));
        System.out.println(a1.getListaDatosNivel(3));
        System.out.println(a1.getListaDatosNivel(4));
        System.out.println();

        System.out.println(a1.isArbolHomogeneo1());
        System.out.println(a1.isArbolHomogeneo2());
        System.out.println();

        a1.ADD(14);
        for (int i = 0; i < a1.getAltura() + 1; i++) {
            System.out.println(a1.getListaDatosNivel(i));
        }
        System.out.println();

        System.out.println(a1.isArbolHomogeneo1());
        System.out.println(a1.isArbolHomogeneo2());
        System.out.println();

        Nodo<Integer> n2 = new Nodo<>(8);

        ArbolBinarioDeBusqueda<Integer> a4 = new ArbolBinarioDeBusqueda<>(n2);
        a4.ADD(4);
        a4.ADD(12);
        a4.ADD(2);
        a4.ADD(2);
        a4.ADD(6);
        a4.ADD(10);
        a4.ADD(14);
        a4.ADD(14);
        a4.ADD(1);
        a4.ADD(1);
        a4.ADD(3);
        a4.ADD(5);
        a4.ADD(7);
        a4.ADD(9);
        a4.ADD(9);
        a4.ADD(9);
        a4.ADD(11);
        a4.ADD(13);
        a4.ADD(13);
        a4.ADD(13);
        a4.ADD(13);
        a4.ADD(15);

        for (int i = 0; i < a4.getAltura() + 1; i++) {
            System.out.println(a4.getListaDatosNivel(i));
        }
        System.out.println();

        System.out.println(a4.isArbolHomogeneo1());
        System.out.println(a4.isArbolHomogeneo2());
        System.out.println();

        System.out.println(a4.isNodoInArbol(6));
        System.out.println(a4.isNodoInArbol(40));
        System.out.println(a4.isNodoInArbol(12));
        System.out.println();

        System.out.println(a4.getNivel(5));
        System.out.println(a4.getNivel(77));
        System.out.println(a4.getNivel(4));
        System.out.println();

        System.out.println(a4.getAltura());
        System.out.println();

        System.out.println(a4.isArbolCompleto());
        System.out.println(a1.isArbolCompleto());
        System.out.println(a4.isArbolCompleto2());
        System.out.println(a1.isArbolCompleto2());
        System.out.println();

        System.out.println(a4.getRepeticionesNodo(13));
        System.out.println(a4.getRepeticionesNodo(9));
        System.out.println(a4.getRepeticionesNodo(5));
        System.out.println();

        System.out.println(a4.getNodosHoja());
        System.out.println(a2.getNodosHoja());
        System.out.println(a1.getNodosHoja());
        System.out.println();

        a4.DEL(15);
        System.out.println(a4.isArbolCompleto2());
        System.out.println(a4.isArbolCasiCompleto());
        System.out.println();

        a4.DEL(13);
        a4.DEL(5);
        a4.DEL(7);
        a4.DEL(4);
        a4.DEL(8);
        for (int i = 0; i < a4.getAltura() + 1; i++) {
            System.out.println(a4.getListaDatosNivel(i));
        }
        System.out.println();

        System.out.println(a4.isArbolCompleto2());
        System.out.println(a4.isArbolCasiCompleto());
        System.out.println();

        System.out.println(a4.getNodoMasPequeño());
        System.out.println(a4.getNodoMasGrande());
        System.out.println();

        System.out.println(a4.getCaminoDatos(2));
        System.out.println(a4.getCaminoDatos(3));
        System.out.println(a4.getCaminoDatos(13));
        System.out.println();
    }
}


import Hoja2a.arbolesbinarios.ArbolBinarioDeBusqueda;
import Hoja2a.arbolesbinarios.Nodo;

void main() {

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
    System.out.println("La raíz del subárbol derecho es: " + a2.getRaiz().getDatos());

    ArbolBinarioDeBusqueda a3 = a1.getSubArbolIzquierda();
    System.out.println("La raíz del subárbol izquierdo es: " + a3.getRaiz().getDatos());
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
    for (int i = 0; i < a1.getAltura(); i++) {
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

    for (int i = 0; i < a4.getAltura(); i++) {
        System.out.println(a4.getListaDatosNivel(i));
    }
    System.out.println();

    System.out.println(a4.isArbolHomogeneo1());
    System.out.println(a4.isArbolHomogeneo2());
    System.out.println();

    System.out.println(a4.isNodoInArbol(6));
    System.out.println(a4.isNodoInArbol(40));
    System.out.println();

    System.out.println(a4.getNivel(5));
    System.out.println(a4.getNivel(77));
    System.out.println(a4.getNivel(4));
}

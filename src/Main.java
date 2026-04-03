void main() {

    Nodo<Integer> n1 = new Nodo<>(3);

    ArbolBinarioDeBusqueda<Integer> a1 = new ArbolBinarioDeBusqueda<>(n1);
    a1.ADD(5);
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
    ArbolBinarioDeBusqueda a2 = a1.getSubArbolDerecha();
    ArbolBinarioDeBusqueda a3 = a1.getSubArbolIzquierda();
    System.out.println(a3.getListaOrdenCentral());
    System.out.println(a2.getListaPostOrden());
    System.out.println(a2.getListaOrdenCentral());
    System.out.println(a2.getListaPreOrden());
    System.out.println(a3.getRaiz());
}

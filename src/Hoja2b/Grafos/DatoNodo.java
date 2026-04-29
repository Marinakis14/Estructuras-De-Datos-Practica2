package Hoja2b.Grafos;

public class DatoNodo implements InterfazDatosGrafo {
    private String nombre;
    private String tipo;

    public DatoNodo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Getters
    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getTipo() { return tipo; }

    @Override
    public boolean esValido() {
        // Un nodo no es válido si no tiene nombre o tipo
        return nombre != null && !nombre.isEmpty() && tipo != null;
    }

    @Override
    public String toString() {
        return "[" + tipo.toUpperCase() + "] " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DatoNodo)) return false;
        DatoNodo otro = (DatoNodo) obj;
        return nombre.equals(otro.nombre);
    }
}

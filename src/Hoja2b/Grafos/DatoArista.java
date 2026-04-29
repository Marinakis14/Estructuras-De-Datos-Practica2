package Hoja2b.Grafos;

public class DatoArista implements InterfazDatosGrafo {
    private String predicado;

    public DatoArista(String predicado) {
        this.predicado = predicado;
    }

    public String getPredicado() { return predicado; }

    @Override
    public String getTipo() { return "Relación"; } // O el tipo que prefieras

    @Override
    public String getNombre() { return predicado; }

    @Override
    public boolean esValido() {
        return predicado != null && !predicado.isEmpty();
    }

    @Override
    public String toString() {
        return "--" + predicado + "-->";
    }
}

package Hoja2b.Grafos;

import java.util.Objects;

public class Nodo {
    private String id;

    public Nodo(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Nodo)) {
            return false;
        }
        Nodo nodo = (Nodo) obj;
        return Objects.equals(id, nodo.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


}

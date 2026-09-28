package modelo;

public class NodoTurno {

    private String equipo;
    private NodoTurno siguiente;

    public NodoTurno(String equipo) {
        this.equipo = equipo;
        this.siguiente = null;
    }

    public String getEquipo() {
        return equipo;
    }

    public NodoTurno getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoTurno siguiente) {
        this.siguiente = siguiente;
    }
}

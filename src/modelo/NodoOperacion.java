package modelo;

public class NodoOperacion {

    private OperacionCritica dato;
    private NodoOperacion siguiente;

    public NodoOperacion(OperacionCritica dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public OperacionCritica getDato() {
        return dato;
    }

    public NodoOperacion getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoOperacion siguiente) {
        this.siguiente = siguiente;
    }
}

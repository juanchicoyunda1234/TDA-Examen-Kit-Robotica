package modelo;

public class NodoHistorial {

    private EventoHistorial dato;
    private NodoHistorial anterior;
    private NodoHistorial siguiente;

    public NodoHistorial(EventoHistorial dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public EventoHistorial getDato() {
        return dato;
    }

    public NodoHistorial getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoHistorial anterior) {
        this.anterior = anterior;
    }

    public NodoHistorial getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHistorial siguiente) {
        this.siguiente = siguiente;
    }
}
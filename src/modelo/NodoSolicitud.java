package modelo;

public class NodoSolicitud {

    private SolicitudPrestamo dato;
    private NodoSolicitud siguiente;

    public NodoSolicitud(SolicitudPrestamo dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public SolicitudPrestamo getDato() {
        return dato;
    }

    public NodoSolicitud getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSolicitud siguiente) {
        this.siguiente = siguiente;
    }
}

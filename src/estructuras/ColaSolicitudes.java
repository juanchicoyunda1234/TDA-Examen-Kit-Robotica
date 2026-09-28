package estructuras;

import modelo.NodoSolicitud;
import modelo.SolicitudPrestamo;

public class ColaSolicitudes {

    private NodoSolicitud frente;
    private NodoSolicitud final_;

    public ColaSolicitudes() {
        this.frente = null;
        this.final_ = null;
    }

    public void encolar(SolicitudPrestamo solicitud) {
        NodoSolicitud nuevo = new NodoSolicitud(solicitud);
        if (frente == null) {
            frente = nuevo;
            final_ = nuevo;
            return;
        }
        final_.setSiguiente(nuevo);
        final_ = nuevo;
    }

    public SolicitudPrestamo desencolar() {
        if (frente == null) {
            return null;
        }
        SolicitudPrestamo dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            final_ = null;
        }
        return dato;
    }

    public SolicitudPrestamo verFrente() {
        if (frente == null) {
            return null;
        }
        return frente.getDato();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void listar() {
        if (frente == null) {
            System.out.println("No hay solicitudes en espera.");
            return;
        }
        NodoSolicitud actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}

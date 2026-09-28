package estructuras;

import modelo.EventoHistorial;
import modelo.NodoHistorial;

public class HistorialMovimientos {

    private NodoHistorial primero;
    private NodoHistorial ultimo;

    public HistorialMovimientos() {
        this.primero = null;
        this.ultimo = null;
    }

    public void registrarEvento(EventoHistorial evento) {
        NodoHistorial nuevo = new NodoHistorial(evento);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            return;
        }
        nuevo.setAnterior(ultimo);
        ultimo.setSiguiente(nuevo);
        ultimo = nuevo;
    }

    public void mostrarHaciaAdelante() {
        if (primero == null) {
            System.out.println("El historial esta vacio.");
            return;
        }
        NodoHistorial actual = primero;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void mostrarHaciaAtras() {
        if (ultimo == null) {
            System.out.println("El historial esta vacio.");
            return;
        }
        NodoHistorial actual = ultimo;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }

    public boolean estaVacio() {
        return primero == null;
    }
}

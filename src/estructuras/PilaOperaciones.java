package estructuras;

import modelo.NodoOperacion;
import modelo.OperacionCritica;

public class PilaOperaciones {

    private NodoOperacion tope;

    public PilaOperaciones() {
        this.tope = null;
    }

    public void apilar(OperacionCritica operacion) {
        NodoOperacion nuevo = new NodoOperacion(operacion);
        nuevo.setSiguiente(tope);
        tope = nuevo;
    }

    public OperacionCritica desapilar() {
        if (tope == null) {
            return null;
        }
        OperacionCritica dato = tope.getDato();
        tope = tope.getSiguiente();
        return dato;
    }

    public OperacionCritica verTope() {
        if (tope == null) {
            return null;
        }
        return tope.getDato();
    }

    public boolean estaVacia() {
        return tope == null;
    }
}

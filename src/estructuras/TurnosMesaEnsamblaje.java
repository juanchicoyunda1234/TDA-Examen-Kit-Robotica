package estructuras;

import modelo.NodoTurno;

public class TurnosMesaEnsamblaje {

    private NodoTurno actual;
    private int cantidad;

    public TurnosMesaEnsamblaje() {
        this.actual = null;
        this.cantidad = 0;
    }

    public void insertarEquipo(String equipo) {
        NodoTurno nuevo = new NodoTurno(equipo);
        if (actual == null) {
            nuevo.setSiguiente(nuevo);
            actual = nuevo;
            cantidad = 1;
            return;
        }
        NodoTurno ultimo = actual;
        while (ultimo.getSiguiente() != actual) {
            ultimo = ultimo.getSiguiente();
        }
        nuevo.setSiguiente(actual);
        ultimo.setSiguiente(nuevo);
        cantidad++;
    }

    public String avanzarTurno() {
        if (actual == null) {
            return null;
        }
        actual = actual.getSiguiente();
        return actual.getEquipo();
    }

    public String eliminarActual() {
        if (actual == null) {
            return null;
        }
        String equipoEliminado = actual.getEquipo();
        if (actual.getSiguiente() == actual) {
            actual = null;
            cantidad = 0;
            return equipoEliminado;
        }
        NodoTurno anterior = actual;
        while (anterior.getSiguiente() != actual) {
            anterior = anterior.getSiguiente();
        }
        anterior.setSiguiente(actual.getSiguiente());
        actual = actual.getSiguiente();
        cantidad--;
        return equipoEliminado;
    }

    public String equipoActual() {
        if (actual == null) {
            return null;
        }
        return actual.getEquipo();
    }

    public boolean estaVacia() {
        return actual == null;
    }

    public void mostrarRonda() {
        if (actual == null) {
            System.out.println("No hay equipos registrados en la mesa de ensamblaje.");
            return;
        }
        NodoTurno recorrido = actual;
        for (int i = 0; i < cantidad; i++) {
            String marca = (recorrido == actual) ? " <- turno actual" : "";
            System.out.println((i + 1) + ". " + recorrido.getEquipo() + marca);
            recorrido = recorrido.getSiguiente();
        }
    }
}

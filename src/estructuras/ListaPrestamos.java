package estructuras;

import modelo.NodoPrestamo;
import modelo.Prestamo;

public class ListaPrestamos {

    private NodoPrestamo cabeza;

    public ListaPrestamos() {
        this.cabeza = null;
    }

    public void insertar(Prestamo prestamo) {
        NodoPrestamo nuevo = new NodoPrestamo(prestamo);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        NodoPrestamo actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public Prestamo buscarPorKit(String codigoKit) {
        NodoPrestamo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getCodigoKit().equalsIgnoreCase(codigoKit)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Prestamo buscarPorEquipo(String nombreEquipo) {
        NodoPrestamo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getEquipo().getNombreEquipo().equalsIgnoreCase(nombreEquipo)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Prestamo eliminarPorKit(String codigoKit) {
        if (cabeza == null) {
            return null;
        }
        if (cabeza.getDato().getCodigoKit().equalsIgnoreCase(codigoKit)) {
            Prestamo eliminado = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
            return eliminado;
        }
        NodoPrestamo anterior = cabeza;
        NodoPrestamo actual = cabeza.getSiguiente();
        while (actual != null) {
            if (actual.getDato().getCodigoKit().equalsIgnoreCase(codigoKit)) {
                anterior.setSiguiente(actual.getSiguiente());
                return actual.getDato();
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void mostrarTodos() {
        if (cabeza == null) {
            System.out.println("No hay prestamos activos.");
            return;
        }
        NodoPrestamo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}

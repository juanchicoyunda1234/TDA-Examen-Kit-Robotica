package estructuras;

import modelo.EstadoKit;
import modelo.KitRobotica;

public class InventarioKits {

    private KitRobotica[] kits;
    private int tope;
    private int capacidad;

    public InventarioKits(int capacidad) {
        this.capacidad = capacidad;
        this.kits = new KitRobotica[capacidad];
        this.tope = 0;
    }

    public boolean agregarKit(KitRobotica kit) {
        if (tope >= capacidad) {
            return false;
        }
        if (buscarPorCodigo(kit.getCodigo()) != null) {
            return false;
        }
        kits[tope] = kit;
        tope++;
        return true;
    }

    public KitRobotica buscarPorCodigo(String codigo) {
        for (int i = 0; i < tope; i++) {
            if (kits[i].getCodigo().equalsIgnoreCase(codigo)) {
                return kits[i];
            }
        }
        return null;
    }

    public boolean modificarEstado(String codigo, EstadoKit nuevoEstado) {
        KitRobotica kit = buscarPorCodigo(codigo);
        if (kit == null) {
            return false;
        }
        kit.setEstado(nuevoEstado);
        return true;
    }

    public boolean eliminarKit(String codigo) {
        int indice = indexOf(codigo);
        if (indice == -1) {
            return false;
        }
        if (kits[indice].getEstado() == EstadoKit.PRESTADO) {
            return false;
        }
        for (int i = indice; i < tope - 1; i++) {
            kits[i] = kits[i + 1];
        }
        kits[tope - 1] = null;
        tope--;
        return true;
    }

    private int indexOf(String codigo) {
        for (int i = 0; i < tope; i++) {
            if (kits[i].getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public KitRobotica obtenerPrimerDisponible() {
        for (int i = 0; i < tope; i++) {
            if (kits[i].getEstado() == EstadoKit.DISPONIBLE) {
                return kits[i];
            }
        }
        return null;
    }

    public boolean hayKitDisponible() {
        return obtenerPrimerDisponible() != null;
    }

    public void mostrarEnMantenimiento() {
        boolean encontrado = false;
        for (int i = 0; i < tope; i++) {
            if (kits[i].getEstado() == EstadoKit.MANTENIMIENTO) {
                System.out.println(kits[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay kits en mantenimiento.");
        }
    }

    public void mostrarTodos() {
        if (tope == 0) {
            System.out.println("El inventario no tiene kits registrados.");
            return;
        }
        System.out.println("Codigo   | Controlador  | Piezas     | Estado");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < tope; i++) {
            System.out.println(kits[i]);
        }
    }

    public int getTope() {
        return tope;
    }

    public int getCapacidad() {
        return capacidad;
    }
}

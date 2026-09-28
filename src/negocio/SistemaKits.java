package negocio;

import estructuras.ColaSolicitudes;
import estructuras.HistorialMovimientos;
import estructuras.InventarioKits;
import estructuras.ListaPrestamos;
import estructuras.PilaOperaciones;
import estructuras.TurnosMesaEnsamblaje;
import modelo.EstadoKit;
import modelo.EventoHistorial;
import modelo.KitRobotica;
import modelo.OperacionCritica;
import modelo.Prestamo;
import modelo.SolicitudPrestamo;
import modelo.Equipo;

public class SistemaKits {

    private InventarioKits inventario;
    private ListaPrestamos prestamos;
    private ColaSolicitudes solicitudes;
    private HistorialMovimientos historial;
    private PilaOperaciones operaciones;
    private TurnosMesaEnsamblaje mesaEnsamblaje;
    private int contadorTurno;

    public SistemaKits(int capacidadInventario) {
        this.inventario = new InventarioKits(capacidadInventario);
        this.prestamos = new ListaPrestamos();
        this.solicitudes = new ColaSolicitudes();
        this.historial = new HistorialMovimientos();
        this.operaciones = new PilaOperaciones();
        this.mesaEnsamblaje = new TurnosMesaEnsamblaje();
        this.contadorTurno = 0;
    }

    private int siguienteTurno() {
        contadorTurno++;
        return contadorTurno;
    }

    public boolean registrarKit(String codigo, String controlador, int piezas, EstadoKit estado) {
        KitRobotica kit = new KitRobotica(codigo, controlador, piezas, estado);
        boolean agregado = inventario.agregarKit(kit);
        if (agregado) {
            int turno = siguienteTurno();
            historial.registrarEvento(new EventoHistorial(turno, "REGISTRO_KIT", codigo, "-", "Kit registrado con " + piezas + " piezas, estado " + estado));
        }
        return agregado;
    }

    public void mostrarInventario() {
        inventario.mostrarTodos();
    }

    public KitRobotica buscarKit(String codigo) {
        return inventario.buscarPorCodigo(codigo);
    }

    public boolean eliminarKit(String codigo) {
        return inventario.eliminarKit(codigo);
    }

    public String solicitarPrestamo(String nombreEquipo, String responsable, int integrantes) {
        Equipo equipo = new Equipo(nombreEquipo, responsable, integrantes);
        int turno = siguienteTurno();
        KitRobotica kitDisponible = inventario.obtenerPrimerDisponible();
        if (kitDisponible == null) {
            solicitudes.encolar(new SolicitudPrestamo(equipo, turno));
            historial.registrarEvento(new EventoHistorial(turno, "SOLICITUD_EN_ESPERA", "-", nombreEquipo, "Sin kits completos disponibles, enviado a cola"));
            return "SIN_DISPONIBILIDAD";
        }
        return concretarPrestamo(kitDisponible, equipo, turno);
    }

    private String concretarPrestamo(KitRobotica kit, Equipo equipo, int turno) {
        EstadoKit estadoAnterior = kit.getEstado();
        kit.setEstado(EstadoKit.PRESTADO);
        Prestamo prestamo = new Prestamo(kit.getCodigo(), equipo, turno);
        prestamos.insertar(prestamo);
        historial.registrarEvento(new EventoHistorial(turno, "PRESTAMO", kit.getCodigo(), equipo.getNombreEquipo(), "Prestamo registrado"));
        operaciones.apilar(new OperacionCritica("PRESTAMO", kit.getCodigo(), estadoAnterior, prestamo, turno));
        return kit.getCodigo();
    }

    public boolean sembrarPrestamo(String codigoKit, String nombreEquipo, String responsable, int integrantes) {
        KitRobotica kit = inventario.buscarPorCodigo(codigoKit);
        if (kit == null) {
            return false;
        }
        kit.setEstado(EstadoKit.PRESTADO);
        Equipo equipo = new Equipo(nombreEquipo, responsable, integrantes);
        int turno = siguienteTurno();
        Prestamo prestamo = new Prestamo(codigoKit, equipo, turno);
        prestamos.insertar(prestamo);
        historial.registrarEvento(new EventoHistorial(turno, "PRESTAMO", codigoKit, nombreEquipo, "Prestamo cargado como dato inicial"));
        return true;
    }

    public String atenderSiguienteSolicitud() {
        if (solicitudes.estaVacia()) {
            return "COLA_VACIA";
        }
        KitRobotica kitDisponible = inventario.obtenerPrimerDisponible();
        if (kitDisponible == null) {
            return "SIN_DISPONIBILIDAD";
        }
        SolicitudPrestamo solicitud = solicitudes.desencolar();
        int turno = siguienteTurno();
        return concretarPrestamo(kitDisponible, solicitud.getEquipo(), turno);
    }

    public void listarSolicitudes() {
        solicitudes.listar();
    }

    public void registrarEquipoEnMesa(String equipo) {
        mesaEnsamblaje.insertarEquipo(equipo);
        int turno = siguienteTurno();
        historial.registrarEvento(new EventoHistorial(turno, "TURNO_REGISTRADO", "-", equipo, "Ingresa a la rotacion de la mesa de ensamblaje"));
    }

    public String avanzarTurnoMesa() {
        return mesaEnsamblaje.avanzarTurno();
    }

    public String eliminarTurnoActual() {
        String eliminado = mesaEnsamblaje.eliminarActual();
        if (eliminado != null) {
            int turno = siguienteTurno();
            historial.registrarEvento(new EventoHistorial(turno, "TURNO_ELIMINADO", "-", eliminado, "Sale de la rotacion de la mesa de ensamblaje"));
        }
        return eliminado;
    }

    public String equipoTurnoActual() {
        return mesaEnsamblaje.equipoActual();
    }

    public void mostrarRondaMesa() {
        mesaEnsamblaje.mostrarRonda();
    }

    public void mostrarHistorialAdelante() {
        historial.mostrarHaciaAdelante();
    }

    public void mostrarHistorialAtras() {
        historial.mostrarHaciaAtras();
    }

    public void mostrarPrestamosActivos() {
        prestamos.mostrarTodos();
    }

    public InventarioKits getInventario() {
        return inventario;
    }
}

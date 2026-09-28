package app;

import modelo.EstadoKit;
import modelo.KitRobotica;
import negocio.SistemaKits;

public class CasosPrueba {

    public static void ejecutar(SistemaKits sistema) {
        separador("ESTADO INICIAL DEL INVENTARIO");
        sistema.mostrarInventario();

        separador("CASO 1 - Prestamo exitoso con kit disponible");
        String resultado1 = sistema.solicitarPrestamo("Equipo Delta", "Joseph Romo", 3);
        System.out.println("Resultado: Equipo Delta recibe el kit " + resultado1);
        sistema.mostrarPrestamosActivos();

        separador("CASO 2 - Solicitud enviada a la cola por falta de kits completos");
        String prestamoEpsilon = sistema.solicitarPrestamo("Equipo Epsilon", "Andres Llamuca", 3);
        System.out.println("Equipo Epsilon recibe el kit " + prestamoEpsilon);
        String resultadoZeta = sistema.solicitarPrestamo("Equipo Zeta", "Noemi Tuza", 2);
        System.out.println("Resultado para Equipo Zeta: " + resultadoZeta + " (todos los kits estan prestados)");
        sistema.listarSolicitudes();

        separador("CASO 3 - Deshacer la ultima operacion critica (el prestamo de Equipo Epsilon)");
        String deshacer1 = sistema.deshacerUltimaOperacion();
        System.out.println("Operacion deshecha: " + deshacer1);
        KitRobotica kit003 = sistema.buscarKit("KIT003");
        System.out.println("Estado de KIT003 tras deshacer: " + kit003.getEstado());

        separador("CASO 4 - Devolucion completa y atencion automatica de la cola");
        String devolucion1 = sistema.procesarDevolucion("KIT001", 42);
        System.out.println("Devolucion de KIT001 por Equipo Delta: " + devolucion1);
        String atendido = sistema.atenderSiguienteSolicitud();
        System.out.println("Solicitud atendida con el kit " + atendido + " (Equipo Zeta ya no espera en cola)");

        separador("CASO 5 - Regla diferenciadora: devolucion con piezas faltantes");
        String devolucion2 = sistema.procesarDevolucion("KIT002", 33);
        System.out.println("Devolucion de KIT002 por Equipo Alpha (faltan piezas): " + devolucion2);
        KitRobotica kit002 = sistema.buscarKit("KIT002");
        System.out.println("Estado de KIT002 tras la devolucion incompleta: " + kit002.getEstado());

        separador("CASO 6 - Reposicion de piezas y liberacion del kit en mantenimiento");
        boolean repuesto = sistema.reponerPiezas("KIT002");
        System.out.println("Reposicion de piezas de KIT002: " + (repuesto ? "exitosa" : "fallida"));
        System.out.println("Estado de KIT002 despues de reponer piezas: " + kit002.getEstado());

        separador("CASO 7 - Turnos circulares en la mesa de ensamblaje");
        System.out.println("Ronda inicial:");
        sistema.mostrarRondaMesa();
        System.out.println("Turno actual: " + sistema.avanzarTurnoMesa());
        System.out.println("Turno actual: " + sistema.avanzarTurnoMesa());
        String eliminadoTurno = sistema.eliminarTurnoActual();
        System.out.println("Se elimina de la rotacion a " + eliminadoTurno);
        System.out.println("Ronda final:");
        sistema.mostrarRondaMesa();

        separador("CASO 8 - Validacion al eliminar del inventario (lista secuencial)");
        boolean eliminarPrestado = sistema.eliminarKit("KIT001");
        System.out.println("Intento de eliminar KIT001 mientras esta prestado: " + (eliminarPrestado ? "permitido (error)" : "rechazado correctamente"));
        sistema.registrarKit("KIT004", "Raspberry Pi", 25, EstadoKit.DISPONIBLE);
        boolean eliminarDisponible = sistema.eliminarKit("KIT004");
        System.out.println("Intento de eliminar KIT004 disponible: " + (eliminarDisponible ? "eliminado correctamente" : "rechazado (error)"));

        separador("HISTORIAL COMPLETO (mas reciente primero)");
        sistema.mostrarHistorialAtras();

        separador("INVENTARIO FINAL");
        sistema.mostrarInventario();
    }

    private static void separador(String titulo) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println(titulo);
        System.out.println("========================================================");
    }
}

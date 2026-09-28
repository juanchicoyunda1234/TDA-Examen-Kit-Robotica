package app;

import java.util.Scanner;

import modelo.EstadoKit;
import modelo.KitRobotica;
import negocio.DatosPrueba;
import negocio.SistemaKits;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaKits sistema = new SistemaKits(20);
        DatosPrueba.cargarDatosIniciales(sistema);

        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("===== SISTEMA DE CONTROL DE KITS DE ROBOTICA - GRUPO 3 =====");
            System.out.println("1. Inventario de kits");
            System.out.println("2. Prestamos por equipo");
            System.out.println("3. Cola de solicitudes en espera");
            System.out.println("4. Devoluciones");
            System.out.println("5. Mantenimiento");
            System.out.println("6. Historial de movimientos");
            System.out.println("7. Turnos de la mesa de ensamblaje");
            System.out.println("8. Deshacer ultima operacion critica");
            System.out.println("9. Ejecutar casos de prueba automaticos");
            System.out.println("10. Salir");
            System.out.print("Elige una opcion: ");

            int opcion = leerEntero();
            switch (opcion) {
                case 1:
                    menuInventario(sistema);
                    break;
                case 2:
                    menuPrestamos(sistema);
                    break;
                case 3:
                    menuCola(sistema);
                    break;
                case 4:
                    menuDevoluciones(sistema);
                    break;
                case 5:
                    menuMantenimiento(sistema);
                    break;
                case 6:
                    menuHistorial(sistema);
                    break;
                case 7:
                    menuTurnos(sistema);
                    break;
                case 8:
                    deshacer(sistema);
                    break;
                case 9:
                    CasosPrueba.ejecutar(sistema);
                    break;
                case 10:
                    salir = true;
                    System.out.println("Cerrando el sistema de kits de robotica.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
        sc.close();
    }

    private static void menuInventario(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Inventario de kits ---");
            System.out.println("1. Registrar kit");
            System.out.println("2. Buscar kit por codigo");
            System.out.println("3. Mostrar todos los kits");
            System.out.println("4. Modificar estado de un kit");
            System.out.println("5. Eliminar kit");
            System.out.println("6. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1: {
                    System.out.print("Codigo del kit: ");
                    String codigo = leerTexto();
                    System.out.print("Controlador: ");
                    String controlador = leerTexto();
                    System.out.print("Numero de piezas: ");
                    int piezas = leerEntero();
                    boolean ok = sistema.registrarKit(codigo, controlador, piezas, EstadoKit.DISPONIBLE);
                    System.out.println(ok ? "Kit registrado correctamente." : "No se pudo registrar el kit (codigo repetido o inventario lleno).");
                    break;
                }
                case 2: {
                    System.out.print("Codigo del kit: ");
                    String codigo = leerTexto();
                    KitRobotica kit = sistema.buscarKit(codigo);
                    System.out.println(kit == null ? "No existe un kit con ese codigo." : kit);
                    break;
                }
                case 3:
                    sistema.mostrarInventario();
                    break;
                case 4: {
                    System.out.print("Codigo del kit: ");
                    String codigo = leerTexto();
                    KitRobotica kit = sistema.buscarKit(codigo);
                    if (kit == null) {
                        System.out.println("No existe un kit con ese codigo.");
                        break;
                    }
                    System.out.println("Estado actual: " + kit.getEstado());
                    System.out.println("1. DISPONIBLE  2. PRESTADO  3. MANTENIMIENTO");
                    int op = leerEntero();
                    EstadoKit nuevo = op == 1 ? EstadoKit.DISPONIBLE : op == 2 ? EstadoKit.PRESTADO : EstadoKit.MANTENIMIENTO;
                    kit.setEstado(nuevo);
                    System.out.println("Estado actualizado a " + nuevo);
                    break;
                }
                case 5: {
                    System.out.print("Codigo del kit a eliminar: ");
                    String codigo = leerTexto();
                    boolean ok = sistema.eliminarKit(codigo);
                    System.out.println(ok ? "Kit eliminado." : "No se pudo eliminar (no existe o esta prestado).");
                    break;
                }
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void menuPrestamos(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Prestamos por equipo ---");
            System.out.println("1. Solicitar prestamo para un equipo");
            System.out.println("2. Mostrar prestamos activos");
            System.out.println("3. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1: {
                    System.out.print("Nombre del equipo: ");
                    String equipo = leerTexto();
                    System.out.print("Responsable: ");
                    String responsable = leerTexto();
                    System.out.print("Numero de integrantes: ");
                    int integrantes = leerEntero();
                    String resultado = sistema.solicitarPrestamo(equipo, responsable, integrantes);
                    if (resultado.equals("SIN_DISPONIBILIDAD")) {
                        System.out.println("No hay kits completos disponibles. La solicitud paso a la cola de espera.");
                    } else {
                        System.out.println("Prestamo concretado con el kit " + resultado);
                    }
                    break;
                }
                case 2:
                    sistema.mostrarPrestamosActivos();
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void menuCola(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Cola de solicitudes en espera ---");
            System.out.println("1. Atender siguiente solicitud");
            System.out.println("2. Listar solicitudes en espera");
            System.out.println("3. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1: {
                    String resultado = sistema.atenderSiguienteSolicitud();
                    if (resultado.equals("COLA_VACIA")) {
                        System.out.println("No hay solicitudes en espera.");
                    } else if (resultado.equals("SIN_DISPONIBILIDAD")) {
                        System.out.println("Sigue sin haber kits disponibles para atender la solicitud.");
                    } else {
                        System.out.println("Solicitud atendida con el kit " + resultado);
                    }
                    break;
                }
                case 2:
                    sistema.listarSolicitudes();
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void menuDevoluciones(SistemaKits sistema) {
        System.out.println();
        System.out.println("--- Devoluciones ---");
        System.out.print("Codigo del kit a devolver: ");
        String codigo = leerTexto();
        System.out.print("Piezas devueltas: ");
        int piezas = leerEntero();
        String resultado = sistema.procesarDevolucion(codigo, piezas);
        switch (resultado) {
            case "KIT_NO_EXISTE":
                System.out.println("No existe un kit con ese codigo.");
                break;
            case "KIT_NO_PRESTADO":
                System.out.println("Ese kit no esta actualmente prestado.");
                break;
            case "FALTANTE":
                System.out.println("Faltan piezas. El kit fue enviado a Mantenimiento y no puede volver a Disponible hasta reponerlas.");
                break;
            case "COMPLETA":
                System.out.println("Devolucion completa. El kit vuelve a estar Disponible.");
                break;
            default:
                System.out.println("Resultado: " + resultado);
        }
    }

    private static void menuMantenimiento(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Mantenimiento ---");
            System.out.println("1. Mostrar kits en mantenimiento");
            System.out.println("2. Reponer piezas de un kit");
            System.out.println("3. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1:
                    sistema.mostrarKitsEnMantenimiento();
                    break;
                case 2: {
                    System.out.print("Codigo del kit: ");
                    String codigo = leerTexto();
                    boolean ok = sistema.reponerPiezas(codigo);
                    System.out.println(ok ? "Piezas repuestas, el kit vuelve a estar Disponible." : "No se pudo reponer (el kit no existe o no esta en Mantenimiento).");
                    break;
                }
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void menuHistorial(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Historial de movimientos ---");
            System.out.println("1. Mostrar historial cronologico (mas antiguo primero)");
            System.out.println("2. Mostrar historial reciente primero");
            System.out.println("3. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1:
                    sistema.mostrarHistorialAdelante();
                    break;
                case 2:
                    sistema.mostrarHistorialAtras();
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void menuTurnos(SistemaKits sistema) {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Turnos de la mesa de ensamblaje ---");
            System.out.println("1. Registrar equipo en la rotacion");
            System.out.println("2. Avanzar turno");
            System.out.println("3. Eliminar equipo actual de la rotacion");
            System.out.println("4. Mostrar ronda completa");
            System.out.println("5. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1: {
                    System.out.print("Nombre del equipo: ");
                    String equipo = leerTexto();
                    sistema.registrarEquipoEnMesa(equipo);
                    System.out.println("Equipo agregado a la rotacion.");
                    break;
                }
                case 2: {
                    String siguiente = sistema.avanzarTurnoMesa();
                    System.out.println(siguiente == null ? "No hay equipos en la rotacion." : "Turno actual: " + siguiente);
                    break;
                }
                case 3: {
                    String eliminado = sistema.eliminarTurnoActual();
                    System.out.println(eliminado == null ? "No hay equipos en la rotacion." : "Se elimino de la rotacion a " + eliminado);
                    break;
                }
                case 4:
                    sistema.mostrarRondaMesa();
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void deshacer(SistemaKits sistema) {
        String resultado = sistema.deshacerUltimaOperacion();
        if (resultado.equals("PILA_VACIA")) {
            System.out.println("No hay operaciones criticas para deshacer.");
        } else {
            System.out.println("Se deshizo la ultima operacion critica: " + resultado);
        }
    }

    private static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static String leerTexto() {
        String texto = sc.nextLine().trim();
        while (texto.isEmpty()) {
            System.out.print("El valor no puede estar vacio, ingresa de nuevo: ");
            texto = sc.nextLine().trim();
        }
        return texto;
    }
}


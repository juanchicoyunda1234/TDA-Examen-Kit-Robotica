package negocio;

import modelo.EstadoKit;

public class DatosPrueba {

    public static void cargarDatosIniciales(SistemaKits sistema) {
        sistema.registrarKit("KIT001", "Arduino", 42, EstadoKit.DISPONIBLE);
        sistema.registrarKit("KIT002", "ESP32", 38, EstadoKit.DISPONIBLE);
        sistema.registrarKit("KIT003", "Micro:bit", 40, EstadoKit.DISPONIBLE);

        sistema.sembrarPrestamo("KIT002", "Equipo Alpha", "Monica Tacuri", 4);

        sistema.registrarEquipoEnMesa("Equipo Alpha");
        sistema.registrarEquipoEnMesa("Equipo Beta");
        sistema.registrarEquipoEnMesa("Equipo Gamma");
        sistema.deshacerUltimaOperacion();
    }
}

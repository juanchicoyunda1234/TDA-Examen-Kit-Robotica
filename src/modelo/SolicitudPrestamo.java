package modelo;

public class SolicitudPrestamo {

    private Equipo equipo;
    private int turnoSolicitud;

    public SolicitudPrestamo(Equipo equipo, int turnoSolicitud) {
        this.equipo = equipo;
        this.turnoSolicitud = turnoSolicitud;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getTurnoSolicitud() {
        return turnoSolicitud;
    }

    @Override
    public String toString() {
        return String.format("Solicitud de %s (turno %d)", equipo.getNombreEquipo(), turnoSolicitud);
    }
}

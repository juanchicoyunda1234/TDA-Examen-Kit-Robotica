package modelo;

public class Prestamo {

    private String codigoKit;
    private Equipo equipo;
    private int turnoSolicitud;

    public Prestamo(String codigoKit, Equipo equipo, int turnoSolicitud) {
        this.codigoKit = codigoKit;
        this.equipo = equipo;
        this.turnoSolicitud = turnoSolicitud;
    }

    public String getCodigoKit() {
        return codigoKit;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getTurnoSolicitud() {
        return turnoSolicitud;
    }

    @Override
    public String toString() {
        return String.format("Kit %s -> %s (turno %d)", codigoKit, equipo.getNombreEquipo(), turnoSolicitud);
    }
}

package modelo;

public class EventoHistorial {

    private int turno;
    private String tipo;
    private String codigoKit;
    private String equipo;
    private String detalle;

    public EventoHistorial(int turno, String tipo, String codigoKit, String equipo, String detalle) {
        this.turno = turno;
        this.tipo = tipo;
        this.codigoKit = codigoKit;
        this.equipo = equipo;
        this.detalle = detalle;
    }

    public int getTurno() {
        return turno;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigoKit() {
        return codigoKit;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getDetalle() {
        return detalle;
    }

    @Override
    public String toString() {
        return String.format("[Turno %02d] %-18s Kit %-8s Equipo %-12s %s", turno, tipo, codigoKit, equipo, detalle);
    }
}

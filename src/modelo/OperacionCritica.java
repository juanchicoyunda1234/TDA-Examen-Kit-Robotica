package modelo;

public class OperacionCritica {

    private String tipo;
    private String codigoKit;
    private EstadoKit estadoAnterior;
    private Prestamo prestamoAfectado;
    private int turno;

    public OperacionCritica(String tipo, String codigoKit, EstadoKit estadoAnterior, Prestamo prestamoAfectado, int turno) {
        this.tipo = tipo;
        this.codigoKit = codigoKit;
        this.estadoAnterior = estadoAnterior;
        this.prestamoAfectado = prestamoAfectado;
        this.turno = turno;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigoKit() {
        return codigoKit;
    }

    public EstadoKit getEstadoAnterior() {
        return estadoAnterior;
    }

    public Prestamo getPrestamoAfectado() {
        return prestamoAfectado;
    }

    public int getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return String.format("%s sobre kit %s (turno %d)", tipo, codigoKit, turno);
    }
}

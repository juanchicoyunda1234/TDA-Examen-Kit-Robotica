package modelo;

public class KitRobotica {

    private String codigo;
    private String controlador;
    private int piezasRegistradas;
    private EstadoKit estado;

    public KitRobotica(String codigo, String controlador, int piezasRegistradas, EstadoKit estado) {
        this.codigo = codigo;
        this.controlador = controlador;
        this.piezasRegistradas = piezasRegistradas;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getControlador() {
        return controlador;
    }

    public int getPiezasRegistradas() {
        return piezasRegistradas;
    }

    public EstadoKit getEstado() {
        return estado;
    }

    public void setEstado(EstadoKit estado) {
        this.estado = estado;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public void setPiezasRegistradas(int piezasRegistradas) {
        this.piezasRegistradas = piezasRegistradas;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-12s | %3d piezas | %s", codigo, controlador, piezasRegistradas, estado);
    }
}

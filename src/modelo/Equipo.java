package modelo;

public class Equipo {

    private String nombreEquipo;
    private String responsable;
    private int integrantes;

    public Equipo(String nombreEquipo, String responsable, int integrantes) {
        this.nombreEquipo = nombreEquipo;
        this.responsable = responsable;
        this.integrantes = integrantes;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getResponsable() {
        return responsable;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    @Override
    public String toString() {
        return String.format("%s (responsable: %s, %d integrantes)", nombreEquipo, responsable, integrantes);
    }
}

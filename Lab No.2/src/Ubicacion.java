public final class Ubicacion {
    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado;

    public Ubicacion(String codigo, String nombre, String direccion,
                     int nivelRiesgo, String estado) {

        if (codigo == null || codigo.trim().isEmpty()
                || nombre == null || nombre.trim().isEmpty()
                || direccion == null || direccion.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Codigo, nombre y direccion son obligatorios.");
        }

        actualizar(nivelRiesgo, estado);
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.direccion = direccion.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public String getEstado() {
        return estado;
    }

    public void actualizar(int nivelRiesgo, String estado) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new IllegalArgumentException(
                    "El riesgo debe estar entre 1 y 10.");
        }

        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El estado es obligatorio.");
        }

        this.nivelRiesgo = nivelRiesgo;
        this.estado = estado.trim();
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Direccion: " + direccion
                + " | Riesgo: " + nivelRiesgo
                + " | Estado: " + estado;
    }
}
    

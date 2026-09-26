public final class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia,
                 int nivelImportancia, int nivelConfiabilidad) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El codigo de la pista es obligatorio.");
        }

        actualizar(descripcion, tipoEvidencia,
                nivelImportancia, nivelConfiabilidad);

        this.codigo = codigo.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void actualizar(String descripcion, String tipoEvidencia,
                           int nivelImportancia, int nivelConfiabilidad) {

        if (descripcion == null || descripcion.trim().isEmpty()
                || tipoEvidencia == null || tipoEvidencia.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Descripcion y tipo de evidencia son obligatorios.");
        }

        if (nivelImportancia < 1 || nivelImportancia > 10) {
            throw new IllegalArgumentException(
                    "La importancia debe estar entre 1 y 10.");
        }

        if (nivelConfiabilidad < 0 || nivelConfiabilidad > 100) {
            throw new IllegalArgumentException(
                    "La confiabilidad debe estar entre 0 y 100.");
        }

        this.descripcion = descripcion.trim();
        this.tipoEvidencia = tipoEvidencia.trim();
        this.nivelImportancia = nivelImportancia;
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Descripcion: " + descripcion
                + " | Tipo: " + tipoEvidencia
                + " | Importancia: " + nivelImportancia
                + " | Confiabilidad: " + nivelConfiabilidad + "%";
    }
}
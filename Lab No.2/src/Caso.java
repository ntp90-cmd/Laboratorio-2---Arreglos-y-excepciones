import java.util.ArrayList;

public class Caso {
    private String nombre;
    private String codigo;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detectiveResponsable) {
        if (nombre == null || nombre.trim().isEmpty()
                || codigo == null || codigo.trim().isEmpty()
                || detectiveResponsable == null
                || detectiveResponsable.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Todos los datos del caso son obligatorios.");
        }

        this.nombre = nombre.trim();
        this.codigo = codigo.trim();
        this.detectiveResponsable = detectiveResponsable.trim();

        ubicaciones = new Ubicacion[5];
        pistas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetectiveResponsable() {
        return detectiveResponsable;
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException(
                    "La posicion debe estar entre 0 y 4.");
        }
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion) {
        validarPosicion(posicion);

        if (ubicacion == null) {
            throw new IllegalArgumentException(
                    "La ubicacion no puede ser null.");
        }

        if (ubicaciones[posicion] != null) {
            throw new IllegalArgumentException(
                    "La posicion ya esta ocupada.");
        }

        ubicaciones[posicion] = ubicacion;
    }

    public String consultarUbicaciones() {
        String resultado = "";

        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                resultado += "Posicion " + i + " -> "
                        + ubicaciones[i] + "\n";
            }
        }

        if (resultado.isEmpty()) {
            return "No hay ubicaciones registradas.";
        }

        return resultado;
    }

    public Ubicacion buscarUbicacion(int posicion) {
        validarPosicion(posicion);
        return ubicaciones[posicion];
    }

    public void modificarUbicacion(int posicion, int nivelRiesgo,
                                   String estado) {

        Ubicacion ubicacion = buscarUbicacion(posicion);

        if (ubicacion == null) {
            throw new IllegalArgumentException(
                    "La posicion esta vacia.");
        }

        ubicacion.actualizar(nivelRiesgo, estado);
    }

    public void descartarUbicacion(int posicion) {
        if (buscarUbicacion(posicion) == null) {
            throw new IllegalArgumentException(
                    "La posicion esta vacia.");
        }

        ubicaciones[posicion] = null;
    }

    public void registrarPista(Pista pista) {
        if (pista == null) {
            throw new IllegalArgumentException(
                    "La pista no puede ser null.");
        }

        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una pista con ese codigo.");
        }

        pistas.add(pista);
    }

    public String consultarPistas() {
        if (pistas.isEmpty()) {
            return "No hay pistas registradas.";
        }

        String resultado = "";

        for (Pista pista : pistas) {
            resultado += pista + "\n";
        }

        return resultado;
    }

    public Pista buscarPista(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El codigo de la pista es obligatorio.");
        }

        for (Pista pista : pistas) {
            if (pista.getCodigo().equals(codigo.trim())) {
                return pista;
            }
        }

        return null;
    }

    public void modificarPista(String codigo, String descripcion,
                              String tipoEvidencia, int nivelImportancia,
                              int nivelConfiabilidad) {

        Pista pista = buscarPista(codigo);

        if (pista == null) {
            throw new IllegalArgumentException(
                    "No existe una pista con ese codigo.");
        }

        pista.actualizar(descripcion, tipoEvidencia,
                nivelImportancia, nivelConfiabilidad);
    }

    public void eliminarPista(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El codigo de la pista es obligatorio.");
        }

        for (int i = 0; i < pistas.size(); i++) {
            if (pistas.get(i).getCodigo().equals(codigo.trim())) {
                pistas.remove(i);
                return;
            }
        }

        throw new IllegalArgumentException(
                "No existe una pista con ese codigo.");
    }

    public int contarUbicaciones() {
        int cantidad = 0;

        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int contarEspaciosDisponibles() {
        return ubicaciones.length - contarUbicaciones();
    }

    public Ubicacion obtenerUbicacionMayorRiesgo() {
        Ubicacion mayor = null;

        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null
                    && (mayor == null
                    || ubicacion.getNivelRiesgo() > mayor.getNivelRiesgo())) {

                mayor = ubicacion;
            }
        }

        return mayor;
    }

    public int contarPistas() {
        return pistas.size();
    }

    public Pista obtenerPistaMayorImportancia() {
        Pista mayor = null;

        for (Pista pista : pistas) {
            if (mayor == null
                    || pista.getNivelImportancia() > mayor.getNivelImportancia()) {

                mayor = pista;
            }
        }

        return mayor;
    }

    public Pista obtenerPistaMayorConfiabilidad() {
        Pista mayor = null;

        for (Pista pista : pistas) {
            if (mayor == null
                    || pista.getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {

                mayor = pista;
            }
        }

        return mayor;
    }

    public double calcularPromedioImportancia() {
        if (pistas.isEmpty()) {
            return 0.0;
        }

        double suma = 0;

        for (Pista pista : pistas) {
            suma += pista.getNivelImportancia();
        }

        return suma / pistas.size();
    }
}
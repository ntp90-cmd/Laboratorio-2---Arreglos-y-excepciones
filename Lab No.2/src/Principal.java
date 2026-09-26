import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {
    private static Scanner entrada;
    private static Caso casoActual;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);

        try {
            System.out.println("AGENCIA DE DETECTIVES");
            casoActual = crearCaso();

            boolean continuar = true;

            while (continuar) {
                try {
                    mostrarMenu();
                    int opcion = leerEntero("Seleccione una opcion: ");
                    continuar = ejecutarOpcion(opcion);

                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        } catch (NoSuchElementException e) {
            System.out.println("\nLa entrada ha finalizado.");

        } finally {
            entrada.close();
            System.out.println("Programa finalizado.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                            Menu 
                           1. Nuevo caso
                           2. Registrar ubicacion
                           3. Consultar ubicaciones
                           4. Consultar una ubicacion
                           5. Modificar ubicacion
                           6. Descartar ubicacion
                           7. Registrar pista
                           8. Consultar pistas
                           9. Buscar pista
                           10. Modificar pista
                           11. Eliminar pista
                           12. Mostrar reporte de investigacion
                           13. Salir""");
    }

    private static boolean ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 ->  {
                casoActual = crearCaso();
                System.out.println(
                        "Nuevo caso creado sin ubicaciones ni pistas.");
            }

            case 2 ->  {
                int posicion = leerEntero("Posicion (0 a 4): ");

                if (casoActual.buscarUbicacion(posicion) != null) {
                    throw new IllegalArgumentException(
                            "La posicion ya esta ocupada.");
                }

                String codigo = leerTexto("Codigo: ");
                String nombre = leerTexto("Nombre: ");
                String direccion = leerTexto(
                        "Direccion o descripcion del lugar: ");
                int riesgo = leerEntero("Riesgo (1 a 10): ");
                String estado = leerTexto("Estado: ");

                Ubicacion ubicacion = new Ubicacion(
                        codigo, nombre, direccion, riesgo, estado);

                casoActual.registrarUbicacion(posicion, ubicacion);
                System.out.println("Ubicacion registrada.");
            }

            case 3 -> System.out.println(casoActual.consultarUbicaciones());

            case 4 ->  {
                int posicion = leerEntero("Posicion (0 a 4): ");
                Ubicacion ubicacion = casoActual.buscarUbicacion(posicion);

                if (ubicacion == null) {
                    System.out.println("La posicion esta vacia.");
                } else {
                    System.out.println(ubicacion);
                }

            }

            case 5 ->  {
                int posicion = leerEntero("Posicion (0 a 4): ");

                if (casoActual.buscarUbicacion(posicion) == null) {
                    throw new IllegalArgumentException(
                            "La posicion esta vacia.");
                }

                int riesgo = leerEntero("Nuevo riesgo (1 a 10): ");
                String estado = leerTexto("Nuevo estado: ");

                casoActual.modificarUbicacion(posicion, riesgo, estado);
                System.out.println("Ubicacion modificada.");
            }

            case 6 ->  {
                int posicion = leerEntero("Posicion (0 a 4): ");
                casoActual.descartarUbicacion(posicion);
                System.out.println("Ubicacion descartada.");
            }

            case 7 ->  {
                String codigo = leerTexto("Codigo: ");
                String descripcion = leerTexto("Descripcion: ");
                String tipo = leerTexto("Tipo de evidencia: ");
                int importancia = leerEntero("Importancia (1 a 10): ");
                int confiabilidad = leerEntero("Confiabilidad (0 a 100): ");

                Pista pista = new Pista(
                        codigo, descripcion, tipo, importancia, confiabilidad);

                casoActual.registrarPista(pista);
                System.out.println("Pista registrada.");
            }

            case 8 -> System.out.println(casoActual.consultarPistas());

            case 9 ->  {
                String codigo = leerTexto("Codigo de la pista: ");
                Pista pista = casoActual.buscarPista(codigo);

                if (pista == null) {
                    System.out.println("No se encontro la pista.");
                } else {
                    System.out.println(pista);
                }

            }

            case 10 ->  {
                String codigo = leerTexto("Codigo de la pista: ");

                if (casoActual.buscarPista(codigo) == null) {
                    throw new IllegalArgumentException(
                            "No existe una pista con ese codigo.");
                }

                String descripcion = leerTexto("Nueva descripcion: ");
                String tipo = leerTexto("Nuevo tipo de evidencia: ");
                int importancia = leerEntero(
                        "Nueva importancia (1 a 10): ");
                int confiabilidad = leerEntero(
                        "Nueva confiabilidad (0 a 100): ");

                casoActual.modificarPista(
                        codigo, descripcion, tipo, importancia, confiabilidad);

                System.out.println("Pista modificada.");
            }

            case 11 ->  {
                String codigo = leerTexto("Codigo de la pista: ");
                casoActual.eliminarPista(codigo);
                System.out.println("Pista eliminada.");
            }

            case 12 -> mostrarReporte();

            case 13 -> {
                return false;
            }

            default -> System.out.println(
                        "Opcion invalida. Seleccione un numero del 1 al 13.");
        }

        return true;
    }

    private static Caso crearCaso() {
        System.out.println("\nDATOS DEL CASO");

        String nombre = leerTexto("Nombre del caso: ");
        String codigo = leerTexto("Codigo del caso: ");
        String detective = leerTexto("Detective responsable: ");

        return new Caso(nombre, codigo, detective);
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("El texto no puede estar vacio.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = entrada.nextInt();
                entrada.nextLine();
                return numero;

            } catch (InputMismatchException e) {
                System.out.println(
                        "Entrada incorrecta. Escriba un numero entero.");

                entrada.nextLine();
            }
        }
    }

    private static void mostrarReporte() {
        System.out.println("\nREPORTE DE INVESTIGACION");
        System.out.println("Caso: " + casoActual.getNombre());
        System.out.println("Codigo: " + casoActual.getCodigo());
        System.out.println(
                "Detective: " + casoActual.getDetectiveResponsable());

        System.out.println(
                "Ubicaciones registradas: " + casoActual.contarUbicaciones());

        System.out.println(
                "Espacios disponibles: " + casoActual.contarEspaciosDisponibles());

        Ubicacion mayorRiesgo = casoActual.obtenerUbicacionMayorRiesgo();

        if (mayorRiesgo == null) {
            System.out.println(
                    "No hay ubicaciones para comparar el riesgo.");
        } else {
            System.out.println(
                    "Ubicacion de mayor riesgo: " + mayorRiesgo);
        }

        System.out.println(
                "Pistas registradas: " + casoActual.contarPistas());

        if (casoActual.contarPistas() == 0) {
            System.out.println(
                    "No hay pistas para calcular maximos o promedio.");
        } else {
            System.out.println(
                    "Pista de mayor importancia: "
                            + casoActual.obtenerPistaMayorImportancia());

            System.out.println(
                    "Pista de mayor confiabilidad: "
                            + casoActual.obtenerPistaMayorConfiabilidad());

            System.out.printf(
                    "Promedio de importancia: %.2f%n",
                    casoActual.calcularPromedioImportancia());
        }
    }
}
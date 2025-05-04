import java.util.Scanner;

public class TeatroMoro {
    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static String[] ubicacionesVendidas = new String[100];  // Arreglo para ubicaciones vendidas
    static double[] preciosFinales = new double[100];        // Arreglo para precios finales
    static double[] descuentosAplicados = new double[100];   // Arreglo para descuentos aplicados
    static int[] idVentas = new int[100];                    // Arreglo para IDs de ventas
    static int[] idClientes = new int[100];                  // Arreglo para IDs de clientes
    static int[] edadesClientes = new int[100];              // Arreglo para edades de clientes
    static int[] idReservas = new int[100];                  // Arreglo para IDs de reservas
    static String[] ubicacionesReservadas = new String[100]; // Arreglo para ubicaciones reservadas
    static int contadorVentas = 0;                            // Contador de ventas

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n--- MENÚ TEATRO MORO ---");
            System.out.println("1. Vender entrada");
            System.out.println("2. Eliminar venta");
            System.out.println("3. Visualizar resumen de ventas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                venderEntrada(scanner);
            } else if (opcion == 2) {
                System.out.print("Ingrese ID de la venta a eliminar: ");
                eliminarVenta(scanner.nextInt());
            } else if (opcion == 3) {
                visualizarResumen();
            } else if (opcion == 4) {
                System.out.println("¡Gracias por visitar el Teatro Moro!");
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    // Método para vender una entrada
    public static void venderEntrada(Scanner scanner) {
        System.out.println("\n--- VENTA DE ENTRADAS ---");
        System.out.println("Seleccione ubicación:");
        System.out.println("1. VIP ($150)");
        System.out.println("2. Platea ($100)");
        System.out.println("3. Balcón ($70)");
        int tipoEntrada = scanner.nextInt();

        String ubicacion = "";
        double precioBase = 0;
        if (tipoEntrada == 1) {
            ubicacion = "VIP";
            precioBase = 150;
        } else if (tipoEntrada == 2) {
            ubicacion = "Platea";
            precioBase = 100;
        } else if (tipoEntrada == 3) {
            ubicacion = "Balcón";
            precioBase = 70;
        } else {
            System.out.println("Ubicación inválida. Venta cancelada.");
            return;
        }

        // Ingreso del cliente
        System.out.println("¿El comprador es estudiante o adulto mayor?");
        System.out.println("1. Estudiante (10% de descuento)");
        System.out.println("2. Adulto mayor (15% de descuento)");
        System.out.println("3. Ninguno");
        int tipoCliente = scanner.nextInt();

        double descuento = 0.0;
        if (tipoCliente == 1) {
            descuento = 0.10;
        } else if (tipoCliente == 2) {
            descuento = 0.15;
        }

        // Cálculo del costo final
        double costoFinal = precioBase - (precioBase * descuento);

        // Almacenar la venta en los arreglos
        idVentas[contadorVentas] = contadorVentas + 1; // ID de venta es el contador
        idClientes[contadorVentas] = contadorVentas + 1; // ID de cliente es el contador (simplificación)
        edadesClientes[contadorVentas] = tipoCliente == 1 ? 20 : (tipoCliente == 2 ? 70 : 30); // Edad aproximada
        ubicacionesVendidas[contadorVentas] = ubicacion;
        preciosFinales[contadorVentas] = costoFinal;
        descuentosAplicados[contadorVentas] = precioBase * descuento;
        idReservas[contadorVentas] = contadorVentas + 1; // ID de reserva igual a ID de venta
        ubicacionesReservadas[contadorVentas] = ubicacion;

        totalEntradasVendidas++;
        totalIngresos += costoFinal;

        // Imprimir boleta
        System.out.println("\n--- BOLETA DE COMPRA ---");
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Costo base: $" + precioBase);
        System.out.println("Descuento aplicado: $" + (precioBase * descuento));
        System.out.println("Costo total: $" + costoFinal);
        System.out.println("¡Gracias por su visita al teatro Moro!\n");

        contadorVentas++;
    }

    // Método para eliminar una venta
    public static void eliminarVenta(int idVenta) {
        boolean encontrado = false;

        for (int i = 0; i < contadorVentas; i++) {
            if (idVentas[i] == idVenta) {
                // Eliminar la venta desplazando los elementos
                for (int j = i; j < contadorVentas - 1; j++) {
                    idVentas[j] = idVentas[j + 1];
                    idClientes[j] = idClientes[j + 1];
                    edadesClientes[j] = edadesClientes[j + 1];
                    ubicacionesVendidas[j] = ubicacionesVendidas[j + 1];
                    preciosFinales[j] = preciosFinales[j + 1];
                    descuentosAplicados[j] = descuentosAplicados[j + 1];
                    idReservas[j] = idReservas[j + 1];
                    ubicacionesReservadas[j] = ubicacionesReservadas[j + 1];
                }
                contadorVentas--;
                System.out.println("Venta eliminada exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró una venta con ese ID.");
        }
    }

    // Método para visualizar el resumen de ventas
    public static void visualizarResumen() {
        System.out.println("\n--- RESUMEN DE VENTAS ---");

        if (contadorVentas == 0) {
            System.out.println("No se han realizado ventas todavía.");
            return;
        }

        for (int i = 0; i < contadorVentas; i++) {
            System.out.println("Venta " + (i + 1) + ":");
            System.out.println("ID Venta: " + idVentas[i]);
            System.out.println("ID Cliente: " + idClientes[i]);
            System.out.println("Ubicación: " + ubicacionesVendidas[i]);
            System.out.println("Descuento aplicado: $" + descuentosAplicados[i]);
            System.out.println("Costo final: $" + preciosFinales[i]);
            System.out.println();
        }

        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Total de ingresos: $" + totalIngresos);
    }
}
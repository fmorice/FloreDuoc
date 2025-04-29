import java.util.ArrayList;
import java.util.Scanner;

public class TeatroMoro {
    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static ArrayList<String> ubicacionesVendidas = new ArrayList<>();
    static ArrayList<Double> preciosFinales = new ArrayList<>();
    static ArrayList<Double> descuentosAplicados = new ArrayList<>();

    // Variables de instancia
    String ubicacion;
    double precioBase;
    double descuento;
    double costoFinal;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ TEATRO MORO ---");
            System.out.println("1. Vender entrada");
            System.out.println("2. Visualizar resumen de ventas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                venderEntrada(scanner);
            } else if (opcion == 2) {
                visualizarResumen();
            } else if (opcion == 3) {
                System.out.println("¡Gracias por visitar el Teatro Moro!");
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 3);

        scanner.close();
    }

    public static void venderEntrada(Scanner scanner) {
        TeatroMoro venta = new TeatroMoro();

        System.out.println("\n--- VENTA DE ENTRADAS ---");
        System.out.println("Seleccione ubicación:");
        System.out.println("1. VIP ($150)");
        System.out.println("2. Platea ($100)");
        System.out.println("3. Balcón ($70)");
        int tipoEntrada = scanner.nextInt();

        if (tipoEntrada == 1) {
            venta.ubicacion = "VIP";
            venta.precioBase = 150;
        } else if (tipoEntrada == 2) {
            venta.ubicacion = "Platea";
            venta.precioBase = 100;
        } else if (tipoEntrada == 3) {
            venta.ubicacion = "Balcón";
            venta.precioBase = 70;
        } else {
            System.out.println("Ubicación inválida. Venta cancelada.");
            return;
        }

        System.out.println("¿El comprador es estudiante o adulto mayor?");
        System.out.println("1. Estudiante (10% de descuento)");
        System.out.println("2. Adulto mayor (15% de descuento)");
        System.out.println("3. Ninguno");
        int tipoCliente = scanner.nextInt();

        if (tipoCliente == 1) {
            venta.descuento = 0.10;
        } else if (tipoCliente == 2) {
            venta.descuento = 0.15;
        } else if (tipoCliente == 3) {
            venta.descuento = 0.0;
        } else {
            System.out.println("Opción inválida. No se aplicará descuento.");
            venta.descuento = 0.0;
        }

        // Cálculo del costo final
        venta.costoFinal = venta.precioBase - (venta.precioBase * venta.descuento);

        // Guardar información en las listas
        ubicacionesVendidas.add(venta.ubicacion);
        preciosFinales.add(venta.costoFinal);
        descuentosAplicados.add(venta.precioBase * venta.descuento);

        totalEntradasVendidas++;
        totalIngresos = totalIngresos + venta.costoFinal; // Suma más simple

        // Imprimir boleta
        System.out.println("\n--- BOLETA DE COMPRA ---");
        System.out.println("Ubicación: " + venta.ubicacion);
        System.out.println("Costo base: $" + venta.precioBase);
        System.out.println("Descuento aplicado: $" + (venta.precioBase * venta.descuento));
        System.out.println("Costo total: $" + venta.costoFinal);
        System.out.println("¡Gracias por su visita al teatro Moro!\n");
    }

    public static void visualizarResumen() {
        System.out.println("\n--- RESUMEN DE VENTAS ---");

        if (ubicacionesVendidas.isEmpty()) {
            System.out.println("No se han realizado ventas todavía.");
            return;
        }

        for (int i = 0; i < ubicacionesVendidas.size(); i++) {
            System.out.println("Venta " + (i + 1) + ":");
            System.out.println("Ubicación: " + ubicacionesVendidas.get(i));
            System.out.println("Descuento aplicado: $" + descuentosAplicados.get(i));
            System.out.println("Costo final: $" + preciosFinales.get(i));
            System.out.println();
        }

        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Total de ingresos: $" + totalIngresos);
    }
}
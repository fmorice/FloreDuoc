import java.util.Scanner;

public class TeatroMoro {

    // Variables estáticas (Paso 2)
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int totalReservas = 0;

    // Variables de instancia (Paso 2)
    int numeroEntrada = 0;
    String ubicacion = "";
    double precioFinal = 0;
    boolean reservado = false;

    // Variables locales 
    static int entradasVIP = 8;
    static int entradasPlatea = 10;
    static int entradasGeneral = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TeatroMoro entrada = new TeatroMoro();

        int opcion;
        do {
            // Menú de opciones (Paso 1)
            System.out.println("\n--- MENÚ DE VENTA TEATRO MORO ---");
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar entrada");
            System.out.println("4. Imprimir boleto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                entrada.reservarEntrada(scanner);
            } else if (opcion == 2) {
                entrada.comprarEntrada(scanner);
            } else if (opcion == 3) {
                entrada.modificarVenta(scanner);
            } else if (opcion == 4) {
                entrada.imprimirBoleta();
            } else if (opcion == 5) {
                System.out.println("Gracias por visitar el Teatro Moro.");
            } else {
                System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }

    // Paso 3: Reservar entrada
    public void reservarEntrada(Scanner scanner) {
        System.out.println("--- RESERVA DE ENTRADA ---");
        System.out.println("1. VIP ($150) - Disponibles: " + entradasVIP);
        System.out.println("2. Platea ($100) - Disponibles: " + entradasPlatea);
        System.out.println("3. General ($70) - Disponibles: " + entradasGeneral);
        System.out.print("Seleccione ubicación: ");
        int tipo = scanner.nextInt();

        // Validación y asignación
        if (tipo == 1 && entradasVIP > 0) {
            ubicacion = "VIP"; entradasVIP--; precioFinal = 150;
        } else if (tipo == 2 && entradasPlatea > 0) {
            ubicacion = "Platea"; entradasPlatea--; precioFinal = 100;
        } else if (tipo == 3 && entradasGeneral > 0) {
            ubicacion = "General"; entradasGeneral--; precioFinal = 70;
        } else {
            System.out.println("No hay entradas disponibles en esa ubicación.");
            return;
        }

        reservado = true;
        totalReservas++;
        numeroEntrada++;
        System.out.println("Entrada reservada con éxito. Número: " + numeroEntrada); // Breakpoint
    }

    // Paso 3: Comprar entrada
    public void comprarEntrada(Scanner scanner) {
    System.out.println("--- COMPRA DE ENTRADA ---");
    if (!reservado) {
        System.out.println("Primero debe hacer una reserva.");
        return;
    }

    System.out.println("¿Es estudiante?");
    System.out.println("1. Sí");
    System.out.println("2. No");
    System.out.print("Seleccione una opción: ");
    int opcionEstudiante = scanner.nextInt();

    double descuento = 0;
    if (opcionEstudiante == 1) {
        descuento = 0.10;
    } else if (opcionEstudiante == 2) {
        descuento = 0;
    } else {
        System.out.println("Opción inválida. Se asumirá que no es estudiante.");
    }

    precioFinal = precioFinal - (precioFinal * descuento); // Breakpoint
    totalIngresos = totalIngresos + precioFinal;
    totalEntradasVendidas++;

    System.out.println("Compra realizada con éxito. Total pagado: $" + precioFinal); // Breakpoint
}
    // Paso 3: Modificar la venta
    public void modificarVenta(Scanner scanner) {
        if (!reservado) {
            System.out.println("No hay reserva que modificar.");
            return;
        }

        System.out.println("Modificar ubicación de entrada:");
        System.out.println("1. VIP ($150)");
        System.out.println("2. Platea ($100)");
        System.out.println("3. General ($70)");
        int nuevaUbicacion = scanner.nextInt();

        if (nuevaUbicacion == 1 && entradasVIP > 0) {
            ubicacion = "VIP"; precioFinal = 150;
        } else if (nuevaUbicacion == 2 && entradasPlatea > 0) {
            ubicacion = "Platea"; precioFinal = 100;
        } else if (nuevaUbicacion == 3 && entradasGeneral > 0) {
            ubicacion = "General"; precioFinal = 70;
        } else {
            System.out.println("No hay disponibilidad.");
            return;
        }

        System.out.println("Modificación realizada."); // Breakpoint
    }

    // Paso 3: Imprimir boleta
    public void imprimirBoleta() {
        if (!reservado) {
            System.out.println("No hay compra registrada.");
            return;
        }

        System.out.println("\n--- BOLETA DE COMPRA ---"); // Breakpoint
        System.out.println("Entrada Nº: " + numeroEntrada);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Precio final: $" + precioFinal);
    }
}
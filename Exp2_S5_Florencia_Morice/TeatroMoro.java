import java.util.Scanner;

public class TeatroMoro {
    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0.0;
    static int totalEstudiantes = 0;

    // Variables de instancia
    Entrada[] entradasVendidas = new Entrada[200];
    int cantidadEntradas = 0;

    String nombreTeatro = "Teatro Moro";
    int capacidadSala = 200;
    int entradasDisponibles = capacidadSala;

    // Método principal
    public static void main(String[] args) {
        TeatroMoro teatro = new TeatroMoro();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Promociones");
            System.out.println("3. Ver última entrada vendida");
            System.out.println("4. Eliminar última entrada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                teatro.venderEntrada(scanner);
            } else if (opcion == 2) {
                teatro.mostrarPromociones();
            } else if (opcion == 3) {
                teatro.verUltimaEntrada();
            } else if (opcion == 4) {
                teatro.eliminarUltimaEntrada();
            } else if (opcion == 5) {
                salir = true;
                System.out.println("Gracias por visitar el Teatro Moro.");
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    public void venderEntrada(Scanner scanner) {
        if (cantidadEntradas >= capacidadSala) {
            System.out.println("Lo sentimos, no hay más entradas disponibles.");
            return;
        }

        String ubicacion = "";
        double precioBase = 0.0;
        double descuento = 0.0;
        String tipoPublico = "Público General";

        System.out.println("\nSeleccione la ubicación:");
        System.out.println("1. VIP ($120)");
        System.out.println("2. Platea ($90)");
        System.out.println("3. General ($60)");
        System.out.print("Opción: ");
        int opcionUbicacion = scanner.nextInt();

        if (opcionUbicacion == 1) {
            ubicacion = "VIP";
            precioBase = 120;
        } else if (opcionUbicacion == 2) {
            ubicacion = "Platea";
            precioBase = 90;
        } else if (opcionUbicacion == 3) {
            ubicacion = "General";
            precioBase = 60;
        } else {
            System.out.println("Ubicación no válida.");
            return;
        }

        System.out.println("Seleccione el tipo de público:");
        System.out.println("1. Estudiante");
        System.out.println("2. Otro");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            descuento = 0.10;
            tipoPublico = "Estudiante";
            totalEstudiantes++;
        } else {
            System.out.print("Ingrese su edad: ");
            int edad = scanner.nextInt();
            if (edad >= 60) {
                descuento = 0.15;
                tipoPublico = "Tercera Edad";
            }
        }

        double precioFinal = precioBase - (precioBase * descuento);
        int numeroEntrada = cantidadEntradas + 1;

        Entrada nuevaEntrada = new Entrada(numeroEntrada, ubicacion, tipoPublico, precioFinal);
        entradasVendidas[cantidadEntradas] = nuevaEntrada;
        cantidadEntradas++;

        totalEntradasVendidas++;
        totalIngresos += precioFinal;

        System.out.println("\n--- Resumen de la compra ---");
        System.out.println("Número de entrada: " + numeroEntrada);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Tipo de público: " + tipoPublico);
        System.out.println("Precio base: $" + precioBase);
        System.out.println("Descuento aplicado: " + (int)(descuento * 100) + "%");
        System.out.println("Total a pagar: $" + precioFinal);
    }

    public void mostrarPromociones() {
        System.out.println("\n--- Promociones Disponibles ---");
        System.out.println("- 10% de descuento para estudiantes.");
        System.out.println("- 15% de descuento para personas de la tercera edad (60+ años).\n");
    }

    public void verUltimaEntrada() {
        System.out.println("\n--- Última Entrada Vendida ---");

        if (cantidadEntradas > 0) {
            Entrada ultima = entradasVendidas[cantidadEntradas - 1];
            ultima.mostrar();
        } else {
            System.out.println("No hay entradas registradas.");
        }
    }

    public void eliminarUltimaEntrada() {
        if (cantidadEntradas > 0) {
            Entrada ultima = entradasVendidas[cantidadEntradas - 1];
            totalEntradasVendidas = totalEntradasVendidas - 1;
            totalIngresos = totalIngresos - entradasVendidas[cantidadEntradas - 1].precioFinal;
            cantidadEntradas = cantidadEntradas - 1;
            System.out.println("Última entrada eliminada correctamente.");
        } else {
            System.out.println("No hay entradas para eliminar.");
        }
    }
}

// Clase Entrada
class Entrada {
    int numero;
    String ubicacion;
    String tipoPublico;
    double precioFinal;

    Entrada(int numero, String ubicacion, String tipoPublico, double precioFinal) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tipoPublico = tipoPublico;
        this.precioFinal = precioFinal;
    }

    void mostrar() {
        System.out.println("\nEntrada Nº: " + numero);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Tipo de público: " + tipoPublico);
        System.out.println("Precio final: $" + precioFinal);
    }
}
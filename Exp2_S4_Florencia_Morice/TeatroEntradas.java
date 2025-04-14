import java.util.Scanner;

public class TeatroEntradas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        // Definimos los precios base por zona
        int precioZonaA = 100;
        int precioZonaB = 80;
        int precioZonaC = 50;
        
        for (int i= 1; i<=5; i++) { // Ciclo for para comprar entrada o salir
            System.out.println("\n--- Compra de entradas ---");
            System.out.println("1). Comprar entrada");
            System.out.println("2). Salir");
            System.out.print("Hola, ¿Deseas comprar una entrada? Seleccione una opción:  ");
            opcion = scanner.nextInt();
            
            if (opcion == 2) {
                System.out.println("Gracias por su visita. Hasta luego!");
                break;
            } else if (opcion == 1) {
                int precioEntrada = 0;
                String tipoPublico = "Público General";
                String ubicacion = "";
                
                System.out.println("¿En qué ubicación quieres comprar tu entrada?: ");
                System.out.println("1). Zona A ($100)");
                System.out.println("2). Zona B ($80)");
                System.out.println("3). Zona C ($50)");
                System.out.print("Ingrese número de la zona: ");
                int zona = scanner.nextInt();
                
                if (zona == 1) {
                    precioEntrada = precioZonaA;
                    ubicacion = "Zona A";
                } else if (zona == 2) {
                    precioEntrada = precioZonaB;
                    ubicacion = "Zona B";
                } else if (zona == 3) {
                    precioEntrada = precioZonaC;
                    ubicacion = "Zona C";
                } else {
                    System.out.println("Zona no válida. Inténtelo nuevamente.");
                    continue;
                }
                
                // Preguntar si es estudiante para aplicar descuento
                System.out.println("¿Es estudiante?");
                System.out.println("1). Si");
                System.out.println("2). No");
                System.out.print("Ingrese número correspondiente: ");
                int esEstudiante = scanner.nextInt();
                double descuento = 0;
                
                if (esEstudiante == 1) {
                    descuento = 0.10; // 10% para estudiantes
                    tipoPublico = "Estudiante";
                } else {
                    System.out.print("Ingrese su edad: ");
                    int edad = scanner.nextInt();
                    if (edad >= 60) {
                        descuento = 0.15; // 15% para tercera edad
                        tipoPublico = "Tercera Edad";
                    }
                }
                
                // Calcular precio con descuento
                double precioFinal = precioEntrada;
                if (descuento > 0) {
                    precioFinal = precioEntrada - (precioEntrada * descuento);
                }
                
                // Mostrar resumen de la transacción
                System.out.println("\n--- Resumen de la compra ---");
                System.out.println("Zona: " + ubicacion);
                System.out.println("Precio base: $" + precioEntrada);
                System.out.println("Descuento: " + descuento * 100 + "%");
                System.out.println("Total a pagar: $" + precioFinal);
                System.out.println("Tipo de público: " + tipoPublico);
                
                
                System.out.println("\n¿Quiéres comprar otra entrada?");
                System.out.println("1). Si");
                System.out.println("2). No");
                System.out.print("Ingrese número correspondiente: ");
                int continuar = scanner.nextInt();
                if (continuar != 1) {
                    System.out.println("Gracias por su compra. ¡Disfrute el espectáculo!");
                    break;
                }
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
        
        scanner.close();
    }
}

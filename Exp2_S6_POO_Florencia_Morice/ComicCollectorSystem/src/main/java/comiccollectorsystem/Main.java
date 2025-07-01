package comiccollectorsystem;

import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        
        try {
            // Reasigna la salida estándar con UTF-8
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Error al configurar UTF-8: " + e.getMessage());
        }
        // Línea de prueba para verificar que UTF-8 funciona correctamente
        System.out.println("🎉 ¡Bienvenido al Comic Collector System con acentos y emojis! áéíóú ñ");

        ComicStore tienda = new ComicStore();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n🎮 === MENÚ COMIC COLLECTOR ===");
            System.out.println("1. Cargar cómics desde archivo CSV");
            System.out.println("2. Mostrar todos los cómics");
            System.out.println("3. Registrar usuario");
            System.out.println("4. Mostrar usuarios registrados");
            System.out.println("5. Prestar un cómic");
            System.out.println("6. Devolver un cómic");
            System.out.println("7. Guardar usuarios en archivo");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> tienda.cargarComicsDesdeCSV("comics.csv");
                    case 2 -> tienda.mostrarComics();
                    case 3 -> {
                        System.out.print("Ingrese ID del usuario: ");
                        String id = scanner.nextLine();
                        System.out.print("Ingrese nombre del usuario: ");
                        String nombre = scanner.nextLine();
                        Usuario nuevo = new Usuario(id, nombre);
                        tienda.registrarUsuario(nuevo);
                    }
                    case 4 -> tienda.mostrarUsuarios();
                    case 5 -> {
                        System.out.print("Ingrese el título del cómic a prestar: ");
                        String titulo = scanner.nextLine();
                        tienda.prestarComic(titulo);
                    }
                    case 6 -> {
                        System.out.print("Ingrese el título del cómic a devolver: ");
                        String titulo = scanner.nextLine();
                        tienda.devolverComic(titulo);
                    }
                    case 7 -> tienda.guardarUsuarios("usuarios.txt");
                    case 8 -> System.out.println("👋 Saliendo del sistema...");
                    default -> System.out.println("❌ Opción no válida. Intente nuevamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, ingrese un número válido.");
                opcion = 0; // Forzar que vuelva al menú
            }

        } while (opcion != 8);

        scanner.close();
    }
}

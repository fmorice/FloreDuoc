import java.util.Scanner;

public class TeatroMoro {
    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0.0;

    // Variables de instancia
    Entrada[] entradasVendidas = new Entrada[200];
    int cantidadEntradas = 0;

    String nombreTeatro = "Teatro Moro";
    int capacidadSala = 200;

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
            System.out.println("5. Modificar última entrada");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            // Usando if-else 
            if (opcion == 1) {
                teatro.venderEntrada(scanner);  // Llamar al método para vender una entrada
            } else if (opcion == 2) {
                teatro.mostrarPromociones();  // Llamar al método para mostrar promociones
            } else if (opcion == 3) {
                teatro.verUltimaEntrada();  // Llamar al método para ver la última entrada vendida
            } else if (opcion == 4) {
                teatro.eliminarUltimaEntrada();  // Llamar al método para eliminar la última entrada
            } else if (opcion == 5) {
                teatro.modificarUltimaEntrada(scanner);  // Llamar al método para modificar la última entrada
            } else if (opcion == 6) {
                salir = true;  // Cambiar la variable salir para salir del bucle
                System.out.println("Gracias por visitar el Teatro Moro.");
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();  // Cerrar el scanner cuando ya no se necesite
    }

    // Método para vender entrada
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
        System.out.println("1. VIP ($150)");
        System.out.println("2. Palco ($130)");
        System.out.println("3. Platea Baja ($110)");
        System.out.println("4. Platea Alta ($90)");
        System.out.println("5. Galería ($60)");
        System.out.print("Opción: ");
        int opcionUbicacion = scanner.nextInt();

        if (opcionUbicacion == 1) {
            ubicacion = "VIP";
            precioBase = 150;
        } else if (opcionUbicacion == 2) {
            ubicacion = "Palco";
            precioBase = 130;
        } else if (opcionUbicacion == 3) {
            ubicacion = "Platea Baja";
            precioBase = 110;
        } else if (opcionUbicacion == 4) {
            ubicacion = "Platea Alta";
            precioBase = 90;
        } else if (opcionUbicacion == 5) {
            ubicacion = "Galería";
            precioBase = 60;
        } else {
            System.out.println("Ubicación no válida.");
            return;
        }

        // Ingresar datos del cliente
        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();

        System.out.println("Seleccione su sexo:");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");
        System.out.print("Opción: ");
        int opcionSexo = scanner.nextInt();
        String sexo = (opcionSexo == 1) ? "M" : "F";

        System.out.println("¿Es estudiante?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Opción: ");
        int opcionEstudiante = scanner.nextInt();
        boolean esEstudiante = (opcionEstudiante == 1);

        // Evaluar descuentos (aplicar el mayor)
        if (edad < 12) {
            descuento = 0.10;
            tipoPublico = "Niño";
        }
        if (sexo.equals("F") && descuento < 0.20) {
            descuento = 0.20;
            tipoPublico = "Mujer";
        }
        if (esEstudiante && descuento < 0.15) {
            descuento = 0.15;
            tipoPublico = "Estudiante";
        }
        if (edad >= 60 && descuento < 0.25) {
            descuento = 0.25;
            tipoPublico = "Tercera Edad";
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

    // Mostrar promociones disponibles
    public void mostrarPromociones() {
        System.out.println("\n--- Promociones Disponibles ---");
        System.out.println("Niños (menores de 12 años): 10% de descuento");
        System.out.println("Mujeres: 20% de descuento");
        System.out.println("Estudiantes: 15% de descuento");
        System.out.println("Tercera Edad (60+): 25% de descuento");
    }

    // Ver última entrada vendida
    public void verUltimaEntrada() {
        if (cantidadEntradas == 0) {
            System.out.println("No se ha vendido ninguna entrada aún.");
        } else {
            System.out.println("\n--- Última Entrada Vendida ---");
            entradasVendidas[cantidadEntradas - 1].imprimirBoleta();
        }
    }

    // Eliminar última entrada vendida
    public void eliminarUltimaEntrada() {
        if (cantidadEntradas == 0) {
            System.out.println("No hay entradas para eliminar.");
        } else {
            Entrada ultima = entradasVendidas[cantidadEntradas - 1];
            totalIngresos -= ultima.precioFinal;
            totalEntradasVendidas--;
            entradasVendidas[cantidadEntradas - 1] = null;
            cantidadEntradas--;
            System.out.println("Última entrada eliminada correctamente.");
        }
    }

    // Modificar última entrada vendida
    public void modificarUltimaEntrada(Scanner scanner) {
        if (cantidadEntradas == 0) {
            System.out.println("No hay entradas para modificar.");
            return;
        }

        Entrada ultimaEntrada = entradasVendidas[cantidadEntradas - 1];
        System.out.println("\n--- Modificar Última Entrada ---");
        System.out.println("1. Cambiar ubicación");
        System.out.println("2. Cambiar tipo de cliente");
        System.out.print("Seleccione una opción: ");
        int opcionModificacion = scanner.nextInt();

        if (opcionModificacion == 1) {
            System.out.println("Seleccione la nueva ubicación:");
            System.out.println("1. VIP ($150)");
            System.out.println("2. Palco ($130)");
            System.out.println("3. Platea Baja ($110)");
            System.out.println("4. Platea Alta ($90)");
            System.out.println("5. Galería ($60)");
            System.out.print("Opción: ");
            int nuevaUbicacion = scanner.nextInt();
            double nuevoPrecio = 0.0;
            String nuevaUbicacionStr = "";

            if (nuevaUbicacion == 1) {
                nuevaUbicacionStr = "VIP";
                nuevoPrecio = 150;
            } else if (nuevaUbicacion == 2) {
                nuevaUbicacionStr = "Palco";
                nuevoPrecio = 130;
            } else if (nuevaUbicacion == 3) {
                nuevaUbicacionStr = "Platea Baja";
                nuevoPrecio = 110;
            } else if (nuevaUbicacion == 4) {
                nuevaUbicacionStr = "Platea Alta";
                nuevoPrecio = 90;
            } else if (nuevaUbicacion == 5) {
                nuevaUbicacionStr = "Galería";
                nuevoPrecio = 60;
            } else {
                System.out.println("Ubicación no válida.");
                return;
            }

            ultimaEntrada.ubicacion = nuevaUbicacionStr;
            ultimaEntrada.precioFinal = nuevoPrecio;
            System.out.println("Ubicación modificada correctamente.");
        } else if (opcionModificacion == 2) {
            System.out.println("Seleccione el nuevo tipo de cliente:");
            System.out.println("1. Niño");
            System.out.println("2. Mujer");
            System.out.println("3. Estudiante");
            System.out.println("4. Tercera Edad");
            System.out.print("Opción: ");
            int nuevoTipo = scanner.nextInt();
            String nuevoTipoStr = "";

            if (nuevoTipo == 1) {
                nuevoTipoStr = "Niño";
            } else if (nuevoTipo == 2) {
                nuevoTipoStr = "Mujer";
            } else if (nuevoTipo == 3) {
                nuevoTipoStr = "Estudiante";
            } else if (nuevoTipo == 4) {
                nuevoTipoStr = "Tercera Edad";
            } else {
                System.out.println("Tipo de cliente no válido.");
                return;
            }

            ultimaEntrada.tipoPublico = nuevoTipoStr;
            System.out.println("Tipo de cliente modificado correctamente.");
        } else {
            System.out.println("Opción no válida.");
        }
    }
}

// Clase Entrada
class Entrada {
    int numero;
    String ubicacion;
    String tipoPublico;
    double precioFinal;

    public Entrada(int numero, String ubicacion, String tipoPublico, double precioFinal) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tipoPublico = tipoPublico;
        this.precioFinal = precioFinal;
    }

    public void imprimirBoleta() {
        System.out.println("Entrada #" + numero + " | Ubicación: " + ubicacion +
                           " | Tipo de público: " + tipoPublico + " | Precio final: $" + precioFinal);
    }
}
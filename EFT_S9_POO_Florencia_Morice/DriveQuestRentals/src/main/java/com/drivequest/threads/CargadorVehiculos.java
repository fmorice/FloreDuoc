package com.drivequest.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de cargar vehículos desde un archivo CSV en un hilo independiente.
 * Cada instancia puede leer un archivo diferente de forma concurrente.
 */
public class CargadorVehiculos implements Runnable {

    private final String archivo;

    /**
     * Constructor que recibe el nombre del archivo a cargar.
     * 
     * @param archivo Ruta o nombre del archivo CSV a leer.
     */
    public CargadorVehiculos(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Método principal del hilo. Lee el archivo CSV línea por línea,
     * parsea los datos y simula la carga de vehículos.
     * 
     * En caso de error de lectura o formato, informa en consola.
     */
    @Override
    public void run() {
        System.out.println("🔄 Iniciando carga desde archivo: " + archivo);

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int lineaActual = 0;

            while ((linea = br.readLine()) != null) {
                lineaActual++;

                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) continue;

                // Separar la línea por comas
                String[] partes = linea.split(",");

                // Validar que la línea tenga al menos 5 campos
                if (partes.length < 5) {
                    System.out.println("⚠️ Línea inválida (" + lineaActual + "): " + linea);
                    continue;
                }

                // Extraer y parsear los campos
                String patente = partes[0].trim();
                String marca = partes[1].trim();
                int anio = Integer.parseInt(partes[2].trim());
                int diasArriendo = Integer.parseInt(partes[3].trim());
                double valorDiario = Double.parseDouble(partes[4].trim());

                // Aquí puedes agregar lógica para crear objetos Vehiculo y almacenarlos
                System.out.println("✅ Vehículo cargado: " + patente + ", " + marca + ", " + anio);
            }

        } catch (IOException e) {
            System.err.println("❌ Error leyendo el archivo: " + archivo);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("❌ Error de formato numérico en el archivo: " + archivo);
            e.printStackTrace();
        }

        System.out.println("✅ Finalizó carga de archivo: " + archivo);
    }
}

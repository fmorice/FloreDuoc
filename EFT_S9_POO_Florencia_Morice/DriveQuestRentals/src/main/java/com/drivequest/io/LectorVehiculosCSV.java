package com.drivequest.io;

import com.drivequest.modelo.Vehiculo;
import com.drivequest.modelo.VehiculoPasajeros;
import com.drivequest.modelo.VehiculoCarga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorVehiculosCSV {

    /**
     * Lee un archivo CSV con vehículos y devuelve una lista de objetos Vehiculo.
     * @param rutaArchivo Ruta relativa o absoluta al archivo CSV
     * @return Lista de vehículos cargados desde el archivo
     */
    public List<Vehiculo> cargarVehiculos(String rutaArchivo) {
        List<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Suponiendo formato: tipo,patente,marca,año,dias,valor,extra
                // tipo = "P" o "C" para distinguir pasajero/carga
                String[] datos = linea.split(",");

                String tipo = datos[0];
                String patente = datos[1];
                String marca = datos[2];
                int anio = Integer.parseInt(datos[3]);
                int dias = Integer.parseInt(datos[4]);
                double valor = Double.parseDouble(datos[5]);

                if (tipo.equalsIgnoreCase("P")) {
                    int pasajeros = Integer.parseInt(datos[6]);
                    VehiculoPasajeros v = new VehiculoPasajeros(patente, marca, anio, dias, valor, pasajeros);
                    lista.add(v);
                } else if (tipo.equalsIgnoreCase("C")) {
                    double cargaKg = Double.parseDouble(datos[6]);
                    VehiculoCarga v = new VehiculoCarga(patente, marca, anio, dias, valor, cargaKg);
                    lista.add(v);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer vehículos: " + e.getMessage());
        }

        return lista;
    }
}

package com.drivequest.manager;

import com.drivequest.interfaces.Calculable;
import com.drivequest.modelo.Vehiculo;
import com.drivequest.modelo.VehiculoCarga;
import com.drivequest.modelo.VehiculoPasajeros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class VehiculoManager {

    // Mapa concurrente para almacenar vehículos únicos por patente
    private final ConcurrentHashMap<String, Vehiculo> vehiculos = new ConcurrentHashMap<>();

    // Agrega un vehículo al sistema si tiene una patente válida
    public void agregarVehiculo(Vehiculo v) {
        if (v != null && v.getPatente() != null) {
            vehiculos.put(v.getPatente(), v);
        }
    }

    // Devuelve una lista con todos los vehículos
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.values());
    }

    // Busca un vehículo por su patente
    public Vehiculo getVehiculoPorPatente(String patente) {
        return vehiculos.get(patente);
    }

    // Verifica si ya existe un vehículo con esa patente
    public boolean existeVehiculo(String patente) {
        return vehiculos.containsKey(patente);
    }

    // Elimina un vehículo del sistema por su patente
    public void eliminarVehiculo(String patente) {
        vehiculos.remove(patente);
    }

    // Muestra boletas de todos los vehículos que implementan la interfaz Calculable
    public void mostrarBoletas() {
        for (Vehiculo v : vehiculos.values()) {
            if (v instanceof Calculable calc) {
                calc.calcularYMostrarBoleta();
            } else {
                v.mostrarDatos();
            }
            System.out.println("-----------------------------");
        }
    }

    // Filtra vehículos por tipo (Carga o Pasajeros)
    public void filtrarPorTipo(String tipo) {
        for (Vehiculo v : vehiculos.values()) {
            if ((tipo.equalsIgnoreCase("Carga") && v instanceof VehiculoCarga) ||
                (tipo.equalsIgnoreCase("Pasajeros") && v instanceof VehiculoPasajeros)) {
                System.out.println(v);
            }
        }
    }

    // Carga vehículos desde todos los archivos CSV ubicados en la carpeta especificada
    public void cargarVehiculosDesdeCSV(String carpeta) {
        File directorio = new File(carpeta);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("La carpeta '" + carpeta + "' no existe o no es un directorio.");
            return;
        }

        File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".csv"));

        if (archivos == null || archivos.length == 0) {
            System.out.println("No se encontraron archivos CSV en la carpeta '" + carpeta + "'.");
            return;
        }

        for (File archivo : archivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length < 7) continue;

                    String tipo = datos[0].trim();
                    String patente = datos[1].trim();
                    String marca = datos[2].trim();
                    int anio = Integer.parseInt(datos[3].trim());
                    int dias = Integer.parseInt(datos[4].trim());
                    double valor = Double.parseDouble(datos[5].trim());

                    if (tipo.equalsIgnoreCase("Carga")) {
                        double peso = Double.parseDouble(datos[6].trim());
                        VehiculoCarga vc = new VehiculoCarga(patente, marca, anio, dias, valor, peso);
                        agregarVehiculo(vc);
                    } else if (tipo.equalsIgnoreCase("Pasajeros")) {
                        int asientos = Integer.parseInt(datos[6].trim());
                        VehiculoPasajeros vp = new VehiculoPasajeros(patente, marca, anio, dias, valor, asientos);
                        agregarVehiculo(vp);
                    }
                }
                System.out.println("Vehículos cargados desde archivo: " + archivo.getName());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo: " + archivo.getName());
                e.printStackTrace();
            }
        }
    }
}

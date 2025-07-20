package com.drivequest.ui;

import com.drivequest.manager.VehiculoManager;
import com.drivequest.modelo.Vehiculo;
import com.drivequest.modelo.VehiculoCarga;
import com.drivequest.modelo.VehiculoPasajeros;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private VehiculoManager vehiculosManager = new VehiculoManager();
    private Scanner scanner = new Scanner(System.in);

    // Método principal que inicia el menú y carga los archivos CSV
    public void iniciar() {
        // Cargar los vehículos desde la carpeta 'datos'
        vehiculosManager.cargarVehiculosDesdeCSV("datos");

        boolean salir = false;
        while (!salir) {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Cargar y mostrar vehículos desde archivos CSV");
            System.out.println("2. Agregar Vehículo de Carga");
            System.out.println("3. Agregar Vehículo de Pasajeros");
            System.out.println("4. Mostrar boletas de todos los vehículos");
            System.out.println("5. Listar todos los vehículos");
            System.out.println("6. Salir");


            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> cargarYMostrarVehiculosDesdeCSV(); 
                case 2 -> agregarVehiculoCarga();
                case 3 -> agregarVehiculoPasajeros();
                case 4 -> mostrarBoletas();
                case 5 -> listarVehiculos();
                case 6 -> {
                   salir = true;
                   System.out.println("Saliendo del sistema...");
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
}

        }
    }

    private void agregarVehiculoCarga() {
        System.out.println("=== Agregar Vehículo de Carga ===");
        String patente = leerTexto("Ingrese patente: ");
        String marca = leerTexto("Ingrese marca: ");
        int anio = leerEntero("Ingrese año: ");
        int diasArriendo = leerEntero("Ingrese días de arriendo: ");
        double valorDiario = leerDouble("Ingrese valor diario: ");
        double pesoCarga = leerDouble("Ingrese peso de carga (toneladas): ");

        VehiculoCarga vc = new VehiculoCarga(patente, marca, anio, diasArriendo, valorDiario, pesoCarga);
        vehiculosManager.agregarVehiculo(vc);

        System.out.println("Vehículo de carga agregado correctamente.");
    }

    private void agregarVehiculoPasajeros() {
        System.out.println("=== Agregar Vehículo de Pasajeros ===");
        String patente = leerTexto("Ingrese patente: ");
        String marca = leerTexto("Ingrese marca: ");
        int anio = leerEntero("Ingrese año: ");
        int diasArriendo = leerEntero("Ingrese días de arriendo: ");
        double valorDiario = leerDouble("Ingrese valor diario: ");
        int cantidadAsientos = leerEntero("Ingrese cantidad de asientos: ");

        VehiculoPasajeros vp = new VehiculoPasajeros(patente, marca, anio, diasArriendo, valorDiario, cantidadAsientos);
        vehiculosManager.agregarVehiculo(vp);

        System.out.println("Vehículo de pasajeros agregado correctamente.");
    }
    
    private void cargarYMostrarVehiculosDesdeCSV() {
        System.out.println("=== Cargar Vehículos desde Archivos CSV ===");
        vehiculosManager.cargarVehiculosDesdeCSV("datos"); // ← Asumiendo que este método ya está implementado
        listarVehiculos(); // muestra los cargados
    }

    private void mostrarBoletas() {
        System.out.println("=== Boletas de Vehículos Registrados ===");
        vehiculosManager.mostrarBoletas();
    }

    private void listarVehiculos() {
        System.out.println("=== Listado de Vehículos ===");
        List<Vehiculo> lista = vehiculosManager.getVehiculos();
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo v : lista) {
                v.mostrarDatos();
                System.out.println("-----------------------");
            }
        }
    }

    // Métodos auxiliares
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }
}

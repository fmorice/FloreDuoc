package com.mycompany.safevotesystem;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SafeVoteSystem {

    private static final BlockingQueue<Integer> voteQueue = new LinkedBlockingQueue<>();
    private static final Object lock = new Object();
    private static boolean processingVotes = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrimesList lista = new PrimesList();

        // Leer primos desde archivo CSV al iniciar
        cargarPrimosDesdeCSV("primos.csv", lista);

        // Iniciar hilo consumidor (Topic/Queue + wait/notify)
        Thread consumidor = new Thread(() -> {
            while (processingVotes || !voteQueue.isEmpty()) {
                try {
                    synchronized (lock) {
                        while (voteQueue.isEmpty() && processingVotes) {
                            lock.wait();
                        }
                    }
                    Integer voto = voteQueue.poll(1, TimeUnit.SECONDS);
                    if (voto != null && lista.isPrime(voto)) {
                        lista.add(voto);
                        System.out.println("🗳️ Voto cifrado con primo " + voto + " registrado.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumidor.start();

        System.out.println("\uD83D\uDEE1️ SafeVoteSystem - Sistema de mensajeria segura\n");

        while (true) {
            System.out.println("::::::::::::: MENU PRINCIPAL :::::::::::::");
            System.out.println("1. Verificar numeros impares almacenados");
            System.out.println("2. Ingresar numero de forma manual");
            System.out.println("3. Mostrar numeros primos almacenados");
            System.out.println("4. Eliminar un numero primo");
            System.out.println("5. Simular voto cifrado (Queue)");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion (1-6): ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> verificarNumeros(lista);
                    case 2 -> ingresarNumeros(scanner, lista);
                    case 3 -> mostrarNumerosPrimos(lista);
                    case 4 -> eliminarNumero(scanner, lista);
                    case 5 -> {
                        System.out.print("Ingresa un numero para cifrar el voto: ");
                        int voto = Integer.parseInt(scanner.nextLine());
                        voteQueue.offer(voto);
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                    case 6 -> {
                        processingVotes = false;
                        synchronized (lock) {
                            lock.notify();
                        }
                        consumidor.join();
                        guardarPrimosEnArchivo("primos_guardados.txt", lista);
                        System.out.println("Saliendo del sistema...");
                        return;
                    }
                    default -> System.out.println("Opcion invalida. Intenta nuevamente ingresando una opcion del 1 al 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error. Debes ingresar un numero valido");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void cargarPrimosDesdeCSV(String ruta, PrimesList lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    int numero = Integer.parseInt(linea.trim());
                    lista.add(numero);
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Número inválido o no primo en archivo: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void guardarPrimosEnArchivo(String ruta, PrimesList lista) {
        try (FileWriter writer = new FileWriter(ruta)) {
            for (int primo : lista.getPrimes()) {
                writer.write(primo + "\n");
            }
            System.out.println("✅ Primos guardados correctamente en " + ruta);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar en archivo: " + e.getMessage());
        }
    }

    private static void verificarNumeros(PrimesList lista) {
        int[] numeros = {2, 3, 4, 5, 15, 17, 20, 23, 24, 29};
        Thread[] hilos = new Thread[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            hilos[i] = new PrimeCheckerThread(numeros[i], lista);
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void ingresarNumeros(Scanner scanner, PrimesList lista) {
        System.out.print("Ingresa los numeros separados por espacios: ");
        String[] entrada = scanner.nextLine().split(" ");
        Thread[] hilos = new Thread[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            try {
                int num = Integer.parseInt(entrada[i]);
                hilos[i] = new PrimeCheckerThread(num, lista);
                hilos[i].start();
            } catch (NumberFormatException e) {
                System.out.println("❌ '" + entrada[i] + "' no es un numero valido");
            }
        }
        for (Thread hilo : hilos) {
            try {
                if (hilo != null) hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void mostrarNumerosPrimos(PrimesList lista) {
        List<Integer> primos = new ArrayList<>(lista.getPrimes());
        Collections.sort(primos);
        if (primos.isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            System.out.println("🔢 Primos: " + primos);
            System.out.println("🧮 Total: " + lista.getPrimesCount());
        }
    }

    private static void eliminarNumero(Scanner scanner, PrimesList lista) {
        System.out.print("\nIngresa el numero primo que deseas eliminar: ");
        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (lista.remove((Integer) num)) {
                System.out.println("✅ Numero " + num + " eliminado correctamente");
            } else {
                System.out.println("❌ El numero " + num + " no esta en la lista o no es primo");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un numero valido");
        }
    }
}


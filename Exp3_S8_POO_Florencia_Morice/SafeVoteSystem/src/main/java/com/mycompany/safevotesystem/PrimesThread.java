package com.mycompany.safevotesystem;

import java.util.Random;

public class PrimesThread implements Runnable {

    private final PrimesList primesList;
    private final int cantidadNumeros;  // Cantidad de números aleatorios a generar
    private final int rangoMaximo;      // Rango máximo para los números aleatorios

    private final Random random = new Random();

    public PrimesThread(PrimesList primesList, int cantidadNumeros, int rangoMaximo) {
        this.primesList = primesList;
        this.cantidadNumeros = cantidadNumeros;
        this.rangoMaximo = rangoMaximo;
    }

    @Override
    public void run() {
        int generados = 0;
        while (generados < cantidadNumeros && !Thread.currentThread().isInterrupted()) {
            int num = random.nextInt(rangoMaximo) + 1; // números entre 1 y rangoMaximo

            if (primesList.isPrime(num)) {
                try {
                    boolean agregado = primesList.add(num);
                    if (agregado) {
                        System.out.println("✅ Número primo agregado: " + num);
                    } else {
                        System.out.println("⚠️ Número primo ya existía: " + num);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Error al agregar número: " + e.getMessage());
                }
                generados++;
            }
            // Simula procesamiento lento
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restablece el estado de interrupción
                break;
            }
        }
        System.out.println("Hilo " + Thread.currentThread().getName() + " ha terminado.");
    }
}

package com.mycompany.safevotesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimesList extends ArrayList<Integer> {

    // Verifica si un número es primo (algoritmo eficiente)
    public boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // Añadir solo si es primo y no está duplicado
    @Override
    public synchronized boolean add(Integer n) {
        if (n == null || !isPrime(n)) {
            throw new IllegalArgumentException("❌ No se puede agregar, no es primo: " + n);
        }
        if (this.contains(n)) {
            System.out.println("⚠️ El número ya está en la lista: " + n);
            return false;
        }
        return super.add(n);
    }

    // Remover solo si el número es primo y está en la lista
    @Override
    public synchronized boolean remove(Object o) {
        if (o instanceof Integer n) {
            if (!isPrime(n)) {
                throw new IllegalArgumentException("❌ No se puede eliminar, no es un número primo: " + n);
            }
            if (!this.contains(n)) {
                System.out.println("⚠️ El número " + n + " no está en la lista");
                return false;
            }
            return super.remove(n);
        }
        throw new IllegalArgumentException("❌ Tipo de dato inválido para eliminar");
    }

    // Retorna copia ordenada de la lista de primos
    public synchronized List<Integer> getPrimes() {
        List<Integer> copia = new ArrayList<>(this);
        Collections.sort(copia);
        return copia;
    }

    // Cuenta la cantidad de números primos almacenados
    public synchronized int getPrimesCount() {
        return this.size();
    }
}

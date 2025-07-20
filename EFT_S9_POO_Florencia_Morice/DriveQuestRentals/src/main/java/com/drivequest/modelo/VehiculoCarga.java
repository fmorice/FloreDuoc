package com.drivequest.modelo;

import com.drivequest.interfaces.Calculable;

public class VehiculoCarga extends Vehiculo implements Calculable {

    private double pesoCarga; // toneladas

    // Constructor vacío
    public VehiculoCarga() {
        super();
    }

    // Constructor original completo
    public VehiculoCarga(String patente, String marca, int anio, int diasArriendo, double valorDiario, double pesoCarga) {
        super(patente, marca, anio, diasArriendo, valorDiario);
        this.pesoCarga = pesoCarga;
    }

    // Nuevo constructor simplificado para que acepte (String, String, double, double)
    public VehiculoCarga(String patente, String marca, double valorDiario, double pesoCarga) {
        super(patente, marca, 2025, 1, valorDiario);  // Año y días por defecto
        this.pesoCarga = pesoCarga;
    }

    // Getter y setter
    public double getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(double pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    @Override
    public void calcularYMostrarBoleta() {
        double subtotal = getDiasArriendo() * getValorDiario();
        double iva = subtotal * IVA;
        double descuento = subtotal * DESCUENTO_CARGA;
        double total = subtotal + iva - descuento;

        System.out.println("=== BOLETA VEHÍCULO DE CARGA ===");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Año: " + getAnio());
        System.out.println("Peso de carga: " + pesoCarga + " toneladas");
        System.out.println("Días de arriendo: " + getDiasArriendo());
        System.out.println("Valor diario: $" + getValorDiario());
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Descuento carga (7%): -$" + descuento);
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Total a pagar: $" + total);
        System.out.println("===============================");
    }

    @Override
    public double calcularMontoFinal() {
        double montoBase = getValorDiario() * getDiasArriendo();
        double descuento = montoBase * DESCUENTO_CARGA;
        double montoConDescuento = montoBase - descuento;
        return montoConDescuento * (1 + IVA);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de carga - Patente: " + getPatente() + 
                           ", Marca: " + getMarca() + 
                           ", Año: " + getAnio() + 
                           ", Peso carga: " + pesoCarga + " toneladas");
    }
}

package com.drivequest.modelo;

import com.drivequest.interfaces.Calculable;

public class VehiculoPasajeros extends Vehiculo implements Calculable {

    private int cantidadAsientos;

    // Constructor vacío
    public VehiculoPasajeros() {
        super();
    }

    // Constructor original completo
    public VehiculoPasajeros(String patente, String marca, int anio, int diasArriendo, double valorDiario, int cantidadAsientos) {
        super(patente, marca, anio, diasArriendo, valorDiario);
        this.cantidadAsientos = cantidadAsientos;
    }

    // Nuevo constructor simplificado para que acepte (String, String, double, int)
    public VehiculoPasajeros(String patente, String marca, double valorDiario, int cantidadAsientos) {
        super(patente, marca, 2025, 1, valorDiario);  // Año y días por defecto
        this.cantidadAsientos = cantidadAsientos;
    }

    // Getter y setter
    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    @Override
    public void calcularYMostrarBoleta() {
        double subtotal = getDiasArriendo() * getValorDiario();
        double descuento = cantidadAsientos > 5 ? subtotal * 0.10 : 0;
        double iva = (subtotal - descuento) * IVA;
        double total = subtotal - descuento + iva;

        System.out.println("=== BOLETA VEHÍCULO DE PASAJEROS ===");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Año: " + getAnio());
        System.out.println("Cantidad de asientos: " + cantidadAsientos);
        System.out.println("Días de arriendo: " + getDiasArriendo());
        System.out.println("Valor diario: $" + getValorDiario());
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Descuento: -$" + descuento);
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Total a pagar: $" + total);
        System.out.println("====================================");
    }

    @Override
    public double calcularMontoFinal() {
        double montoBase = getValorDiario() * getDiasArriendo();
        double descuento = montoBase * DESCUENTO_PASAJEROS;
        double montoConDescuento = montoBase - descuento;
        return montoConDescuento * (1 + IVA);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de pasajeros - Patente: " + getPatente() + 
                           ", Marca: " + getMarca() + 
                           ", Año: " + getAnio() + 
                           ", Asientos: " + cantidadAsientos);
    }
}

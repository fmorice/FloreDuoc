package com.drivequest.interfaces;

import java.text.DecimalFormat;

/**
 * Interfaz que define la estructura para calcular y mostrar boletas
 * para distintos tipos de vehículos en DriveQuest.
 */
public interface Calculable {

    // Constantes fiscales
    double IVA = 0.19;
    double DESCUENTO_CARGA = 0.07;
    double DESCUENTO_PASAJEROS = 0.12;

    DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Método que cada clase debe implementar para calcular el monto final
     * con los descuentos e IVA correspondientes.
     * 
     * @return el total a pagar
     */
    double calcularMontoFinal();

    /**
     * Método que imprime el detalle completo de la boleta con descuentos e IVA.
     * Se puede llamar desde la implementación de calcularYMostrarBoleta().
     * 
     * @param tipoVehiculo      el tipo de vehículo (carga o pasajeros)
     * @param valorBase         valor sin descuentos
     * @param aplicaDescuento   indica si se aplica descuento
     */
    default void mostrarDetalleBoleta(String tipoVehiculo, double valorBase, boolean aplicaDescuento) {
        System.out.println("------ Boleta " + tipoVehiculo + " ------");
        System.out.println("Valor base: $" + df.format(valorBase));

        double descuento = 0.0;
        if (aplicaDescuento) {
            if (tipoVehiculo.equalsIgnoreCase("Carga")) {
                descuento = valorBase * DESCUENTO_CARGA;
                System.out.println("Descuento carga (7%): -$" + df.format(descuento));
            } else if (tipoVehiculo.equalsIgnoreCase("Pasajeros")) {
                descuento = valorBase * DESCUENTO_PASAJEROS;
                System.out.println("Descuento pasajeros (12%): -$" + df.format(descuento));
            }
        }

        double subtotal = valorBase - descuento;
        double montoIVA = subtotal * IVA;
        double total = subtotal + montoIVA;

        System.out.println("Subtotal: $" + df.format(subtotal));
        System.out.println("IVA (19%): $" + df.format(montoIVA));
        System.out.println("TOTAL A PAGAR: $" + df.format(total));
        System.out.println("-----------------------------------");
    }

    /**
     * Método para calcular y mostrar la boleta completa.
     * Debe ser implementado por las clases que usen esta interfaz.
     */
    void calcularYMostrarBoleta();
}

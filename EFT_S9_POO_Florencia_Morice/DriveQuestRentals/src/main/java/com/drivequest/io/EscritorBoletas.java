package com.drivequest.io;

import com.drivequest.modelo.Vehiculo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorBoletas {

    private static final String ARCHIVO_BOLETAS = "datos/boletas.txt";

    /**
     * Guarda los datos de un vehículo como boleta en el archivo.
     * @param vehiculo El vehículo que se ha arrendado
     */
    public void guardarBoleta(Vehiculo vehiculo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_BOLETAS, true))) {
            bw.write("BOLETA DE ARRIENDO");
            bw.newLine();
            bw.write("Patente: " + vehiculo.getPatente());
            bw.newLine();
            bw.write("Marca: " + vehiculo.getMarca());
            bw.newLine();
            bw.write("Año: " + vehiculo.getAnio());
            bw.newLine();
            bw.write("Días arriendo: " + vehiculo.getDiasArriendo());
            bw.newLine();
            bw.write("Valor diario: $" + vehiculo.getValorDiario());
            bw.newLine();
            bw.write("Total: $" + (vehiculo.getValorDiario() * vehiculo.getDiasArriendo()));
            bw.newLine();
            bw.write("-----------------------------");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir boleta: " + e.getMessage());
        }
    }
}

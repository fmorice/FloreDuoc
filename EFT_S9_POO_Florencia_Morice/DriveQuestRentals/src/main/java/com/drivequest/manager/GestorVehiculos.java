package com.drivequest.manager;

import com.drivequest.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase encargada de gestionar la flota de vehículos.
 * Utiliza sincronización para evitar condiciones de carrera.
 */
public class GestorVehiculos {

    // Lista que almacena los vehículos; se sincroniza para acceso seguro
    private final List<Vehiculo> listaVehiculos = Collections.synchronizedList(new ArrayList<>());

    /**
     * Agrega un vehículo a la lista solo si la patente no existe ya.
     * Este método está sincronizado para que solo un hilo pueda modificar la lista a la vez.
     * 
     * @param vehiculo Vehículo a agregar.
     * @throws IllegalArgumentException Si ya existe un vehículo con la misma patente.
     */
    public synchronized void agregarVehiculo(Vehiculo vehiculo) throws IllegalArgumentException {
        // Verifica si ya existe un vehículo con la patente dada
        if (existePatente(vehiculo.getPatente())) {
            throw new IllegalArgumentException("Ya existe un vehículo con la patente: " + vehiculo.getPatente());
        }
        listaVehiculos.add(vehiculo);
    }

    /**
     * Verifica si un vehículo con determinada patente ya está en la lista.
     * 
     * @param patente Patente a verificar.
     * @return true si existe un vehículo con esa patente; false en caso contrario.
     */
    public synchronized boolean existePatente(String patente) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna una copia sincronizada de la lista de vehículos para evitar modificaciones externas.
     * 
     * @return Lista con los vehículos actuales.
     */
    public List<Vehiculo> listarVehiculos() {
        synchronized (listaVehiculos) {
            return new ArrayList<>(listaVehiculos);
        }
    }

    /**
     * Obtiene una lista con los vehículos cuyo arriendo es de 7 días o más.
     * 
     * @return Lista filtrada con vehículos de arriendo largo.
     */
    public List<Vehiculo> obtenerVehiculosArriendoLargo() {
        List<Vehiculo> resultado = new ArrayList<>();
        synchronized (listaVehiculos) {
            for (Vehiculo v : listaVehiculos) {
                if (v.getDiasArriendo() >= 7) {
                    resultado.add(v);
                }
            }
        }
        return resultado;
    }
}

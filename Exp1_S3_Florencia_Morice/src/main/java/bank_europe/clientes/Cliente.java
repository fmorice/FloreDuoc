/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_europe.clientes;

/**
 *
 * @author Flore
 */
import bank_europe.cuentas.CuentaBancaria;

public class Cliente implements InfoCliente {
    private final String nombre;
    private final String rut;
    private final CuentaBancaria cuenta;

    public Cliente(String nombre, String rut, CuentaBancaria cuenta) {
        this.nombre = nombre;
        this.rut = rut;
        this.cuenta = cuenta;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    @Override
    public void mostrarInformacionCliente() {
        System.out.println("Cliente: " + nombre);
        System.out.println("RUT: " + rut);
        System.out.println("Cuenta N°: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
        System.out.println("Interés generado: $" + cuenta.calcularInteres());
    }
}
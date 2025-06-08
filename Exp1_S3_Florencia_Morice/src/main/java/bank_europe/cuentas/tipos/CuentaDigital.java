/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_europe.cuentas.tipos;

/**
 *
 * @author Flore
 */
import bank_europe.cuentas.CuentaBancaria;

public class CuentaDigital extends CuentaBancaria {

    public CuentaDigital(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public double calcularInteres() {
        return saldo * 0.015; // 1.5% interés
    }
}
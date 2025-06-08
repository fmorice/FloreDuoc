package bank_europe;

import bank_europe.clientes.Cliente;
import bank_europe.cuentas.tipos.CuentaAhorros;
import bank_europe.cuentas.tipos.CuentaCorriente;
import bank_europe.cuentas.tipos.CuentaDigital;
import bank_europe.cuentas.CuentaBancaria;
import java.util.Scanner;

public class Bank_EuropeV2 {

    static Scanner scanner = new Scanner(System.in);
    static Cliente[] clientes = new Cliente[10];
    static int totalClientes = 0;

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n=== Menú Bank Europe ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de clientes");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Girar dinero");
            System.out.println("5. Consultar saldo e interés");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 1) {
                registrarCliente();
            } else if (opcion == 2) {
                verClientes();
            } else if (opcion == 3) {
                operarDinero(true);
            } else if (opcion == 4) {
                operarDinero(false);
            } else if (opcion == 5) {
                consultarSaldo();
            } else if (opcion == 6) {
                System.out.println("Saliendo del sistema...");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    public static void registrarCliente() {
        if (totalClientes >= clientes.length) {
            System.out.println("No se pueden registrar más clientes.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Número de cuenta (9 dígitos): ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        System.out.println("Tipo de cuenta: 1. Corriente  2. Ahorros  3. Digital");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        CuentaBancaria cuenta;
        if (tipo == 1) {
            cuenta = new CuentaCorriente(numeroCuenta, saldo);
        } else if (tipo == 2) {
            cuenta = new CuentaAhorros(numeroCuenta, saldo);
        } else if (tipo == 3) {
            cuenta = new CuentaDigital(numeroCuenta, saldo);
        } else {
            System.out.println("Tipo de cuenta inválido.");
            return;
        }

        clientes[totalClientes++] = new Cliente(nombre, rut, cuenta);
        System.out.println("Cliente registrado correctamente.");
    }

    public static void verClientes() {
        if (totalClientes == 0) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (int i = 0; i < totalClientes; i++) {
            System.out.println("\nCliente #" + (i + 1));
            clientes[i].mostrarInformacionCliente();
        }
    }

    public static void operarDinero(boolean esDeposito) {
        if (totalClientes == 0) {
            System.out.println("No hay clientes.");
            return;
        }

        System.out.print("Ingrese número de cliente (1 a " + totalClientes + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= totalClientes) {
            System.out.println("Cliente inválido.");
            return;
        }

        System.out.print("Monto: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        CuentaBancaria cuenta = clientes[index].getCuenta();

        if (esDeposito) {
            cuenta.depositar(monto);
            System.out.println("Depósito exitoso.");
        } else {
            if (cuenta.girar(monto)) {
                System.out.println("Giro exitoso.");
            } else {
                System.out.println("Fondos insuficientes.");
            }
        }
    }

    public static void consultarSaldo() {
        if (totalClientes == 0) {
            System.out.println("No hay clientes.");
            return;
        }

        System.out.print("Ingrese número de cliente (1 a " + totalClientes + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= totalClientes) {
            System.out.println("Cliente inválido.");
            return;
        }

        CuentaBancaria cuenta = clientes[index].getCuenta();
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
        System.out.println("Interés calculado: $" + cuenta.calcularInteres());
    }
}

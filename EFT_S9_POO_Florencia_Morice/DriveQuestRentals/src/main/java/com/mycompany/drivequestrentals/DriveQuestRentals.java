package com.mycompany.drivequestrentals;

import com.drivequest.ui.MenuPrincipal;

public class DriveQuestRentals {
    public static void main(String[] args) {
        System.out.println("Bienvenido a DriveQuest Rentals");

        // Crear instancia del menú principal
        MenuPrincipal menu = new MenuPrincipal();

        // Iniciar el menú interactivo
        menu.iniciar();

        System.out.println("Gracias por usar DriveQuest Rentals. ¡Hasta luego!");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp2s1sebastian;
import java.util.Scanner;

public class Exp2S1Sebastian {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int VIP = 30000;
        int PlateaAlta = 18000;
        int PlateaBaja = 15000;
        int Palco = 13000;

        while (true) {
            System.out.println("\nBienvenido al sistema de compra de entradas:");
            System.out.println("1.- Comprar entradas");
            System.out.println("2.- Salir");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                while (true) {
                    System.out.println("\nPlano del teatro:");
                    System.out.println("[Zona A] VIP");
                    System.out.println("[Zona B] Palco");
                    System.out.println("[Zona C] Platea Alta");
                    System.out.println("[Zona D] Platea Baja");

                    System.out.println("\nSelecciona la zona:");
                    System.out.println("1.- Zona A (VIP)");
                    System.out.println("2.- Zona B (Palco)");
                    System.out.println("3.- Zona C (Platea Alta)");
                    System.out.println("4.- Zona D (Platea Baja)");
                    int zona = scanner.nextInt();

                    int precioBase = 0;
                    String ubicacion = "";

                    if (zona == 1) {
                        precioBase = VIP;
                        ubicacion = "Zona A (VIP)";
                    } else if (zona == 2) {
                        precioBase = Palco;
                        ubicacion = "Zona B (Palco)";
                    } else if (zona == 3) {
                        precioBase = PlateaAlta;
                        ubicacion = "Zona C (Platea Alta)";
                    } else if (zona == 4) {
                        precioBase = PlateaBaja;
                        ubicacion = "Zona D (Platea Baja)";
                    } else {
                        System.out.println("Zona inválida. Selecciona una opción del 1 al 4.");
                        continue;
                    }

                    int edad;
                    do {
                        System.out.print("\nIngresa tu edad: ");
                        edad = scanner.nextInt();
                        if (edad < 1 || edad > 110) {
                            System.out.println("Edad inválida. Intenta nuevamente.");
                        }
                    } while (edad < 1 || edad > 110);

                    System.out.println("¿Eres estudiante? (1.- Sí / 2.- No)");
                    int esEstudiante = scanner.nextInt();

                    double descuento = 0;
                    if (edad >= 60) {
                        descuento = 0.15;
                    } else if (esEstudiante == 1) {
                        descuento = 0.10;
                    }

                    double precioFinal = precioBase - (precioBase * descuento);

                    System.out.println("\nResumen de tu compra:");
                    System.out.println("Ubicación de asiento: " + ubicacion);
                    System.out.println("Precio base: " + precioBase + " Pesos");
                    System.out.println("Descuento por estudiante: " + (int)(descuento * 100) + "%");
                    System.out.println("Total a pagar: " + (int)precioFinal + " Pesos");

                    System.out.println("\n¿Deseas confirmar la compra?");
                    System.out.println("1.- Sí");
                    System.out.println("2.- No");
                    int confirmar = scanner.nextInt();

                    if (confirmar == 1) {
                        System.out.println("¡Compra realizada con éxito!");
                    } else {
                        System.out.println("Compra cancelada.");
                    }

                    System.out.println("\n¿Deseas comprar otra entrada?");
                    System.out.println("1.- Sí");
                    System.out.println("2.- No");
                    int otraCompra = scanner.nextInt();

                    if (otraCompra != 1) {
                        System.out.println("\nGracias por visitar el Teatro Moro.");
                        scanner.close();
                        return;
                    }
                }

            } else if (opcion == 2) {
                System.out.println("\nGracias por visitar el Teatro Moro.");
                scanner.close();
                return;
            } else {
                System.out.println("Opción inválida. Elige 1 o 2.");
            }
        }
    }
}
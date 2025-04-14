/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp2s2sebastian;

import java.util.ArrayList;
import java.util.Scanner;

class Entrada {

    int numero;
    String ubicacion;
    double precioFinal;
    String tipoCliente;

    public Entrada(int numero, String ubicacion, double precioFinal, String tipoCliente) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.precioFinal = precioFinal;
        this.tipoCliente = tipoCliente;
    }

    public String toString() {
        return "Entrada #" + numero + " | Ubicación: " + ubicacion + " | Cliente: " + tipoCliente + " | Precio: $" + precioFinal;
    }
}

public class Exp2S2Sebastian {

    static String nombreTeatro = "Teatro Moro";
    static int capacidadSala = 100;
    static int entradasDisponibles = 100;
    static double ingresosTotales = 0.0;
    static int contadorEntradas = 1;

    static ArrayList<Entrada> entradasVendidas = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Bienvenido al Sistema de Entradas del " + nombreTeatro + " ---");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Ver promociones");
            System.out.println("3. Buscar entrada");
            System.out.println("4. Eliminar entrada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    venderEntrada();
                    break;
                case 2:
                    mostrarPromociones();
                    break;
                case 3:
                    buscarEntrada();
                    break;
                case 4:
                    eliminarEntrada();
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    public static void venderEntrada() {

        String ubicacion = "";
        String tipoCliente = "";
        double precioBase = 0;
        double descuento = 0;

        if (entradasDisponibles <= 0) {
            System.out.println("No hay entradas disponibles.");
            return;
        }

        System.out.println("Seleccione ubicación:");
        System.out.println("1. VIP ($20.000)");
        System.out.println("2. Platea ($15.000)");
        System.out.println("3. General ($10.000)");
        System.out.print("Opción: ");
        int opcionUbicacion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        switch (opcionUbicacion) {
            case 1:
                ubicacion = "VIP";
                precioBase = 20000;
                break;
            case 2:
                ubicacion = "PLATEA";
                precioBase = 15000;
                break;
            case 3:
                ubicacion = "GENERAL";
                precioBase = 10000;
                break;
            default:
                System.out.println("Opción de ubicación no válida.");
                return;
        }

        System.out.println("Seleccione tipo de cliente:");
        System.out.println("1. Normal");
        System.out.println("2. Estudiante");
        System.out.println("3. Tercera Edad");
        System.out.print("Opción: ");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        switch (opcionCliente) {
            case 1:
                tipoCliente = "NORMAL";
                descuento = 0.0;
                break;
            case 2:
                tipoCliente = "ESTUDIANTE";
                descuento = 0.10;
                break;
            case 3:
                tipoCliente = "TERCERA EDAD";
                descuento = 0.15;
                break;
            default:
                System.out.println("Opción de cliente no válida.");
                return;
        }

        double precioFinal = precioBase - (precioBase * descuento);

        System.out.println("\n--- Confirmación de Compra ---");
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Tipo de Cliente: " + tipoCliente);
        System.out.println("Precio Final: $" + precioFinal);
        System.out.print("¿Desea confirmar la compra? (S/N): ");
        String confirmacion = scanner.nextLine().toUpperCase();

        if (!confirmacion.equals("S")) {
            System.out.println("Compra cancelada.");
            return;
        }

        Entrada entrada = new Entrada(contadorEntradas, ubicacion, precioFinal, tipoCliente);
        entradasVendidas.add(entrada);
        contadorEntradas++;
        entradasDisponibles--;
        ingresosTotales += precioFinal;

        System.out.println("Entrada vendida con éxito:");
        System.out.println(entrada);
    }

    public static void mostrarPromociones() {
        System.out.println("\n--- Promociones ---");
        System.out.println("- 10% de descuento para estudiantes.");
        System.out.println("- 15% de descuento para personas de la tercera edad.");
        System.out.println("- Compra 5 o más entradas y obtén una entrada gratis.");
    }

    public static void buscarEntrada() {
        System.out.println("\nBuscar entrada por:");
        System.out.println("1. Número");
        System.out.println("2. Ubicación");
        System.out.println("3. Tipo de cliente");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese número de entrada: ");
                int num = scanner.nextInt();
                scanner.nextLine();
                for (Entrada e : entradasVendidas) {
                    if (e.numero == num) {
                        System.out.println(e);
                        return;
                    }
                }
                System.out.println("Entrada no encontrada.");
                break;

            case 2:
                System.out.print("Ingrese ubicación (VIP, Platea, General): ");
                String ubic = scanner.nextLine().toUpperCase();
                for (Entrada e : entradasVendidas) {
                    if (e.ubicacion.equals(ubic)) {
                        System.out.println(e);
                    }
                }
                break;

            case 3:
                System.out.print("Ingrese tipo de cliente (NORMAL, ESTUDIANTE, TERCERA EDAD): ");
                String tipo = scanner.nextLine().toUpperCase();
                for (Entrada e : entradasVendidas) {
                    if (e.tipoCliente.equals(tipo)) {
                        System.out.println(e);
                    }
                }
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    public static void eliminarEntrada() {
        System.out.print("Ingrese número de entrada a eliminar: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        for (Entrada e : entradasVendidas) {
            if (e.numero == num) {
                entradasVendidas.remove(e);
                entradasDisponibles++;
                ingresosTotales -= e.precioFinal;
                System.out.println("Entrada eliminada con éxito.");
                return;
            }
        }
        System.out.println("Entrada no encontrada.");
    }
}

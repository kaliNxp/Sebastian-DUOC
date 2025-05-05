/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp3s8sebastian;

import java.util.*;

public class Exp3S8Sebastian {

    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int totalReservas = 0;

    static String[] asientos = new String[10];
    static boolean[] reservado = new boolean[10];
    static boolean[] comprado = new boolean[10];
    static double[] precios = new double[10];
    static int[] idsVenta = new int[10];
    static String[] clientes = new String[10];

    static List<String> promociones = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static int contadorVentas = 1;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAsientos();

        int opcion;
        do {
            System.out.println("\n--- Menú Teatro Moro ---");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    reservarEntrada();
                    break;
                case 2:
                    comprarEntrada();
                    break;
                case 3:
                    modificarVenta();
                    break;
                case 4:
                    imprimirBoleta();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    static void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = "Asiento " + (i + 1);
            reservado[i] = false;
            comprado[i] = false;
            precios[i] = 5000;
        }
        promociones.add("Estudiante - 10%");
        promociones.add("Tercera Edad - 15%");
    }

    static void mostrarAsientos() {
        System.out.println("\nEstado de los asientos:");
        for (int i = 0; i < asientos.length; i++) {
            String estado = comprado[i] ? "Comprado" : (reservado[i] ? "Reservado" : "Disponible");
            System.out.println((i + 1) + ". " + asientos[i] + " - " + estado);
        }
    }

    static void reservarEntrada() {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a reservar: ");
        int asiento = validarEntradaNumerica() - 1;

        if (asiento < 0 || asiento >= asientos.length || reservado[asiento] || comprado[asiento]) {
            System.out.println("Asiento no disponible para reserva.");
            return;
        }

        reservado[asiento] = true;
        totalReservas++;

        System.out.print("Ingrese su nombre: ");
        sc.nextLine();
        String cliente = sc.nextLine();
        reservas.add(new Reserva(contadorVentas++, cliente, asiento));

        System.out.println("Reserva realizada para " + asientos[asiento]);
    }

    static void comprarEntrada() {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a comprar: ");
        int asiento = validarEntradaNumerica() - 1;

        if (asiento < 0 || asiento >= asientos.length || comprado[asiento]) {
            System.out.println("Asiento no disponible para compra.");
            return;
        }

        System.out.print("Ingrese su nombre: ");
        sc.nextLine();
        String cliente = sc.nextLine();

        System.out.print("Ingrese su edad: ");
        int edad = validarEntradaNumerica();

        double descuento = 0;
        if (edad <= 25) {
            descuento = 0.10;
            System.out.println("Descuento aplicado: 10% (Estudiante)");
        } else if (edad >= 65) {
            descuento = 0.15;
            System.out.println("Descuento aplicado: 15% (Tercera Edad)");
        } else {
            System.out.println("No aplica descuento.");
        }

        double precioBase = precios[asiento];
        double precioFinal = precioBase * (1 - descuento);

        System.out.printf("Precio final con descuento: $%.2f\n", precioFinal);
        System.out.print("¿Desea confirmar la compra? (s/n): ");
        String confirmacion = sc.next();

        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Compra cancelada.");
            return;
        }

        comprado[asiento] = true;
        reservado[asiento] = false;
        precios[asiento] = precioFinal;
        clientes[asiento] = cliente;
        idsVenta[asiento] = contadorVentas++;

        totalEntradasVendidas++;
        totalIngresos += precioFinal;

        System.out.println("Compra confirmada para " + asientos[asiento]);
    }

    static void modificarVenta() {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a modificar (cancelar compra): ");
        int asiento = validarEntradaNumerica() - 1;

        if (asiento < 0 || asiento >= asientos.length || !comprado[asiento]) {
            System.out.println("No se puede modificar este asiento.");
            return;
        }

        totalEntradasVendidas--;
        totalIngresos -= precios[asiento];

        comprado[asiento] = false;
        precios[asiento] = 5000;
        clientes[asiento] = null;
        idsVenta[asiento] = 0;

        System.out.println("Venta cancelada para " + asientos[asiento]);
    }

    static void imprimirBoleta() {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento para imprimir boleta: ");
        int asiento = validarEntradaNumerica() - 1;

        if (asiento < 0 || asiento >= asientos.length || !comprado[asiento]) {
            System.out.println("No hay compra registrada para este asiento.");
            return;
        }

        System.out.println("\n--- BOLETA ---");
        System.out.println("Cliente: " + clientes[asiento]);
        System.out.println("ID Venta: " + idsVenta[asiento]);
        System.out.println("Asiento: " + asientos[asiento]);
        System.out.printf("Precio: $%.2f\n", precios[asiento]);
        System.out.println("Gracias por su compra.");
    }

    static int validarEntradaNumerica() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static class Reserva {
        int idReserva;
        String cliente;
        int idAsiento;

        public Reserva(int idReserva, String cliente, int idAsiento) {
            this.idReserva = idReserva;
            this.cliente = cliente;
            this.idAsiento = idAsiento;
        }
    }
} 



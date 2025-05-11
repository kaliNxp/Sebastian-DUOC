/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.etf_s9_sebastianbugueno;

import java.util.*;

public class ETF_S9_SebastianBugueno {

    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int totalReservas = 0;

    static String[] asientos = new String[50]; // 50 asientos en total
    static boolean[] reservado = new boolean[50];
    static boolean[] comprado = new boolean[50];
    static double[] precios = new double[50];
    static String[] clientes = new String[50];
    static String[] zonas = new String[50]; // Zona de cada asiento
    static String[] secciones = {"VIP", "Palco", "Platea Baja", "Platea Alta", "Galería"};
    static List<String> promociones = new ArrayList<>();
    static int contadorVentas = 1;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAsientos();

        int opcion;
        do {
            System.out.println("\n--- Menú Teatro Moro ---");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar/Cancelar venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Ver estadísticas");
            System.out.println("6. Salir");
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
                    mostrarEstadisticas();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    static void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = "Asiento " + (i + 1);
            reservado[i] = false;
            comprado[i] = false;
            precios[i] = 5000; // Precio base por asiento
            // Asignación de zonas
            if (i < 10) {
                zonas[i] = "VIP";
                precios[i] = 10000; // Precio VIP
            } else if (i < 20) {
                zonas[i] = "Palco";
                precios[i] = 8000; // Precio Palco
            } else if (i < 30) {
                zonas[i] = "Platea Baja";
                precios[i] = 6000; // Precio Platea Baja
            } else if (i < 40) {
                zonas[i] = "Platea Alta";
                precios[i] = 4000; // Precio Platea Alta
            } else {
                zonas[i] = "Galería";
                precios[i] = 2000; // Precio Galería
            }
        }
        promociones.add("Estudiante - 10%");
        promociones.add("Tercera Edad - 15%");
    }

    static void mostrarAsientos() {
        System.out.println("\nEstado de los asientos:");
        for (int i = 0; i < asientos.length; i++) {
            String estado = comprado[i] ? "Comprado" : (reservado[i] ? "Reservado" : "Disponible");
            System.out.println((i + 1) + ". " + asientos[i] + " - " + zonas[i] + " - " + estado);
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

        String tipoCliente = determinarTipoCliente(edad);

        double descuento = obtenerDescuento(tipoCliente);

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

        totalEntradasVendidas++;
        totalIngresos += precioFinal;

        System.out.println("Compra confirmada para " + asientos[asiento] + " en la zona " + zonas[asiento]);
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
        precios[asiento] = obtenerPrecioBaseZona(asiento);
        clientes[asiento] = null;

        System.out.println("Venta cancelada para " + asientos[asiento] + " en la zona " + zonas[asiento]);
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
        System.out.println("Asiento: " + asientos[asiento]);
        System.out.println("Zona: " + zonas[asiento]);
        System.out.printf("Precio: $%.2f\n", precios[asiento]);
        System.out.println("Gracias por su compra.");
    }

    static void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Total ingresos: $" + totalIngresos);
        System.out.println("Reservas realizadas: " + totalReservas);
    }

    static int validarEntradaNumerica() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static String determinarTipoCliente(int edad) {
        if (edad <= 12) return "niño";
        if (edad >= 60) return "tercera edad";
        System.out.print("¿Es estudiante? (s/n): ");
        boolean esEstudiante = sc.next().equalsIgnoreCase("s");
        if (esEstudiante) return "estudiante";
        return "general";
    }

    static double obtenerDescuento(String tipo) {
        switch (tipo) {
            case "niño": return 0.10;
            case "mujer": return 0.20;
            case "estudiante": return 0.15;
            case "tercera edad": return 0.25;
            default: return 0.0;
        }
    }

    static double obtenerPrecioBaseZona(int asiento) {
        switch (zonas[asiento]) {
            case "VIP": return 10000;
            case "Palco": return 8000;
            case "Platea Baja": return 6000;
            case "Platea Alta": return 4000;
            case "Galería": return 2000;
            default: return 5000; // Fallback en caso de error
        }
    }
}




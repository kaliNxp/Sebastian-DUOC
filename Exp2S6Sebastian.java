/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp2s6sebastian;
import java.util.Scanner;

public class Exp2S6Sebastian {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TeatroMoro sistema = new TeatroMoro();
        sistema.inicializarAsientos();

        int opcion;
        do {
            System.out.println("\n--- Menú Teatro Moro ---");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sistema.reservarEntrada(sc);
                    break;
                case 2:
                    sistema.comprarEntrada(sc);
                    break;
                case 3:
                    sistema.modificarVenta(sc);
                    break;
                case 4:
                    sistema.imprimirBoleta(sc);
                    break;
                case 5:
                    sistema.mostrarResumen();
                    System.out.println("Gracias por usar el sistema del Teatro Moro.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 5);
    }
}


class TeatroMoro {

    
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int totalReservas = 0;

    
    String[] ubicaciones = new String[10];
    boolean[] reservado = new boolean[10];
    boolean[] comprado = new boolean[10];
    double[] precios = new double[10];

    
    void inicializarAsientos() {
        for (int i = 0; i < ubicaciones.length; i++) {
            ubicaciones[i] = "Asiento " + (i + 1);
            reservado[i] = false;
            comprado[i] = false;
            precios[i] = 5000;
        }
    }

    
    void mostrarAsientos() {
        System.out.println("\nEstado de los asientos:");
        for (int i = 0; i < ubicaciones.length; i++) {
            String estado = comprado[i] ? "Comprado" : (reservado[i] ? "Reservado" : "Disponible");
            System.out.println((i + 1) + ". " + ubicaciones[i] + " - " + estado);
        }
    }

    
    void reservarEntrada(Scanner sc) {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a reservar: ");
        int asiento = sc.nextInt() - 1;

        // Breakpoint
        if (asiento < 0 || asiento >= ubicaciones.length || reservado[asiento] || comprado[asiento]) {
            System.out.println("Asiento no disponible para reserva.");
            return;
        }

        reservado[asiento] = true;
        totalReservas++;

        // Breakpoint
        System.out.println("Reserva realizada para " + ubicaciones[asiento]);
    }

    
    void comprarEntrada(Scanner sc) {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a comprar: ");
        int asiento = sc.nextInt() - 1;

        // Breakpoint
        if (asiento < 0 || asiento >= ubicaciones.length || comprado[asiento]) {
            System.out.println("Asiento no disponible para compra.");
            return;
        }

       
        String tipoCliente;
        double descuento = 0.0;
        double precioBase = precios[asiento];

        System.out.print("¿Es cliente VIP o normal? (vip/normal): ");
        tipoCliente = sc.next();

        if (tipoCliente.equalsIgnoreCase("vip")) {
            descuento = 0.2;
        } else {
            descuento = 0.1;
        }

        double precioFinal = precioBase * (1 - descuento);

       
        System.out.println("\nResumen de compra:");
        System.out.println("Asiento: " + ubicaciones[asiento]);
        System.out.println("Precio base: $" + precioBase);
        System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
        System.out.printf("Precio final: $%.2f\n", precioFinal);
        System.out.print("¿Desea confirmar la compra? (s/n): ");
        String confirmar = sc.next();

        if (!confirmar.equalsIgnoreCase("s")) {
            System.out.println("Compra cancelada.");
            return;
        }

        comprado[asiento] = true;
        reservado[asiento] = false; 
        precios[asiento] = precioFinal;

        totalEntradasVendidas++;
        totalIngresos += precioFinal;

        // Breakpoint
        System.out.println("✅ Compra confirmada para " + ubicaciones[asiento]);
    }

    
    void modificarVenta(Scanner sc) {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento a modificar (cancelar compra): ");
        int asiento = sc.nextInt() - 1;

        // Breakpoint
        if (asiento < 0 || asiento >= ubicaciones.length || !comprado[asiento]) {
            System.out.println("No se puede modificar este asiento.");
            return;
        }

        comprado[asiento] = false;
        totalEntradasVendidas--;
        totalIngresos -= precios[asiento];
        precios[asiento] = 5000;

        // Breakpoint
        System.out.println("Venta cancelada para " + ubicaciones[asiento]);
    }

    
    void imprimirBoleta(Scanner sc) {
        mostrarAsientos();
        System.out.print("Seleccione el número del asiento para imprimir boleta: ");
        int asiento = sc.nextInt() - 1;

        // Breakpoint
        if (asiento < 0 || asiento >= ubicaciones.length || !comprado[asiento]) {
            System.out.println("No hay compra registrada para este asiento.");
            return;
        }

        // Breakpoint
        System.out.println("\n--- BOLETA ---");
        System.out.println("Asiento: " + ubicaciones[asiento]);
        System.out.println("Precio pagado: $" + precios[asiento]);
        System.out.println("¡Gracias por su compra!");
    }

    
    void mostrarResumen() {
        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Total de reservas activas: " + totalReservas);
        System.out.printf("Total de ingresos: $%.2f\n", totalIngresos);
    }
}










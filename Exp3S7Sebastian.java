/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp3s7sebastian;
import java.util.ArrayList;
import java.util.Scanner;

public class Exp3S7Sebastian {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TeatroMoro sistema = new TeatroMoro();
        sistema.inicializarTeatro();

        int opcion;
        do {
            System.out.println("\n--- Menú Teatro Moro ---");
            System.out.println("1. Vender entrada");
            System.out.println("2. Visualizar resumen de ventas");
            System.out.println("3. Generar boleta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sistema.venderEntrada(sc);
                    break;
                case 2:
                    sistema.visualizarResumen();
                    break;
                case 3:
                    sistema.generarBoleta(sc);
                    break;
                case 4:
                    sistema.mostrarResumenFinal();
                    System.out.println("Gracias por usar el sistema del Teatro Moro.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }
}

class TeatroMoro {

    // Variables estáticas para estadísticas globales
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int totalVentas = 0;

    // Variables de instancia (persistentes)
    String nombreTeatro;
    int capacidadSala;
    double precioVIP;
    double precioPlatea;
    double precioBalcon;

    // Listas para almacenar la información de ventas
    ArrayList<Integer> numerosVenta = new ArrayList<>();
    ArrayList<String> ubicacionesVendidas = new ArrayList<>();
    ArrayList<Double> preciosFinales = new ArrayList<>();
    ArrayList<String> descuentosAplicados = new ArrayList<>();

    void inicializarTeatro() {
        nombreTeatro = "Teatro Moro";
        capacidadSala = 10; 
        precioVIP = 10000;
        precioPlatea = 7000;
        precioBalcon = 5000;
    }

    void venderEntrada(Scanner sc) {
        if (totalEntradasVendidas >= capacidadSala) {
            System.out.println("Ya no hay entradas disponibles.");
            return;
        }

        System.out.println("\nSeleccione la ubicación:");
        System.out.println("1. VIP ($10.000)");
        System.out.println("2. Platea ($7.000)");
        System.out.println("3. Balcón ($5.000)");
        System.out.print("Opción: ");
        int opcionUbicacion = sc.nextInt();

        String tipoUbicacion = "";
        double costoBase = 0.0;

        switch (opcionUbicacion) {
            case 1:
                tipoUbicacion = "VIP";
                costoBase = precioVIP;
                break;
            case 2:
                tipoUbicacion = "Platea";
                costoBase = precioPlatea;
                break;
            case 3:
                tipoUbicacion = "Balcón";
                costoBase = precioBalcon;
                break;
            default:
                System.out.println("Ubicación inválida.");
                return;
        }

        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();

        double descuento = 0.0;
        String tipoDescuento = "Ninguno";

        if (edad < 18) {
            descuento = 0.10;
            tipoDescuento = "Estudiante (10%)";
        } else if (edad >= 60) {
            descuento = 0.15;
            tipoDescuento = "Tercera Edad (15%)";
        }

        double precioFinal = costoBase * (1 - descuento);

        System.out.println("\nResumen de compra:");
        System.out.println("Ubicación: " + tipoUbicacion);
        System.out.println("Costo base: $" + costoBase);
        System.out.println("Descuento aplicado: " + tipoDescuento);
        System.out.printf("Precio final: $%.2f\n", precioFinal);
        System.out.print("¿Desea confirmar la compra? (s/n): ");
        String confirmacion = sc.next();

        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Compra cancelada.");
            return;
        }

        // Guardamos los datos
        totalVentas++;
        numerosVenta.add(totalVentas); 
        ubicacionesVendidas.add(tipoUbicacion);
        preciosFinales.add(precioFinal);
        descuentosAplicados.add(tipoDescuento);

        totalEntradasVendidas++;
        totalIngresos += precioFinal;

        System.out.println("✅ Compra confirmada. Su número de venta es: " + totalVentas);
    }

    void visualizarResumen() {
        System.out.println("\n--- Resumen de Ventas ---");
        for (int i = 0; i < ubicacionesVendidas.size(); i++) {
            System.out.println("Venta #" + numerosVenta.get(i) + " - Ubicación: " + ubicacionesVendidas.get(i)
                + " - Precio final: $" + preciosFinales.get(i)
                + " - Descuento: " + descuentosAplicados.get(i));
        }
    }

    void generarBoleta(Scanner sc) {
        System.out.print("Ingrese su número de venta para generar la boleta: ");
        int numeroVenta = sc.nextInt();

        int index = numerosVenta.indexOf(numeroVenta);

        if (index == -1) {
            System.out.println("Número de venta no encontrado.");
            return;
        }

        System.out.println("\n--- BOLETA DE COMPRA ---");
        System.out.println("Teatro: " + nombreTeatro);
        System.out.println("Número de venta: " + numerosVenta.get(index));
        System.out.println("Ubicación: " + ubicacionesVendidas.get(index));
        System.out.println("Descuento aplicado: " + descuentosAplicados.get(index));
        System.out.printf("Total pagado: $%.2f\n", preciosFinales.get(index));
        System.out.println("¡Gracias por su compra! 🎭");
    }

    void mostrarResumenFinal() {
        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
        System.out.printf("Total de ingresos: $%.2f\n", totalIngresos);
    }
}


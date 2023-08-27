/*
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * PROGRAMACION ORIENTADA A OBJETOS
 * ROBERTO BARREDA No. 23354
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static final String PASSWORD = "H073L2023$"; // Contra dada por Moises
    private static double totalEarnings = 0.0;
    public static void main(String[] args) {
        ArrayList<Cuarto> habitacionesDisponibles = new ArrayList<>();
        ArrayList<Cliente> listaDeEspera = new ArrayList<>();

        // Inicializar las 3 habitaciones disponibles (hotel peque)
        habitacionesDisponibles.add(new Cuarto(101, 2, 100.0, "ESTANDAR"));
        habitacionesDisponibles.add(new Cuarto(201, 3, 150.0, "DELUXE"));
        habitacionesDisponibles.add(new Cuarto(301, 4, 200.0, "SUITE"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Reservar habitación");
            System.out.println("2. Asignar cliente a habitación");
            System.out.println("3. Mostrar lista de espera");
            System.out.println("4. Realizar checkout");
            System.out.println("5. Mostrar ganancias totales");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        reservarHabitacion(habitacionesDisponibles, listaDeEspera, scanner);
                        break;
                    case 2:
                        asignarClienteAHabitacion(habitacionesDisponibles, listaDeEspera, scanner);
                        break;
                    case 3:
                        mostrarListaDeEspera(listaDeEspera);
                        break;
                    case 4:
                        realizarCheckout(habitacionesDisponibles, totalEarnings, scanner);
                        break;

                    case 5:
                        System.out.println("Saliendo del programa...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next();
            }
        }
    }

    private static void reservarHabitacion(ArrayList<Cuarto> habitacionesDisponibles, ArrayList<Cliente> listaDeEspera, Scanner scanner) {
        System.out.print("Ingrese el nombre completo del cliente: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese el CUI del cliente: ");
        String CUI = scanner.nextLine();
        System.out.print("Ingrese la cantidad de veces que ha visitado el hotel: ");

        int visitas = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                visitas = scanner.nextInt();
                inputValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido para las visitas.");
                scanner.next();
            }
        }

        Cliente cliente = new Cliente(nombreCompleto, CUI);
        cliente.aumentarVisitas(visitas);

        listaDeEspera.add(cliente);
        System.out.println("Cliente registrado en la lista de espera.");
    }

    private static void asignarClienteAHabitacion(ArrayList<Cuarto> habitacionesDisponibles, ArrayList<Cliente> listaDeEspera, Scanner scanner) {
        System.out.print("Ingrese el CUI del cliente: ");
        String CUI = scanner.next();
    
        // Generado por ChatGPT: sistema para verificar la contraseña. Dado el siguiente codigo, genera una manera dentro 
        //de "asignarClienteAHabitacion" de verificar la contraseña H073L2023$ y el CUI del cliente.
        System.out.print("Ingrese la contraseña para asignar al cliente: ");
        String password = scanner.next();
        if (!password.equals(PASSWORD)) {
            System.out.println("Contraseña incorrecta. No se puede asignar al cliente.");
            return;
        }
    
        // Generado por ChatGPT: sistema para verificar el CUI.
        Cliente cliente = null;
        for (Cliente c : listaDeEspera) {
            if (c.getCUI().equals(CUI)) {
                cliente = c;
                break;
            }
        }
    
        if (cliente == null) {
            System.out.println("Cliente no encontrado en la lista de espera.");
            return;
        }
    
        System.out.println("Seleccione el tipo de habitación:");
        System.out.println("1. Habitación Estándar");
        System.out.println("2. Habitación Deluxe");
        System.out.println("3. Suite");
        System.out.print("Ingrese el número de opción: ");
        int opcion = scanner.nextInt();
    
        Cuarto habitacionAsignada = null;
    
        switch (opcion) {
            case 1:
                // Habitación Estándar - Puede ser reservada por cualquier cliente ;)
                habitacionAsignada = buscarHabitacionDisponible(habitacionesDisponibles, "ESTANDAR");
                break;
            case 2:
                // Habitación Deluxe - Puede ser reservada por clientes frecuentes o VIP
                if ("Frecuente".equals(cliente.getClasificacion()) || "VIP".equals(cliente.getClasificacion())) {
                    habitacionAsignada = buscarHabitacionDisponible(habitacionesDisponibles, "DELUXE");
                } else {
                    System.out.println("Esta habitación solo puede ser reservada por clientes frecuentes o VIP.");
                }
                break;
            case 3:
                // Suite - Puede ser reservada por clientes VIP (probablemente gente retirada sin nada mas que hacer)
                if ("VIP".equals(cliente.getClasificacion())) {
                    habitacionAsignada = buscarHabitacionDisponible(habitacionesDisponibles, "SUITE");
                } else {
                    System.out.println("Esta habitación solo puede ser reservada por clientes VIP.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
    
        if (habitacionAsignada != null) {
            habitacionAsignada.setClienteAsignado(cliente);
            listaDeEspera.remove(cliente);
            System.out.println("Cliente asignado a la habitación " + habitacionAsignada.getNumero());
        } else {
            System.out.println("No hay habitaciones disponibles de ese tipo.");
        }
    }    

    private static Cuarto buscarHabitacionDisponible(ArrayList<Cuarto> habitacionesDisponibles, String tipo) {
        for (Cuarto habitacion : habitacionesDisponibles) {
            if (!habitacion.isOcupada() && habitacion.getTipo().equals(tipo)) {
                return habitacion;
            }
        }
        return null;
    }

    // Generado por ChatGPT: dado el siguiente codigo, agrega una manera de permitirle a los clientes hacer un "CheckOut" en el hotel
    // para que las habitaciones se vacien y trata de utilizar un codigo con programacion defensiva.
    private static void realizarCheckout(ArrayList<Cuarto> habitacionesDisponibles, double totalEarnings, Scanner scanner) {
        System.out.print("Ingrese el número de habitación del cliente que hace el checkout: ");
        int numeroHabitacion = scanner.nextInt();
    
        Cuarto habitacionCheckout = null;
        for (Cuarto habitacion : habitacionesDisponibles) {
            if (habitacion.getNumero() == numeroHabitacion) {
                habitacionCheckout = habitacion;
                break;
            }
        }
    
        if (habitacionCheckout == null) {
            System.out.println("Habitación no encontrada. Verifique el número de habitación.");
            return;
        }
    
        Cliente clienteCheckout = habitacionCheckout.getClienteAsignado();
        if (clienteCheckout == null) {
            System.out.println("Esta habitación no tiene un cliente asignado.");
            return;
        }
    
        System.out.print("Ingrese el número de noches que el cliente ha estado en la habitación: ");
        int numNights = scanner.nextInt();
    
        double amountPaid = habitacionCheckout.calcularMontoPorCheckout(numNights);
    
        totalEarnings += amountPaid;
    
        System.out.println("El cliente ha realizado el checkout de la habitación " + habitacionCheckout.getNumero());
        System.out.printf("El cliente debe pagar $%.2f%n", amountPaid);
        System.out.printf("Ganancias totales: $%.2f%n", totalEarnings);
    }

    private static void mostrarListaDeEspera(ArrayList<Cliente> listaDeEspera) {
        System.out.println("Lista de Espera:");
        for (Cliente cliente : listaDeEspera) {
            System.out.println("Nombre: " + cliente.getNombreCompleto() + " | CUI: " + cliente.getCUI() + " | Visitas: " + cliente.getVisitas() + " | Clasificación: " + cliente.getClasificacion());
        }
    }
}
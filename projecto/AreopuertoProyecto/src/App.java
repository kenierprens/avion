import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
        String numeroVuelo = "";
        Vuelo vuelo = new Vuelo(0, null, null, null, 0, null, null);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n|MENU DE VUELO|");
            System.out.println("1. Añadir Aeropuerto");
            System.out.println("2. Añadir vuelo");
            System.out.println("3. Reservar asientos en vuelo");
            System.out.println("4. Ver informacion de pasajeros");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("(X) - Ingrese el nombre del aeropuerto: ");
                    String nombre = scanner.nextLine();
                    if (nombre.equalsIgnoreCase("x")) {
                        break;
                    }
                    System.out.print("(X) - Ingrese la ubicacion del aeropuerto: ");
                    String ubicacion = scanner.nextLine();
                    if (ubicacion.equalsIgnoreCase("x")) {
                        break;
                    }

                    Aeropuerto nuevoAeropuerto = new Aeropuerto(nombre, ubicacion);
                    aeropuertos.add(nuevoAeropuerto);

                    System.out.println("Aeropuerto registrado con exito.");
                    break;
                case 2:
                    System.out.print("(X) - Ingrese el numero de vuelo: ");
                    numeroVuelo = scanner.nextLine();
                    if (numeroVuelo.equalsIgnoreCase("x")) {
                        break;
                    }

                    if (!existeNumeroVuelo(aeropuertos, Integer.parseInt(numeroVuelo))) {
                        System.out.print("(X) - Ingrese el nombre de la aerolinea: ");
                        String aerolinea = scanner.nextLine();
                        if (aerolinea.equalsIgnoreCase("x")) {
                        break;
                        }
                        System.out.print("(X) - Ingrese la hora de salida: ");
                        String horaSalida = scanner.nextLine();
                        if (horaSalida.equalsIgnoreCase("x")) {
                        break;
                        }
                        System.out.print("(X) - Ingrese el destino: ");
                        String destino = scanner.nextLine();
                        if (destino.equalsIgnoreCase("x")) {
                        break;
                        }
                        System.out.print("(X) - Ingrese la capacidad maxima: ");
                        String capacidadMaxima = scanner.nextLine();
                        if (capacidadMaxima.equalsIgnoreCase("x")) {
                        break;
                        }
                        System.out.print("(X) - Ingrese el nombre del aeropuerto de partida: ");
                        String nombreAeropuertoPartida = scanner.nextLine();
                        if (nombreAeropuertoPartida.equalsIgnoreCase("x")) {
                        break;
                        }
                        Aeropuerto aeropuertoPartida = buscarAeropuertoPorNombre(aeropuertos, nombreAeropuertoPartida);
                        if (aeropuertoPartida == null) {
                            System.out.println("Aeropuerto invalido");
                            break;
                        }
                        System.out.print("(X) - Ingrese el nombre del aeropuerto de llegada: ");
                        String nombreAeropuertoLlegada = scanner.nextLine();
                        if (nombreAeropuertoLlegada.equalsIgnoreCase("x")) {
                        break;
                        }
                        Aeropuerto aeropuertoLlegada = buscarAeropuertoPorNombre(aeropuertos, nombreAeropuertoLlegada);
                        if (aeropuertoLlegada == null) {
                            System.out.println("Aeropuerto invalido");
                            break;
                        }
                        
                            vuelo = new Vuelo(Integer.parseInt(numeroVuelo), aerolinea, horaSalida, destino, Integer.parseInt(capacidadMaxima), aeropuertoPartida, aeropuertoLlegada);
                            aeropuertos.get(aeropuertos.indexOf(aeropuertoPartida)).registrarVueloPartida(vuelo);

                            System.out.println("Vuelo registrado con exito.");
                        } else {
                            System.out.println("Ya existe un vuelo con ese numero.");
                        }
                    break;
                case 3:
                    System.out.print("(X) - Ingrese el numero de vuelo: ");
                    numeroVuelo = scanner.nextLine();
                    if (numeroVuelo.equalsIgnoreCase("x")) {
                        break;
                    }

                    vuelo = buscarVueloPorNumero(aeropuertos, Integer.parseInt(numeroVuelo));
                    if (vuelo != null) {
                        if (vuelo.reservarAsiento(1)) {
                            System.out.println("Asiento reservado con exito.");
                        } else {
                            System.out.println("No se pudieron reservar los asientos");
                        }
                    } else {
                        System.out.println("No se encontro el vuelo con el numero especificado");
                    }
                    break;
                case 4:
                    System.out.print("(X) - Ingrese el numero de vuelo: ");
                    numeroVuelo = scanner.nextLine();
                    if (numeroVuelo.equalsIgnoreCase("x")) {
                        break;
                    }
                
                    vuelo = buscarVueloPorNumero(aeropuertos, Integer.parseInt(numeroVuelo));
                    if (vuelo != null) {
                        System.out.println("\nHay " + vuelo.getPasajeros().size() + " pasajeros en este vuelo");
                        for (Pasajero pasajero : vuelo.getPasajeros()) {
                            System.out.println("Nombre: " + pasajero.getNombre() + "\nPasaporte: " + pasajero.getNumeroPasaporte() + "\nEquipaje: " + pasajero.getCantidadEquipaje() + "\n");
                        }
                    } else {
                        System.out.println("No se encontro el vuelo con el numero especificado.");
                    }
                case 5:
                    break;
                default:
                    System.out.println("Opcion no invalida");
            }
        }
        scanner.close();
    }

    private static Vuelo buscarVueloPorNumero(ArrayList<Aeropuerto> aeropuertos, int numeroVuelo) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            for (Vuelo vuelo : aeropuerto.getVuelosPartida()) {
                if (vuelo.getNumeroVuelo() == numeroVuelo) {
                    return vuelo;
                }
            }
            for (Vuelo vuelo : aeropuerto.getVuelosLlegada()) {
                if (vuelo.getNumeroVuelo() == numeroVuelo) {
                    return vuelo;
                }
            }
        }
        return null;
    }

    private static boolean existeNumeroVuelo(ArrayList<Aeropuerto> aeropuertos, int numeroVuelo) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            for (Vuelo vuelo : aeropuerto.getVuelosPartida()) {
                if (vuelo.getNumeroVuelo() == numeroVuelo) {
                    return true;
                }
            }
            for (Vuelo vuelo : aeropuerto.getVuelosLlegada()) {
                if (vuelo.getNumeroVuelo() == numeroVuelo) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Aeropuerto buscarAeropuertoPorNombre(ArrayList<Aeropuerto> aeropuertos, String nombre) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.getNombre().equalsIgnoreCase(nombre)) {
                return aeropuerto;
            }
        }
        return null;
    }
}

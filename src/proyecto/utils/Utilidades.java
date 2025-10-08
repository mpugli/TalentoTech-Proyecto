package proyecto.utils;
import java.util.Scanner;

public class Utilidades {
    public static void enterParaContinuar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static String limpiarTexto(String texto) {
        return texto.trim().toLowerCase();
    }

    public static String pedirNombre(boolean obligatorio){
        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();

            if(!input.isEmpty()  || !obligatorio){
                return input;
            }

            System.out.println("El nombre es obligatorio!");

            System.out.println("Ingrese el nombre del producto nuevamente: ");
        }
    }

    public static double pedirPrecio(boolean obligatorio, double precioMax){
        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();

            if(input.isEmpty()){
                if (!obligatorio) return -1;

                System.out.println("El precio es obligatorio!");
                continue;
            }

            try{
                double precio = Double.parseDouble(input);

                if (precio >= 0 && precio <= precioMax){
                    return precio;
                }

                System.out.println("El precio ingresado no esta permitido");

            }catch(NumberFormatException e){
                System.out.println("Ingrese un numero valido!");
            }

            System.out.println("Ingrese la cantidad del producto nuevamente: ");
        }
    }

    public static int pedirCantidad(boolean obligatorio, int cantidadMax){
        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();

            if(input.isEmpty()){
                if (!obligatorio) return -1;

                System.out.println("La cantidad es obligatorio!");
                continue;
            }

            try{
                int cantidad = Integer.parseInt(input);

                if (cantidad > 0 && cantidad <= cantidadMax){
                    return cantidad;
                }

                System.out.println("La cantidad ingresada no esta permitida");

            }catch(NumberFormatException e){
                System.out.println("Ingrese un numero valido!");
            }

            System.out.println("Ingrese la cantidad del producto nuevamente: ");
        }
    }
}

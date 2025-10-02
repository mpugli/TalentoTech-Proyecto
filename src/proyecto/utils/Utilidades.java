package proyecto.utils;

public class Utilidades {
    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int verificarInt(String numero){
        numero = numero.trim();
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero entero.");
            return 0;
        }
    }
}

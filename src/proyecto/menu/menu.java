package proyecto.menu;
import java.util.List;
import java.util.Scanner;
import proyecto.utils.Utilidades;

public class Consola {
    public static void run() {

    }

    private void printMenu() {
        System.out.println("\n=== Gestión de Productos ===");
        System.out.println("1) Crear producto");
        System.out.println("2) Listar (paginado)");
        System.out.println("3) Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Buscar por nombre");
        System.out.println("0) Salir");
        System.out.print("> Elegí una opción: ");
    }

    private void redireccionar(){
        do {
            Utilidades.limpiarConsola();
            printMenu();
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            switch (input){
                case "1":
                    CrearProducto();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
            }
        }while(input != 0);
    }

    //CRUD

    private void CrearProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo del producto: ");
        System.out.println("Ingrese el nombre del producto: ");
        System.out.println("Ingrese la cantidad del producto: ");

    }

    private void MostrarProductos() {}

}
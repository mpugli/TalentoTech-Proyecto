package proyecto.menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import proyecto.model.Productos;
import proyecto.utils.Utilidades;

public class Menu {
    public void run() {
        List<Productos> productos = new ArrayList<>();
        int input;

        do {
            Utilidades.limpiarConsola();
            printMenu();
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
            switch (input){
                case 1:
                    productos.add(CrearProducto());
                    break;
                case 2:
                    MostrarProductos(productos);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("CHAU!");
                    break;
                default:
                    System.out.println("Tu ingreso no fue valido!");
                    break;
            }
        }while(input != 0);
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

    //CRUD

    private Productos CrearProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.println("Ingrese la cantidad del producto: ");
        int cantidad = sc.nextInt();

        return new Productos(nombre, precio, cantidad);
    }

    private void MostrarProductos(List<Productos> productos) {
        for (Productos producto : productos) {
            producto.verProducto();
        }
    }

}
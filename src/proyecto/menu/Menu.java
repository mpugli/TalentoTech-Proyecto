package proyecto.menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import proyecto.model.Productos;
import proyecto.utils.Utilidades;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        List<Productos> productos = new ArrayList<>();
        String input;

        do {
            Utilidades.limpiarConsola();
            printMenu();
            input = sc.nextLine();
            switch (input){
                case "1" -> CrearProducto(sc, productos);
                case "2" -> MostrarProductos(productos);
                case "3" -> ActualizarProducto(sc, productos);
                case "4"-> EliminarProducto(sc, productos);
                case "5"-> BuscarProductoxNombre(sc, productos);
                case "0" -> System.out.println("CHAU!");
                default -> System.out.println("Tu ingreso no fue valido!");
            }
        }while(!input.equals("0"));
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

    private void CrearProducto(Scanner sc, List<Productos> productos) {
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.println("Ingrese la cantidad del producto: ");
        int cantidad = sc.nextInt();

        Productos productoNuevo = new Productos(nombre, precio, cantidad);
        productos.add(productoNuevo);
    }

    private void MostrarProductos(List<Productos> productos) {
        for (Productos producto : productos) {
            producto.verProducto();
        }
    }

    private void ActualizarProducto(Scanner sc, List<Productos> productos) {
        System.out.println("Ingresa el código del producto a actualizar: ");
        String cod = sc.nextLine();

        boolean encontro = false;
        for (Productos producto : productos ) {

            if (producto.getCodigo() == Integer.parseInt(cod)) {

                System.out.println("Ingrese el nombre (enter para no modificar): ");
                String nombre = sc.nextLine();
                if (!nombre.isEmpty()) {
                    producto.setNombre(nombre);
                }

                System.out.println("Ingrese el precio (enter para no modificar): ");
                String precio = sc.nextLine();
                if (!precio.isEmpty()) {
                    producto.setPrecio(Double.parseDouble(precio));
                }

                System.out.println("Ingrese el stock (enter para no modificar): ");
                String stock = sc.nextLine();
                if (!stock.isEmpty()) {
                    producto.setStock(Integer.parseInt(stock));
                }

                encontro = true;
            }
        }

        if (!encontro) {
            System.out.println("No se encontró ningún producto con ese código!");
        }
    }

    private void BuscarProductoxNombre(Scanner sc, List<Productos> productos) {
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();

        boolean encontro = false;
        for (Productos prod : productos) {
           if (prod.getNombre().equals(nombre)) {
               prod.verProducto();
               encontro = true;
           }
        }
        if (!encontro) {
            System.out.println("No se encontró ningún producto con ese nombre!");
        }
    }

    private void EliminarProducto(Scanner sc, List<Productos> productos) {
        System.out.println("Ingresa el código del producto a eliminar: ");
        String cod = sc.nextLine();

        boolean borrado = productos.removeIf(p -> p.getCodigo() == Integer.parseInt(cod));

        if (borrado)
            System.out.println("Producto eliminado correctamente!");
        else
            System.out.println("No se encontró ningún producto con ese código!");
    }
}
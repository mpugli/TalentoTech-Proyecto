package proyecto.menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import proyecto.model.Productos;
import proyecto.utils.Utilidades;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    final double PRECIO_MAX = 15000;
    final int CANTIDAD_MAX = 100;

    public void run() {
        List<Productos> productos = new ArrayList<>();
        cargarProductosIniciales(productos);

        while(true) {
            printMenu();
            String input = Utilidades.limpiarTexto(sc.nextLine());
            switch (input){
                case "1" -> CrearProducto(productos);
                case "2" -> MostrarProductos(productos);
                case "3" -> ActualizarProducto(sc, productos);
                case "4" -> EliminarProducto(sc, productos);
                case "5" -> BuscarProductoxNombre(sc, productos);
                case "0" -> {
                    System.out.println("\nCHAU!\n");
                    return;
                }
                default -> System.out.println("Tu ingreso no fue valido!");
            }
        }
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

    private void CrearProducto(List<Productos> productos) {
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = Utilidades.pedirNombre(true);

        System.out.println("Ingrese el precio del producto: ");
        double precio = Utilidades.pedirPrecio(true, PRECIO_MAX);

        System.out.println("Ingrese la cantidad del producto: ");
        int cantidad = Utilidades.pedirCantidad(true, CANTIDAD_MAX);

        Productos productoNuevo = new Productos(nombre, precio, cantidad);
        productos.add(productoNuevo);

        System.out.println("Producto agregado correctamente!");
        Utilidades.enterParaContinuar();
    }

    private void MostrarProductos(List<Productos> productos) {
        for (Productos producto : productos) {
            producto.verProducto();
        }
        Utilidades.enterParaContinuar();
    }

    private void ActualizarProducto(Scanner sc, List<Productos> productos) {
        System.out.println("Ingresa el código del producto a actualizar: ");
        String cod = sc.nextLine();

        boolean encontro = false;
        for (Productos producto : productos ) {

            if (producto.getCodigo() == Integer.parseInt(cod)) {

                System.out.println("Ingrese el nombre (enter para no modificar): ");
                String nombre = Utilidades.pedirNombre(false);

                if (!nombre.isEmpty()){
                    producto.setNombre(nombre);
                }

                System.out.println("Ingrese el precio (enter para no modificar): ");
                double precio = Utilidades.pedirPrecio(false, PRECIO_MAX);

                if (precio != -1){
                    producto.setPrecio(precio);
                }

                System.out.println("Ingrese el stock (enter para no modificar): ");
                int stock = Utilidades.pedirCantidad(false, CANTIDAD_MAX);

                if (stock != -1){
                    producto.setStock(stock);
                }

                encontro = true;
            }
        }

        if (!encontro) {
            System.out.println("No se encontró ningún producto con ese código!");
        }

        Utilidades.enterParaContinuar();
    }

    private void BuscarProductoxNombre(List<Productos> productos) {
        System.out.println("Ingresa el nombre del producto/s a buscar: ");
        String nombre = Utilidades.pedirNombre(true);

        nombre = Utilidades.limpiarTexto(nombre);

        List<Productos> resultados = new ArrayList<>();

        boolean encontro = false;
        for (Productos prod : productos) {
           String nombreNormalizado = Utilidades.limpiarTexto(prod.getNombre());

           if (nombreNormalizado.contains(nombre)) {
               resultados.add(prod);
           }
           encontro = true;
        }

        if (encontro) {
            MostrarProductos(resultados);
        }else {
            System.out.println("No existe el producto con ese nombre!");
            Utilidades.enterParaContinuar();
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

        Utilidades.enterParaContinuar();
    }

    private static void cargarProductosIniciales(List<Productos> productos) {

        productos.add(new Productos("Coca-Cola 2.25L", 2850.00, 50));
        productos.add(new Productos("Yerba Mate Playadito 1kg", 4300.50, 30));
        productos.add(new Productos("Fernet Branca 750ml", 8900.00, 24));
        productos.add(new Productos("Galletitas Oreo 118g", 1150.00, 80));
        productos.add(new Productos("Leche La Serenísima 1L", 1050.75, 45));
        productos.add(new Productos("Azúcar Ledesma 1kg", 980.00, 100));
        productos.add(new Productos("Papas Fritas Lays Clásicas 150g", 2300.00, 40));
        productos.add(new Productos("Alfajor Jorgito de Chocolate", 700.00, 120));
        productos.add(new Productos("Pan Lactal Fargo", 2100.00, 25));
        productos.add(new Productos("Queso Cremoso Punta del Agua (kg)", 7500.00, 15));
    }
}
package proyecto.menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import proyecto.model.LineaPedido;
import proyecto.model.Pedido;
import proyecto.model.Producto;
import proyecto.utils.Utilidades;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    final double PRECIO_MAX = 15000;
    final int CANTIDAD_MAX = 100;

    public void run() {
        List<Producto> productos = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        cargarProductosIniciales(productos);

        while(true) {
            printMenu();
            String input = Utilidades.limpiarTexto(sc.nextLine());
            switch (input){
                case "1" -> CrearProducto(productos);
                case "2" ->
                    {
                        MostrarProductos(productos);
                        Utilidades.enterParaContinuar();
                    }
                case "3" -> BuscaryModificar(sc, productos);
                case "4" -> EliminarProducto(sc, productos);
                case "5" -> GenerarPedido(sc, productos, pedidos);
                case "6" -> ListarPedidos(pedidos);
                case "0" -> {
                    System.out.println("\nNos vemos!! Gracias por usar el programa!\n");
                    return;
                }
                default -> System.out.println("Tu ingreso no fue valido!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Gestión de Producto ===");
        System.out.println("1) Crear producto");
        System.out.println("2) Listar (paginado)");
        System.out.println("3) Buscar / Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Generar Pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("0) Salir");
        System.out.print("\n> Elegí una opción: ");
    }

    //CRUD

    private void CrearProducto(List<Producto> productos) {
        System.out.println("\nIngrese el nombre del producto: ");
        String nombre = Utilidades.pedirNombre(true);

        System.out.println("Ingrese el precio del producto: ");
        double precio = Utilidades.pedirPrecio(true, PRECIO_MAX);

        System.out.println("Ingrese la cantidad del producto: ");
        int cantidad = Utilidades.pedirCantidad(true, CANTIDAD_MAX);

        Producto productoNuevo = new Producto(nombre, precio, cantidad);
        productos.add(productoNuevo);

        System.out.println("Producto agregado correctamente!");
        Utilidades.enterParaContinuar();
    }

    private void MostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            producto.verProducto();
        }
    }

    private void BuscaryModificar(Scanner sc, List<Producto> productos) {

        System.out.println("\nIngresa el nombre del producto/s a buscar: ");
        String nombre = Utilidades.pedirNombre(true);

        nombre = Utilidades.limpiarTexto(nombre);

        List<Producto> resultados = new ArrayList<>();

        for (Producto prod : productos) {
           String nombreNormalizado = Utilidades.limpiarTexto(prod.getNombre());

           if (nombreNormalizado.contains(nombre)) {
               resultados.add(prod);
           }
        }

        if (resultados.isEmpty()) {
            System.out.println("No existe el producto con ese nombre!");
            Utilidades.enterParaContinuar();
            return;
        }

        MostrarProductos(resultados);

        if (resultados.size() == 1) {
            System.out.println("\nDesea modificar este producto (S / N): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("n")) {
                Utilidades.enterParaContinuar();
                return;
            }

            ActualizarProducto(resultados.getLast());
            Utilidades.enterParaContinuar();
            return;
        }

        if (resultados.size() > 1) {
            System.out.println("\nIngrese el ID del producto quiere modificar (N si no quiere modificar): ");

            while (true) {
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("n")) {
                    Utilidades.enterParaContinuar();
                    return;
                }

                for (Producto prod : resultados) {
                    try {
                        if (prod.getCodigo() == Integer.parseInt(input)) {
                            ActualizarProducto(prod);
                            Utilidades.enterParaContinuar();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingrese un ID valido (N para salir)!");
                    }
                }
            }
        }
    }

    private void ActualizarProducto(Producto producto) {

        System.out.println("\nIngrese el nombre (enter para no modificar): ");
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
    }

    private void EliminarProducto(Scanner sc, List<Producto> productos) {
        System.out.println("\nIngresa el código del producto a eliminar: ");
        String cod = sc.nextLine();

        boolean borrado = productos.removeIf(p -> p.getCodigo() == Integer.parseInt(cod));

        if (borrado)
            System.out.println("Producto eliminado correctamente!");
        else
            System.out.println("No se encontró ningún producto con ese código!");

        Utilidades.enterParaContinuar();
    }

    private void GenerarPedido(Scanner sc, List<Producto> productos, List<Pedido> pedidos) {

        Pedido pedido = new Pedido();
        boolean existe = false;

        while(true) {
            System.out.println("\nIngresa el ID del producto a agregar (fin para terminar): ");
            String id = sc.nextLine();

            if (id.equalsIgnoreCase("fin")) break;

            Producto prod = ConsultarStockId(id, productos);

            if (prod == null) {
                System.out.println("No existe el producto con ese id! Pruebe nuevamente");
                continue; //No encontro el producto
            }

            int stockDisponible = pedido.getStockDisponible(prod);

            if (stockDisponible > 0) {
                LineaPedido lPedido = CrearLineaPedido(prod, stockDisponible);
                pedido.agregarLinea(lPedido);
                if (!existe) existe = true;
            } else  {
                System.out.println("No hay mas stock de ese producto!");
            }
        }

        if (existe) {
            System.out.println("Desea guardar su pedido? S/N: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("n")) {
                System.out.println("\nNo se genero su pedido");
                Utilidades.enterParaContinuar();
                return;
            }

            ConfirmarPedido(pedido, pedidos);
        } else {
            System.out.println("\nNo se genero su pedido");
        }
        Utilidades.enterParaContinuar();
    }

    private void ConfirmarPedido(Pedido pedido, List<Pedido> pedidos) {
        for (LineaPedido linea : pedido.getLineaPedidos()) {
            Producto productoAfectado = linea.getProducto();
            int cantidadComprada = linea.getCantidad();
            int nuevoStock = productoAfectado.getStock() - cantidadComprada;
            productoAfectado.setStock(nuevoStock);
        }
        pedidos.add(pedido);
        System.out.println("\nPedido confirmado y stock actualizado!");
    }

    private Producto ConsultarStockId(String id, List<Producto> productos) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == Integer.parseInt(id)) {
                return producto;
            }
        }
        return null;
    }

    private LineaPedido CrearLineaPedido(Producto prod, int stockDisponible) {
        System.out.println(prod.getNombre()+" | Cantidad: "+stockDisponible);
        while(true) {
            System.out.println("Ingrese la cantidad que desea: ");
            String cant = sc.nextLine();
            try {
                int cantidad = Integer.parseInt(cant);
                if (stockDisponible >= cantidad) {
                    System.out.println("Producto agregado correctamente!");
                    return new LineaPedido(prod, cantidad);
                } else {
                    System.out.println("No hay más stock de ese producto o ya agregó todo el stock disponible a su pedido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingreso invalido!");
            }
        }
    }

    private void ListarPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            pedido.verPedido();
        }
        Utilidades.enterParaContinuar();
    }

    private static void cargarProductosIniciales(List<Producto> productos) {

        productos.add(new Producto("Coca-Cola 2.25L", 2850.00, 50));
        productos.add(new Producto("Yerba Mate Playadito 1kg", 4300.50, 30));
        productos.add(new Producto("Fernet Branca 750ml", 8900.00, 24));
        productos.add(new Producto("Galletitas Oreo 118g", 1150.00, 80));
        productos.add(new Producto("Leche La Serenísima 1L", 1050.75, 45));
        productos.add(new Producto("Azúcar Ledesma 1kg", 980.00, 100));
        productos.add(new Producto("Papas Fritas Lays Clásicas 150g", 2300.00, 40));
        productos.add(new Producto("Alfajor Jorgito de Chocolate", 700.00, 120));
        productos.add(new Producto("Pan Lactal Fargo", 2100.00, 25));
        productos.add(new Producto("Queso Cremoso Punta del Agua (kg)", 7500.00, 15));
    }
}
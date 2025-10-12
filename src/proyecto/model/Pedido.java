package proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private final int codigo;

    private final List<LineaPedido> lineaPedidos = new ArrayList<>();
    private double total;

    public Pedido() {
        this.codigo = contador;
        contador++;
    }

    public void agregarLinea(LineaPedido lineaPedido) {
        lineaPedidos.add(lineaPedido);
        total += lineaPedido.getCantidad() * lineaPedido.getProducto().getPrecio();
    }

    public void verPedido() {
        System.out.println("\n--- Pedido N"+ this.codigo +" ----");
        for  (LineaPedido lineaPedido : lineaPedidos) {
            lineaPedido.getProducto().verProducto();
            System.out.println("Cantidad Pedida: " + lineaPedido.getCantidad());
        }
        System.out.println("\nTotal a abonar: " + total);
    }

    public int getStockDisponible(Producto producto) {
        int cantidadYaEnPedido = 0;
        for (LineaPedido linea : this.lineaPedidos) {
            if (linea.getProducto().getCodigo() == producto.getCodigo()) {
                cantidadYaEnPedido += linea.getCantidad();
            }
        }
        return producto.getStock() - cantidadYaEnPedido;
    }

    public List<LineaPedido> getLineaPedidos() {
        return lineaPedidos;
    }
}

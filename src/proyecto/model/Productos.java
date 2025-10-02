package proyecto.model;

public class Productos {
    private static int contador = 0;
    private int codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Productos(String nombre, double precio,  int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        contador++;
        this.codigo = contador;
    }

    public void verProducto() {
        System.out.printf("""

            Código: %d
            Nombre: %s
            Precio: %.2f
            Stock: %d
            """, codigo, nombre, precio, stock);
    }
}

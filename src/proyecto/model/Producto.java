package proyecto.model;

public class Producto {
    private static int contador = 1;
    private final int codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio,  int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigo = contador;
        contador++;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() { return precio; }

    public int getStock() { return stock; }

    public void verProducto() {
        System.out.printf("""

            Código: %d
            Nombre: %s
            Precio: %.2f
            Stock: %d
            """, codigo, nombre, precio, stock);
    }

    public void descontarStock(int stock_a_reducir) {
        if (stock_a_reducir <= this.stock) {
            this.stock -= stock_a_reducir;
        }
    }
}

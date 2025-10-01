package proyecto.model;

public class Productos {
    private int codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Productos(String nombre, double precio) {
        this.codigo = this.codigo + 1;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0;
    }
}

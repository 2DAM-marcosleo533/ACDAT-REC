import java.io.Serializable;

public class Producto implements Serializable {

    private int cod;
    private String nombre;
    private float precio;
    private int stock;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(int cod, String nombre, float precio, int stock) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

}

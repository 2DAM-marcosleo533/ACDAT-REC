import java.io.File;

public class ProductosApp {
    public static void main(String[] args) {
        GestionaProductos gp = new GestionaProductos();
        String rutaCSV = "res/productos.csv";

        if (gp.generarFichero(rutaCSV)) {
            System.out.println("Archivo binario generado correctamente.");
        } else {
            System.out.println("Error al generar archivo binario.");
        }
        File archivoDAT = new File(rutaCSV.replace(".csv", ".dat"));
        System.out.println("\nProductos con stock menor a 50:");
        GestionaProductos.consultarStock(archivoDAT, 50);
        }
}


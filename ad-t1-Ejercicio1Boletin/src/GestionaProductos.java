import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionaProductos {

    static boolean generarFichero(String fcsv){
        String linea;
        List<Producto> listaProductos = new ArrayList<>();
        File archivoCSV = new File(fcsv);
        File archivoDAT = new File(fcsv.replace(".csv", ".dat"));

        try(BufferedReader bf = new BufferedReader(new FileReader(archivoCSV))){
            while ((linea = bf.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length == 4){
                    try{
                        int cod = Integer.parseInt(datos[0].trim());
                        String nombre = datos[1].trim();
                        float precio = Float.parseFloat(datos[2].trim());
                        int stock = Integer.parseInt(datos[3].trim());
                        listaProductos.add(new Producto(cod, nombre, precio, stock));
                    }
                    catch (NumberFormatException e){
                        System.out.println("Error al convertir los datos: " + e.getMessage());
                        return false;
                    }
                }
            }

            try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(archivoDAT))){
                for (Producto p : listaProductos){
                    oos.writeObject(p);
                }
            }
            catch (IOException e){
                System.out.println("Error al escribir el archivo DAT: " + e.getMessage());
                return false;
            }

            return true;
        }
        catch (Exception e){
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            return false;
        }
    }


    static void consultarStock(File f, int minstock){
        if (!f.exists() || !f.getName().endsWith(".dat")) {
            System.out.println("Fichero no válido.");
            return;
        }

        List<Producto> productos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    Producto p = (Producto) ois.readObject();
                    if (p.getStock() >= minstock) {
                        productos.add(p);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        productos.sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()));

        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}

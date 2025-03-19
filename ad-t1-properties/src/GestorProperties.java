import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GestorProperties {
    private File f;
    public GestorProperties(File f) {
        this.f = f;
    }

    public String leerPropiedad(String c) {
        try {
            Properties props = new Properties();
            props.load(new FileReader(f));

            String propiedad = props.getProperty(c);
            if (propiedad != null) {
                System.out.println("Propiedad " + c + ": " + propiedad);
            } else {
                System.err.println("no existe la propiedad buscada");
            }
            return propiedad;
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no existe");
        } catch (IOException e) {
            System.err.println("Error en lectura de propiedades de configuración");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    //mostrar las propiedades y su valor. Por ejemplo: background-color: red
    public void mostrarPropiedades() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(f));

       System.out.print("background color: " + props.getProperty("background-color")+"\n");
       System.out.print("tamaño de la fuente: " + props.getProperty("tamanio-fuente"));
    }
}

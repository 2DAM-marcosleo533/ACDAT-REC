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

    public String leerPropiedad(String c){
        try {
            Properties props = new Properties();
            props.load(new FileReader("res" + File.separator + "app.properties"));

            if (props.getProperty("username") != null)
                System.out.print("DEFAULT COLOR: " + props.getProperty("background-color"));
        }
        catch (FileNotFoundException e) {
            System.err.println("Fichero no existe");
        }
        catch (IOException e) {
            System.err.println("Error en lectura de propiedades de configuración");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return c;
    }

    public void mostrarPropiedades(){

    }
}

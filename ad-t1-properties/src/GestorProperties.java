import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

public class GestorProperties {
    private File f;
    public GestorProperties(File f) {

        this.f = f;
    }

    public String leerPropiedad(String c) {
       return properties.getProperty(c);
    }
    //mostrar las propiedades y su valor. Por ejemplo: background-color: red
    public void mostrarPropiedades() throws IOException {
        Enumeration<Object> claves = properties.keys();
    }
}

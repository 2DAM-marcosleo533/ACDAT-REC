import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        //10 lineas de codigo de prueba
        //le pregunta al usuario por el archivo properties
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el archivo: ");
        String archivo = sc.nextLine();

        File propertiesFile = new File(archivo);

        if (!propertiesFile.exists()) {
            System.err.println("El archivo no existe.");

        } else if(!propertiesFile.isFile() || !archivo.endsWith(".properties")){
            System.err.println("No es un archivo o no es un archivo .properties");
        } else{
            GestorProperties gestor = new GestorProperties(propertiesFile);

            gestor.leerPropiedad("tamanio-fuente");
            gestor.mostrarPropiedades();
        }



    }
}

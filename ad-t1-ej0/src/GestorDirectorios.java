import java.io.File;
import java.util.Scanner;

public class GestorDirectorios {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el directorio: ");
        File directorio = new File(sc.nextLine());
        System.out.println("El directorio introducido es: " + directorio);

        if (directorio.isDirectory()) {
            File[] listaArchivos = directorio.listFiles();
            if (listaArchivos != null) {
                for (File archivo : listaArchivos) {
                    if (archivo.isDirectory()) {
                        System.out.println("+" +  archivo.getName());
                    } else if (archivo.isFile()) {
                        System.out.println("-" +  archivo.getName());
                    }
                }
            } else {
                System.err.println("No se encuentra el directorio");
            }
        }
        // Si es un archivo, le saco el nombre de este
        else if (directorio.isFile()) {
            System.out.print("Es un archivo con el nombre " + directorio.getName());
        }
        // Si la respuesta no es un directorio o archivo
        else {
            System.err.print("La respuesta introducida no es un directorio ni un archivo.");
        }
    }
}

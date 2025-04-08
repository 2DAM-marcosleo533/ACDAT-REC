import java.io.File;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.println("Mostramos el contenido del fichero:");
            GestorFichero.mostrar();
            System.out.println("Inserta una cadena para insertar en un nuevo fichero:");
            String cad = sc.nextLine();
            if (GestorFichero.insertar(cad)) {
                System.out.println("Se ha insertado la cadena en el fichero");
            } else {
                System.out.println("No se ha podido insertar la cadena en el fichero");
            }
            System.out.println("Inserta una cadena para buscar en el fichero: ");
            String cad2 = sc.nextLine();
            if (GestorFichero.buscar(cad2)) {
                System.out.println("La cadena está en el fichero");
            } else {
                System.out.println("La cadena no esta en el fichero");
            }
            System.out.println("Inserta la ceadena a buscar y sustituir: ");
            String cad3 = sc.nextLine();
            System.out.println("Inserta la cadena por la que sustituir: ");
            String cad4 = sc.nextLine();
            System.out.println("Se ha sustituido " + GestorFichero.actualizar(cad3, cad4) + " veces");
            System.out.println("Inserta la cadena a eliminar: ");
            String cad5 = sc.nextLine();
            System.out.println("Se ha eliminado " + GestorFichero.eliminar(cad5) + " veces");}
}


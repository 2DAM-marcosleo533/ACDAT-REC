import java.io.*;
import java.util.Scanner;

public class GestorFichero {
    private File f;

    public GestorFichero(File f) {
        this.f = f;
    }

    //muestra el contenido del fichero creado
    public static void mostrar() {
        try (BufferedReader br = new BufferedReader(new FileReader("res/fichero.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    //coger el fochero e insertar una cadena en el mismo fichero al final de este
    public static boolean insertar(String cad) {
        try {
            FileWriter escritura = new FileWriter("res/fichero.txt", true);
            escritura.write(cad);
            escritura.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //busca una cadena en el fichero
    public static boolean buscar(String cad) {
        boolean bool = false;
        try (BufferedReader br = new BufferedReader(new FileReader("res/fichero.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(cad)) {
                    bool = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            bool = false;
        }

        return bool;
    }

    //coge una cadena del fichero y la sustituye por otra, pintandolo en un nuevo fichero, devuelve el numero de veces que se ha sustituido
    public static int actualizar(String cad, String cad2) {
        int cont = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("res/fichero.txt"))) {
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                int index = 0;

                while ((index = linea.indexOf(cad, index)) != -1) {
                    cont++;
                    index += cad.length();
                }

                linea = linea.replace(cad, cad2);
                contenido.append(linea).append(System.lineSeparator());
            }
            try (FileWriter escritura = new FileWriter("res/FicheroPalabraActualizada.txt")) {
                escritura.write(contenido.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }


    //elimina una cadena del fichero, pintandolo en un nuevo fichero, devuelve el numero de veces que se ha eliminado
    public static int eliminar(String cad) {
        int cont = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("res/fichero.txt"))) {
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                int index = 0;

                while ((index = linea.indexOf(cad, index)) != -1) {
                    cont++;
                    index += cad.length();
                }

                linea = linea.replace(cad, "");
                contenido.append(linea).append(System.lineSeparator());
            }
            try (FileWriter escritura = new FileWriter("res/FicheroPalabraEliminada.txt")) {
                escritura.write(contenido.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }

}

//no usar ObjectInputStream. Directamente sobre el fichero
//No usar arraylist o collecciones

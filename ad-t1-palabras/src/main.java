import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int cont = 0;

        System.out.print("Introduzca la ruta del archivo: ");
        String ruta = sc.nextLine();

        File palabrasFile = new File(ruta);

        if (!palabrasFile.exists()) {
            System.err.println("El archivo no existe.");

        } else if(!palabrasFile.isFile()){
            System.err.println("No es un archivo");
        }
        else {
            System.out.println("Qué palabra quiere?");
            String palabra = sc.nextLine();

            FileReader fileReader = new FileReader(palabrasFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("sinPalabras.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] palabras = line.split("\\s+");
                StringBuilder nuevaLinea = new StringBuilder();
                for (String p : palabras) {
                    if (p.equalsIgnoreCase(palabra)) {
                        cont++;
                    } else {
                        nuevaLinea.append(p).append(" ");
                    }
                }
                bufferedWriter.write(nuevaLinea.toString().trim());
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();

            System.out.println("La palabra '" + palabra + "' aparece " + cont + " veces");
            System.out.println("Se ha creado el nuevo archivo");
        }
    }
}

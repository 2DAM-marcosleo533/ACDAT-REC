package iesmm.ad.t1_03;

import java.io.*;
import java.util.Scanner;

public class PokemonGenera2 {
    public static void main(String[] args) {
        try {
            // Definimos los archivos de entrada y salida
            File inputFile = new File("res" + File.separator + "pokemons.txt");
            File outputFile = new File("res" + File.separator + "pokemons.dat");

            // Vemos si es txt existe
            if (!inputFile.exists()) {
                throw new FileNotFoundException("El archivo de entrada no existe: " + inputFile.getAbsolutePath());
            }

            // flujo de salida binario
            DataOutputStream fichero = new DataOutputStream(new FileOutputStream(outputFile));

            // bufferedReader del archivo txt
            BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = fileReader.readLine()) != null) {
                // separamos los datos por ;
                String[] pokemonData = line.split(";");


                    String nombre = pokemonData[0];
                    int vida = Integer.parseInt(pokemonData[1]);
                    int ataque = Integer.parseInt(pokemonData[2]);
                    int defensa = Integer.parseInt(pokemonData[3]);
                    boolean evoluciona = Boolean.parseBoolean(pokemonData[4]);

                    // Escribimos los datos en el archivo binario
                    fichero.writeUTF(nombre);
                    fichero.writeInt(vida);
                    fichero.writeInt(ataque);
                    fichero.writeInt(defensa);
                    fichero.writeBoolean(evoluciona);
                    fichero.writeBoolean(evoluciona);

            }

            // Cerramos los flujos
            fichero.close();
            fileReader.close();

            System.out.println("Fichero binario generado en: " + outputFile.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al generar el fichero binario: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }
    }
}

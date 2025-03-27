package iesmm.ad.t1_03;

import iesmm.ad.t1_03.model.Pokemon;

import java.io.*;
import java.util.Scanner;

public class PokemonGenera2 {
    public static void main(String[] args) {
        // Definimos los archivos de entrada y salida
        File inputFile = new File("res" + File.separator + "pokemons.txt");
        File outputFile = new File("res" + File.separator + "pokemons.dat");

        if (inputFile.exists()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
                 ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(outputFile))) {

                String line;
                while ((line = fileReader.readLine()) != null) {
                    // Separamos los datos por ;
                    String[] pokemonData = line.split(";");

                    if (pokemonData.length == 5) {
                        String nombre = pokemonData[0];
                        int vida = Integer.parseInt(pokemonData[1]);
                        int ataque = Integer.parseInt(pokemonData[2]);
                        int defensa = Integer.parseInt(pokemonData[3]);
                        boolean evoluciona = Boolean.parseBoolean(pokemonData[4]);

                        // Creamos el objeto
                        Pokemon pokemon = new Pokemon(nombre, vida, ataque, defensa, evoluciona);

                        // Escribimos el objeto Pokemon en el archivo binario
                        objectWriter.writeObject(pokemon);
                    } else {
                        System.err.println("Formato incorrecto en la línea: " + line);
                    }
                }

                System.out.println("Fichero binario generado en: " + outputFile.getAbsolutePath());

            } catch (FileNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al generar el fichero binario: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error al procesar los datos: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo txt no existe");
        }
    }
}

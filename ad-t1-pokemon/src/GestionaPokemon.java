import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GestionaPokemon {

    public void mostrarPokemons(File f, String tipo) throws IOException {
        if (!f.exists() || !f.isFile()){
            System.err.println("El archivo no existe o no es un fichero");
        } else {
            System.out.println("Ruta absoluta del archivo: " + f.getAbsolutePath());
            ArrayList<String> listaPokemon = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[2].equals(tipo)) {
                    listaPokemon.add("Pokemon: " + datos[1] + ", Tipo: " + datos[2]);
                }
            }
                Collections.sort(listaPokemon);
                System.out.println("Lista de pokemons de tipo " + tipo + " ordenados por nombre:");
            for (String pokemon : listaPokemon) {
                System.out.println(pokemon);
            }
            br.close();
        }
    }

    public boolean generarFichero(String fcsv){
        boolean bool = false;
        File inputFile = new File(fcsv);
        File outputFile = new File(fcsv.replace(".csv", ".dat"));

        try (
                BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
                ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(outputFile))) {

            if (!inputFile.exists()) {
                System.err.println("El archivo no existe");
            } else {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    // Separamos los datos por ;
                    String[] pokemonData = line.split(",");

                    if (pokemonData.length == 7) {
                        String nombre = pokemonData[1];
                        String tipo = pokemonData[2];
                        int vida = Integer.parseInt(pokemonData[3]);
                        int ataque = Integer.parseInt(pokemonData[4]);
                        int defensa = Integer.parseInt(pokemonData[5]);
                        int velocidad = Integer.parseInt(pokemonData[6]);

                        // Creamos el objeto
                        Pokemon pokemon = new Pokemon(nombre, tipo, vida, ataque, defensa, velocidad);

                        // Escribimos el objeto Pokemon en el archivo binario
                        objectWriter.writeObject(pokemon);
                    } else {
                        System.err.println("No son 7 datos");
                    }
                }
                bool = true;
                System.out.println("Fichero binario generado en: " + outputFile.getAbsolutePath());

                objectWriter.close();
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al generar el fichero binario: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }

return bool;
    }

    public void contabilizarTipos(File f){
        File outputFile = new File("res" + File.separator + "tipos.txt");
        Map<String, Integer> tipoContador = new HashMap<>();

        try (
                ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(f));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            while (true) {
                try {
                    Pokemon pokemon = (Pokemon) objectReader.readObject();
                    String tipo = pokemon.getTipo();

                    tipoContador.put(tipo, tipoContador.getOrDefault(tipo, 0) + 1);

                } catch (EOFException e) {
                    break;
                }
            }

            for (String tipo : tipoContador.keySet()) {
                int cantidad = tipoContador.get(tipo);
                writer.write("Tipo: " + tipo + ", Número de pokemons: " + cantidad);
                writer.newLine();
            }

            System.out.println("Fichero de texto generado en: " + outputFile.getAbsolutePath());

            objectReader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al generar el fichero de texto: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }

    }
}

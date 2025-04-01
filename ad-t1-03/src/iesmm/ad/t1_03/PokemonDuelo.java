package iesmm.ad.t1_03;

import iesmm.ad.t1_03.model.Pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PokemonDuelo {
    public static void main(String[] args) {
        Pokemon pok1 = null, pok2 = null;

        try {
            File pokemonFile = new File("res" + File.separator + "pokemons.dat");

            Pokemon[] pokemons = buscar(pokemonFile);

            if (pokemons != null) {
                System.out.println("Pokémon encontrados:");
                for (Pokemon p : pokemons) {
                    System.out.println(p);
                }
            } else {
                System.err.println("No se encontraron ambos Pokémon.");
            }

            // Lectura del fichero de dos pokemons
            ObjectInputStream finput = new ObjectInputStream(new FileInputStream("res" + File.separator + "pokemon.dat"));
            pok1 = (Pokemon) finput.readObject();
            pok2 = (Pokemon) finput.readObject();
            finput.close();

            // Generar batalla entre pokemons
            if (pok1 != null && pok2 != null)
                batalla(pok1, pok2);

            // Fin del programa
            System.out.println("FIN DEL JUEGO");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static Pokemon[] buscar(File f) {
        if (!f.exists()) {
            System.err.println("El archivo no existe.");
            return null;
        } else {
            List<Pokemon> listaPokemons = new ArrayList<>();

            try (
                    ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))
            ) {
                while (true) {
                    try {
                        listaPokemons.add((Pokemon) input.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
                return null;
            }

            if (listaPokemons.isEmpty()) {
                System.err.println("No hay Pokémon en el archivo.");
                return null;
            } else {
                Scanner scanner = new Scanner(System.in);
                Pokemon[] seleccionados = new Pokemon[2];

                for (int i = 0; i < 2; i++) {
                    System.out.print("Introduce el nombre del Pokémon " + (i + 1) + ": ");
                    String pokemonBuscado = scanner.nextLine();

                    //Comparamos los nombres de los pokemons de ambas listas de cada fichero
                    for (Pokemon p : listaPokemons) {
                        if (p.getNombre().equalsIgnoreCase(pokemonBuscado)) {
                            seleccionados[i] = p;
                            break;
                        }
                    }
                }

                //Si uno de los dos pokemons no esta, se devuelve null
                if (seleccionados[0] == null || seleccionados[1] == null) {
                    return null;
                } else {
                    return seleccionados;
                }

            }
        }

    }

    private static void batalla (Pokemon pok1, Pokemon pok2) {
        while (pok1.getVida() > 0 && pok2.getVida() > 0) {
            System.out.println("DUELO ENTRE:");
            System.out.println(pok1 + "\n" + pok2);
            pausa();

            System.out.println("TURNO DE ATAQUE PARA " + pok1.getNombre());
            pok1.atacar(pok2, new Random().nextInt(pok1.getAtaque()));
            pausa();

            if (pok2.getVida() >= 0) {
                System.out.println("DUELO ENTRE:");
                System.out.println(pok1 + "\n" + pok2);
                pausa();

                System.out.println("TURNO DE ATAQUE PARA " + pok2.getNombre());
                pok2.atacar(pok1, new Random().nextInt(pok2.getAtaque()));
                pausa();

                if (pok1.getVida() <= 0)
                    System.out.println(pok1.getNombre() + " HA SIDO DERROTADO.\n");
            } else
                System.out.println(pok2.getNombre() + " HA SIDO DERROTADO.\n");
        }
    }

    private static void pausa() {
        try {
            System.out.print("\n\t\tPRESIONAR ENTER PARA ATACAR\n\n");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

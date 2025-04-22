import java.io.File;
import java.io.IOException;

public class PokemonApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Mostrar pokemons");
        GestionaPokemon gp = new GestionaPokemon();
        File f = new File("res" + File.separator + "pokemon.csv");
        gp.mostrarPokemons(f, "Water");

        System.out.println("Generación de fichero binario: ");
        if (gp.generarFichero("res" + File.separator + "pokemon.csv")){
            System.out.println("Fichero generado correctamente");
        } else {
            System.out.println("No se ha podido generar el fichero");
        }

        File f2 = new File("res" + File.separator + "pokemon.dat");
        System.out.println("Contabilización de tipos: ");
        if (!f2.exists()){
            System.out.println("El fichero binario no existe");
        } else {
            gp.contabilizarTipos(f2);
        }
    }
}

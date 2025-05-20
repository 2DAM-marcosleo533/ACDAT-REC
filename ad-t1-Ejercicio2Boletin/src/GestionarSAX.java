import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GestionarSAX {

    public static void main(String[] args) {
        int totalUsuarios = 0;
        int usuariosSinPassword = 0;

        File userFile = new File("res/users.txt");
        if (!userFile.exists()) {
            System.out.println("El archivo users.txt no existe.");
            return;
        }

        List<String> usuariosValidos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                totalUsuarios++;
                String[] partes = linea.split(":", 2);
                if (partes.length < 2 || partes[1].trim().isEmpty()) {
                    usuariosSinPassword++;
                } else {
                    usuariosValidos.add(partes[0].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo users.txt: " + e.getMessage());
            return;
        }


        System.out.println("Total usuarios en users.txt: " + totalUsuarios);
        System.out.println("Usuarios sin password definida: " + usuariosSinPassword);


        try {
            File inputFile = new File("res/sessions.xml");
            if (!inputFile.exists()) {
                System.out.println("El archivo sessions.xml no existe.");
                return;
            }

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ManejadorSAXSesiones handler = new ManejadorSAXSesiones();
            saxParser.parse(inputFile, handler);

            System.out.println("\nInforme generado correctamente: informe_sesiones.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

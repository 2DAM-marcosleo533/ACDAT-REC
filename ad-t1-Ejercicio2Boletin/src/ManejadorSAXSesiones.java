import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ManejadorSAXSesiones extends DefaultHandler {

    private int totalUsuarios = 0;
    private int usuariosSinHoraFinal = 0;
    private int sesionesRegistradas = 0;
    private boolean tieneHoraFinal = false;

    private String currentElement = "";
    private String currentUsername = "";
    private String currentHoraInicio = "";

    private final List<String> usuariosMañana = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Iniciando análisis XML...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName.toLowerCase();

        if (qName.equalsIgnoreCase("user")) {
            totalUsuarios++;
            tieneHoraFinal = false;
            currentUsername = "";
            currentHoraInicio = "";
        } else if (qName.equalsIgnoreCase("horafinal")) {
            tieneHoraFinal = true;
            sesionesRegistradas++;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contenido = new String(ch, start, length).trim();
        if (contenido.isEmpty()) return;

        switch (currentElement) {
            case "username":
                currentUsername += contenido;
                break;
            case "horainicio":
                currentHoraInicio += contenido;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            if (!tieneHoraFinal) {
                usuariosSinHoraFinal++;
            }

            try {
                LocalTime hora = LocalTime.parse(currentHoraInicio);
                if (!hora.isBefore(LocalTime.of(9, 0)) && !hora.isAfter(LocalTime.of(14, 0))) {
                    usuariosMañana.add(currentUsername + " (" + currentHoraInicio + ")");
                }
            } catch (Exception e) {
                System.out.println("Formato de hora inválido para usuario " + currentUsername + ": " + currentHoraInicio);
            }
        }
        currentElement = "";
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Análisis completado.");
        System.out.println("Usuarios dados de alta: " + totalUsuarios);
        System.out.println("Usuarios sin hora final definida: " + usuariosSinHoraFinal);
        System.out.println("Sesiones registradas: " + sesionesRegistradas);
        System.out.println("Sesiones activas en ejecución: " + usuariosSinHoraFinal);

        if (!usuariosMañana.isEmpty()) {
            System.out.println("Usuarios con hora de inicio entre las 9:00 y las 14:00:");
            for (String u : usuariosMañana) {
                System.out.println("  - " + u);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/informeSesiones.txt"))) {
            writer.write("Nº de sesiones registradas: " + sesionesRegistradas);
            writer.newLine();
            writer.write("Nº de sesiones en ejecución en el sistema: " + usuariosSinHoraFinal);
            writer.newLine();
            writer.write("Usuarios conectados entre las 9:00 y las 14:00:");
            writer.newLine();
            for (String u : usuariosMañana) {
                writer.write("  - " + u);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SAXException("Error al escribir el informe de sesiones.", e);
        }
    }
}

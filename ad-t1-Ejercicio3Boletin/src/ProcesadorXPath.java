import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import java.io.File;

public class ProcesadorXPath {

    private Document doc;

    public ProcesadorXPath(String archivoXML) {
        try {
            File archivo = new File(archivoXML);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int contarVideojuegosNES() {
        var videojuegos = doc.getElementsByTagName("videojuego");
        int count = 0;
        for (int i = 0; i < videojuegos.getLength(); i++) {
            var elem = (Element) videojuegos.item(i);
            if (elem.getElementsByTagName("plataforma").item(0).getTextContent().equals("NES")) {
                count++;
            }
        }
        return count;
    }

    public int contarVideojuegosConCaptura() {
        var videojuegos = doc.getElementsByTagName("videojuego");
        int count = 0;
        for (int i = 0; i < videojuegos.getLength(); i++) {
            var elem = (Element) videojuegos.item(i);
            var imagenes = (Element) elem.getElementsByTagName("imagenes").item(0);
            var capturas = imagenes.getElementsByTagName("captura");
            if (capturas.getLength() > 0) {
                count++;
            }
        }
        return count;
    }

    public void mostrarVideojuegosNESStockBajo() {
        var videojuegos = doc.getElementsByTagName("videojuego");
        for (int i = 0; i < videojuegos.getLength(); i++) {
            var elem = (Element) videojuegos.item(i);
            String plataforma = elem.getElementsByTagName("plataforma").item(0).getTextContent();
            int stock = Integer.parseInt(elem.getElementsByTagName("stock").item(0).getTextContent());
            if (plataforma.equals("NES") && stock <= 10) {
                String titulo = elem.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("   - " + titulo);
            }
        }
    }

    public int stockTotalJuegosMesa() {
        var juegos = doc.getElementsByTagName("juegomesa");
        int total = 0;
        for (int i = 0; i < juegos.getLength(); i++) {
            var elem = (Element) juegos.item(i);
            int stock = Integer.parseInt(elem.getElementsByTagName("stock").item(0).getTextContent());
            total += stock;
        }
        return total;
    }

    public void mostrarVideojuegosSinCaptura() {
        var videojuegos = doc.getElementsByTagName("videojuego");
        for (int i = 0; i < videojuegos.getLength(); i++) {
            var elem = (Element) videojuegos.item(i);
            var imagenes = (Element) elem.getElementsByTagName("imagenes").item(0);
            var capturas = imagenes.getElementsByTagName("captura");
            if (capturas.getLength() == 0) {
                String titulo = elem.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("   - " + titulo);
            }
        }
    }
}

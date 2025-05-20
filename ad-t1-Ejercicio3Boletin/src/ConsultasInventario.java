public class ConsultasInventario {
    public static void main(String[] args) {
        ProcesadorXPath procesador = new ProcesadorXPath("juegos.xml");

        System.out.println("1) Videojuegos en plataforma NES: " + procesador.contarVideojuegosNES());
        System.out.println("2) Videojuegos con al menos una captura: " + procesador.contarVideojuegosConCaptura());

        System.out.println("3) Títulos de videojuegos NES con stock <= 10:");
        procesador.mostrarVideojuegosNESStockBajo();

        System.out.println("4) Stock total de juegos de mesa: " + procesador.stockTotalJuegosMesa());

        System.out.println("5) Videojuegos sin capturas de pantalla:");
        procesador.mostrarVideojuegosSinCaptura();
    }
}

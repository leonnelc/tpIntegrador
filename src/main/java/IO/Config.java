package IO;

public class Config {
    private Config(){}
    private static int puntosPorAcierto = 1;
    private static int puntosPorAciertoRonda = 2;
    private static int puntosPorAciertoFase = 3;
    private static int partidosPorRonda = 4;
    private static int rondasPorFase = 2;

    public static int getPuntosPorAcierto() {
        return puntosPorAcierto;
    }

    public static void setPuntosPorAcierto(int puntosPorAcierto) {
        Config.puntosPorAcierto = puntosPorAcierto;
    }

    public static int getPuntosPorAciertoRonda() {
        return puntosPorAciertoRonda;
    }

    public static void setPuntosPorAciertoRonda(int puntosPorAciertoRonda) {
        Config.puntosPorAciertoRonda = puntosPorAciertoRonda;
    }

    public static int getPuntosPorAciertoFase() {
        return puntosPorAciertoFase;
    }

    public static void setPuntosPorAciertoFase(int puntosPorAciertoFase) {
        Config.puntosPorAciertoFase = puntosPorAciertoFase;
    }

    public static void setPartidosPorRonda(int partidosPorRonda) {
        Config.partidosPorRonda = partidosPorRonda;
    }

    public static void setRondasPorFase(int rondasPorFase) {
        Config.rondasPorFase = rondasPorFase;
    }

    public static int getPartidosPorRonda() {
        return partidosPorRonda;
    }

    public static int getRondasPorFase() {
        return rondasPorFase;
    }
}

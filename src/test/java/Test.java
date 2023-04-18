import IO.Config;

public class Test {
    public static void main(String[] args) {
        System.out.println(Config.getPuntosPorAcierto()); // 1
        Config.setPuntosPorAcierto(23);
        System.out.println(Config.getPuntosPorAcierto()); // 23
    }
}

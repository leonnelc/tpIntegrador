import IO.LectorCSV;
import IO.ParseadorArgs;

public class Main {
    public static void main(String[] args) {
        String[] argsParseados = ParseadorArgs.parsearArgs(args, " ");
        String rutaResultados = argsParseados[0];
        String rutaPronosticos = argsParseados[1];
        //
        LectorCSV resultados = new LectorCSV(rutaResultados, ",");
        LectorCSV pronosticosCSV = new LectorCSV(rutaPronosticos, ",");
        // TODO: instanciar Partidos y Personas con sus Pronosticos

    }
}

package IO;

import Excepciones.ConfigInvalidaException;

public class ConfigDB {
    // tipo de base de datos
    private static String tipoDB = "csv"; // puede ser csv o sql (para csv hay que configurar las columnas y para sql los datos de la DB)
    // Ajustes SQL
    private static String url = "localhost:666";
    private static String username = "admin";
    private static String password = "1234";
    // Ajustes CSV
    // los numeros son las columnas del csv, siendo el 0 la primera columna
    private static int fase = 0;
    private static int ronda = 1;
    // ajustes del csv que contiene los resultados
    private static int equipo1r = 2; // la r significa que pertenece al archivo csv de los resultados
    private static int equipo2r = 5;
    private static int goles1 = 3;
    private static int goles2 = 4;
    // ajustes del csv que contiene los pronosticos
    private static int participante = 2;
    private static int resultado = 4;
    private static int equipo1p = 3; // la p significa que pertenece al archivo csv de los pronosticos
    private static int equipo2p = 5;

    public static void validar() throws ConfigInvalidaException {
        int[] columnasResultados = new int[]{ronda, equipo1r, equipo2r, goles1, goles2};
        int[] columnasPronosticos = new int[]{participante, resultado, equipo1p, equipo2p};
        for (int numColumna : columnasResultados) {
            if ((numColumna > 5) || (numColumna < 0)) {
                // TODO: hacer mas especifico el error, posiblemente dando el nombre de la columna fuera de rango
                throw new ConfigInvalidaException("El numero de columna especificado \"" + numColumna + "\" para el archivo de resultados esta fuera de rango");
            }
        }
        for (int numColumna : columnasPronosticos) {
            if ((numColumna > 5) || (numColumna < 0)) {
                throw new ConfigInvalidaException("El numero de columna especificado \"" + numColumna + "\" para el archivo de pronosticos esta fuera de rango");
            }
        }
    }
}
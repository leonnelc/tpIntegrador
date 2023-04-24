package IO;

import Excepciones.ColumnasInvalidasException;
import Excepciones.ConfigInvalidaException;

import java.sql.SQLException;

public class ControladorDB {
    private static Fila[] resultados;
    private static Fila[] pronosticos;
    private ControladorDB(){

    }
    public static void cargar() throws SQLException{
        String tipoDB = ConfigDB.getTipoDB();
        if (tipoDB.equalsIgnoreCase("csv")){
            cargarCSV();
        } else if (tipoDB.equalsIgnoreCase("sql")) {
            cargarSQL();
        }else{
            throw new ConfigInvalidaException("Tipo de base de datos \""+tipoDB+"\" invalido, deberia ser csv o sql");
        }

    }

    public static Fila[] getPronosticos() {
        return pronosticos;
    }

    public static Fila[] getResultados() {
        return resultados;
    }

    private static void cargarCSV(){
        String rutaResultados = ConfigDB.getRutaResultados();
        String rutaPronosticos = ConfigDB.getRutaPronosticos();

        LectorCSV r = new LectorCSV(rutaResultados, ",");
        r.cargarCSV();
        resultados = new Fila[r.numFilas()-1];
        for (int numFila = 1; numFila < r.numFilas(); numFila++){
            try {
                String[] fila = r.getFila(numFila);
                int fase = Integer.parseInt(fila[ConfigDB.getFase()]);
                int ronda = Integer.parseInt(fila[ConfigDB.getRonda()]);
                String equipo1 = fila[ConfigDB.getEquipo1r()];
                int goles1 = Integer.parseInt(fila[ConfigDB.getGoles1()]);
                String equipo2 = fila[ConfigDB.getEquipo2r()];
                int goles2 = Integer.parseInt(fila[ConfigDB.getGoles2()]);
                resultados[numFila-1] = new Fila(fase, ronda, equipo1, equipo2, goles1, goles2);
            }catch (NumberFormatException e){
                throw new ColumnasInvalidasException("En la base de datos SQL, una de las columnas que deberian ser numeros, no es un numero");
            }
        }
        LectorCSV p = new LectorCSV(rutaPronosticos, ",");
        p.cargarCSV();
        pronosticos = new Fila[p.numFilas()-1];
        for (int numFila = 1; numFila < p.numFilas(); numFila++){
            try {
                String[] fila = p.getFila(numFila);
                int fase = Integer.parseInt(fila[ConfigDB.getFase()]);
                int ronda = Integer.parseInt(fila[ConfigDB.getRonda()]);
                String equipo1 = fila[ConfigDB.getEquipo1p()];
                String persona = fila[ConfigDB.getParticipante()];
                String equipo2 = fila[ConfigDB.getEquipo2p()];
                int resultado = Integer.parseInt(fila[ConfigDB.getResultado()]);
                pronosticos[numFila-1] = new Fila(fase, ronda, persona, equipo1, equipo2, resultado);
            }catch (NumberFormatException e){
                throw new ColumnasInvalidasException("Alguna de las columnas esta mal escrita o tiene letras donde deberia tener numeros!");
            }
        }


    }
    private static void cargarSQL() throws SQLException {
        resultados = LectorSQL.getResultados();
        pronosticos = LectorSQL.getPronosticos();

    }
}

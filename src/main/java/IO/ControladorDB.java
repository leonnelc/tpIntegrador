package IO;

import Excepciones.ColumnasInvalidasException;
import Excepciones.ConfigInvalidaException;

import java.sql.SQLException;

public class ControladorDB {
    // esta clase es usada como una capa de abstraccion para que en el main se traten de igual forma los datos obtenidos,
    // sin importar que tipo de DB se use
    private static Fila[] resultados;
    private static Fila[] pronosticos;
    private ControladorDB(){

    }
    public static void cargar() throws SQLException{
        String tipoDB = ConfigDB.getTipoDB();
        if (tipoDB.equalsIgnoreCase("csv")){ // si el tipoDB es csv,
            // se cargan los datos usando los archivos.csv configurados en configDB.props
            cargarCSV();
        } else if (tipoDB.equalsIgnoreCase("sql")) { // si el tipoDB es sql,
            // se cargan los datos usando la DB sql configurada en configDB.props
            cargarSQL();
        }else{
            throw new ConfigInvalidaException("Tipo de base de datos \""+tipoDB+"\" invalido, deberia ser csv o sql");
        }

    }

    private static void cargarCSV(){
        String rutaResultados = ConfigDB.getRutaResultados();
        String rutaPronosticos = ConfigDB.getRutaPronosticos();

        LectorCSV r = new LectorCSV(rutaResultados);
        // se instancia un nuevo LectorCSV para leer el archivo de resultados
        resultados = new Fila[r.numFilas()-1];
        // se hace un array de Filas con el número de filas - 1 para no incluir los nombres de las columnas
        for (int numFila = 1; numFila < r.numFilas(); numFila++){ // numFila empieza en 1 porque 0 es donde están
                                                                // los nombres de las columnas
            try {
                String[] fila = r.getFila(numFila);
                int fase = Integer.parseInt(fila[ConfigDB.getFase()]);
                int ronda = Integer.parseInt(fila[ConfigDB.getRonda()]);
                String equipo1 = fila[ConfigDB.getEquipo1r()];
                int goles1 = Integer.parseInt(fila[ConfigDB.getGoles1()]);
                String equipo2 = fila[ConfigDB.getEquipo2r()];
                int goles2 = Integer.parseInt(fila[ConfigDB.getGoles2()]);
                // se instancia la Fila y se agrega al indice al que pertenece
                // ( es numFila-1 porque numFila empieza en 1 y los arrays empiezan en 0 )
                resultados[numFila-1] = new Fila(fase, ronda, equipo1, equipo2, goles1, goles2);
            }catch (NumberFormatException e){ // si algunas de las columnas da error al parsear a int, se lanza un error
                throw new ColumnasInvalidasException("En la base de datos CSV una de las columnas que deberia ser un numero, no lo es");
            }
        }
        // se hace lo mismo pero con los pronosticos
        LectorCSV p = new LectorCSV(rutaPronosticos);
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
        // LectorSQL ya devuelve los datos en arrays de Fila y no hace falta hacer nada más
        LectorSQL lectorsql = new LectorSQL();
        resultados = lectorsql.getResultados();
        pronosticos = lectorsql.getPronosticos();
        lectorsql.cerrarConexion();


    }
    public static Fila[] getPronosticos() {
        return pronosticos;
    }

    public static Fila[] getResultados() {
        return resultados;
    }
}

package IO;

import java.sql.*;
import java.util.ArrayList;

public class LectorSQL {

    Connection connection;

    LectorSQL() throws SQLException{
        String url = ConfigDB.getUrl();
        String username = ConfigDB.getUsername();
        String password = ConfigDB.getPassword();
        connection = DriverManager.getConnection(url, username, password);
    }
    public Fila[] getResultados() throws SQLException {

            String sql = "SELECT * FROM resultados";
            Statement statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery(sql);
            ArrayList<Fila> filas = new ArrayList<>();
            while (resultset.next()) {
                int fase = resultset.getInt("Fase");
                int ronda = resultset.getInt("Ronda");
                String equipo1 = resultset.getString("Equipo 1");
                int golesEq1 = resultset.getInt("Cant. goles 1");
                int golesEq2 = resultset.getInt("Cant. goles 2");
                String equipo2 = resultset.getString("Equipo 2");
                filas.add(new Fila(fase, ronda, equipo1, equipo2, golesEq1, golesEq2));
            }
            return filas.toArray(new Fila[0]);

    }
    public Fila[] getPronosticos() throws SQLException{

            String sql = "SELECT * FROM pronosticos";
            Statement statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery(sql);
            ArrayList<Fila> filas = new ArrayList<>();
            while (resultset.next()) { // mientras haya datos, se instancia una nueva Fila y se agrega a la lista de filas
                int fase = resultset.getInt("Fase");
                int ronda = resultset.getInt("Ronda");
                String equipo1 = resultset.getString("Equipo 1");
                String participante = resultset.getString("Participante");
                int resultado = resultset.getInt("Resultado");
                String equipo2 = resultset.getString("Equipo 2");
                filas.add(new Fila(fase, ronda, participante, equipo1, equipo2, resultado));
            }
            return filas.toArray(new Fila[0]);

    }
    public void cerrarConexion() throws SQLException{
        connection.close();
    }
}

package IO;

import java.sql.*;
import java.util.ArrayList;

public class LectorSQL {
    public static Fila[] getResultados() throws SQLException {

            String url = ConfigDB.getUrl();
            String username = ConfigDB.getUsername();
            String password = ConfigDB.getPassword();
            Connection connection = DriverManager.getConnection(url, username, password);

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
            connection.close();
            return filas.toArray(new Fila[0]);

    }
    public static Fila[] getPronosticos() throws SQLException{
            String url = ConfigDB.getUrl();
            String username = ConfigDB.getUsername();
            String password = ConfigDB.getPassword();
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM pronosticos";
            Statement statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery(sql);
            ArrayList<Fila> filas = new ArrayList<>();
            while (resultset.next()) {
                int fase = resultset.getInt("Fase");
                int ronda = resultset.getInt("Ronda");
                String equipo1 = resultset.getString("Equipo 1");
                String participante = resultset.getString("Participante");
                int resultado = resultset.getInt("Resultado");
                String equipo2 = resultset.getString("Equipo 2");
                filas.add(new Fila(fase, ronda, participante, equipo1, equipo2, resultado));
            }
            connection.close();
            return filas.toArray(new Fila[0]);

    }
}

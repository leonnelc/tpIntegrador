import Excepciones.ColumnasInvalidasException;
import Excepciones.ConfigInvalidaException;
import IO.*;

import java.sql.SQLException;

public class tpIntegrador {
    public static void main(String[] args) {
        try {
            String[] argsParseados = ParseadorArgs.parsearArgs(args);
            if (argsParseados.length != 2) {
                throw new ConfigInvalidaException("Argumentos invalidos, uso: tpIntegrador \"ruta/configuracion/basededatos.props\" \"ruta/configuracion/programa.props\"");
            }
            Class.forName("com.mysql.cj.jdbc.Driver"); // esto es para asegurarse que el driver de jdbc está cargado
            String rutaConfigDB = argsParseados[0];
            String rutaConfig = argsParseados[1];
            LectorConfig.cargarConfigDB(rutaConfigDB);
            LectorConfig.cargarConfig(rutaConfig);
            ConfigDB.validar();
            ControladorDB.cargar();


            Fila[] resultados = ControladorDB.getResultados();
            // array de filas de los resultados

            Fila[] pronosticos = ControladorDB.getPronosticos();
            // array de filas de los pronosticos

            for (Fila fila : resultados) {
                // lo que hace este bucle for es instanciar los equipos y los
                // partidos iterando el array de Filas
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila.getEquipo1(), "Seleccion");
                int golesEq1 = fila.getGolesEq1();
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila.getEquipo2(), "Seleccion");
                int golesEq2 = fila.getGolesEq2();
                int fase = fila.getFase();
                int ronda = fila.getRonda();
                if ((golesEq1 < 0) || (golesEq2 < 0)) {
                    // valida que los goles no sean negativos
                    throw new ColumnasInvalidasException("Alguna de las columnas de goles tiene un valor menor a 0");
                }
                if ((fase <= 0) || (ronda <= 0)) {
                    // valida que fase y ronda sean mayores a 0
                    throw new ColumnasInvalidasException("Alguna de las columnas de fase o ronda tiene un valor menor o igual a 0");
                }
                Partido.instanciarSiNoExiste(fase, ronda, equipo1, equipo2, golesEq1, golesEq2);
                // se instancia el partido, y al instanciar el partido, también se instancian su Fase y Ronda automaticamente
            }
            for (Fila fila : pronosticos) {
                //
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila.getEquipo1(), "Seleccion");
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila.getEquipo2(), "Seleccion");
                int numFase = fila.getFase();
                int numRonda = fila.getRonda();
                int resultado = fila.getResultado();
                switch (resultado) { // se usa -1 para definirlo como empate porque no puede existir ningun equipo con ese id
                    case 1:
                        resultado = equipo1.getId();
                        break;
                    case 2:
                        resultado = equipo2.getId();
                        break;
                    default:
                        resultado = -1;

                }
                int idPartido = Fase.getFase(numFase).getRonda(numRonda).getPartidoId(equipo1.getId(), equipo2.getId());
                // explicacion de Fase.getFase(numFase).getRonda(numRonda).getPartidoId(equipo1.getId() lo que hace
                // es primero obtener el objeto Fase usando el numFase del pronóstico, después con ese objeto Fase
                // obtener el objeto Ronda usando el numRonda del pronóstico, y con ese objeto Ronda, obtener el ID
                // del partido que tiene como equipo1 y equipo2 los mismos equipo1 y equipo2 del Pronostico


                Pronostico pronostico = new Pronostico(idPartido, resultado);
                Persona.instanciarSiNoExiste(fila.getPersona()).addPronostico(pronostico);

            }
            Persona[] personas = Persona.getPersonas();
            for (int i = 0; i < personas.length;i++ ){ // algoritmo para ordenar los resultados ( muy ineficiente )
                for (int j = 0; j < personas.length; j++){
                    if (personas[i].getPuntos() > personas[j].getPuntos()){
                        Persona temp = personas[j];
                        personas[j] = personas[i];
                        personas[i] = temp;
                    }
                }
            }
            for (Persona persona : personas) {
                System.out.println(persona.getNombre() + ": " + persona.getPuntos());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo encontrar la clase: " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
            System.exit(1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }
}

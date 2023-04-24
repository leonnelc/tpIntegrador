import Excepciones.ColumnasInvalidasException;
import IO.*;

public class Main {
    public static void main(String[] args) {
        String[] argsParseados = ParseadorArgs.parsearArgs(args, " ");
        if (argsParseados.length != 2){
            System.out.println("Argumentos invalidos, uso: tpIntegrador \"ruta/configuracion/basededatos.props\" \"ruta/configuracion/programa.props\"");
            System.exit(1);
        }
        try {
            String rutaConfigDB = argsParseados[0];
            String rutaConfig = argsParseados[1];
            LectorConfig.configDB(rutaConfigDB);
            LectorConfig.config(rutaConfig);
            ConfigDB.validar();
            ControladorDB.cargar();


            Fila[] resultados = ControladorDB.getResultados();
            Fila[] pronosticos = ControladorDB.getPronosticos();

            for (Fila fila : resultados) {
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila.getEquipo1(), "Seleccion");
                int golesEq1 = fila.getGolesEq1();
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila.getEquipo2(), "Seleccion");
                int golesEq2 = fila.getGolesEq2();
                int fase = fila.getFase();
                int ronda = fila.getRonda();
                if ((golesEq1 < 0) || (golesEq2 < 0) || (fase < 0) || (ronda < 0)) {
                    throw new ColumnasInvalidasException("Alguna de las columnas de goles, fase o ronda tiene un valor menor a 0");
                }
                Partido.instanciarSiNoExiste(fase, ronda, equipo1, equipo2, golesEq1, golesEq2);
            }
            for (Fila fila : pronosticos) {
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila.getEquipo1(), "Seleccion");
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila.getEquipo2(), "Seleccion");
                int resultado = fila.getResultado();
                switch (resultado) {
                    case 1:
                        resultado = equipo1.getId();
                        break;
                    case 2:
                        resultado = equipo2.getId();
                        break;
                    default:
                        resultado = -1;

                }
                int fase = fila.getFase();
                int ronda = fila.getRonda();

                Pronostico pronostico = new Pronostico(Fase.getFase(fase).getRonda(ronda).getPartidoId(equipo1.getId(), equipo2.getId()), resultado);
                Persona.instanciarSiNoExiste(fila.getPersona()).addPronostico(pronostico);

            }
            for (Persona persona : Persona.getPersonas()) {
                int idPersona = persona.getId();
                for (Fase fase : Fase.getFases()) {
                    if (fase.getAciertos(idPersona) == fase.getNumPartidos()) {
                        persona.addPuntos(Config.getPuntosPorAciertoFase());
                    }
                    for (Ronda ronda : fase.getRondas()) {
                        if (ronda.getAciertos(idPersona) == ronda.getNumPartidos()) {
                            persona.addPuntos(Config.getPuntosPorAciertoRonda());
                        }
                    }

                }
                System.out.println(persona.getNombre() + ": " + persona.getPuntos());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }
}

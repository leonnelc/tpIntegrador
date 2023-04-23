import IO.*;
public class Main {
    public static void main(String[] args) {
        String[] argsParseados = ParseadorArgs.parsearArgs(args, " ");
        //String rutaResultados = argsParseados[0];
        //String rutaPronosticos = argsParseados[1];
        //
        String rutaResultados = "src/main/resources/resultados.csv";
        String rutaPronosticos = "src/main/resources/pronosticos.csv";
        LectorCSV resultados = new LectorCSV(rutaResultados, ",");
        LectorCSV pronosticos = new LectorCSV(rutaPronosticos, ",");
        // LectorConfig.config();
        // LectorConfig.configDB();
        ConfigDB.validar();
        resultados.cargarCSV();
        pronosticos.cargarCSV();
        // TODO: instanciar Partidos y Personas con sus Pronosticos
        for (int numfila = 1; numfila < resultados.numFilas(); numfila++){
            // empieza con 1 porque la numero 0 son los nombres de las columnas
            try{
                String[] fila = resultados.getFila(numfila);
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila[ConfigDB.getEquipo1r()], "Seleccion");
                int golesEq1 = Integer.parseInt(fila[ConfigDB.getGoles1()]);
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila[ConfigDB.getEquipo2r()], "Seleccion");
                int golesEq2 = Integer.parseInt(fila[ConfigDB.getGoles2()]);
                int fase = Integer.parseInt(fila[ConfigDB.getFase()]);
                int ronda = Integer.parseInt(fila[ConfigDB.getRonda()]);
                if ((golesEq1 < 0) || (golesEq2 < 0) || (fase < 0) || (ronda < 0)){
                    throw new NumberFormatException();
                }
                Partido.instanciarSiNoExiste(fase, ronda, equipo1, equipo2, golesEq1, golesEq2);
            }catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("Alguna de las columnas de goles, fase o ronda tiene un valor que no es un numero entero mayor o igual a 0");
                System.exit(1);

            }


        }
        for (int numfila = 1; numfila < pronosticos.numFilas(); numfila++){
            try{
                String[] fila = pronosticos.getFila(numfila);
                Equipo equipo1 = Equipo.instanciarSiNoExiste(fila[ConfigDB.getEquipo1p()], "Seleccion");
                Equipo equipo2 = Equipo.instanciarSiNoExiste(fila[ConfigDB.getEquipo2p()], "Seleccion");
                int resultado = Integer.parseInt(fila[ConfigDB.getResultado()]);
                switch (resultado){
                    case 1:
                        resultado = equipo1.getId();
                        break;
                    case 2:
                        resultado = equipo2.getId();
                        break;
                    default:
                        resultado = -1;

                }
                int fase = Integer.parseInt(fila[ConfigDB.getFase()]);
                int ronda = Integer.parseInt(fila[ConfigDB.getRonda()]);


                Pronostico pronostico = new Pronostico(Fase.getFase(fase).getRonda(ronda).getPartidoId(equipo1.getId(), equipo2.getId()), resultado);
                Persona.instanciarSiNoExiste(fila[ConfigDB.getParticipante()]).addPronostico(pronostico);



            }catch (NumberFormatException e){
                System.out.println("Alguna de las columnas de fase, ronda o resultado tiene un valor que no es un numero entero mayor o igual a 0");
            }
        }
        for (Persona persona: Persona.getPersonas()) {
            int idPersona = persona.getId();
            for (Fase fase : Fase.getFases()) {
                if (fase.getAciertos(idPersona) == fase.getNumPartidos()){
                    persona.addPuntos(Config.getPuntosPorAciertoFase());
                }
                for (Ronda ronda : fase.getRondas()) {
                    if (ronda.getAciertos(idPersona) == ronda.getNumPartidos()){
                        persona.addPuntos(Config.getPuntosPorAciertoRonda());
                    }
                }

            }
            System.out.println(persona.getNombre()+": "+persona.getPuntos());
        }

    }
}

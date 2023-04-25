import Excepciones.PartidoInvalidoException;
import Excepciones.PartidoNoExisteException;
import IO.Config;

import java.util.Arrays;
import java.util.HashMap;

public class Ronda {

    private final int numRonda;
    private final int numFase;
    private final HashMap<Integer, Integer> pronosticosAcertados = new HashMap<>(); // <idPersona, numero de pronosticos acertados>
    private final HashMap<Integer, Partido> partidos = new HashMap<>(); // <idPartido, Ppartido>

    private Ronda(int numRonda, int numFase) {
        this.numRonda = numRonda;
        this.numFase = numFase;
        Fase.getFase(numFase).addRonda(this); // al instanciarse, se agrega a sí mismo a la lista de rondas de la fase a la que pertenece

    }

    public void addPartido(Partido partido) throws PartidoInvalidoException {
        if (partidos.size() == Config.getPartidosPorRonda()) {
            if (partidos.containsKey(partido.getId())) {
                return;
            }
            partidos.get(partido.getId());
            System.out.print("Intentando agregar " + partido + " a la ronda con los partidos: ");
            System.out.println(Arrays.toString(partidos.values().toArray(new Partido[0])));
            System.out.println();
            throw new PartidoInvalidoException("Se intento agregar el partido con id " + partido.getId() + " a la ronda numero " + numRonda + ",pero ya tiene el numero maximo de partidos por ronda(" + Config.getPartidosPorRonda() + ")");
        }
        partidos.put(partido.getId(), partido);
    }

    public Partido[] getPartidos() {
        return partidos.values().toArray(new Partido[0]);
    }

    public static Ronda instanciarSiNoExiste(int numRonda, int numFase) {
        Fase fase = Fase.instanciarSiNoExiste(numFase); // instancia la fase numFase si todavia no está instanciada
        Ronda ronda = fase.getRonda(numRonda);
        if (ronda != null) { // si hay una ronda numRonda en la fase numFase, devuelve la ronda
            return ronda;
        }
        // si no se cumple la condicion anterior, se instancia una nueva ronda y se devuelve
        return new Ronda(numRonda, numFase);

    }

    public int getPartidoId(int idEq1, int idEq2) throws PartidoNoExisteException {
        // devuelve el ID de un partido usando el ID de sus equipos como parametro
        for (Partido partido : partidos.values()) {
            if (partido.tieneEquipos(idEq1, idEq2)) {
                return partido.getId();
            }
        }
        throw new PartidoNoExisteException("Pronostico a partido que no existe: el partido con los equipos id "+idEq1 +"("+Equipo.getEquipo(idEq1).getNombre()+ ") y " + idEq2 +"("+Equipo.getEquipo(idEq2).getNombre()+") no existe en la fase " + this.numFase + ",ronda " + this.numRonda);
    }

    public int getNumero() {
        return numRonda;
    }

    public int getNumPartidos() {
        // devuelve el número de partidos en la ronda
        // sirve para calcular si la persona pudo acertar el pronóstico de todos los partidos de esta ronda o fase
        return partidos.size();
    }

    public void addAcierto(int idPersona) {
        if (!pronosticosAcertados.containsKey(idPersona)) {
            pronosticosAcertados.put(idPersona, 0);
        }
        pronosticosAcertados.put(idPersona, pronosticosAcertados.get(idPersona) + 1);
    }

    public int getAciertos(int idPersona) {
        if (pronosticosAcertados.get(idPersona) == null) {
            return 0;
        }
        return pronosticosAcertados.get(idPersona);
    }

}

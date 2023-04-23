import Excepciones.PartidoInvalidoException;
import Excepciones.PartidoNoExisteException;
import IO.Config;

import java.util.Arrays;
import java.util.HashMap;

public class Ronda {

    private final int numRonda;
    private final int numFase;
    private final HashMap<Integer, Integer> pronosticosAcertados = new HashMap<>(); // <idPersona, numero de pronosticos acertados>
    private final HashMap<Integer, Partido> partidos = new HashMap<>(); // <idPartido, partido>

    private Ronda(int numRonda, int numFase) {
        this.numRonda = numRonda;
        this.numFase = numFase;
        Fase.getFase(numFase).addRonda(this);

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
        // instancia una nueva ronda numero n si todavia no existe
        Fase fase = Fase.instanciarSiNoExiste(numFase);
        Ronda ronda = fase.getRonda(numRonda);
        if (ronda == null) {
            return new Ronda(numRonda, numFase);
        }
        return ronda;

    }

    public int getPartidoId(int idEq1, int idEq2) throws PartidoNoExisteException {
        for (Partido partido : partidos.values()) {
            if (partido.tieneEquipos(idEq1, idEq2)) {
                return partido.getId();
            }
        }
        throw new PartidoNoExisteException("El partido con los equipos id " + idEq1 + " y " + idEq2 + " no existe en la fase " + this.numFase + ",ronda " + this.numRonda);
    }

    public int getNumero() {
        return numRonda;
    }

    public int getNumPartidos() {
        // devuelve el numero de partidos en la ronda
        // sirve para calcular si la persona pudo acertar el pronostico de todos los partidos de esta ronda
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

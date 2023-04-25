import Excepciones.RondaInvalidaException;
import IO.Config;

import java.util.HashMap;

public class Fase {
    private static final HashMap<Integer, Fase> fases = new HashMap<>(); // mapeo de numFase: Fase
    private final HashMap<Integer, Ronda> rondas = new HashMap<>(); // mapeo de numRonda: Ronda
    private final HashMap<Integer, Integer> pronosticosAcertados = new HashMap<>(); // mapeo de idPersona: numPronosticosAcertados
    private final int numero; // numero de fase

    private Fase(int numero) {
        this.numero = numero;
        fases.put(numero, this); // al instanciarse, se agrega a si mismo al mapa de fases
    }

    public static Fase getFase(int numFase) {
        return fases.get(numFase);
    }

    public Ronda getRonda(int numRonda) {
        return rondas.get(numRonda);
    }

    public void addRonda(Ronda ronda) throws RondaInvalidaException {
        if (rondas.size() == Config.getRondasPorFase()) {
            throw new RondaInvalidaException("Se intento agregar la ronda numero " + ronda.getNumero() + " a la fase numero " + numero + ",pero ya tiene el numero maximo de rondas por fase(" + Config.getRondasPorFase() + ")");
        }
        rondas.put(ronda.getNumero(), ronda);
    }

    public static Fase instanciarSiNoExiste(int numFase) {
        // instancia una nueva fase si todavia no existe
        Fase fase = fases.get(numFase);
        if (fase == null) {
            return new Fase(numFase);
        }
        return fase;
    }

    public Ronda[] getRondas() {
        return rondas.values().toArray(new Ronda[0]);
    }

    public int getAciertos(int idPersona) {
        if (pronosticosAcertados.get(idPersona) == null){
            return 0;
        }
        // devuelve los aciertos de la persona con el ID idPersona en esta fase
        return pronosticosAcertados.get(idPersona);
    }

    public void addAcierto(int idPersona) {
        if (!pronosticosAcertados.containsKey(idPersona)) {
            pronosticosAcertados.put(idPersona, 0);
        }
        // agrega un acierto en esta fase a la persona con el ID idPersona
        pronosticosAcertados.put(idPersona, pronosticosAcertados.get(idPersona) + 1);
    }

    public int getNumPartidos() {
        int numPartidos = 0;
        for (Ronda ronda : getRondas()) { // itera sobre cada ronda de la fase para calcular cuantos partidos hay en total
            numPartidos += ronda.getNumPartidos();
        }
        return numPartidos;
    }
}

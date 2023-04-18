import Excepciones.PartidoInvalidoException;
import IO.Config;

import java.util.ArrayList;

public class Ronda {

    private static ArrayList<Ronda> rondas;
    private int numero;
    private ArrayList<Partido> partidos;
    public Ronda(int numRonda, int numFase){
        rondas.add(numRonda,this);

    }
    public void addPartido(Partido partido) throws PartidoInvalidoException{
        if (partidos.size() == Config.getPartidosPorRonda()){
            throw new PartidoInvalidoException("Se intento agregar el partido con id "+partido.getId()+" a la ronda numero "+numero+ ",pero ya tiene el numero maximo de partidos por ronda("+Config.getPartidosPorRonda()+")");
        }
        partidos.add(partido);
}
    public Partido[] getPartidos() {
        return partidos.toArray(new Partido[0]);
    }
    public static void instanciarSiNoExiste(int numRonda, int numFase){
        // instancia una nueva ronda numero n si todavia no existe
        if (rondas.get(numRonda) == null){
            new Ronda(numRonda, numFase);
        }

    }
    public static Ronda getRonda(int numRonda){
        return rondas.get(numRonda);
    }

    public int getNumero() {
        return numero;
    }

    public int getNumPartidos(){
        // devuelve el numero de partidos en la ronda
        // sirve para calcular si la persona pudo acertar el pronostico de todos los partidos de esta ronda
        return partidos.size();
    }

}

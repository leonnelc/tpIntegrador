import Excepciones.RondaInvalidaException;
import IO.Config;

import java.util.ArrayList;

public class Fase {
    private static ArrayList<Fase> fases = new ArrayList<>();
    private ArrayList<Ronda> rondas = new ArrayList<>();
    private int numero;
    public Fase(int numero){
        this.numero = numero;
    }
    public void addRonda(Ronda ronda) throws RondaInvalidaException{
        if (rondas.size() == Config.getRondasPorFase()){
            throw new RondaInvalidaException("Se intento agregar la ronda numero "+ronda.getNumero()+" a la fase numero "+numero+ ",pero ya tiene el numero maximo de rondas por fase("+Config.getRondasPorFase()+")");
        }
        rondas.add(ronda);
    }
    public int getNumRondas(){
        // se usa para despues saber si la persona pudo acertar los resultados de todas las rondas de esta fase
        return rondas.size();
    }
    public static void instanciarSiNoExiste(int numFase) {
        // instancia una nueva fase si todavia no existe
        if (fases.get(numFase) == null) {
            new Fase(numFase);
        }
    }
}

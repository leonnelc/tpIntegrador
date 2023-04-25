public class Pronostico {
    private final int idPartido;
    private final boolean acertado;
    private final int numFase;
    private final int numRonda;
    Pronostico(int idPartido,int idEquipoGanador){
        this.idPartido = idPartido;
        Partido partido = Partido.getPartido(idPartido);
        this.numFase = partido.getNumFase();
        this.numRonda = partido.getNumRonda();
        this.acertado = (partido.getGanadorId() == idEquipoGanador);
    }
    public boolean fueAcertado(){
        return this.acertado;
    }
    public int getIdPartido() {
        return idPartido;
    }
    public int getNumFase(){
        return numFase;
    }
    public int getNumRonda(){
        return numRonda;
    }
}

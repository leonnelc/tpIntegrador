import java.util.ArrayList;

public class Partido {
    private static int ultimoId = 0; // se usa para otorgarle ids unicos a los partidos en su instanciacion
    private static final ArrayList<Partido> partidos = new ArrayList<>();
    // lista de instancias de Partido, indexados acorde a su id por lo cual partidos.get(X) devuelve el partido con ID X
    private final int id;
    private final int numRonda; // numero de ronda en la que se da el partido
    private final int numFase; // numero de fase en la que se da el partido
    private final Equipo equipo1;
    private final Equipo equipo2;
    private final int golesEq1;
    private final int golesEq2;
    public Partido(int fase, int ronda, Equipo equipo1, Equipo equipo2, int golesEq1, int golesEq2){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
        this.numRonda = ronda;
        this.numFase = fase;
        this.id = ultimoId;
        ultimoId++;
        partidos.add(this); // se agrega a sí mismo a la lista de partidos
        Fase.getFase(fase).getRonda(ronda).addPartido(this); // se agrega a sí mismo a la ronda de la fase a la que pertenece el partido

    }

    public int getGanadorId(){
        // devuelve el ID del equipo ganador, si es empate devuelve -1 ya que no puede existir un equipo con ese ID
        if (golesEq1 > golesEq2){
            return equipo1.getId();
        } else if (golesEq2 > golesEq1) {
            return equipo2.getId();
        }else{
            return -1;
        }
    }

    public int getId() {
        return id;
    }
    public static Partido getPartido(int id){
        return partidos.get(id);
    }

    public boolean tieneEquipos(int idEquipo1, int idEquipo2){
        return ((equipo1.getId() == idEquipo1) && (equipo2.getId() == idEquipo2));
    }

    public int getNumFase() {
        return numFase;
    }
    public int getNumRonda(){
        return numRonda;
    }
    public static void instanciarSiNoExiste(int fase, int ronda, Equipo equipo1, Equipo equipo2, int golesEq1, int golesEq2){
        Ronda.instanciarSiNoExiste(ronda, fase);
        for (Partido partido : Fase.getFase(fase).getRonda(ronda).getPartidos()){
            // itera sobre cada partido en la lista de partidos de la ronda para determinar si ya existe el mismo partido
            if (partido.tieneEquipos(equipo1.getId(), equipo2.getId())){ // si tiene los mismos id de equipo1 y equipo2 es el mismo partido
                // por lo cual no hace falta instanciar, y se termina el metodo.
                return;

            }
        }
        // si no se cumple la anterior condicion, se instancia un nuevo partido
        new Partido(fase, ronda, equipo1, equipo2, golesEq1, golesEq2);
    }
    @Override
    public String toString(){
        return this.equipo1.getNombre()+"-"+this.equipo1.getNombre();
    }
}

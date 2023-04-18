import java.util.ArrayList;

public class Partido {
    private static int ultimoId = 0; // se usa para otorgarle ids unicos a los partidos en su instanciacion
    private static ArrayList<Partido> partidos = new ArrayList<>();
    private final int id;
    private final int ronda;
    private final int fase;
    private final Equipo equipo1;
    private final Equipo equipo2;
    private final int golesEq1;
    private final int golesEq2;
    public Partido(int fase, int ronda, Equipo equipo1, Equipo equipo2, int golesEq1, int golesEq2){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
        this.ronda = ronda;
        this.fase = fase;
        this.id = ultimoId;
        ultimoId++; //
        partidos.add(this);
        Ronda.instanciarSiNoExiste(ronda, fase);
        Ronda.getRonda(ronda).addPartido(this);

    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getGanadorId(){
        if (golesEq1 > golesEq2){
            return equipo1.getId();
        } else if (golesEq2 > golesEq1) {
            return equipo1.getId();
        }else{
            return -1;
        }
    }

    public int getId() {
        return id;
    }

    public boolean tieneEquipos(int idEquipo1, int idEquipo2){
        if ( (equipo1.getId() == idEquipo1 && equipo2.getId() == idEquipo2)
                || (equipo1.getId() == idEquipo2 && equipo2.getId() == idEquipo1) ){
            return true;
        }
        return false;
    }
}

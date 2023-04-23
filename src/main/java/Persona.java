import IO.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class Persona {
    private static int ultimoId = 0;
    private static final ArrayList<Persona> personas = new ArrayList<>();
    private static final HashMap<String, Integer> mapaMombreId = new HashMap<>();//
    private final HashMap<Integer, Pronostico> pronosticos = new HashMap<>(); // <idPartido, Pronostico>
    private HashMap<Integer, Integer> aciertosPorFase = new HashMap<>(); // <numfase, aciertos>
    private HashMap<Integer, Integer> aciertosPorRonda = new HashMap<>(); // <numronda, aciertos>
    private int puntos;
    private final int id;
    private String nombre;

    private Persona(String nombre) {
        this.puntos = 0;
        this.nombre = nombre;
        this.id = ultimoId;
        ultimoId++; // esto hace que la proxima instancia de Equipo aumente su id
        personas.add(this);
        mapaMombreId.put(nombre, id);

    }

    public static Persona instanciarSiNoExiste(String nombre) {
        // si no existe una persona con ese nombre se instancia una, sino se devuelve la existente
        if (mapaMombreId.containsKey(nombre)) {
            return personas.get(mapaMombreId.get(nombre));
        }
        return new Persona(nombre);
    }
    public void addPronostico(Pronostico pronostico){
        if (pronosticos.get(pronostico.getIdPartido()) != null){
            return;
        }
        pronosticos.put(pronostico.getIdPartido(),pronostico);
        Fase fase = Fase.getFase(pronostico.getNumFase());
        Ronda ronda = fase.getRonda(pronostico.getNumRonda());
        if (pronostico.fueAcertado()){
            this.puntos += Config.getPuntosPorAcierto();
            fase.addAcierto(this.id);
            ronda.addAcierto(this.id);
        }
    }
    public static Persona getPersona(int id){
        return personas.get(id);
    }
    public void addPuntos(int puntos){
        this.puntos += puntos;
    }

    public Pronostico[] getPronosticos() {
        return pronosticos.values().toArray(new Pronostico[0]);
    }

    public static ArrayList<Persona> getPersonas() {
        return personas;
    }

    public int getId() {
        return id;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }
}

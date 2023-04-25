import IO.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class Persona {
    private static int ultimoId = 0; // esto se usa para que cada persona
    // tenga un id unico determinado por el orden de instanciacion
    private static final ArrayList<Persona> personas = new ArrayList<>();
    // lista de instancias de Persona, indexadas acorde a su id por lo cual personas.get(X) devuelve la persona con id X
    private static final HashMap<String, Integer> mapaMombreId = new HashMap<>();// <nombre persona, id persona>
    private final HashMap<Integer, Pronostico> pronosticos = new HashMap<>(); // <idPartido, Pronostico>
    private int puntos; // puntos de la persona
    private final int id; // id de la persona
    private final String nombre; // nombre de la persona

    private Persona(String nombre) { // el constructor es privado para que
        // solo se pueda instanciar usando el metodo estatico y prevenir
        // algunos errores

        // asigno las variables de la instancia
        this.puntos = 0;
        this.nombre = nombre;
        this.id = ultimoId;
        ultimoId++; // esto hace que la proxima instancia de Persona aumente su id, por lo cual dos personas nunca van a tener el mismo id
        personas.add(this); // la nueva instancia se agrega a la lista de instancias
        mapaMombreId.put(nombre, id); // agrego el mapeo del nombre de la persona a su id

    }

    public static Persona instanciarSiNoExiste(String nombre) {
        // que hace el metodo? instancia una persona si no existe, en caso de que exista simplemente se devuelve su referencia
        if (mapaMombreId.containsKey(nombre)) { // si el mapa tiene un id mapeado a ese nombre (es decir, ya existe la Persona)
            return personas.get(mapaMombreId.get(nombre));  // se devuelve la referencia a la Persona ya instanciada
        }
        // en caso de que no se cumpla la condicion anterior...
        return new Persona(nombre); // se instancia una nueva Persona con ese nombre y se devuelve su referencia
    }
    public void addPronostico(Pronostico pronostico){
        if (pronosticos.containsKey(pronostico.getIdPartido())){
            // si la persona ya tiene un pronostico del mismo partido,
            // termina el metodo sin hacer nada ( esto evita problemas por
            // pronosticos duplicados )
            return;
        }
        /* lo siguiente solo se ejecuta si la persona no tiene un pronostico
        con el mismo partido del pronostico que se quiere agregar
         */

        // agrega un nuevo mapeo del id del partido con la referencia al Pronostico
        pronosticos.put(pronostico.getIdPartido(),pronostico);

        Fase fase = Fase.getFase(pronostico.getNumFase());// obtengo el
        // objeto Fase usando el numero de fase del pronostico, y
        // declaro fase como variable para poder usarla varias veces sin tener
        // que llamar de nuevo el metodo

        Ronda ronda = fase.getRonda(pronostico.getNumRonda());// lo mismo
        // que arriba pero con Ronda

        if (pronostico.fueAcertado()){ // si el pronostico que se esta
            // agregando es un acierto

            this.puntos += Config.getPuntosPorAcierto(); // se agregan los
            // puntos por acertar

            fase.addAcierto(this.id); // se agrega un acierto al contador de
            // aciertos de la fase ( cada Fase y Ronda tienen una cuenta de
            // aciertos privada que es un mapa con <idPersona, numeroAciertos>

            ronda.addAcierto(this.id); // lo mismo que arriba pero para la ronda

            if (fase.getAciertos(this.id) == fase.getNumPartidos()){ // si la
                // persona tiene la misma cantidad de pronosticos acertados
                // que la cantidad de partidos en la fase ( es decir, acerto
                // todos los partidos en la fase )

                addPuntos(Config.getPuntosPorAciertoFase()); // se agregan
                // los puntos por acertar toda la fase
            }
            if (ronda.getAciertos(this.id) == ronda.getNumPartidos()){ // lo
                // mismo que arriba pero con la ronda
                addPuntos(Config.getPuntosPorAciertoRonda()); // se agregan
                // los puntos por acertar toda la ronda
            }
        }
    }
    public void addPuntos(int puntos){
        // suma puntos a la persona ( o resta si puntos es negativo)
        this.puntos += puntos;
    }

    public static Persona[] getPersonas() {
        // devuelve un array con todas las instancias de Persona en el programa
        return personas.toArray(new Persona[0]);
    }
    public int getPuntos() {
        // devuelve los puntos de la persona
        return puntos;
    }

    public String getNombre() {
        // devuelve el nombre de la persona
        return nombre;
    }
}

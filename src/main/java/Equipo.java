import java.util.ArrayList;
import java.util.HashMap;

public class Equipo {
    private static int ultimoId = 0; // se usa para darle un id unico a cada equipo en su instanciacion
    final private static ArrayList<Equipo> equipos = new ArrayList<>(); // lista de instancias de Equipo, indexadas
    // acorde a su id por lo que equiposIndexados.get(X) devuelve el equipo con ID X
    final private static HashMap<String, Integer> mapaNombresIds = new HashMap<>(); // mapa de nombres de equipos asociados a sus ids
    private final int id;
    private final String nombre;
    private String descripcion;
    Equipo(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        // la parte mas importante del programa
        if (nombre.equalsIgnoreCase("Argentina")){
            this.descripcion = "Campeones del mundo";
        } else if (nombre.equalsIgnoreCase("Francia")) {
            this.descripcion = "Segundo";
        }
        this.id = ultimoId;
        ultimoId++; // esto hace que la proxima instancia de Equipo aumente su id, evitando que dos equipos tengan el mismo id
        equipos.add(this);
        mapaNombresIds.put(nombre, id);

    }


    public int getId() {
        return id;
    }
    public static Equipo instanciarSiNoExiste(String nombre, String descripcion){
        if (mapaNombresIds.containsKey(nombre)){ // si ya existe un mapeo de su nombre en el mapa de <nombres, ids>
            return getEquipo(mapaNombresIds.get(nombre)); // devuelve el equipo ya existente
        }
        // si no se cumple lo anterior significa que no existe el equipo, entonces se instancia un nuevo equipo y se devuelve
        return new Equipo(nombre, descripcion);

    }
    public static Equipo getEquipo(int id){
        return equipos.get(id);
    }

    public String getNombre() {
        return nombre;
    }
}

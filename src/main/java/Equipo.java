import java.util.ArrayList;
import java.util.HashMap;

public class Equipo {
    private static int ultimoId = 0; // se usa para darle un id unico a cada equipo en su instanciacion
    private static ArrayList<Equipo> equiposIndexados = new ArrayList<>();
    private static HashMap<String, Integer> mapaNombresIds = new HashMap<>();
    private int id;
    private String nombre;
    private String descripcion;
    Equipo(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = ultimoId;
        ultimoId++; // esto hace que la proxima instancia de Equipo aumente su id
        equiposIndexados.add(this);
        mapaNombresIds.put(nombre, id);

    }


    public int getId() {
        return id;
    }
    public static Equipo instanciarSiNoExiste(String nombre, String descripcion){
        if (mapaNombresIds.containsKey(nombre)){
            return getEquipo(mapaNombresIds.get(nombre));
        }
        return new Equipo(nombre, descripcion);

    }
    public static int getEquipoId(String equipo){
        return mapaNombresIds.get(equipo);
    }
    public static Equipo getEquipo(int id){
        return equiposIndexados.get(id);
    }
}

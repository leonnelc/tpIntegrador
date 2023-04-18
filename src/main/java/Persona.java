import java.util.ArrayList;
import java.util.HashMap;

public class Persona {
    private static int ultimoId = 0;
    private static final ArrayList<Persona> personas = new ArrayList<>();
    private static final HashMap<Persona, Integer> mapaPersonaId = new HashMap<>();//
    private static final ArrayList<Pronostico> pronosticos = new ArrayList<>();
    private final int id;
    private String nombre;
    private Persona(String nombre){
        //


    }
    public void instanciar(String nombre){
        if (){// si no existe una persona con ese nombre se instancia una, sino se devuelve la existente
    }
}

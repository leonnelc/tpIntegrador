package IO;

import java.util.ArrayList;
import java.util.List;

public class ParseadorArgs {
    public static String[] parsearArgs(String[] args) {
        // lo que hace este metodo es convertir los args de un main separados
        // por espacios en argumentos que tambien estan separados por
        // espacios pero que al poner algo entre comillas "", ignore los
        // espacios y de esa forma poder usar rutas de archivos que contengan
        // espacios como argumentos en el main
        // por ejemplo al poner "c:/users/pepito/tp integrador/pronosticos.csv"
        // sin usar este metodo, quedaria el array de Strings
        // ["c:/users/pepito/tp", "integrador/pronosticos.csv"] , y usando
        // este metodo quedaria
        // ["c:/users/pepito/tp integrador/pronosticos.csv"]

        String string = String.join(" ", args); // convierto el array de
        // argumentos en un solo string
        List<String> partes = new ArrayList<>();
        boolean escapado = false; // escapado significa que el caracter esta
        // entre comillas dobles
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i); // obtengo el caracter en el indice i
            // del string

            if (!escapado && (c == ' ' || c == '\t')) {    // si es un espacio
                // y no esta escapado, se agrega el stringbuilder a la lista
                partes.add(stringBuilder.toString());
                stringBuilder = new StringBuilder(); // se reinicia el
                // stringbuilder para seguir usandolo

            } else if (c == '"') {     // si el caracter es " entonces cambia el
                escapado = !escapado;  // estado de escape
            }

            else {  // si no se cumplen las anteriores condiciones, entonces se
                // agrega el caracter al stringbuilder
                stringBuilder.append(c);
            }
        }
        partes.add(stringBuilder.toString());    // agrega la ultima parte
        // del stringbuilder a la lista de partes
        return partes.toArray(new String[0]);
    }
}

package IO;

import java.util.ArrayList;
import java.util.List;

public class ParseadorArgs {
    public static String[] parsearArgs(String[] args) {
        /* lo que hace este metodo es convertir los args del main de forma que se puedan poner espacios entre las comillas
        por ejemplo al usar "c:/users/pepito/tp integrador/pronosticos.csv" como argumento en el main
        sin usar este metodo, quedaria el array de Strings ["c:/users/pepito/tp, integrador/pronosticos.csv"]
        y usando este metodo quedaria el array de Strings [c:/users/pepito/tp integrador/pronosticos.csv]
         */

        String string = String.join(" ", args); // convierto el array de argumentos en un solo string
        List<String> partes = new ArrayList<>();
        boolean escapado = false; // escapado significa que el caracter está entre comillas dobles
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i); // obtengo el caracter en el indice i del string

            if (!escapado && (c == ' ' || c == '\t')) {// si es un espacio y no está escapado, se agrega el stringbuilder a la lista
                partes.add(stringBuilder.toString());
                stringBuilder = new StringBuilder(); // se reinicia el stringbuilder para seguir usandolo

            } else if (c == '"') {     // si el caracter es comillas dobles, cambia el estado de escapado
                escapado = !escapado;
            }

            else {  // si no se cumplen las anteriores condiciones, entonces se agrega el caracter al stringbuilder
                stringBuilder.append(c);
            }
        }
        partes.add(stringBuilder.toString());    // agrega la ultima parte del stringbuilder a la lista de partes
        return partes.toArray(new String[0]);
    }
}

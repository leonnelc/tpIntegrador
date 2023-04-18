package IO;

import java.util.ArrayList;
import java.util.List;

public class ParseadorArgs {
    public static String[] parsearArgs(String[] args, String separador){
        String str = String.join(" ", args);
        List<String> parts = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean entreComillas = false;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '\"') {
                    entreComillas = !entreComillas;
                }

                if (!entreComillas && str.startsWith(separador, i)) {
                    parts.add(sb.toString().trim());
                    sb.setLength(0);
                    i += separador.length() - 1;
                } else {
                    sb.append(c);
                }
            }

            parts.add(sb.toString().trim());

            return parts.toArray(new String[0]);
        }

    }

package IO;

import Excepciones.ColumnasInvalidasException;
import Excepciones.ConfigInvalidaException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorCSV {
    // no tenía ganas de aprenderme una libreria como opencsv, asi que hice mi propia implementacion de un lector csv
        private final String archivo;
    private final ArrayList<String[]> filas = new ArrayList<>();


        public LectorCSV(String archivo) {
            this.archivo = archivo;
            cargarCSV();
        }

        private void cargarCSV() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.archivo));
                int nroLinea = 0;
                int numColumnas = 6;
                String linea = br.readLine();

                while (linea != null) { // mientras que haya una línea se van a ejecutar las siguientes instrucciones

                    String[] columnas = linea.split(","); // hago un array de valores separados por coma
                    for (int i = 0; i < columnas.length; i++) {

                        if (columnas.length != numColumnas){ // valido que el número de columnas en cada fila sea el mismo
                            throw new ColumnasInvalidasException("El archivo \""+archivo+"\" tiene "+columnas.length+" columnas en la fila numero "+(nroLinea+1)+", cuando deberia tener "+ numColumnas);
                        }
                        columnas[i] = columnas[i].strip(); // le saco los espacios a la izquierda y a la derecha para prevenir errores

                    }

                    this.filas.add(nroLinea, columnas); // agrego la fila a la lista de filas
                    linea = br.readLine(); // avanzo de linea
                    nroLinea++; // aumento numero de linea

                }
                br.close(); // cierro el stream

            } catch (IOException e) {
                throw new ConfigInvalidaException("El archivo \""+archivo+"\" no existe o no se pudo acceder");
            }

        }

    public String[] getFila(int indice){
            return filas.get(indice);
    }
    public int numFilas(){
            return filas.size();
    }
}

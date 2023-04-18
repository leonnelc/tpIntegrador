package IO;

import Excepciones.ColumnasInvalidasException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorCSV {
        private final String separador;
        private final String archivo;
        private final String[] columnas = new String[6];
        private final ArrayList<String[]> filas = new ArrayList<>();


        public LectorCSV(String archivo, String separador) {
            this.archivo = archivo;
            this.separador = separador;
        }

        public void cargarCSV() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.archivo));
                int nroLinea = 0;
                String linea = br.readLine();
                while (linea != null) {
                    for (int i = 0; i < columnas.length; i++) {
                        String[] columnas = linea.split(this.separador);
                        if (columnas.length != this.columnas.length){
                            throw new ColumnasInvalidasException("El archivo \""+archivo+"\" tiene "+columnas.length+" columnas en la fila numero "+(nroLinea+1)+", cuando deberia tener "+this.columnas.length);
                        }
                        this.filas.add(nroLinea, new String[this.columnas.length]);
                        this.filas.get(nroLinea)[i] = columnas[i].strip(); // le saco los espacios a los lados para prevenir errores
                        if (nroLinea == 0){
                            this.columnas[i] = filas.get(0)[i];
                        }
                    }
                    linea = br.readLine(); // avanzo de linea
                    nroLinea++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    public ArrayList<String[]> getFilas() {
        return filas;
    }
    public String[] getFila(int indice){
            return filas.get(indice);
    }
}

package IO;

import Excepciones.ColumnasInvalidasException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorCSV {
        private final String separador;
        private final String archivo;
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

                    String[] columnas = linea.split(this.separador);
                    for (int i = 0; i < columnas.length; i++) {
                        int numColumnas = 6;
                        if (columnas.length != numColumnas){
                            throw new ColumnasInvalidasException("El archivo \""+archivo+"\" tiene "+columnas.length+" columnas en la fila numero "+(nroLinea+1)+", cuando deberia tener "+ numColumnas);
                        }
                        columnas[i] = columnas[i].strip(); // le saco los espacios para prevenir errores

                    }
                    this.filas.add(nroLinea, columnas);
                    //System.out.println(Arrays.toString(columnas));
                    //System.out.println(Arrays.toString(this.filas.get(nroLinea)));
                    linea = br.readLine(); // avanzo de linea
                    nroLinea++;

                }
                br.close();

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
    public int numFilas(){
            return filas.size();
    }
}

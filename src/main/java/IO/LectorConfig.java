package IO;

import Excepciones.ConfigInvalidaException;
import IO.Config;
import IO.ConfigDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class LectorConfig {
    // carga la configuracion del programa
    private static void validarArchivo(File archivo) throws ConfigInvalidaException{
        if (!archivo.exists()){
            throw new ConfigInvalidaException("El archivo de configuracion \""+archivo.getPath()+"\" no existe!");
        }
        if (!archivo.isFile()){
            throw new ConfigInvalidaException("\""+archivo.toString()+"\" no es un archivo!");
        }
    }
    public static Config config(String archivo) {
        File archivoJSON = new File(archivo);
        ObjectMapper mapeador = new ObjectMapper();
        Config config = null;
        validarArchivo(archivoJSON);
        // TODO: agregar excepciones con mensajes especificos como archivo invalido, o no se pudo leer el archivo
        try {
            config = mapeador.readValue(archivoJSON, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;

    }
    // carga la configuracion de la base de datos
    public static ConfigDB configDB(String archivo){
        File archivoJSON = new File(archivo);
        ObjectMapper mapeador = new ObjectMapper();
        ConfigDB config = null;
        validarArchivo(archivoJSON);

        try {
            config = mapeador.readValue(archivoJSON, ConfigDB.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
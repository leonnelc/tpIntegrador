package IO;

import Excepciones.ConfigInvalidaException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LectorConfig {
    // TODO: prevenir que config y configDB den valores null a las configuraciones y lanzar un error si es asi

    private static void validarArchivo(String ruta) throws ConfigInvalidaException{
        File archivo = new File(ruta);
        if (!archivo.exists()){
            throw new ConfigInvalidaException("El archivo de configuracion \""+archivo.getPath()+"\" no existe!");
        }
        if (!archivo.isFile()){
            throw new ConfigInvalidaException("\""+archivo+"\" no es un archivo!");
        }
    }
    public static void config(String archivo) {
        validarArchivo(archivo);
        // carga la configuracion del programa
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(archivo)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            if ((props.getProperty("puntosPorAcierto") == null) ||
                    (props.getProperty("puntosPorAciertoRonda") == null) ||
                    (props.getProperty("puntosPorAciertoFase") == null) ||
                    (props.getProperty("partidosPorRonda") == null) ||
                    (props.getProperty("rondasPorFase") == null))
            {
                throw new ConfigInvalidaException("Falta la configuracion de un campo en el archivo "+ archivo);
            }
            Config.setPuntosPorAcierto(Integer.parseInt(props.getProperty("puntosPorAcierto")));
            Config.setPuntosPorAciertoRonda(Integer.parseInt(props.getProperty("puntosPorAciertoRonda")));
            Config.setPuntosPorAciertoFase(Integer.parseInt(props.getProperty("puntosPorAciertoFase")));
            Config.setPartidosPorRonda(Integer.parseInt(props.getProperty("partidosPorRonda")));
            Config.setRondasPorFase(Integer.parseInt(props.getProperty("rondasPorFase")));


        }catch (NumberFormatException e){
            throw new ConfigInvalidaException("Uno de los campos en \""+archivo+"\" no es un numero entero");
        }

    }

    public static void configDB(String archivo){
        validarArchivo(archivo);
        // carga la configuracion de la base de datos

                Properties props = new Properties();
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    props.load(fis);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                try {
                    if ((props.getProperty("tipoDB") == null) ||
                            (props.getProperty("url") == null) ||
                            (props.getProperty("username") == null) ||
                            (props.getProperty("password") == null) ||
                            (props.getProperty("rutaResultados") == null) ||
                            (props.getProperty("rutaPronosticos") == null) ||
                            (props.getProperty("fase") == null) ||
                            (props.getProperty("ronda") == null) ||
                            (props.getProperty("equipo1r") == null) ||
                            (props.getProperty("equipo2r") == null) ||
                            (props.getProperty("goles1") == null) ||
                            (props.getProperty("goles2") == null) ||
                            (props.getProperty("participante") == null) ||
                            (props.getProperty("resultado") == null) ||
                            (props.getProperty("equipo1p") == null) ||
                            (props.getProperty("equipo2p") == null)){
                        throw new ConfigInvalidaException("Falta la configuracion de un campo en el archivo "+ archivo);
                    }
                    ConfigDB.setTipoDB(props.getProperty("tipoDB"));
                    ConfigDB.setUrl(props.getProperty("url"));
                    ConfigDB.setUsername(props.getProperty("username"));
                    ConfigDB.setPassword(props.getProperty("password"));
                    ConfigDB.setRutaResultados(props.getProperty("rutaResultados"));
                    ConfigDB.setRutaPronosticos(props.getProperty("rutaPronosticos"));
                    ConfigDB.setFase(Integer.parseInt(props.getProperty("fase")));
                    ConfigDB.setRonda(Integer.parseInt(props.getProperty("ronda")));
                    ConfigDB.setEquipo1r(Integer.parseInt(props.getProperty("equipo1r")));
                    ConfigDB.setEquipo2r(Integer.parseInt(props.getProperty("equipo2r")));
                    ConfigDB.setGoles1(Integer.parseInt(props.getProperty("goles1")));
                    ConfigDB.setGoles2(Integer.parseInt(props.getProperty("goles2")));
                    ConfigDB.setParticipante(Integer.parseInt(props.getProperty("participante")));
                    ConfigDB.setResultado(Integer.parseInt(props.getProperty("resultado")));
                    ConfigDB.setEquipo1p(Integer.parseInt(props.getProperty("equipo1p")));
                    ConfigDB.setEquipo2p(Integer.parseInt(props.getProperty("equipo2p")));
                }catch (NumberFormatException e){
                    throw new ConfigInvalidaException("Configuracion invalida, alguno de los campos que deberian ser numeros enteros no lo son.");
            }
        }

    }
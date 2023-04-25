package IO;

import Excepciones.ConfigInvalidaException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LectorConfig {
    public static void cargarConfig(String archivo) {
        // carga la configuracion del programa
        Properties props = new Properties();
        try (FileInputStream stream = new FileInputStream(archivo)) {
            props.load(stream);
        } catch (IOException e) {
            throw new ConfigInvalidaException("El archivo \""+archivo+"\" no existe o no se pudo leer");
        }
        try {
            if ((props.getProperty("puntosPorAcierto") == null) ||
                    (props.getProperty("puntosPorAciertoRonda") == null) ||
                    (props.getProperty("puntosPorAciertoFase") == null) ||
                    (props.getProperty("partidosPorRonda") == null) ||
                    (props.getProperty("rondasPorFase") == null))
            {
                // lo que hace esta parte del codigo es validar que ninguno de los campos este sin declarar y si ocurre,
                // lanzar un error
                throw new ConfigInvalidaException("Falta la configuracion de un campo en el archivo de configuracion del programa \""+archivo+"\"");
            }
            // esta porcion de codigo setea las variables estaticas de la clase Config
            Config.setPuntosPorAcierto(Integer.parseInt(props.getProperty("puntosPorAcierto")));
            Config.setPuntosPorAciertoRonda(Integer.parseInt(props.getProperty("puntosPorAciertoRonda")));
            Config.setPuntosPorAciertoFase(Integer.parseInt(props.getProperty("puntosPorAciertoFase")));
            Config.setPartidosPorRonda(Integer.parseInt(props.getProperty("partidosPorRonda")));
            Config.setRondasPorFase(Integer.parseInt(props.getProperty("rondasPorFase")));


        }catch (NumberFormatException e){
            throw new ConfigInvalidaException("Uno de los campos en \""+archivo+"\" no es un numero entero");
        }

    }

    public static void cargarConfigDB(String archivo){
        // carga la configuracion de la base de datos
                Properties props = new Properties();
                try (FileInputStream stream = new FileInputStream(archivo)) {
                    props.load(stream);
                }catch (IOException e) {
                    throw new ConfigInvalidaException("El archivo \""+archivo+"\" no existe o no se pudo leer");
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
                        // la condicion es que ninguna de las propiedades está sin declarar, y si hay alguna sin declarar,
                        // lanza un error
                        throw new ConfigInvalidaException("Falta la configuracion de un campo en el archivo de configuracion de la DB \""+archivo+"\"");
                    }
                    // esta porcion de codigo setea las variables estaticas de la clase ConfigDB
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
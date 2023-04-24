import IO.ParseadorArgs;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;

public class TestParseadorArgs {
    // testea si el parseador de argumentos funciona correctamente
    @Test
    public void test1(){
        String str = "\"c:/hola soy juan/datos/config.props\" \"c:/datos/archivos de configuracion/config.props\"";
        String[] args = str.split(" ");
        String[] argsParseados = ParseadorArgs.parsearArgs(args);
        String[] argsValidos = new String[]{"c:/hola soy juan/datos/config.props", "c:/datos/archivos de configuracion/config.props"};
        System.out.println(Arrays.toString(argsParseados));
        System.out.println(Arrays.toString(argsValidos));
        Assert.assertEquals(argsValidos, argsParseados);
    }
    @Test
    public void test2(){
        String str = "\"c:/hola soy juan/datos/config.props\" c:/datos/archivosdeconfiguracion/config.props";
        String[] args = str.split(" ");
        String[] argsParseados = ParseadorArgs.parsearArgs(args);
        String[] argsValidos = new String[]{"c:/hola soy juan/datos/config.props", "c:/datos/archivosdeconfiguracion/config.props"};
        System.out.println(Arrays.toString(argsParseados));
        System.out.println(Arrays.toString(argsValidos));
        Assert.assertEquals(argsValidos, argsParseados);
    }
    @Test
    public void test3(){
        String str = "c:/holasoyjuan/datos/config.props \"c:/datos/archivos de configuracion/config.props\"";
        String[] args = str.split(" ");
        String[] argsParseados = ParseadorArgs.parsearArgs(args);
        String[] argsValidos = new String[]{"c:/holasoyjuan/datos/config.props", "c:/datos/archivos de configuracion/config.props"};
        System.out.println(Arrays.toString(argsParseados));
        System.out.println(Arrays.toString(argsValidos));
        Assert.assertEquals(argsValidos, argsParseados);
    }
    @Test
    public void test4(){
        String str = "c:/hola soy juan/datos/config.props c:/datos/archivos de configuracion/config.props";
        String[] args = str.split(" ");
        String[] argsParseados = ParseadorArgs.parsearArgs(args);
        String[] argsValidos = new String[]{"c:/hola soy juan/datos/config.props", "c:/datos/archivos de configuracion/config.props"};
        System.out.println(Arrays.toString(argsParseados));
        System.out.println(Arrays.toString(argsValidos));
        Assert.assertNotEquals(argsValidos, argsParseados);
    }

}

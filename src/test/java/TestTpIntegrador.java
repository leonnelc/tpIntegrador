import org.junit.jupiter.api.Test;

public class TestTpIntegrador {
    // uno de los tests tiene que estar comentado para que el otro funcione
    /*
    @Test
    public void testSQL(){
        System.out.println("Ejecutando testSQL");
        tpIntegrador.main(new String[]{"src/test/resources/configDBSQL.props","src/test/resources/config1.props"});

    }
     */
    @Test
    public void testCSV(){
        System.out.println("Ejecutando testCSV");
        tpIntegrador.main(new String[]{"C:/Users/Leo/Documents/UtnCurso/integrador/tpIntegrador2/src/test/resources/ConfigDBCSV.props", "C:/Users/Leo/Documents/UtnCurso/integrador/tpIntegrador2/src/test/resources/config1.props"});
    }
}

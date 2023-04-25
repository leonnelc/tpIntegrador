package IO;

public class Fila {
    int fase;
    int ronda;
    String equipo1;
    String equipo2;
    int golesEq1;
    int golesEq2;

    String persona;
    int resultado;
    // uso 2 constructores diferentes, uno para resultados y otro para pronosticos
    Fila(int fase, int ronda, String equipo1, String equipo2, int golesEq1, int golesEq2){
        // al instanciar las variables golesEq1 y golesEq2, se asume que es una Fila de resultados
        this.fase = fase;
        this.ronda = ronda;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;

    }
    Fila(int fase, int ronda, String persona, String equipo1, String equipo2, int resultado){
        // al instanciar las variables persona y resultado, se asume que es una Fila de pronosticos
        this.resultado = resultado;
        this.persona = persona;
        this.fase = fase;
        this.ronda = ronda;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    // getters:
    public int getFase() {
        return fase;
    }
    public int getRonda() {
        return ronda;
    }
    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public int getGolesEq1() {
        return golesEq1;
    }

    public int getGolesEq2() {
        return golesEq2;
    }

    public String getPersona() {
        return persona;
    }

    public int getResultado() {
        return resultado;
    }

}

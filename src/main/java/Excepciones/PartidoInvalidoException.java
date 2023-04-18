package Excepciones;

public class PartidoInvalidoException extends RuntimeException{
    public PartidoInvalidoException(String msg){
        super(msg);
    }
}

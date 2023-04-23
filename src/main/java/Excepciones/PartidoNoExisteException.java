package Excepciones;

public class PartidoNoExisteException extends RuntimeException{
    public PartidoNoExisteException(String msg){
        super(msg);
    }

}

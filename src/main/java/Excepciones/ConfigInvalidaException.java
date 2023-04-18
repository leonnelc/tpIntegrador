package Excepciones;

public class ConfigInvalidaException extends RuntimeException{
    public ConfigInvalidaException(String msg){
        super(msg);
    }
}

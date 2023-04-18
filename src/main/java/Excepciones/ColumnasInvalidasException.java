package Excepciones;

public class ColumnasInvalidasException extends RuntimeException{
    public ColumnasInvalidasException(String msg){
        super(msg);
    }
}

package oop.ex6.variables;

/**
 * This class represents Error of invalid type format
 */
public class InvalidTypeException extends VariableException {
    private static final String INVALID_TYPE_MSG = "Invalid variable type";
    public InvalidTypeException(){
        super(INVALID_TYPE_MSG);
    }
}

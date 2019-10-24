package oop.ex6.methods;

/**
 * This class represents an error of invalid parameters Structure to a method
 */
public class InvalidArgsFormatException extends MethodsException{
    private static final String INVALID_PARAMETERS_MSG = "Invalid method parameters";
    public InvalidArgsFormatException(){
        super(INVALID_PARAMETERS_MSG);
    }
}


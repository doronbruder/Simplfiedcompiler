package oop.ex6.variables;

/**
 * This class is a super class of all Variables Exceptions
 */
public abstract class VariableException extends Exception {
    /**
     * Sends the Error msg to the Exception super class to raise it
     * @param errorType
     */
    VariableException(String errorType){
        super(errorType);
    }
}

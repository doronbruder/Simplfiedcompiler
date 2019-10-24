package oop.ex6.variables;

/**
 * This class represents an error of reassign of final variable
 */
public class FinalReassigmentException extends VariableException {
    private static final String FINAL_CHANGE_MSG = "Cannot reassign final variable";
    public FinalReassigmentException(){
        super(FINAL_CHANGE_MSG);
    }
}

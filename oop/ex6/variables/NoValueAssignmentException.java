package oop.ex6.variables;

/**
 * This class represents Error of try to assign using variable with no value
 */
public class NoValueAssignmentException extends VariableException {
    private static final String NO_VALUE_VARIBLE_MSG ="Cannot assign with variable that was not initialized";
    public NoValueAssignmentException(){
        super(NO_VALUE_VARIBLE_MSG);
    }
}

package oop.ex6.methods;

/**
 * This class represents an error of having to many parameters( bad structure)
 */
public class InvalidArgsAmountException extends MethodsException {
    private static final String INVALID_ARGS_AMOUNT = "Invalid parameters amount";
    public InvalidArgsAmountException(){
        super(INVALID_ARGS_AMOUNT);
    }
}

package oop.ex6.parsing;

/**
 * This class represents an error of return statement in invalid place in code
 */
public class InvalidPlaceForReturnException extends ParsingException {
    private static final String INVALID_RETURN_CALL = "Invalid place from return statement";
    public InvalidPlaceForReturnException(){
        super(INVALID_RETURN_CALL);
    }
}

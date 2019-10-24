package oop.ex6.parsing;

/**
 * This class is a super class of all  possible Parsing Errors
 */
public abstract class ParsingException extends Exception {
    ParsingException(String errorType){
        super(errorType);
    }
}

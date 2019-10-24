package oop.ex6.methods;

/**
 * This class is a super of all possible methods syntax errors
 */
public abstract class MethodsException extends Exception {

    public MethodsException(String errorType){
        super(errorType);
    }

}

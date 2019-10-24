package oop.ex6.scopes;

/**
 * This class is a super of all Exceptions regarding Scopes
 */
public abstract class ScopeException extends Exception {
    ScopeException(String errorType){
        super(errorType);
    }
}

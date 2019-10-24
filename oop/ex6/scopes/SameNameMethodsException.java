package oop.ex6.scopes;

/**
 * This class represents a Syntax error of having two methods with the same name in the same scope
 */
class SameNameMethodsException extends ScopeException {
    private static final String SAME_NAME_METHODS_MSG = "Two methods with the same name in scope";
    SameNameMethodsException(){
        super(SAME_NAME_METHODS_MSG);
    }
}

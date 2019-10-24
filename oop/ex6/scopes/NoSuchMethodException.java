package oop.ex6.scopes;

/**
 * This class represents an error of trying to get a methods that not actually exists within the scope
 */
public class NoSuchMethodException extends ScopeException {
    private static final String NO_SUCH_METHOD_MSG = "There is no method with this name in the Scope";
    public NoSuchMethodException(){
        super(NO_SUCH_METHOD_MSG);
    }
}

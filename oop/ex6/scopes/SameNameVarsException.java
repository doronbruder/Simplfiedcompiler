package oop.ex6.scopes;

/**
 * This class represents a Syntax error of having two variables with the same name in the same scope
 */
class SameNameVarsException extends ScopeException {
    private static final String SAME_NAME_VARS_MSG= "Two variables with the same name in scope";
    SameNameVarsException(){
        super(SAME_NAME_VARS_MSG);
    }
}

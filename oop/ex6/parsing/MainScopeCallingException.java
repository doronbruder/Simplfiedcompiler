package oop.ex6.parsing;

/**
 * This class represents an error of calling a method from the main scope
 */
class MainScopeCallingException extends ParsingException {
    private static final String MAIN_SCOPE_CALLING_EXCEPTION= "Cannot call a method from the main scope";
    MainScopeCallingException(){
        super(MAIN_SCOPE_CALLING_EXCEPTION);
    }
}
